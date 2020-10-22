package kr.co.study.eatgo.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem,Long> {
    List<MenuItem> findByRestaurantId(Long id);
    void deleteById(Long id);
}
