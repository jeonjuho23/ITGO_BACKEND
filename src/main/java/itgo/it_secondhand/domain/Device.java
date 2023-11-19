package itgo.it_secondhand.domain;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Device {

    @Id @GeneratedValue
    @Column(name = "device_id")
    private Long id;

    private String manufacturer;
    private String deviceName;
    private int launchPrice;
    private LocalDateTime releaseDate;

    // 기기의 자세한 제원을 저장한 NoSQL DB의 id값
    @Column(name = "detail_id")
    private Long detailId;


    protected Device(){}

    private Device(String manufacturer, String deviceName, int launchPrice){
        this.manufacturer = manufacturer;
        this.deviceName = deviceName;
        this.launchPrice = launchPrice;
        this.releaseDate = LocalDateTime.now();
    }
    //== 생성 메서드 ==//
    public static Device createDevice(String manufacturer, String deviceName, int launchPrice) {
        return new Device(manufacturer, deviceName, launchPrice);
    }


}
