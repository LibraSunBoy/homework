package cn.com.demo.j20200325.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <pre>
 * Filename      :  InitClass
 * Package       :  cn.com.demo.j20200325.init
 * Company       :  上海想星商务服务有限公司
 * Create Date   :  2020年03月2020/3/30日
 * </pre>
 *
 *
 * 两种方式都是本类初始化之后调用
 *
 * 第一种有侵入性，依赖于spring,相比较而言第二种更好
 *
 * @author : yangdong.jia
 */
@Component
public class InitClass implements InitializingBean {

    //第一种
    @Override
    public void afterPropertiesSet() throws Exception {
       System.out.println("本类初始化之后调用！");
    }

    //第二种
    @PostConstruct
    public void init(){
        System.out.println("初始化");
    }
}
