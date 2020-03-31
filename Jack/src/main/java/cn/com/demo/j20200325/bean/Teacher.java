package cn.com.demo.j20200325.bean;

import cn.com.demo.j20200325.annotation.MyComponent;
import lombok.Data;

/**
 * <pre>
 * Filename      :  Teacher
 * Package       :  cn.com.demo.j20200325.bean
 * Company       :  上海想星商务服务有限公司
 * Create Date   :  2020年03月2020/3/31日
 * </pre>
 *
 * @author : yangdong.jia
 */
@Data
@MyComponent
public class Teacher {

    private Long no;
    private String account;
}
