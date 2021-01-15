package cn.readsense.order;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.readsense.base.activity.BaseActivity;
import cn.readsense.base.bean.Personal;

@Route(path = "/order/mainActivity")
public class MainActivity extends BaseActivity {

    private static final String TAG = "order_MainActivity";

    @Autowired(name = "text")
    String text;

    @Autowired(name = "time")
    int time;

    private Button button;
    private TextView textView;

    Personal personal1;
    Personal personal2;
    List<Personal> personals;
    Map<String, List<Personal>> personalMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_main);
        textView = findViewById(R.id.tv_text);
        textView.setText(text + time);
        setResult(123);

        button = findViewById(R.id.button);

        personal1 = new Personal(1, "q", 123);
        personal2 = new Personal(2, "w", 456);
        personals = new ArrayList<>();
        personals.add(personal1);
        personals.add(personal2);
        personalMap = new HashMap();
        personalMap.put("qyg", personals);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("arouter://m.aliyun.com/personal/mainActivity");
                ARouter.getInstance()
                        .build(uri)
                        .withString("text", "这是personal_mainActivity")
                        .withInt("time", 3)
                        .withObject("p_list", personals)
                        .withObject("p_map", personalMap)
                        .navigation(MainActivity.this, new NavigationCallback() {
                            @Override
                            public void onFound(Postcard postcard) {
                                //目标路由被发现时调用
                                Log.d(TAG, "onFound: 发现路由" + postcard.getPath());
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                //路由丢失后调用
                                Log.d(TAG, "onLost: 路由丢失");
                            }

                            @Override
                            public void onArrival(Postcard postcard) {
                                //路由到达时调用
                                Log.d(TAG, "onArrival: 路由到达");
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                //路由被拦截后调用
                                Log.d(TAG, "onInterrupt: sorry啊 阁下被拦截了");
                            }
                        });
            }
        });
    }

}
