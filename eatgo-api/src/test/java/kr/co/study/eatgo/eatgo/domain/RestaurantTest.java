package kr.co.study.eatgo.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


class RestaurantTest {

    @Test
    public void creation(){
        Restaurant restaurant = new Restaurant(1004L,"Bob zip","");
        assertThat(restaurant.getId(), is(1004L));
        assertThat(restaurant.getName(), is("Bob zip"));
    }

    @Test
    public void information(){
        Restaurant restaurant = new Restaurant(1004L,"Bob zip", "seoul");
        assertThat(restaurant.getId(), is(1004L));
        assertThat(restaurant.getInformation(), is("seoul"));
    }

}