package itgo.it_secondhand.service;

import itgo.it_secondhand.domain.Device;
import itgo.it_secondhand.domain.Member;
import itgo.it_secondhand.domain.SecondhandScrapedPost;
import itgo.it_secondhand.domain.value.Location;
import itgo.it_secondhand.service.post.DTO.PostViewReqDTO;
import itgo.it_secondhand.service.post.DTO.ScrapedPostViewResDTO;
import itgo.it_secondhand.repository.DeviceRepository;
import itgo.it_secondhand.repository.MemberRepository;
import itgo.it_secondhand.repository.PostRepository;
import itgo.it_secondhand.service.post.ScrapingPostService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
@Transactional
class ScrapingPostServiceImplTest {

    @Autowired
    ScrapingPostService scrapingPostService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PostRepository<SecondhandScrapedPost> postRepository;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void viewTest() throws Exception {
        // given
        ViewTestDTO memberAndPost = createMemberAndPost();

        PostViewReqDTO postViewReqDTO = new PostViewReqDTO(memberAndPost.getMemberId(), memberAndPost.postId);

        // when
        ScrapedPostViewResDTO first = scrapingPostService.viewScrapingPost(postViewReqDTO);

        ScrapedPostViewResDTO second = scrapingPostService.viewScrapingPost(postViewReqDTO);

        ScrapedPostViewResDTO third = scrapingPostService.viewScrapingPost(postViewReqDTO);

        // then

        assertEquals(first.getViewCount()+1, second.getViewCount(), "조회수가 증가합니다.");
        assertEquals(second.getViewCount()+1, third.getViewCount(), "조회수가 증가합니다.");

    }


    @Data
    @AllArgsConstructor
    static class ViewTestDTO{
        private Long memberId;
        private Long postId;
    }

    public ViewTestDTO createMemberAndPost(){
//        Location location = new Location("city", "street", "zipcode");
//        Member member = Member.createMember("phone", "name", "address", location);
//        Device device = Device.createDevice("manufacturer", "deviceName", 1000);
//        SecondhandScrapedPost secondhandScrapedPost = SecondhandScrapedPost.builder()
//                        .member(member).postTitle("postTitle").postContent("postContent")
//                        .imgFolderAddress("imgFolderAddress").device(device).secondhandPrice(1000).postUrl("postUrl")
//                        .location(location).build();
//        memberRepository.save(member);
//        deviceRepository.save(device);
//        postRepository.save(secondhandScrapedPost);
//
//        ViewTestDTO viewTestDTO = new ViewTestDTO(member.getId(), secondhandScrapedPost.getId());
//
//        em.flush();
//        em.clear();
//
//        return viewTestDTO;
        return null;
    }
}