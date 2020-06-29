package com.meal.list.backend.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class IngredientsListResponse {

    private String name;
    private int gram;
}
