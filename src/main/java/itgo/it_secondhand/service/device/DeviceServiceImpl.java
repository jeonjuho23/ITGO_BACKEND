package itgo.it_secondhand.service.device;

import itgo.it_secondhand.domain.Device;
import itgo.it_secondhand.domain.LaptopInfo;
import itgo.it_secondhand.domain.MobileInfo;
import itgo.it_secondhand.repository.LaptopInfoRepository;
import itgo.it_secondhand.repository.DeviceRepository;
import itgo.it_secondhand.repository.MobileInfoRepository;
import itgo.it_secondhand.service.device.DTO.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService{

    private final DeviceRepository deviceRepository;
    private final MobileInfoRepository mobileInfoRepository;
    private final LaptopInfoRepository laptopInfoRepository;


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
    public FindDeviceListResDTO findDeviceListByCategory(FindDeviceListByCategoryReqDTO findDeviceListByCategoryReqDTO) {

        Pageable pageable = PageRequest.of(findDeviceListByCategoryReqDTO.getPage(), findDeviceListByCategoryReqDTO.getSize());
        Slice<Device> deviceSlice = deviceRepository.findSliceByCategory_Id(pageable, findDeviceListByCategoryReqDTO.getCategory());

        List<FindDeviceDTO> findDeviceList = new ArrayList<>();
        for (Device device: deviceSlice.getContent()){
            findDeviceList.add(new FindDeviceDTO(device));
        }

        return FindDeviceListResDTO.builder()
                .deviceList(findDeviceList)
                .hasNext(deviceSlice.hasNext()).build();    }

    @Override
    public FindDeviceInfoResDTO<MobileInfo> findMobileInfo(FindDeviceInfoReqDTO findDeviceInfoReqDTO) {

        MobileInfo mobileInfo = mobileInfoRepository.findById(findDeviceInfoReqDTO.getDetailId()).orElseThrow();

        return FindDeviceInfoResDTO.<MobileInfo>builder()
                .info(mobileInfo).build();
    }

    @Override
    public FindDeviceInfoResDTO<LaptopInfo> findLaptopInfo(FindDeviceInfoReqDTO findDeviceInfoReqDTO) {

        LaptopInfo laptopInfo = laptopInfoRepository.findById(findDeviceInfoReqDTO.getDetailId()).orElseThrow();

        return FindDeviceInfoResDTO.<LaptopInfo>builder()
                .info(laptopInfo).build();
    }


}
