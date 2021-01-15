package cn.readsense.provider.service;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * 暴露服务
 */
public interface HelloService extends IProvider {
    String sayHello(String name);
}