package com.company.Back_End.Core;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by user on 8/27/2016.
 */
public class viewer  {
    private controller controllerInstance = null;
    public void createControllerInstance(String controllerName, String methodName){
        controllerInstance = new controller(controllerName, methodName);
    }

    public Object getdata(Object[] params) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return controllerInstance.getData(params);
    }


}
