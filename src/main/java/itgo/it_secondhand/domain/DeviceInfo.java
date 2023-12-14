package itgo.it_secondhand.domain;

import itgo.it_secondhand.domain.DeviceInfoSchema.*;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "device")
@Builder
@Data
public class DeviceInfo {

    @Id
    private String id;
    private String modelname;
    private Basic basic;
    private Screen screen;
    private ChipSet chipSet;
    private Camera camera;
    private FrontCamera front_camera;
    private Battery battery;
    private String biometric_recognition;
    private String image;
}
