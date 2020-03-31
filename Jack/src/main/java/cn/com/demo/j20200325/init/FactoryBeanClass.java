package cn.com.demo.j20200325.init;

import cn.com.demo.j20200325.bean.Student;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * Filename      :  FactoryBeanClass
 * Package       :  cn.com.demo.j20200325.bean
 * Company       :  上海想星商务服务有限公司
 * Create Date   :  2020年03月2020/3/30日
 * </pre>
 *
 * @author : yangdong.jia
 */
@Component
public class FactoryBeanClass implements FactoryBean  {
    /*
     * 需要代理的时候
     * mybatis跟spring整合的时候，生成代理
     *
     * 比如说  feign
     *
     * dubbo里面，生成了客户端的代理
     * */
    /*
     * 这个方法是返回类的实例
     * */
    @Override
    public Object getObject() throws Exception {
        return new Student();
    }

    @Override
    public Class<?> getObjectType() {
        return Student.class;
    }
}
