package cn.readsense.ali_arouter_demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import cn.readsense.base.activity.BaseActivity;
import cn.readsense.provider.service.HelloService;

//路径至少需要两级
@Route(path = "/app/mainActivity")
public class MainActivity extends BaseActivity {

    private static final String TAG = "app_MainActivity";
    private Button button;

    @Autowired(name = "/order/hello")
    HelloService helloService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        String qyg = helloService.sayHello("qyg");
        Log.e(TAG, "helloService " + qyg);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build("/order/mainActivity")
                        .withString("text", "这是order_mainActivity")
                        .withInt("time", 3)
                        .withTransition(R.anim.nav_default_pop_enter_anim, R.anim.nav_default_pop_exit_anim)
                        .navigation(MainActivity.this, 123, new NavigationCallback() {
                            @Override
                            public void onFound(Postcard postcard) {
                                //目标路由被发现时调用
                                Log.e(TAG, "onFound: 发现路由" + postcard.getPath());
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                //路由丢失后调用
                                Log.e(TAG, "onLost: 路由丢失");
                            }

                            @Override
                            public void onArrival(Postcard postcard) {
                                //路由到达时调用
                                Log.e(TAG, "onArrival: 路由到达");
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                //路由被拦截后调用
                                Log.e(TAG, "onInterrupt: sorry啊 阁下被拦截了");
                            }
                        });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 123:
                Toast.makeText(this, "lalala", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}