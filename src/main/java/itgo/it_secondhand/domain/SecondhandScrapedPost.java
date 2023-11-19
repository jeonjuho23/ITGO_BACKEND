package itgo.it_secondhand.domain;


import itgo.it_secondhand.domain.value.Location;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DiscriminatorValue("secondhand_scraped")
public class SecondhandScrapedPost extends Post{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private Device device;

    private int secondhandPrice;
    private String postUrl;

    @Embedded
    private Location location;


    private SecondhandScrapedPost(Member member, String postTitle, String postContent, String imgFolderAddress, Device device, int secondhandPrice, String postUrl){
        super(member, postTitle, postContent, LocalDateTime.now(), LocalDateTime.now(), 0, 0, imgFolderAddress);
        this.device = device;
        this.secondhandPrice = secondhandPrice;
        this.postUrl = postUrl;
        this.location = member.getLocation();
    }

    public static SecondhandScrapedPost createPost(Member member, String postTitle, String postContent, String imgFolderAddress, Device device, int secondhandPrice, String postUrl){
        return new SecondhandScrapedPost(member, postTitle, postContent, imgFolderAddress, device, secondhandPrice, postUrl);
    }


}
