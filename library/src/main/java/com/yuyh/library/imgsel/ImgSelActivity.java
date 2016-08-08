package com.yuyh.library.imgsel;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyh.library.imgsel.common.Callback;
import com.yuyh.library.imgsel.common.Constant;
import com.yuyh.library.imgsel.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;

public class ImgSelActivity extends FragmentActivity implements View.OnClickListener, Callback {

    public static final String INTENT_RESULT = "result";
    private static final int IMAGE_CROP_CODE = 1;

    private ImgSelConfig config;

    private RelativeLayout rlTitleBar;
    private TextView tvTitle;
    private Button btnConfirm;
    private String cropImagePath;

    private ArrayList<String> result = new ArrayList<>();

    public static void startActivity(Activity activity, ImgSelConfig config, int RequestCode) {
        Intent intent = new Intent(activity, ImgSelActivity.class);
        Constant.config = config;
        activity.startActivityForResult(intent, RequestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_sel);

        config = Constant.config;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fmImageList, ImgSelFragment.instance(config), null)
                .commit();

        initView();
        if (!FileUtils.isSdCardAvailable()) {
            Toast.makeText(this, "SD卡不可用", Toast.LENGTH_SHORT).show();
        }
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
                exit();
            }
        }
    }

    @Override
    public void onSingleImageSelected(String path) {
        if (config.needCrop) {
            crop(path);
        } else {
            Constant.imageList.add(path);
            exit();
        }
    }

    @Override
    public void onImageSelected(String path) {
        btnConfirm.setText("确定(" + Constant.imageList.size() + "/" + config.maxNum + ")");

    }

    @Override
    public void onImageUnselected(String path) {
        btnConfirm.setText("确定(" + Constant.imageList.size() + "/" + config.maxNum + ")");
    }

    @Override
    public void onCameraShot(File imageFile) {
        if (imageFile != null) {
            if (config.needCrop) {
                crop(imageFile.getAbsolutePath());
            } else {
                Constant.imageList.add(imageFile.getAbsolutePath());
                exit();
            }
        }
    }

    private void crop(String imagePath) {
        File file = new File(FileUtils.createRootPath(this) + "/" + System.currentTimeMillis() + ".jpg");

        cropImagePath = file.getAbsolutePath();
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(Uri.fromFile(new File(imagePath)), "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", config.aspectX);
        intent.putExtra("aspectY", config.aspectY);
        intent.putExtra("outputX", config.outputX);
        intent.putExtra("outputY", config.outputY);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, IMAGE_CROP_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_CROP_CODE && resultCode == RESULT_OK) {
            Constant.imageList.add(cropImagePath);
            exit();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void exit() {
        Intent intent = new Intent();
        result.clear();
        result.addAll(Constant.imageList);
        intent.putStringArrayListExtra(INTENT_RESULT, result);
        setResult(RESULT_OK, intent);
        Constant.imageList.clear();
        finish();
    }
}
