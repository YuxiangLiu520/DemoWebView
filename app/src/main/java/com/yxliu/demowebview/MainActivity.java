package com.yxliu.demowebview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private String url = "file:///android_asset/index.html";
    private String url = "file:///android_asset/web/test-framework.html";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        findViewById(R.id.btn_load_html).setOnClickListener(this);
        findViewById(R.id.btn_load_apk).setOnClickListener(this);
        findViewById(R.id.btn_load_local).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, WebViewActivity.class);
        switch (v.getId()) {
            case R.id.btn_load_html:
                intent.putExtra("URL", "http:www.baidu.com");
                break;
            case R.id.btn_load_apk:
                intent.putExtra("URL", url);
                break;
            case R.id.btn_load_local:
                intent.putExtra("URL", getLocalUrl());
                break;
        }
        startActivity(intent);
    }

    private String getLocalUrl() {
        //文件存储的本地文件夹路径
        File filesDir = getFilesDir().getAbsoluteFile();
        //本地存储的文件
        File destFile = new File(filesDir, "staticHtmlCopy.html");
        //将assets路径下的文件copy到filesDir路径下，注：此处也可以通过网络访问，对页面进行存储
        InputStream is = null;
        try {
            is = getAssets().open("web/staticHtml.html");
            if (destFile.exists()) {
                destFile.delete();
            }
            FileOutputStream out = new FileOutputStream(destFile);
            try {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) >= 0) {
                    out.write(buffer, 0, bytesRead);
                }
            } finally {
                out.flush();
                try {
                    out.getFD().sync();
                } catch (IOException e) {
                    Log.d("Error",e.getMessage());
                }
                out.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //拼接生成WebView使用的url地址
        return "file://"+destFile.getAbsolutePath();
    }

}
