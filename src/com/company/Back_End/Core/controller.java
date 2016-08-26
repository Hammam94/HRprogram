package com.company.Back_End.Core;

import java.lang.reflect.*;

/**
 * Created by user on 8/25/2016.
 */
public class controller {
    private String controllerName, methodName;

    public controller (String controllerName, String methodName){
        this.controllerName = controllerName;
        this.methodName = methodName;
    }

    public Object getData(Object[] params) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> paramters[] = new Class[params.length];
        for (int i = 0; i < params.length; ++i) {
            paramters[i] = params.getClass();
        }
        Class<?> clas = Class.forName(controllerName);
        Object inst = clas.newInstance();
        Method myMethod = clas.getDeclaredMethod(methodName, paramters);
        return myMethod.invoke(inst, params);
    }

}
