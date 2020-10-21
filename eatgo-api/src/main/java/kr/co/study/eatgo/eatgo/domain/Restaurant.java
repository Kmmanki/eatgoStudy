package kr.co.study.eatgo.eatgo.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotEmpty
    private  String name;
    @NotEmpty
    private  String information;

    @Transient
    private List<MenuItem> menuItems ;

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
        this.menuItems = new ArrayList<>();

    }

    public void setId(long l) {
        this.id = l;
    }
}
