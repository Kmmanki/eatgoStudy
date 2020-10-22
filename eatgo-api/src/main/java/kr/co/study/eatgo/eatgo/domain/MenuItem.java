package kr.co.study.eatgo.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long restaurantId;
    private String name ;
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean destroy;

    public MenuItem(String menuItem) {
        this.name=  menuItem;
    }

    public String getName() {
        return name;
    }
}
