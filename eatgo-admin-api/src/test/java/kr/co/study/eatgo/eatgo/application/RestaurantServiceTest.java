package kr.co.study.eatgo.eatgo.application;

import kr.co.study.eatgo.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
class RestaurantServiceTest {

    RestaurantService restaurantService;
    @MockBean
    RestaurantRepository restaurantRepository;
    @MockBean
    MenuItemRepository menuItemRepository;
    @MockBean
    private ReviewRepository reviewRepository;

    @BeforeEach()
    public void setUp(){
        MockitoAnnotations.initMocks(this); // 일일이 생성자를 만들어주지 않아도 된다.

        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1004L,"Bob zip", "Seoul");
        restaurants.add(restaurant);
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant)) ;
        given(restaurantRepository.findAll()).willReturn(restaurants);

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("kimchi"));
        given(menuItemRepository.findByRestaurantId(1004L)).willReturn(menuItems);

        List<Review> reviews = new ArrayList();
        reviews.add(Review.builder()
                .name("Joker")
                .score(5L)
                .description("good")
                .build());
        given(reviewRepository.findAllByRestaurantId(1004L)).willReturn(reviews);



//        restaurantRepository =new RestaurantRepositoryImpl();
//        menuItemRepository = new MenuItemRepositoryImpl();
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository, reviewRepository);
    }

    @Test
    public void getWithExistedRestaurant(){
       Restaurant restaurant = restaurantService.getRestaurant(1004L);
        verify(menuItemRepository).findByRestaurantId(eq(1004L));
        verify(reviewRepository).findAllByRestaurantId(eq(1004L));
        
        assertThat(restaurant.getId(), is(1004L));
        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertThat(menuItem.getName(), is("kimchi"));
        Review review = restaurant.getReviews().get(0);
        assertThat(review.getName(), is("Joker"));

    }

    @Test
    public void getNotExistedRestaurant(){
        try {
            Restaurant restaurant = restaurantService.getRestaurant(404L);

        }catch (RestaurantNotFoundException e){
            assertThat(e.getMessage(), is("Could Not Find restaurant404"));
        }

    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        assertThat(restaurants.get(0).getId(), is(1004L));

    }

    @Test
    public void addRestaurant(){
        given(restaurantRepository.save(any())).will(invocation ->{
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });



        Restaurant restaurant = Restaurant.builder()
                .name("BeRyong")
                .information("Busan")
                .build();

        Restaurant saved = Restaurant.builder()
                .id(1234L)
                .name("BeRyong")
                .information("Busan")
                .build();

       Restaurant created =  restaurantService.addRestaurant(restaurant);

       assertThat(saved.getId() , is(1234L));
    }
}