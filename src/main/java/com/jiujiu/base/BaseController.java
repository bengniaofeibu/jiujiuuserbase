package com.jiujiu.base;

import com.jiujiu.util.ReqHeadUtil;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseController {

    @Autowired
    protected ReqHeadUtil headUtil;

}
