package com.kxjsj.doctorassistant.RongY;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.kxjsj.doctorassistant.Constant.Constance;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by vange on 2017/9/19.
 */

public class RongYunInitialUtils {
    public static void init(Application application) {
        if (Constance.DEBUGTAG)
            Log.i(Constance.DEBUG, "init: ");

        /**
         *
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (application.getApplicationInfo().packageName.equals(getCurProcessName(application))) {
            if (Constance.DEBUGTAG)
                Log.i(Constance.DEBUG, "init: 22222");
            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(application);
        }
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return
     */
    private static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    public static void connect(Context context, String token, final onRongConnectSuccess listener) {
        if (Constance.DEBUGTAG)
            Log.i(Constance.DEBUG, "connect: aaa");
        if (context.getApplicationInfo().packageName.equals(getCurProcessName(context))) {
            if (Constance.DEBUGTAG)
                Log.i(Constance.DEBUG, "connect: aaa2");
            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {

                    Log.d("LoginActivity", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {
                    listener.onSuccess(userid);
                    Log.d("LoginActivity", "--onSuccess" + userid);

                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 *                  http://www.rongcloud.cn/docs/android.html#常见错误码
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                    Log.d("LoginActivity", "--onError" + errorCode);
                }
            });
        }
    }
    /**
     * 判断消息是否是 push 消息
     */
    public static void isReconnect(Activity activity,String token, RongYunInitialUtils.onRongConnectSuccess listener) {

        Intent intent = activity.getIntent();


        //push，通知或新消息过来
        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong")) {

            //通过intent.getData().getQueryParameter("push") 为true，判断是否是push消息
            if (intent.getData().getQueryParameter("push") != null
                    && intent.getData().getQueryParameter("push").equals("true")) {

                RongYunInitialUtils.connect(activity.getApplicationContext(),token,listener);
            } else {
                //程序切到后台，收到消息后点击进入,会执行这里
                if (RongIM.getInstance() == null || RongIM.getInstance().getRongIMClient() == null) {

                    RongYunInitialUtils.connect(activity.getApplicationContext(),token,listener);
                } else {
                    listener.onSuccess(null);
                }
            }
        }
    }
    public interface onRongConnectSuccess {
        void onSuccess(String userid);
    }
}
