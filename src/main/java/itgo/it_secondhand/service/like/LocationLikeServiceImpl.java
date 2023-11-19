package itgo.it_secondhand.service.like;

import itgo.it_secondhand.service.like.DTO.DeviceLikeListResDTO;
import itgo.it_secondhand.service.like.DTO.LikeReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LocationLikeServiceImpl implements LikeService {

    @Transactional
    @Override
    public Long regist(LikeReqDTO likeReqDTO) {
        return null;
    }

    @Transactional
    @Override
    public void delete(LikeReqDTO likeReqDTO) {
    }

    @Override
    public List<DeviceLikeListResDTO> checkList(Long memberId) {
        return null;
    }
}
