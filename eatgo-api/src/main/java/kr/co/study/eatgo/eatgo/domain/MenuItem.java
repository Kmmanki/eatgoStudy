package kr.co.study.eatgo.eatgo.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class MenuItem {
    @Id
    private Long restaurantId;
    private String name ;
    public MenuItem(String menuItem) {
        this.name=  menuItem;
    }

    public String getName() {
        return name;
    }
}
