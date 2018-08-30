package com.jiujiu.base;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = 5895699582957353403L;
}
