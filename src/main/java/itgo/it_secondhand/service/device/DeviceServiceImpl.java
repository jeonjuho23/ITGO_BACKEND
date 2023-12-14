package itgo.it_secondhand.service.device;

import itgo.it_secondhand.domain.Device;
import itgo.it_secondhand.domain.DeviceInfo;
import itgo.it_secondhand.repository.DeviceInfoRepository;
import itgo.it_secondhand.repository.DeviceRepository;
import itgo.it_secondhand.service.device.DTO.*;
import itgo.it_secondhand.service.post.DTO.FindPostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService{

    private final DeviceRepository deviceRepository;
    private final DeviceInfoRepository deviceInfoRepository;

    @Override
    public FindDeviceListResDTO findDeviceList(FindDeviceListReqDTO findDeviceListReqDTO) {

        Pageable pageable = PageRequest.of(findDeviceListReqDTO.getPage(), findDeviceListReqDTO.getSize());
        Slice<Device> deviceSlice = deviceRepository.findSliceBy(pageable);

        List<FindDeviceDTO> findDeviceList = new ArrayList<>();
        for (Device device: deviceSlice.getContent()){
            findDeviceList.add(new FindDeviceDTO(device));
        }

        return FindDeviceListResDTO.builder()
                .deviceList(findDeviceList)
                .hasNext(deviceSlice.hasNext()).build();
    }

    @Override
    public FindDeviceInfoResDTO findDeviceInfo(FindDeviceInfoReqDTO findDeviceInfoReqDTO) {

        DeviceInfo deviceInfo = deviceInfoRepository.findById(findDeviceInfoReqDTO.getDetailId()).orElseThrow();

        return FindDeviceInfoResDTO.builder()
                .deviceInfo(deviceInfo).build();
    }


}
