package com.jiujiu.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jiujiu.Head.ApiHead;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public abstract class BaseEntity extends ApiHead implements Serializable{
    private static final long serialVersionUID = 5895699582957353403L;

}
