package com.yxliu.demowebview.injector;

import android.webkit.WebView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Create by liuyx
 * 2018/12/3 23:12
 */
public class GetPackageNameHandler implements JsHandler{
    @Override
    public String action() {
        return "getPackageName";
    }

    @Override
    public void handleJs(WebView webView, JSONObject object) {
        JSONObject toJs = new JSONObject();
        try {
            String name = webView.getContext().getPackageName();
            toJs.put("result", "ok");
            JSONObject data = new JSONObject();
            data.put("name", name);
            toJs.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        WebViewInjector.invokeCallback(webView, object, toJs);
    }
}
