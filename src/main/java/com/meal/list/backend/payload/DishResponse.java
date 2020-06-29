package com.meal.list.backend.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Builder
@Setter
@Getter
public class DishResponse {

    private MakroDishResponse makroDish;
    private Map<String, Integer> ingredientsMap;
    private List<String> descriptions;
}
