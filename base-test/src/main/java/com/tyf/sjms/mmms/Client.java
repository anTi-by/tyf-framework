package com.tyf.sjms.mmms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
    public  static  final Logger logger=LoggerFactory.getLogger(Client.class);
    public static void main(String[] args) {
        Facade facade = new Facade();
    //    facade.test();
        logger.info("FSAFSA");
    }
}
