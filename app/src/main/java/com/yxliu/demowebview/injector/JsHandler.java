package com.yxliu.demowebview.injector;

import android.webkit.WebView;

import org.json.JSONObject;

/**
 *
 * Create by yxliu
 * 2018/12/3 23:07
 */
public interface JsHandler {
    String action();

    // 如果有需要，可以用Gson或fastjson转换成bean，而不是用JSONObject
    void handleJs(WebView webView, JSONObject object);
}
