package com.games.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    /*
    in this class we will be creating the reusable logic to read from configuration.properties file
     */

    //1.Create the property object
    //making in provate we are limiting access to the pbject
    //static is to make sure its created and loaded before anything else
    //4.Use "properties" object to read from the file (read properties)
    private static Properties properties = new Properties();

    static {
        //2.create file using FileInputStream
        try {
            String path = "configuration.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);

        } catch (IOException e) {
            System.out.println("FILE NOT FOUND WITH GIVEN PATH!!!");
            e.printStackTrace();
        }

    }
    public static String getProperty(String keyword){
        return properties.getProperty(keyword);
    }
}
