package cn.readsense.personal;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.List;
import java.util.Map;

import cn.readsense.base.activity.BaseActivity;
import cn.readsense.base.bean.Personal;
import cn.readsense.base.constant.Constant;

@Route(path = "/personal/mainActivity", extras = Constant.LOGIN_NEEDED)
public class MainActivity extends BaseActivity {

    private static final String TAG = "personal_MainActivity";

    @Autowired(name = "text")
    String text;

    @Autowired(name = "time")
    int time;

    @Autowired(name = "p_list")
    List<Personal> personals;

    @Autowired(name = "p_map")
    Map<String, List<Personal>> personalMap;

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity_main);
        textView = findViewById(R.id.tv_text);

        textView.setText(text + "\n"
                + time + "\n"
                + personals.get(0).toString() + "\n"
                + personals.get(1).toString() + "\n"
                + personalMap.get("qyg").toString());
        setResult(123);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build("/shoppingcar/mainActivity")
                        .withString("text", "这是shoppingcar_mainActivity")
                        .navigation(MainActivity.this, new NavigationCallback() {
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
}
