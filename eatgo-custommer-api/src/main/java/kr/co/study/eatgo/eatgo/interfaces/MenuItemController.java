package kr.co.study.eatgo.eatgo.interfaces;

import kr.co.study.eatgo.eatgo.application.MenuItemService;
import kr.co.study.eatgo.eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;


    @PatchMapping("/restaurants/{restaurnatId}/menuitems")
    public String  bulkUpdate(
            @RequestBody List<MenuItem> menuItems,
            @PathVariable("restaurnatId") Long restaurnatId
            ){
        menuItemService.bulkUpdate(restaurnatId,menuItems);

        return "";
    }

}


