package kr.co.study.eatgo.eatgo.application;

import kr.co.study.eatgo.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class RestaurantServiceTest {

    RestaurantService restaurantService;
    @MockBean
    RestaurantRepository restaurantRepository;
    @MockBean
    MenuItemRepository menuItemRepository;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this); // 일일이 생성자를 만들어주지 않아도 된다.

        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1004L,"Bob zip", "Seoul");
        restaurants.add(restaurant);

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("kimchi"));
        given(menuItemRepository.findByRestaurantId(1004L)).willReturn(menuItems);

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant)) ;
        given(restaurantRepository.findAll()).willReturn(restaurants);


//        restaurantRepository =new RestaurantRepositoryImpl();
//        menuItemRepository = new MenuItemRepositoryImpl();
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    @Test
    public void getRestaurant(){
       Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertThat(restaurant.getId(), is(1004L));
        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertThat(menuItem.getName(), is("kimchi"));
    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();


        assertThat(restaurants.get(0).getId(), is(1004L));
    }
    @Test
    public void addRestaurant(){
        Restaurant restaurant = new Restaurant("BeRyong", "Busan" );
        Restaurant saved = new Restaurant(1234L,"BeRyong", "Busan" );

       Restaurant created =  restaurantService.addRestaurant(restaurant);
        given(restaurantRepository.save(any())).willReturn(restaurant);

       assertThat(saved.getId() , is(1234L));
    }
}