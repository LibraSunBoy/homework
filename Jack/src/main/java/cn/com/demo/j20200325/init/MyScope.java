package cn.com.demo.j20200325.init;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * Filename      :  MyScope
 * Package       :  cn.com.demo.j20200325.init
 * Company       :  上海想星商务服务有限公司
 * Create Date   :  2020年03月2020/3/31日
 * </pre>
 *
 * @author : yangdong.jia
 */
public class MyScope implements Scope {
    private ThreadLocal threadLocal = new ThreadLocal();

    @Override
    public Object get(String s, ObjectFactory<?> objectFactory) {
       if (threadLocal.get()==null){
            Object object = objectFactory.getObject();
            threadLocal.set(object);
        }
        return threadLocal.get();
    }

    @Override
    public Object remove(String s) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
