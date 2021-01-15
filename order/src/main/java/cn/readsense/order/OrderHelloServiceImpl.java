package cn.readsense.order;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.readsense.provider.service.HelloService;

// 实现接口
@Route(path = "/order/hello", name = "测试服务")
public class OrderHelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "order_hello " + name;
    }

    @Override
    public void init(Context context) {

    }
}