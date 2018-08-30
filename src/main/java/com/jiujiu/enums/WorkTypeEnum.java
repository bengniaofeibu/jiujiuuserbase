package com.jiujiu.enums;

public enum WorkTypeEnum {

    MENTAL(1.55, 1.56),

    MODERATE_PHYSICAL(1.78, 1.64),

    SEVERE_PHYSICAL(2.1, 1.82),

    FITNESS(2.35, 2.0);

    Double manRatio;

    Double woManRatio;

    WorkTypeEnum(Double manRatio, Double woManRatio) {
        this.manRatio = manRatio;
        this.woManRatio = woManRatio;
    }

    public Double getManRatio() {
        return manRatio;
    }

    public Double getWoManRatio() {
        return woManRatio;
    }
}
