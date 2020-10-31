package kr.co.study.eatgo.eatgo.eatgo.interfaces;

import kr.co.study.eatgo.eatgo.application.MenuItemService;
import kr.co.study.eatgo.eatgo.interfaces.MenuItemController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MenuItemController.class)
class MenuItemControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MenuItemService menuItemService;

    @Test
    public void bulkUpdate() throws Exception{

        mvc.perform(patch("/restaurants/1/menuitems")
            .content("[]")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

            verify(menuItemService).bulkUpdate(eq(1L), any());
//        verify(menuIttemService).updateRestaurant(1004L, "Jocker Bar", "Busan");
    }
}