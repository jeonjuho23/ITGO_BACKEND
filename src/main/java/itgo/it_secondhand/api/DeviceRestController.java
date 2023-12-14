package itgo.it_secondhand.api;

import itgo.it_secondhand.api.DTO.device.FindDeviceInfoResponseDTO;
import itgo.it_secondhand.api.DTO.device.FindDeviceListResponseDTO;
import itgo.it_secondhand.enum_.SortBy;
import itgo.it_secondhand.service.device.DTO.FindDeviceInfoReqDTO;
import itgo.it_secondhand.service.device.DTO.FindDeviceInfoResDTO;
import itgo.it_secondhand.service.device.DTO.FindDeviceListReqDTO;
import itgo.it_secondhand.service.device.DTO.FindDeviceListResDTO;
import itgo.it_secondhand.service.device.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/device")
@RequiredArgsConstructor
public class DeviceRestController {
    private final DeviceService deviceService;


    @GetMapping("/find/list")
    public ResponseEntity<FindDeviceListResponseDTO> findDeviceList(@RequestParam int page, @RequestParam int size, @RequestParam SortBy sortBy){

        FindDeviceListResDTO deviceList = deviceService.findDeviceList(
                FindDeviceListReqDTO.builder()
                        .page(page).size(size).build());


        return ResponseEntity.ok(
                FindDeviceListResponseDTO.builder()
                        .deviceList(deviceList.getDeviceList())
                        .build());
    }

    @GetMapping("/find/info")
    public ResponseEntity<FindDeviceInfoResponseDTO> findDeviceInfo(@RequestParam String detailId){

        FindDeviceInfoResDTO deviceInfo = deviceService.findDeviceInfo(
                FindDeviceInfoReqDTO.builder()
                        .detailId(detailId).build());

        return ResponseEntity.ok(FindDeviceInfoResponseDTO.builder()
                        .deviceInfo(deviceInfo.getDeviceInfo())
                        .build());
    }
}
