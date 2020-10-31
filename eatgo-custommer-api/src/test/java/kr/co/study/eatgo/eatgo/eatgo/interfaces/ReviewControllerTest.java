package kr.co.study.eatgo.eatgo.eatgo.interfaces;

import kr.co.study.eatgo.eatgo.application.ReviewService;
import kr.co.study.eatgo.eatgo.domain.Review;
import kr.co.study.eatgo.eatgo.interfaces.ReviewController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReviewService reviewService;


    @Test
    public void create() throws  Exception{
        given(reviewService.addReview(any(),eq(1L))).willReturn(
                Review.builder()
                .id(1L)
                .name("Joker")
                .description("good")
                .build()
        );

        mvc.perform(post("/restaurants/1/reviews")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Joker\", \"score\": 3, \"description\": \"good\"}"))
                .andExpect(status().isCreated());
        
        verify(reviewService).addReview(any(),eq(1L));
    }

    @Test
    public void createInvalid() throws  Exception{
        mvc.perform(post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(reviewService, never()).addReview(any(),eq(1L));
    }

}