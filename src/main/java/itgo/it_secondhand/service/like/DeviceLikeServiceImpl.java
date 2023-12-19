package itgo.it_secondhand.service.like;


import itgo.it_secondhand.domain.Device;
import itgo.it_secondhand.domain.Member;
import itgo.it_secondhand.domain.MemberLikeDevice;
import itgo.it_secondhand.service.like.DTO.DeviceLikeResDTO;
import itgo.it_secondhand.service.like.DTO.DeviceResDTO;
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
public class DeviceLikeServiceImpl implements LikeService<DeviceLikeResDTO, Long> {

    private final DeviceRepository deviceRepository;
    private final MemberRepository memberRepository;
    //
    private final MemberLikeDeviceRepository memberLikeDeviceRepository;

    @Transactional
    @Override
    public Long regist(LikeReqDTO<Long> likeReqDTO) {

        // 엔티티 조회
        Member member = memberRepository.findById(likeReqDTO.getMemberId()).orElseThrow();
        Device device = deviceRepository.findById(likeReqDTO.getLikedThingId()).orElseThrow();

        // 좋아요 생성
        MemberLikeDevice memberLikeDevice = MemberLikeDevice.createMemberLikeDevice(member, device);

        // 저장
        memberLikeDeviceRepository.save(memberLikeDevice);

        return memberLikeDevice.getId();
    }

    @Transactional
    @Override
    public void  delete(LikeReqDTO<Long> likeReqDTO) {
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
    public List<DeviceLikeResDTO> checkList(Long memberId) {

        List<MemberLikeDevice> memberLikeDeviceList = memberLikeDeviceRepository.findAllByMember_Id(memberId);

        List<DeviceLikeResDTO> deviceLikeListResDTO = new ArrayList<>();
        for(MemberLikeDevice memberLikeDevice : memberLikeDeviceList){
            deviceLikeListResDTO.add(new DeviceLikeResDTO(memberLikeDevice.getDevice().getId(), memberLikeDevice.getDevice().getDeviceName()));
        }

        return deviceLikeListResDTO;
    }

    public List<DeviceResDTO> findByKeyword(String keyword){

        List<Device> deviceList = deviceRepository.searchDeviceByDeviceName(keyword.replace(" ", ""));

        List<DeviceResDTO> res = new ArrayList<>();
        for(Device device: deviceList){
            res.add(DeviceResDTO.builder()
                    .id(device.getId())
                    .deviceName(device.getDeviceName()).build());
        }

        return res;
    }
}
