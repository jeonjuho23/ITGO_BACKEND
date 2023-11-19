package itgo.it_secondhand.api;

import itgo.it_secondhand.api.DTO.like.*;
import itgo.it_secondhand.service.like.DTO.DeviceLikeListResDTO;
import itgo.it_secondhand.service.like.DTO.LikeReqDTO;
import itgo.it_secondhand.service.like.LikeService;
import itgo.it_secondhand.service.post.DTO.PostResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeRestController {

    private final LikeService<DeviceLikeListResDTO> deviceLikeService;
    private final LikeService<PostResDTO> postLikeService;
//    private final LikeService<?> locationLikeService;


    //==  Regist  ==//

    @GetMapping("/regist/device")
    public ResponseEntity<RegistLikeResponseDTO> registDevice(@RequestParam Long memberId, @RequestParam Long deviceId){

        LikeReqDTO reqDTO = LikeReqDTO.builder()
                .memberId(memberId)
                .likedThingId(deviceId)
                .build();
        Long registId = deviceLikeService.regist(reqDTO);

        RegistLikeResponseDTO responseDTO = RegistLikeResponseDTO.builder()
                .registId(registId)
                .build();

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/regist/post")
    public ResponseEntity<RegistLikeResponseDTO> registPost(@RequestParam Long memberId, @RequestParam Long postId){

        LikeReqDTO reqDTO = LikeReqDTO.builder()
                .memberId(memberId)
                .likedThingId(postId)
                .build();
        Long registId = postLikeService.regist(reqDTO);

        RegistLikeResponseDTO responseDTO = RegistLikeResponseDTO.builder()
                .registId(registId)
                .build();

        return ResponseEntity.ok(responseDTO);
    }

//    @GetMapping("/regist/location")
//    public ResponseEntity<RegistLikeResponseDTO> registLocation(Long memberId, Long locationId){
//
//        LikeReqDTO reqDTO = LikeReqDTO.builder().memberId(memberId).likedThingId(locationId).build();
//        Long registId = locationLikeService.regist(reqDTO);
//
//        RegistLikeResponseDTO responseDTO = RegistLikeResponseDTO.builder().registId(registId).build();
//
//        return ResponseEntity.ok(responseDTO);
//    }


    //==  Delete  ==//

    @PostMapping("/delete/device")
    public ResponseEntity<DeleteLikeResponseDTO> deleteDevice(@RequestParam Long memberId, @RequestParam Long deviceId){

        LikeReqDTO reqDTO = LikeReqDTO.builder()
                .memberId(memberId)
                .likedThingId(deviceId)
                .build();
        deviceLikeService.delete(reqDTO);

        DeleteLikeResponseDTO responseDTO = DeleteLikeResponseDTO.builder()
                .msg("성공적으로 삭제되었습니다.")
                .build();

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/delete/post")
    public ResponseEntity<DeleteLikeResponseDTO> deletePost(@RequestParam Long memberId, @RequestParam Long postId){

        LikeReqDTO reqDTO = LikeReqDTO.builder()
                .memberId(memberId)
                .likedThingId(postId)
                .build();
        postLikeService.delete(reqDTO);

        DeleteLikeResponseDTO responseDTO = DeleteLikeResponseDTO.builder()
                .msg("성공적으로 삭제되었습니다.")
                .build();

        return ResponseEntity.ok(responseDTO);
    }

//    @PostMapping("/delete/location")
//    public ResponseEntity<DeleteLikeResponseDTO> deleteLocation(Long memberId, Long locationId){
//
//        LikeReqDTO reqDTO = LikeReqDTO.builder().memberId(memberId).likedThingId(locationId).build();
//        locationLikeService.delete(reqDTO);
//
//        DeleteLikeResponseDTO responseDTO = DeleteLikeResponseDTO.builder().msg("성공적으로 삭제되었습니다.").build();
//
//        return ResponseEntity.ok(responseDTO);
//    }


    //==  Find list  ==//

    @GetMapping("/find/device/list")
    public ResponseEntity<List<PostResDTO>> findDeviceList(@RequestParam Long memberId){

        List<PostResDTO> responseDTO = postLikeService.checkList(memberId);

        return ResponseEntity.ok(responseDTO);
    }

//    @GetMapping("/find/post/list")
//    public ResponseEntity<FindLikeListResponseDTO> findPostList(){}

//    @GetMapping("/find/location/list")
//    public ResponseEntity<List<>> findLocationList(Long memberId){
//
//        List<LocationResDTO> responseDTO = locationLikeService.checkList(memberId);
//
//        return ResponseEntity.ok(responseDTO);
//    }

}
