package cn.readsense.error;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;

import cn.readsense.base.activity.BaseActivity;

@Route(path = "/error/mainActivity")
public class MainActivity extends BaseActivity {

    private static final String TAG = "error_MainActivity";

    @Autowired(name = "url")
    String url;

    private TextView tv_text;
    private ImageView iv_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_activity_main);
        tv_text = findViewById(R.id.tv_text);
        tv_text.setText(url);
        iv_error = findViewById(R.id.iv_error);
        Glide.with(this).load("http://goo.gl/gEgYUd").error(R.drawable.ic_launcher_background).into(iv_error);
        Log.e(TAG,"error_Glide");
        //setResult(123);
    }

}
