package kr.co.study.eatgo.eatgo.interfaces;

import kr.co.study.eatgo.eatgo.application.RestaurantService;
import kr.co.study.eatgo.eatgo.domain.Restaurant;
import kr.co.study.eatgo.eatgo.domain.RestaurantNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    public  void detailWithExist() throws Exception{
       Restaurant restaurant = Restaurant.builder()
               .information("seoul")
               .name("Bob zip")
               .id(1004L)
               .build();
       Restaurant restaurant2 =
               Restaurant.builder()
                       .information("seoul")
                       .name("Cyber food")
                       .id(2020L)
                       .build();

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

    @Test
    public  void detailNotWithExist() throws Exception{
        given(restaurantService.getRestaurant(404L))
                .willThrow(new RestaurantNotFoundException(404L));
        mvc.perform(get("/restaurants/404"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("{}"));
    }

    @Test
    public void createWithValData() throws  Exception{
        given(restaurantService.addRestaurant(any())).will(invocation ->{
            Restaurant restaurant = invocation.getArgument(0);
            return Restaurant.builder()
                    .id(1234L)
                    .name("BeRou")
                    .information("Busan")
                    .build();
        });

        mvc.perform(post("/restaurants")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"BeRou\", \"information\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurants/1234"))
                .andExpect(content().string("{}"));

//        verify(restaurantService).addRestaurant(any());


    }

    @Test
    public void createWithInvalidData() throws  Exception{


        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\", \"information\":\"\"}"))
                .andExpect(status().isBadRequest());

//        verify(restaurantService).addRestaurant(any());


    }
}