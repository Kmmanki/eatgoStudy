package kr.co.study.eatgo.eatgo.application;

import kr.co.study.eatgo.eatgo.domain.Review;
import kr.co.study.eatgo.eatgo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Review resource, Long restaurantId){
        resource.setRestaurantId(restaurantId);
        Review review = reviewRepository.save(resource);

        return review;
    }
}
