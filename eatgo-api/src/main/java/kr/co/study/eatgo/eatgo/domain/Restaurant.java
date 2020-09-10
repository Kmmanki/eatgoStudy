package kr.co.study.eatgo.eatgo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public  class Restaurant {

    private  Long id;
    private  String name;
    private  String information;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(String name, String information) {
        this.name = name;
        this.information = information;
    }

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

    public void setId(long l) {
        this.id = l;
    }
}
