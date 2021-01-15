package cn.readsense.ali_arouter_demo.app;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

public class App extends Application {

    private boolean isDebugArRouter = true;

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        if (isDebugArRouter) {
            ARouter.openDebug();

            ARouter.openLog();
        }
        ARouter.init(App.this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}