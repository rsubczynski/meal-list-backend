package com.meal.list.backend.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class DishResponse {

    private MakroDishResponse makroDish;
    private List<IngredientsListResponse> ingredientsList;
    private List<String> descriptions;
}
