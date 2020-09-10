package kr.co.study.eatgo.eatgo.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long restaurantId;
    private String name ;
    public MenuItem(String menuItem) {
        this.name=  menuItem;
    }

    public String getName() {
        return name;
    }
}
