package com.yuyh.imgsel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tvResult);
    }

    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    };

    public void Multiselect(View view) {
        tvResult.setText("");
        ImgSelConfig config = new ImgSelConfig.Builder(loader).multiSelect(true).build();

        ImgSelActivity.startActivity(this, config, REQUEST_CODE);
    }

    public void Single(View view) {
        tvResult.setText("");
        ImgSelConfig config = new ImgSelConfig.Builder(loader)
                // 是否多选
                .multiSelect(false)
                // 确定按钮背景色
                .btnBgColor(Color.GRAY)
                // 确定按钮文字颜色
                .btnTextColor(Color.BLUE)
                .title("图片")
                .titleColor(Color.WHITE)
                .titleBgColor(Color.parseColor("#3F51B5"))
                .cropSize(1, 1, 200, 200)
                .needCrop(true)
                .needCamera(false)
                .build();

        ImgSelActivity.startActivity(this, config, REQUEST_CODE);
    }

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
        List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
        for (String path : pathList) {
            tvResult.append(path + "\n");
        }
    }
}
}
