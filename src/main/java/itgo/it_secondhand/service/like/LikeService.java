package itgo.it_secondhand.service.like;


import itgo.it_secondhand.service.like.DTO.LikeReqDTO;

import java.util.List;

public interface LikeService<T> {

    public Long regist(LikeReqDTO likeReqDTO);

    public void delete(LikeReqDTO likeReqDTO);

    public List<T> checkList(Long memberId);
}
