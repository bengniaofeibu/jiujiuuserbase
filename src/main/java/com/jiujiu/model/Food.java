package com.jiujiu.model;

import com.jiujiu.base.BaseModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Food extends BaseModel{

    private static final long serialVersionUID = 5895699582957353403L;

    private String name;

    private int grams;
}
