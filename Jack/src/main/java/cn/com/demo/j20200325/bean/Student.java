package cn.com.demo.j20200325.bean;

import lombok.Data;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * Filename      :  Student
 * Package       :  cn.com.demo.j20200325.bean
 * Company       :  上海想星商务服务有限公司
 * Create Date   :  2020年03月2020/3/30日
 * </pre>
 *
 * @author : yangdong.jia
 */
@Repository
@Data
public class Student {

    private String name;

    private String password;
}
