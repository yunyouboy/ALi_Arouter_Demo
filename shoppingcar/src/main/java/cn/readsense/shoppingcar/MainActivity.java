package cn.readsense.shoppingcar;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import cn.readsense.base.activity.BaseActivity;

@Route(path = "/shoppingcar/mainActivity")
public class MainActivity extends BaseActivity {

    private static final String TAG = "shoppingcar_MainActivity";

    @Autowired(name = "text")
    String text;

    @Autowired(name = "pretreatment")
    String pretreatment;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppingcar_activity_main);
        textView = findViewById(R.id.tv_text);
        textView.setText(text + "\n" + pretreatment);
        setResult(123);
    }

}
