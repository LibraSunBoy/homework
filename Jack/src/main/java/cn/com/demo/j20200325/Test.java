package cn.com.demo.j20200325;

import cn.com.demo.j20200325.bean.Scope;
import cn.com.demo.j20200325.init.FactoryBeanClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * Filename      :  Test
 * Package       :  cn.com.demo.j20200325
 * Company       :  上海想星商务服务有限公司
 * Create Date   :  2020年03月2020/3/30日
 * </pre>
 *
 * @author : yangdong.jia
 */
public class Test {


    @org.junit.Test
    public void test1(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("cn.com.demo.j20200325");
        List<String> list = Arrays.asList(annotationConfigApplicationContext.getBeanDefinitionNames());
        System.out.println("test1.list  = >"+list);
        Object factoryBeanClass = annotationConfigApplicationContext.getBean("student");
        System.out.println("test1  = >"+factoryBeanClass);
    }

    @org.junit.Test
    public void test2(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("cn.com.demo.j20200325");
        List<String> list = Arrays.asList(annotationConfigApplicationContext.getBeanDefinitionNames());
        System.out.println("test2.list  = >"+list);
        Object factoryBeanClass = annotationConfigApplicationContext.getBean("&factoryBeanClass");
        System.out.println("test2  = >"+factoryBeanClass);
    }

    @org.junit.Test
    public void test3(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("cn.com.demo.j20200325");
        List<String> list = Arrays.asList(annotationConfigApplicationContext.getBeanDefinitionNames());
        System.out.println("test3.list  = >"+list);
        Object teacher = annotationConfigApplicationContext.getBean("teacher");
        System.out.println("test2  = >"+teacher);

    }

    @org.junit.Test
    public void test4(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("cn.com.demo.j20200325");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+" = >" +annotationConfigApplicationContext.getBean("scope"));
            }).start();
        }

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
