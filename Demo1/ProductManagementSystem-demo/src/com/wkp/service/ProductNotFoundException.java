package com.wkp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductNotFoundException extends Exception{
    public static final Logger LOGGER= LoggerFactory.getLogger("ProductNotFoundException");
    public ProductNotFoundException(String productName){
        LOGGER.info("找不到"+productName+"这个商品。");
        System.out.println("找不到"+productName+"这个商品。");
    }

    public ProductNotFoundException(){
        LOGGER.info("找不到商品。");
        System.out.println("找不到商品。");
    }
}
