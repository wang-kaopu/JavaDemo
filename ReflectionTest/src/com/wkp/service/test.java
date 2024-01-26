package com.wkp.service;

import com.wkp.po.person;
import com.wkp.po.student;

import java.lang.reflect.*;

public class test {
    public static void main(String[] args) throws Exception {
        person stu = new student("Alice", 89);

//        //获取一个class的class实例
//        //1.直接提供一个class的静态变量获取
//        Class<String> stringClass = String.class;
//        System.out.println(stringClass);
//        //2.通过实例变量的getClass获取
//        String s = "Hello";
//        Class<? extends String> aClass = s.getClass();
//        System.out.println(aClass);
//        //3.Class的静态方法，传入这个class的完整类名
//        Class cls = Class.forName("java.lang.String");
//        System.out.println(cls);
//
//        Class<String> stringClass1 = String.class;
//        String s1 = stringClass1.getDeclaredConstructor().newInstance();
//

        //访问字段
//        Class<student> studentClass = student.class;
//
//        System.out.println(studentClass.getDeclaredField("score"));
//        System.out.println(studentClass.getField("name"));
//
//        Field f = String.class.getDeclaredField("value");
//        System.out.println(f.getName());
//        int m = f.getModifiers();
//        System.out.println(Modifier.isFinal(m));
//        System.out.println(Modifier.isPublic(m));
//        System.out.println(Modifier.isInterface(m));
//
//        Field n = student.class.getField("name");
//        Object value = n.get(stu);
//        System.out.println(value);
//
//        Method getScore = studentClass.getDeclaredMethod("getScore", String.class);
//        System.out.println(getScore.getName());
//        System.out.println(getScore.getReturnType());
//        System.out.println(getScore.getParameterTypes());

//        Method[] studentClassMethods = studentClass.getMethods();
//        for (Method method : studentClassMethods) {
//            System.out.println(method);
//        }

//        //调用方法
//        String s = "Hello world";
//        Class<? extends String> c = s.getClass();
//        Method substring = c.getMethod("substring", int.class, int.class);
//        String subs = (String)substring.invoke(s, 3, 6);
//        System.out.println("截取字符串是："+subs);
//        //调用静态方法
//        Method m = Integer.class.getMethod("parseInt", String.class);
//        Integer i = (Integer)m.invoke(null, "12345");
//        System.out.println("生成的整数是："+i);
        //调用非public方法
//        person p = new person();
//        Method m = p.getClass().getDeclaredMethod("setName", String.class);
//        m.setAccessible(true);
//        m.invoke(p, "Bob");
//        System.out.println(p.name);

//        Integer i = Integer.class.getConstructor(int.class).newInstance(123);
//        System.out.println(i);
//        System.out.println(Integer.class.getSuperclass());
//        Class[] is = Integer.class.getInterfaces();
//        for (Class ic :is){
//            System.out.println(ic);
//        }


        //动态代理
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if(method.getName().equals("morning")){
                    System.out.println("Good morning, "+args[0]);
                }
                return null;
            }
        };
        Hello hello = (Hello)Proxy.newProxyInstance(
                Hello.class.getClassLoader(),
                new Class[]{Hello.class},
                handler
        );
        hello.morning("Bob");


    }
}

//    String getName(Object obj) {
//        person p = (person) obj;
//        return p.getName();
//    }
