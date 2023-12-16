package itgo.it_secondhand.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class CategoryBySite {

    @Id @GeneratedValue
    @Column(name = "category_id_by_site")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String siteName;
    private String categoryBySite;


}
