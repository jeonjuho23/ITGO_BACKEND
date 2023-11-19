package itgo.it_secondhand.service.like;


import itgo.it_secondhand.domain.Device;
import itgo.it_secondhand.domain.Member;
import itgo.it_secondhand.domain.MemberLikeDevice;
import itgo.it_secondhand.service.like.DTO.DeviceLikeListResDTO;
import itgo.it_secondhand.service.like.DTO.LikeReqDTO;
import itgo.it_secondhand.repository.DeviceRepository;
import itgo.it_secondhand.repository.MemberLikeDeviceRepository;
import itgo.it_secondhand.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeviceLikeServiceImpl implements LikeService<DeviceLikeListResDTO> {

    private final DeviceRepository deviceRepository;
    private final MemberRepository memberRepository;
    //
    private final MemberLikeDeviceRepository memberLikeDeviceRepository;

    @Transactional
    @Override
    public Long regist(LikeReqDTO likeReqDTO) {

        // 엔티티 조회
        // 여기서는 Optional의 null값 에외처리가 필요 없을까?
        Member member = memberRepository.findById(likeReqDTO.getMemberId()).get();
        Device device = deviceRepository.findById(likeReqDTO.getLikedThingId()).get();

        // 좋아요 생성
        MemberLikeDevice memberLikeDevice = MemberLikeDevice.createMemberLikeDevice(member, device);

        // 저장
        memberLikeDeviceRepository.save(memberLikeDevice);

        return memberLikeDevice.getId();
    }

    @Transactional
    @Override
    public void  delete(LikeReqDTO likeReqDTO) {
        // 엔티티 조회
        Member member;
        Device device;
        try {
            member = memberRepository.findById(likeReqDTO.getMemberId())
                    .orElseThrow(() -> new IllegalArgumentException("no such data"));
            device = deviceRepository.findById(likeReqDTO.getLikedThingId())
                    .orElseThrow(() -> new IllegalArgumentException("no such data"));
        } catch (IllegalArgumentException e){
            return;
        }

        // 좋아요 삭제 후 저장
        MemberLikeDevice memberLikeDevice = memberLikeDeviceRepository.findByMemberAndDevice(member, device);

        memberLikeDeviceRepository.delete(memberLikeDevice);

    }

    @Override
    public List<DeviceLikeListResDTO> checkList(Long memberId) {

        List<MemberLikeDevice> memberLikeDeviceList = memberLikeDeviceRepository.findAllByMember_Id(memberId);

        List<DeviceLikeListResDTO> deviceLikeListResDTOList = new ArrayList<>();
        for(MemberLikeDevice memberLikeDevice : memberLikeDeviceList){
            deviceLikeListResDTOList.add(new DeviceLikeListResDTO(memberLikeDevice.getDevice().getId(), memberLikeDevice.getDevice().getDeviceName()));
        }

        return deviceLikeListResDTOList;
    }
}
