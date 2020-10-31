package kr.co.study.eatgo.eatgo.application;

import kr.co.study.eatgo.eatgo.domain.MenuItem;
import kr.co.study.eatgo.eatgo.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public void bulkUpdate(Long restaurnatId, List<MenuItem> menuItems){
        for(MenuItem m : menuItems){
            update(restaurnatId, m);


        }
        //TODO: Update
    }

    private void update(Long restaurnatId, MenuItem m) {
        if(m.getDestroy() == true){
            menuItemRepository.deleteById(m.getId());
        }
        m.setRestaurantId(restaurnatId);
        menuItemRepository.save(m);
    }
}
