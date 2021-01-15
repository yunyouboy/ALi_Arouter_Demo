package cn.readsense.personal;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.readsense.provider.service.HelloService;

// 实现接口
@Route(path = "/personal/hello", name = "测试服务")
public class PersonalHelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "personal_hello " + name;
    }

    @Override
    public void init(Context context) {

    }
}