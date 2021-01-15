package cn.readsense.provider.degrade;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 自定义全局降级策略
 */
// 实现DegradeService接口，并加上一个Path内容任意的注解即可
@Route(path = "/base/degrade")
public class DegradeServiceImpl implements DegradeService {
    private static final String TAG = "DegradeServiceImpl";
    @Override
    public void onLost(Context context, Postcard postcard) {
        Log.e(TAG, "DegradeServiceImpl onLost 开始执行");
        ARouter.getInstance()
                .build("/error/mainActivity")
                .withString("url", postcard.getPath())
                .navigation();
    }

    @Override
    public void init(Context context) {

    }
}