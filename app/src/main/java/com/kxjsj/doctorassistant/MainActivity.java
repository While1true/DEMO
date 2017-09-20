package com.kxjsj.doctorassistant;

import android.os.Bundle;
import android.view.View;

import com.kxjsj.doctorassistant.Component.BaseTitleActivity;

import java.util.HashMap;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;


public class MainActivity extends BaseTitleActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("drfedf");

    }

    public void cc(View v){
        HashMap<String, Boolean> stringBooleanHashMap = new HashMap<>(2);
        stringBooleanHashMap.put(Conversation.ConversationType.PRIVATE.getName(),false);
        stringBooleanHashMap.put(Conversation.ConversationType.GROUP.getName(),false);
        stringBooleanHashMap.put(Conversation.ConversationType.CHATROOM.getName(),false);
        RongIM.getInstance().startConversationList(this,stringBooleanHashMap);
    }

}
