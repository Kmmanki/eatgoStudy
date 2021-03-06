package kr.co.study.eatgo.eatgo.interfaces;

import kr.co.study.eatgo.eatgo.application.ReviewService;
import kr.co.study.eatgo.eatgo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> createReview (
            @RequestBody @Valid Review resource,
            @PathVariable("restaurantId") Long restaurantId) throws  Exception{

        Review review = reviewService.addReview(resource, restaurantId);

        return ResponseEntity.created(new URI("/restaurants/"+restaurantId+"/reviews/"+review.getId()))
                .body("{}");
    }

}
