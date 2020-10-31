package kr.co.study.eatgo.eatgo.application;

import kr.co.study.eatgo.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository, ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }

    public  Restaurant getRestaurant(Long id){
        Restaurant restaurant =  restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(id);
        List<Review> reviews = reviewRepository.findAllByRestaurantId(id);

        restaurant.setReviews(reviews);
        restaurant.setMenuItems(menuItems);
        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        Restaurant saved = restaurantRepository.save(restaurant);

        return saved;
    }
}
