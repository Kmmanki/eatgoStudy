package kr.co.study.eatgo.eatgo.interfaces;

import kr.co.study.eatgo.eatgo.application.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
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
        mvc.perform(post("/restaurants/1/reviews")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Joker\", \"score\": 3, \"description\": \"good\"}"))
                .andExpect(status().isCreated());
        
        verify(reviewService).addReview(any());
    }

}