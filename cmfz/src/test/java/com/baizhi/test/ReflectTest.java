package com.baizhi.test;

import com.baizhi.annotation.ExcelName;
import com.baizhi.entity.Guru;
import org.junit.Test;

import java.lang.reflect.Field;

public class ReflectTest  {

    @Test
    public void test() throws Exception {
        Guru guru = new Guru(1, "sfg", "/jfldsa/as.jpg", "金轮法王", 1);
//        拿到类对象
        Class<? extends Guru> guruClass = guru.getClass();

//        拿到类的所有属性
        Field[] declaredFields = guruClass.getDeclaredFields();

//        第一个属性不需要，所以跳过第一个属性
        for (int i = 0; i < declaredFields.length; i++) {
//            拿到所以的属性名
            String name = declaredFields[i].getName();
            if(name.equals("serialVersionUID")) continue;
//            由属性名，拼接出属性的get方法
            String methodName="get"+name.substring(0,1).toUpperCase()+name.substring(1);

//            System.out.println("name = " + methodName);
//            由get方法名，执行get方法，并得到目标属性的值
            Object invoke = guruClass.getDeclaredMethod(methodName, null).invoke(guru, null);
            System.out.println("invoke = " + invoke);
        }

        for (int i = 0; i < declaredFields.length; i++) {
            ExcelName annotation = declaredFields[i].getAnnotation(ExcelName.class);
            if(annotation==null) continue;
            String name = annotation.name();
            System.out.println("name = " + name);
        }

    }
}
