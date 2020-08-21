package kr.co.study.eatgo.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuItemRepositoryImpl implements MenuItemRepository {
    List<MenuItem> menuItems = new ArrayList<>();

    public MenuItemRepositoryImpl(){
        menuItems.add(new MenuItem("kimchi"));
    }

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }

    @Override
    public List<MenuItem> findByRestaurantId(Long id) {
        return menuItems;
    }
}
