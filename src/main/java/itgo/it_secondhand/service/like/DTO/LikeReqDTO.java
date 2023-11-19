package itgo.it_secondhand.service.like.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LikeReqDTO {
    private Long memberId;
    private Long likedThingId;
}
