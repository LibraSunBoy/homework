package cn.com.demo.j20200325.init;

import cn.com.demo.j20200325.annotation.MyComponent;
import cn.com.demo.j20200325.bean.Student;
import cn.com.demo.j20200325.bean.Teacher;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * Filename      :  BeanDefinition
 * Package       :  cn.com.demo.j20200325.init
 * Company       :  上海想星商务服务有限公司
 * Create Date   :  2020年03月2020/3/30日
 * </pre>
 *
 * * 调用时序，是在bean实例化之前掉的
 *  *
 *  * 这个是在实例化之前掉的
 * @author : yangdong.jia
 */
@Component
public class BeanDefinition implements  BeanDefinitionRegistryPostProcessor {


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        /*
         * 满足某个条件实例化某个bean
         * */
//        List<String> list = Arrays.asList(beanDefinitionRegistry.getBeanDefinitionNames());
//        System.out.println("BeanDefinition.list = >"+list);
//        long count = list.stream().filter(item -> item.equals("student")).count();
//        if (count>0){
//            GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
//            genericBeanDefinition.setBeanClass(Teacher.class);
//            MutablePropertyValues propertyValues = genericBeanDefinition.getPropertyValues();
//            propertyValues.addPropertyValue("no","123456");
//            propertyValues.addPropertyValue("account","123456789");
//            beanDefinitionRegistry.registerBeanDefinition("teacher",genericBeanDefinition);
//        }
        /*
         * 自定义扫描器
         * */
        ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner(beanDefinitionRegistry);
        classPathBeanDefinitionScanner.scan("cn.com.demo.j20200325");
        classPathBeanDefinitionScanner.addIncludeFilter(new AnnotationTypeFilter(MyComponent.class));

    }
    /*
     *
     * Springcloud springbus 进行刷新的时候
     * */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        configurableListableBeanFactory.registerScope("scope",new MyScope());
    }
}
