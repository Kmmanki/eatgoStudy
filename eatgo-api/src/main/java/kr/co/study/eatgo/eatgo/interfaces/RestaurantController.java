package kr.co.study.eatgo.eatgo.interfaces;

import domain.Restaurant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        List<Restaurant> list = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1004L,"Bob zip", "seoul");
        list.add(restaurant);

        return list;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
        List<Restaurant> restaurants = new ArrayList<>();

        Restaurant restaurant = null;

        restaurants.add(new Restaurant(1004L, "Bob zip", "seoul"));
        restaurants.add(new Restaurant(2020L, "Cyber food", "seoul"));

        restaurant = restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .get();
        return restaurant;
    }

}
