package kr.co.study.eatgo.eatgo.interfaces;

import kr.co.study.eatgo.eatgo.application.RestaurantService;
import kr.co.study.eatgo.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants= restaurantService.getRestaurants();

         return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
        Restaurant restaurant = restaurantService.getRestaurant(id);

//        List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(id);
//        restaurant.setMenuItems(menuItems);
        return restaurant;
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create( @RequestBody  Restaurant resource) throws URISyntaxException {

        String name = resource.getName();
        String information = resource.getInformation();
        Restaurant restaurant = new Restaurant(name, information );
        restaurant = restaurantService.addRestaurant(restaurant);

        URI location = new URI("/restaurants/"+restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }

}
