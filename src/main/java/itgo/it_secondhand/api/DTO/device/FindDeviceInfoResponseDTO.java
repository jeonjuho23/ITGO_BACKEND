package itgo.it_secondhand.api.DTO.device;

import itgo.it_secondhand.domain.DeviceInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindDeviceInfoResponseDTO {
    private DeviceInfo deviceInfo;
}
