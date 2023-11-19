package itgo.it_secondhand.repository;

import itgo.it_secondhand.domain.Member;
import itgo.it_secondhand.domain.MemberLikePost;
import itgo.it_secondhand.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberLikePostRepository extends JpaRepository<MemberLikePost, Long> {
    MemberLikePost findByMemberAndPost(Member member, Post post);

}
