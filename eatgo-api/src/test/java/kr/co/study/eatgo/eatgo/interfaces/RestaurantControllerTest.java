package kr.co.study.eatgo.eatgo.interfaces;

import kr.co.study.eatgo.eatgo.application.RestaurantService;
import kr.co.study.eatgo.eatgo.domain.MenuItem;
import kr.co.study.eatgo.eatgo.domain.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

//    @SpyBean(RestaurantRepositoryImpl.class)
//    RestaurantRepository repository;
//
//    @SpyBean(MenuItemRepositoryImpl.class)
//    MenuItemRepository menuItemRepository;

//    @SpyBean(RestaurantService.class)
//    @InjectMocks // 기존의 만들어진 서비스를 Autowiered처럼 가져온다.
    @MockBean //mockBean은 아무것도 가지지 않는 Service를 만들어준다. 내부의 구현은 다시 만들어야한다.
    RestaurantService restaurantService;

    @Test
    public void list() throws Exception{
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
               .andExpect(status().isOk())
        .andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
        .andExpect(content().string(containsString("\"id\":1004")));
    }

    @Test
    public  void detail() throws Exception{
       Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
       Restaurant restaurant2 = new Restaurant(2020L, "Cyber food", "Seoul");
       restaurant.addMenuItem(new MenuItem("kimchi"));
        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
        .andExpect(content()
                .string(containsString("\"id\":1004")))
        .andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
        .andExpect(content().string(containsString("kimchi")));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"id\":2020")))
                .andExpect(content().string(containsString("\"name\":\"Cyber food\"")));

    }
}