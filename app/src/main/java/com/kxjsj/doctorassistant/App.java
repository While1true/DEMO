package com.kxjsj.doctorassistant;

import android.app.Application;
import android.util.Log;

import com.kxjsj.doctorassistant.Constant.Constance;
import com.kxjsj.doctorassistant.Screen.AdjustUtils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


/**
 * Created by vange on 2017/9/6.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        new Thread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }).start();

        AdjustUtils.Adjust(this, AdjustUtils.TYPE_DP);


//        RongIM.init(this);
//        RongIM.connect("Yx+kCyO3t8FeMOWbvLwoy1Fxw0DW8w3nZrGY+2LJ4XXkSJIiJ+BLdiPGuUTtq3dI4XI+kll6MXY=", new RongIMClient.ConnectCallback() {
//            @Override
//            public void onTokenIncorrect() {
//                if (Constance.DEBUGTAG)
//                    Log.i(Constance.DEBUG, "onTokenIncorrect: ");
//            }
//
//            @Override
//            public void onSuccess(String s) {
//                if (Constance.DEBUGTAG)
//                    Log.i(Constance.DEBUG, "onSuccess: "+s);
//
//            }
//
//            @Override
//            public void onError(RongIMClient.ErrorCode errorCode) {
//                if (Constance.DEBUGTAG)
//                    Log.i(Constance.DEBUG, "onError: "+errorCode.getMessage());
//            }
//        });
    }

    private void init() {

    }
}
