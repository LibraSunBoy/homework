package cn.com.demo.j20200325.bean;

import org.springframework.stereotype.Repository;

/**
 * <pre>
 * Filename      :  Scope
 * Package       :  cn.com.demo.j20200325.bean
 * Company       :  上海想星商务服务有限公司
 * Create Date   :  2020年03月2020/3/31日
 * </pre>
 *
 * @author : yangdong.jia
 */
@Repository
@org.springframework.context.annotation.Scope("scope")
public class Scope {
    private String scopeName;

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }
}
