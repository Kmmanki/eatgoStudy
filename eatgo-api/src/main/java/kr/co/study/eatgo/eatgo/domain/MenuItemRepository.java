package kr.co.study.eatgo.eatgo.domain;

import java.util.List;

public interface MenuItemRepository {
    List<MenuItem> findByRestaurantId(Long id);

}
