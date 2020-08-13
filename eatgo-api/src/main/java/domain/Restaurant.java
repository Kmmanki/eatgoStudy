package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Restaurant {

    private final Long id;
    private final String name;
    private final String information;
}
