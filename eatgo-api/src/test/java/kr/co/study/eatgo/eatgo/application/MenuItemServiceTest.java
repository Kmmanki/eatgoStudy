package kr.co.study.eatgo.eatgo.application;

import kr.co.study.eatgo.eatgo.domain.MenuItem;
import kr.co.study.eatgo.eatgo.domain.MenuItemRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MenuItemServiceTest {


    @Mock
    private MenuItemRepository menuItemRepository;

    private MenuItemService menuItemService;

//    @Before 2.1.5
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.menuItemService = new MenuItemService(menuItemRepository);
    }

    @Test
    public void bulkUpdate(){
        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(MenuItem.builder()
        .name("Kimch")
        .build());

        menuItems.add(MenuItem.builder()
                .name("Gukbob")
                .build());

        menuItemService.bulkUpdate(1L, menuItems);

        verify(menuItemRepository, times(2)).save(any());
    }
}