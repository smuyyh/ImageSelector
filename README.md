# ImageSelector

Android 图片选择器。支持图库多选/单选/图片裁剪/拍照/自定义图片加载库

## 使用

#### 配置权限
```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
```

#### 使用

```java
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
    // 选择结果回调
    if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
        List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
        for (String path : pathList) {
            tvResult.append(path + "\n");
        }
    }
}
```