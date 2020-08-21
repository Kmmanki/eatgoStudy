package kr.co.study.eatgo.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {
    List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantRepositoryImpl(){
        restaurants.add(new Restaurant(1004L, "Bob zip", "seoul"));
        restaurants.add(new Restaurant(2020L, "Cyber food", "seoul"));
    }

    @Override
    public List<Restaurant> findAll(){
        return restaurants;
    }

    @Override
    public Restaurant findById(Long id){
        Restaurant restaurant = restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .get();
        return restaurant;
    }
}
