package com.kxjsj.doctorassistant.RongY;

import android.content.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by vange on 2017/9/19.
 */

public class ConversationUtils {
    /**
     * 单人会话
     *
     * @param context
     * @param RongUseId
     * @param title
     */
    public static void startChartSingle(Context context, String RongUseId, String title) {
        if (RongIM.getInstance() != null)
            RongIM.getInstance().startPrivateChat(context, RongUseId, title);
    }

    public static void openChartList(Context context) {
        if (RongIM.getInstance() != null) {
            Map<String,Boolean>supportedConversation=new HashMap<>(5);
            supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false);
            RongIM.getInstance().startConversationList(context,supportedConversation);
        }
    }

    public static void creatGroupChart(String title, List<String> RongIds, RongIMClient.CreateDiscussionCallback callback) {
        if (RongIM.getInstance() != null)
        /**
         *创建讨论组时，mLists为要添加的讨论组成员，创建者一定不能在 mLists 中
         */
            RongIM.getInstance().getRongIMClient().createDiscussion(title, RongIds, callback);
    }
}
