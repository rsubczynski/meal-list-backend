package com.meal.list.backend.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Setter
@Getter
public class MakroDishResponse {

    private Long id;
    private String type;
    private String name;
    private double protein;
    private double carbohydrate;
    private double fat;
    private double kcal;

}
