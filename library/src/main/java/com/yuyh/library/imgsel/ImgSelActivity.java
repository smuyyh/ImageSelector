package com.yuyh.library.imgsel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuyh.library.imgsel.common.Callback;
import com.yuyh.library.imgsel.common.Constant;
import com.yuyh.library.imgsel.utils.LogUtils;

import java.io.File;

public class ImgSelActivity extends FragmentActivity implements View.OnClickListener, Callback {

    public static final String INTENT_CONFIG = "config";
    public static final String INTENT_RESULT = "result";

    private ImgSelConfig config;

    private RelativeLayout rlTitleBar;
    private TextView tvTitle;
    private Button btnConfirm;

    public static void startActivity(Context context, ImgSelConfig config) {
        Intent intent = new Intent(context, ImgSelActivity.class);
        Constant.config = config;
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_sel);

        config = Constant.config;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fmImageList, ImgSelFragment.instance(config, this), null)
                .commit();

        initView();
    }

    private void initView() {
        rlTitleBar = (RelativeLayout) findViewById(R.id.rlTitleBar);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(this);

        if (config != null) {
            rlTitleBar.setBackgroundColor(config.titleBgColor);
            tvTitle.setTextColor(config.titleColor);
            tvTitle.setText(config.title);
            btnConfirm.setBackgroundColor(config.btnBgColor);
            btnConfirm.setTextColor(config.btnTextColor);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnConfirm) {
            if (Constant.imageList != null && !Constant.imageList.isEmpty()) {
                Intent intent = new Intent();
                intent.putStringArrayListExtra(INTENT_RESULT, Constant.imageList);
                setResult(RESULT_OK, intent);
                Constant.imageList.clear();
                finish();
            }
        }
    }

    @Override
    public void onSingleImageSelected(String path) {
        LogUtils.i("+++++++" + Constant.imageList.size());
    }

    @Override
    public void onImageSelected(String path) {
        LogUtils.i("+++++++" + Constant.imageList.size());
        btnConfirm.setText("确定(" + Constant.imageList.size() + "/" + config.maxNum + ")");

    }

    @Override
    public void onImageUnselected(String path) {
        LogUtils.i("+++++++" + Constant.imageList.size());
        btnConfirm.setText("确定(" + Constant.imageList.size() + "/" + config.maxNum + ")");

    }

    @Override
    public void onCameraShot(File imageFile) {

    }
}
