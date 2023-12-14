package itgo.it_secondhand.domain.DeviceInfoSchema;

import lombok.Data;

@Data
public class Basic {
    private String name;
    private String manufacturer;
    private String releaseDate;
    private String price;
    private String os;
    private String size;
    private String weight;
}
