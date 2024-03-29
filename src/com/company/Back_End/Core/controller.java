package com.company.Back_End.core;

import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by user on 8/25/2016.
 */
public class controller extends ClassLoader {
    private String controllerName, methodName;

    public controller (String controllerName, String methodName){
        this.controllerName = "com.company.Back_End.controllers." + controllerName;
        this.methodName = methodName;
    }

    public void changeMethodName(String newMethodName){
        this.methodName = newMethodName;
    }

    public Object getData(Object[] params) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> paramters[] = new Class[params.length];
        for (int i = 0; i < params.length; ++i) {
            paramters[i] = params[i].getClass();
        }
        Class<?> clas = Class.forName(controllerName, true,this);
        Object inst = clas.newInstance();
        Method myMethod = clas.getDeclaredMethod(methodName, paramters);
        return myMethod.invoke(inst, params);
    }

}
