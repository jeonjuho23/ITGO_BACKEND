package itgo.it_secondhand.service.device;

import itgo.it_secondhand.service.device.DTO.*;

public interface DeviceService {

    public FindDeviceListResDTO findDeviceList(FindDeviceListReqDTO findDeviceListReqDTO);

    public FindDeviceInfoResDTO findDeviceInfo(FindDeviceInfoReqDTO findDeviceInfoReqDTO);
}
