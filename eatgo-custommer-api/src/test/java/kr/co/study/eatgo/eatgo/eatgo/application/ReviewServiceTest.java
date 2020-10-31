package kr.co.study.eatgo.eatgo.eatgo.application;

import kr.co.study.eatgo.eatgo.application.ReviewService;
import kr.co.study.eatgo.eatgo.domain.Review;
import kr.co.study.eatgo.eatgo.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reviewRepository);
    }

    @Mock
    public ReviewRepository reviewRepository;


    @Test
    public void addReview() throws  Exception{
        Review review = Review.builder()
                .id(1L)
                .name("Joker")
                .score(3L)
                .description("good")
                .build();

        reviewService.addReview(review, 1L);

        verify(reviewRepository).save(any());
    }

}