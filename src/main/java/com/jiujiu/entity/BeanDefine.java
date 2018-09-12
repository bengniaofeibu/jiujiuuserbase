package com.jiujiu.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BeanDefine {

    private String id;

    private String className;

    public BeanDefine(String id, String className) {
        this.id = id;
        this.className = className;
    }
}
