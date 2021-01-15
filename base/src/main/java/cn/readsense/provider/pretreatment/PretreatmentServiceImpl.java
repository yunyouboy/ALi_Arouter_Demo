package cn.readsense.provider.pretreatment;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.PretreatmentService;

/**
 * 预处理服务
 */
// 实现 PretreatmentService 接口，并加上一个Path内容任意的注解即可
@Route(path = "/base/pretreatment")
public class PretreatmentServiceImpl implements PretreatmentService {
    @Override
    public boolean onPretreatment(Context context, Postcard postcard) {
        //PretreatmentService类是框架中预留的一个服务类（需要自己实现），可以在路由前做一些操作，比如可以cancel掉这次的路由跳转。触发时机比拦截器更早。
        // 跳转前预处理，如果需要自行处理跳转，该方法返回 false 即可
        if (postcard.getPath().equals("/shoppingcar/mainActivity")) {
            postcard.withString("pretreatment","Pretreatment value");
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void init(Context context) {

    }
}