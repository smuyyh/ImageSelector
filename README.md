# ImageSelector

Android 图片选择器。支持图库多选/单选/图片裁剪/拍照/自定义图片加载库

## 使用

#### 配置权限

    <uses-permission android:<span class="hljs-property">name</span>=<span class="hljs-string">"android.permission.READ_EXTERNAL_STORAGE"</span> />
        <uses-permission android:<span class="hljs-property">name</span>=<span class="hljs-string">"android.permission.WRITE_EXTERNAL_STORAGE"</span> />
        <uses-permission android:<span class="hljs-property">name</span>=<span class="hljs-string">"android.permission.MOUNT_UNMOUNT_FILESYSTEMS"</span> />
    `</pre>

    #### 使用

    <pre>`ImgSelConfig config = <span class="hljs-keyword">new</span> ImgSelConfig.Builder(loader)
          <span class="hljs-comment">// 是否多选</span>
          .multiSelect(<span class="hljs-literal">false</span>)
          <span class="hljs-comment">// 确定按钮背景色</span>
          .btnBgColor(Color.GRAY)
          <span class="hljs-comment">// 确定按钮文字颜色</span>
          .btnTextColor(Color.BLUE)
          .title(<span class="hljs-string">"图片"</span>)
          .titleColor(Color.WHITE)
          .titleBgColor(Color.parseColor(<span class="hljs-string">"#3F51B5"</span>))
          .cropSize(<span class="hljs-number">1</span>, <span class="hljs-number">1</span>, <span class="hljs-number">200</span>, <span class="hljs-number">200</span>)
          .needCrop(<span class="hljs-literal">true</span>)
          .needCamera(<span class="hljs-literal">false</span>)
          .build();
    <span class="hljs-comment">// 跳转</span>
    ImgSelActivity.startActivity(<span class="hljs-keyword">this</span>, config, REQUEST_CODE);
    `</pre>
    <pre>`<span class="hljs-annotation">@Override</span>
    <span class="hljs-function"><span class="hljs-keyword">protected</span> <span class="hljs-keyword">void</span> <span class="hljs-title">onActivityResult</span><span class="hljs-params">(<span class="hljs-keyword">int</span> requestCode, <span class="hljs-keyword">int</span> resultCode, Intent data)</span> </span>{
        <span class="hljs-keyword">super</span>.onActivityResult(requestCode, resultCode, data);
        <span class="hljs-comment">// 选择结果</span>
        <span class="hljs-keyword">if</span> (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != <span class="hljs-keyword">null</span>) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            <span class="hljs-keyword">for</span> (String path : pathList) {
                tvResult.append(path + <span class="hljs-string">"\n"</span>);
            }
        }
    }
    