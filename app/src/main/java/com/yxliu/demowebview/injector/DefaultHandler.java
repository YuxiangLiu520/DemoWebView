package com.yxliu.demowebview.injector;

import android.webkit.WebView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Create by yxliu
 * 2018/12/3 23:10
 */
public class DefaultHandler implements JsHandler{
    @Override
    public String action() {
        return null;
    }

    @Override
    public void handleJs(WebView webView, JSONObject object) {
        JSONObject toJs = new JSONObject();
        try {
            toJs.put("result", "unsupported");
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        WebViewInjector.invokeCallback(webView, object, toJs);
    }
}
