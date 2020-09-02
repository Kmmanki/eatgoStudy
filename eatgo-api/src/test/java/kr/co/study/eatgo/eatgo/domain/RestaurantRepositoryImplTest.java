package kr.co.study.eatgo.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class RestaurantRepositoryImplTest {

    @Test
    public void save(){
        RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();
        int oldCount = restaurantRepository.findAll().size();

        Restaurant reastaurnat = new Restaurant("BeRyong", "Busan");
        restaurantRepository.save(reastaurnat);

        assertThat(reastaurnat.getId() , is(1234L));

        int newCount =  restaurantRepository.findAll().size();

        assertThat(newCount - oldCount, is(1));
    }

}