package itgo.it_secondhand.service.post;


import itgo.it_secondhand.domain.*;
import itgo.it_secondhand.repository.*;
import itgo.it_secondhand.service.post.DTO.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScrapingPostServiceImpl implements ScrapingPostService {

    private final MemberRepository memberRepository;
    private final SecondhandPostRepository secondhandPostRepository;
    private final MemberViewPostRepository memberViewPostRepository;
    private final MemberLikePostRepository memberLikePostRepository;

    @Transactional
    @Override
    public ScrapedPostViewResDTO viewScrapingPost(PostViewReqDTO postViewReqDTO) {
        // 엔티티 조회
        Member member = memberRepository.findById(postViewReqDTO.getMemberId()).get();
        SecondhandScrapedPost secondhandScrapedPost = secondhandPostRepository.findById(postViewReqDTO.getPostId()).get();


        // 첫 조회면 데이터 생성
        MemberViewPost view = memberViewPostRepository.findTopByMemberAndPostOrderByViewDateDesc(member, secondhandScrapedPost);
        if (view != null){
            view.increaseViewCount();
        }else{
            view = MemberViewPost.createMemberViewPost(member, secondhandScrapedPost);
            memberViewPostRepository.save(view);
        }

        // 즐겨찾기된 게시글인지 확인
        Boolean isLike = Boolean.TRUE;
        if(memberLikePostRepository.findByMemberAndPost(member, secondhandScrapedPost) == null){
            isLike = Boolean.FALSE;
        }


        return new ScrapedPostViewResDTO(member, secondhandScrapedPost, secondhandScrapedPost.getDevice(), isLike);
    }

    @Override
    public FindPostResDTO findALlScrapingPostList(FindPostReqDTO findPostReqDTO) {

        // pageable 구현체 생성
        Pageable pageable = PageRequest.of(findPostReqDTO.getPage(), findPostReqDTO.getSize(), Sort.by(findPostReqDTO.getSortBy().label()));

        // 조회
        Slice<SecondhandScrapedPost> posts = secondhandPostRepository.findSliceBy(pageable);

        // res 변환
        Member member = memberRepository.findById(findPostReqDTO.getMemberId()).get();
//        Member platform = memberRepository.findBy
        return setFindPostDTO(member, posts);
    }

    @Override
    public FindPostResDTO findLikeScrapingPostList(FindPostReqDTO findPostReqDTO) {
        Member member = memberRepository.findById(findPostReqDTO.getMemberId()).get();

        // pageable 구현체 생성
        Pageable pageable = PageRequest.of(findPostReqDTO.getPage(), findPostReqDTO.getSize(), Sort.by(findPostReqDTO.getSortBy().label()));

        // 조회
        Slice<SecondhandScrapedPost> likePosts = secondhandPostRepository.findLikePostByMember_Id(findPostReqDTO.getMemberId(), pageable);

        // res 변환


        return setFindPostDTO(member, likePosts);
    }


    // 추후에 꼭 리팩터링!!!
    public FindPostResDTO setFindPostDTO(Member member, Slice<SecondhandScrapedPost> posts){
        List<FindPostDTO> findPostDTOList = new ArrayList<>();

        // isLike, isView 확인 및 DTO 변환
        for(SecondhandScrapedPost post : posts){
            // is like?
            Boolean isLike = Boolean.TRUE;
            MemberLikePost likeMemberAndPost = memberLikePostRepository.findByMemberAndPost(member, post);
            if(likeMemberAndPost == null) isLike = Boolean.FALSE;
            // is view?
            Boolean isView = Boolean.TRUE;
            MemberViewPost viewMemberAndPost = memberViewPostRepository.findTopByMemberAndPostOrderByViewDateDesc(member, post);
            if(viewMemberAndPost == null) isView = Boolean.FALSE;

            findPostDTOList.add(new FindPostDTO(post.getMember(), post, isView, isLike));
        }

        return new FindPostResDTO(findPostDTOList, posts.hasNext());
    }


}
