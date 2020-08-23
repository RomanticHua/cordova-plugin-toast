package com.king.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;


/**
 * This class echoes a string called from JavaScript.
 */
public class ToastPlugin extends CordovaPlugin {

    private Handler mHandler;
    public static final String TAG = ToastPlugin.class.getSimpleName();

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Utils.init(webView.getContext());

        mHandler = new Handler(Looper.getMainLooper());


        String name = Utils.getString(preferences, "name", "000");
        int age = Utils.getInt(preferences, "age", 111);
        toast("initialize.." + name + "  " + age);
    }

    @Override
    protected void pluginInitialize() {
        super.pluginInitialize();
        toast("pluginInitialize..");
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                _execute(action, args, callbackContext);
            }
        });
        return true;
    }

    private void _execute(String action, JSONArray args, CallbackContext callbackContext) {
        switch (action) {
            case "toast":
                toast(args);
                break;
            case "hello":
                hello(args, callbackContext);
                break;
            case "startSplash":
                startSplash(args, callbackContext);
                break;
            case "interval":
                interval(args, callbackContext);
                break;
            case "clearInterval":
                clearInterval(args, callbackContext);
            default:
                break;
        }

    }

    private void clearInterval(JSONArray args, CallbackContext callbackContext) {
        mHandler.removeCallbacksAndMessages(null);
    }

    private int count;

    private void interval(JSONArray args, CallbackContext callbackContext) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                count++;
                toast("hello..." + count);
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, count);
                // 是否继续回调,当count小于10时，一直回调，即保持活性
                pluginResult.setKeepCallback(count < 10);
                callbackContext.sendPluginResult(pluginResult);
                mHandler.postDelayed(this, 1000);
            }
        }, 1000);

    }

    private void startSplash(JSONArray args, CallbackContext callbackContext) {
        Activity activity = cordova.getActivity();
        activity.startActivity(new Intent(activity, SplashActivity.class));
    }

    private void hello(JSONArray args, CallbackContext callbackContext) {
        String message = args.optString(0);
        toast(message);
        if (message.startsWith("jin")) {
            callbackContext.success("大哥威武！！");
        } else {
            callbackContext.error("这是什么。。。");
        }
    }

    private void toast(JSONArray args) {
        String message = args.optString(0);
        Toast.makeText(cordova.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void toast(String message) {
        Log.e(TAG, message);
        Toast.makeText(cordova.getContext(), message, Toast.LENGTH_SHORT).show();

    }

    // 发送消息给其他的组件
//     webView.postMessage("splashscreen", "hide");
    @Override
    public Object onMessage(String id, Object data) {
        // 接受其他组件发送的消息
        return super.onMessage(id, data);
    }
}
