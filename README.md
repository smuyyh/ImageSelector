# ImageSelector

Android 图片选择器。支持图库多选/单选/图片裁剪/拍照/自定义图片加载库

<img src="https://github.com/smuyyh/ImageSelector/blob/master/screenshot/screen.png?raw=true" width=280/>

## 依赖
```
dependencies {
    compile 'com.yuyh.imgsel:library:1.0.2'
}
```

## 使用

#### 配置权限
```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
```

#### 使用

```java
// 自定义图片加载器
private ImageLoader loader = new ImageLoader() {
    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        // TODO 在这边可以自定义图片加载库来加载ImageView，例如Glide、Picasso、ImageLoader等
        Glide.with(context).load(path).into(imageView);
    }
};
// 配置选项
ImgSelConfig config = new ImgSelConfig.Builder(loader)
        // 是否多选
        .multiSelect(false)
        // “确定”按钮背景色
        .btnBgColor(Color.GRAY)
        // “确定”按钮文字颜色
        .btnTextColor(Color.BLUE)
        // 标题
        .title("图片")
        // 标题文字颜色
        .titleColor(Color.WHITE)
        // TitleBar背景色
        .titleBgColor(Color.parseColor("#3F51B5"))
        // 裁剪大小。needCrop为true的时候配置
        .cropSize(1, 1, 200, 200)
        .needCrop(true)
        // 第一个是否显示相机
        .needCamera(false)
        // 最大选择图片数量
        .maxNum(9)
        .build();
        
// 跳转到图片选择器
ImgSelActivity.startActivity(this, config, REQUEST_CODE);
```

```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    // 图片选择结果回调
    if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
        List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
        for (String path : pathList) {
            tvResult.append(path + "\n");
        }
    }
}
```
