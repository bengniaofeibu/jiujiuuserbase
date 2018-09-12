package com.jiujiu.entity;

import com.jiujiu.annotations.ThzBean;


public class UserService {

     @ThzBean(name = "userDao")
     private UserDaoImpl userDao;

    public void getUser(){
         userDao.getUser();
     }
}


