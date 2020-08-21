package kr.co.study.eatgo.eatgo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Restaurant {

    private final Long id;
    private final String name;
    private final String information;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Restaurant(Long id, String name, String information){
        this.id= id;
        this.name=name;
        this.information = information;
    }

    public List<MenuItem> getMenuItems(){
        return this.menuItems;
    }


    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
