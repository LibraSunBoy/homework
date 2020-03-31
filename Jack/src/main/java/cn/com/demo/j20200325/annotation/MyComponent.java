package cn.com.demo.j20200325.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * <pre>
 * Filename      :  MyComponent
 * Package       :  cn.com.demo.j20200325.annotation
 * Company       :  上海想星商务服务有限公司
 * Create Date   :  2020年03月2020/3/31日
 * </pre>
 *
 * @author : yangdong.jia
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyComponent {
    String value() default "";
}
