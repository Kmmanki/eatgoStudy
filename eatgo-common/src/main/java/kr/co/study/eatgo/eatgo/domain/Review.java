package kr.co.study.eatgo.eatgo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;
    @NotNull
    private Long score;
    @NotEmpty
    private String description;
    @NotEmpty
    private Long restaurantId;


    public Long getRestaurantId() {
        return restaurantId;
    }
}
