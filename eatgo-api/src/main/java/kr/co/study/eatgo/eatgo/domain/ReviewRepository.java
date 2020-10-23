package kr.co.study.eatgo.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    public Review save(Review review);
    public List<Review> findAllByRestaurantId(Long restaurantId);
}
