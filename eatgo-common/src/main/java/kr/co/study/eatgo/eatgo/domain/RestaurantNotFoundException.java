package kr.co.study.eatgo.eatgo.domain;

public class RestaurantNotFoundException extends RuntimeException{

    public RestaurantNotFoundException(Long id){
        super("Could Not Find restaurant"+id);
    }

}
