package com.jiujiu.entity.response;

import com.jiujiu.base.BaseEntity;
import com.jiujiu.model.Food;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DietaryAdviceRes extends BaseEntity{

    private static final long serialVersionUID = 5895699582957353403L;

    private Integer heat;

    private Integer protein;

    private Integer nutrientElements;

    private List<Food> foods;

    public DietaryAdviceRes() {
    }

    public DietaryAdviceRes(Integer heat, Integer protein, Integer nutrientElements, List<Food> foods) {
        this.heat = heat;
        this.protein = protein;
        this.nutrientElements = nutrientElements;
        this.foods = foods;
    }
}
