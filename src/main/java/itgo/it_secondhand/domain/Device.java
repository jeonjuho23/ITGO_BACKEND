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
    private String detailId;

    // 카테고리
    private int category;


    protected Device(){}

    private Device(String manufacturer, String deviceName, int launchPrice, int category, LocalDateTime releaseDate){
        this.manufacturer = manufacturer;
        this.deviceName = deviceName;
        this.launchPrice = launchPrice;
        this.category = category;
        this.releaseDate = releaseDate;
    }
    //== 생성 메서드 ==//
    public static Device createDevice(String manufacturer, String deviceName, int launchPrice, int category, LocalDateTime releaseDate) {
        return new Device(manufacturer, deviceName, launchPrice, category, releaseDate);
    }


}
