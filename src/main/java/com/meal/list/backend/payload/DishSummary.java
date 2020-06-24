package com.meal.list.backend.payload;

import com.meal.list.backend.service.dishservice.DishCategoryEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DishSummary {

    private DishCategoryEnum categoryEnum;
    private int count;
}
