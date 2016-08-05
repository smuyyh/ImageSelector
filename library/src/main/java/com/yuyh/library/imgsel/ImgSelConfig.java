package com.yuyh.library.imgsel;

import android.graphics.Color;
import android.os.Environment;

import com.yuyh.library.imgsel.utils.FileUtils;

import java.io.Serializable;

/**
 * @author yuyh.
 * @date 2016/8/5.
 */
public class ImgSelConfig implements Serializable{

    /**
     * 是否需要裁剪
     */
    public boolean needCrop;

    /**
     * 是否多选
     */
    public boolean mutiSelect;

    /**
     * 最多选择图片数
     */
    public int maxNum = 9;

    /**
     * 第一个item是否显示相机
     */
    public boolean needCamera;

    /**
     * 标题
     */
    public String title;

    /**
     * 标题颜色
     */
    public int titleColor;

    /**
     * titlebar背景色
     */
    public int titleBgColor;

    /**
     * 确定按钮文字颜色
     */
    public int btnTextColor;

    /**
     * 确定按钮背景色
     */
    public int btnBgColor;

    /**
     * 拍照存储路径
     */
    public String filePath;

    /**
     * 自定义图片加载器
     */
    public ImageLoader loader;

    public ImgSelConfig(Builder builder) {
        needCrop = builder.needCrop;
        mutiSelect = builder.mutiSelect;
        maxNum = builder.maxNum;
        needCamera = builder.needCamera;
        title = builder.title;
        titleBgColor = builder.titleBgColor;
        titleColor = builder.titleColor;
        btnBgColor = builder.btnBgColor;
        btnTextColor = builder.btnTextColor;
        filePath = builder.filePath;
        loader = builder.loader;
    }

    public static class Builder implements Serializable {

        private boolean needCrop = false;
        private boolean mutiSelect = true;
        private int maxNum = 9;
        private boolean needCamera = true;
        private String title = "图片";
        private int titleColor;
        private int titleBgColor;
        private int btnTextColor;
        private int btnBgColor;
        private String filePath;
        private ImageLoader loader;

        public Builder(ImageLoader loader) {
            this.loader = loader;

            if (FileUtils.isSdCardAvailable())
                filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Camera";
            else
                filePath = Environment.getRootDirectory().getAbsolutePath() + "/Camera";

            titleBgColor = Color.parseColor("#3F51B5");
            titleColor = Color.WHITE;

            btnBgColor = Color.TRANSPARENT;
            btnTextColor = Color.WHITE;

            FileUtils.createDir(filePath);
        }

        public Builder needCrop(boolean needCrop) {
            this.needCrop = needCrop;
            return this;
        }

        public Builder mutiSelect(boolean mutiSelect) {
            this.mutiSelect = mutiSelect;
            return this;
        }

        public Builder maxNum(int maxNum) {
            this.maxNum = maxNum;
            return this;
        }

        public Builder needCamera(boolean needCamera) {
            this.needCamera = needCamera;
            return this;
        }

        private Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder titleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public Builder titleBgColor(int titleBgColor) {
            this.titleBgColor = titleBgColor;
            return this;
        }

        public Builder btnTextColor(int btnTextColor) {
            this.btnTextColor = btnTextColor;
            return this;
        }

        public Builder btnBgColor(int btnBgColor) {
            this.btnBgColor = btnBgColor;
            return this;
        }

        private Builder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public ImgSelConfig build() {
            return new ImgSelConfig(this);
        }
    }
}
