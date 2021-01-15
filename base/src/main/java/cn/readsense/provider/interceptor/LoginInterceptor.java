package cn.readsense.provider.interceptor;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;

import cn.readsense.base.constant.Constant;

/**
 * 声明拦截器(拦截跳转过程，面向切面编程)
 */
// 比较经典的应用就是在跳转过程中处理登陆事件，这样就不需要在目标页重复做登陆检查
// 拦截器会在跳转之间执行，多个拦截器会按优先级顺序依次执行
@Interceptor(priority = 8, name = "用户验证")
public class LoginInterceptor implements IInterceptor {
    private static final String TAG = "LoginInterceptor";

    private Context mContext;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {

        Log.e(TAG, "LoginInterceptor 开始执行");

        //给需要跳转的页面添加值为Constant.LOGIN_NEEDED的extra参数，用来标记是否需要用户先登录才可以访问该页面
        //先判断需不需要
        if (postcard.getExtra() == Constant.LOGIN_NEEDED) {

            //boolean isLogin = mContext.getSharedPreferences().getBoolean(Constant.IS_LOGIN, false);
            boolean isLogin = false;
            Log.e(TAG, "是否已登录: " + isLogin);

            //判断用户的登录情况，可以把值保存在sp中
            if (isLogin) {
                callback.onContinue(postcard);
            } else {//没有登录,注意需要传入context
                ARouter.getInstance()
                        .build("/error/mainActivity")
                        .greenChannel()
                        .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .navigation(mContext);
            }
        } else {//没有extra参数时则继续执行，不做拦截
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        mContext = context;
        Log.e(TAG, "LoginInterceptor 初始化");
    }
}