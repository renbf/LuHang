package com.dalaiye.luhang.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dalaiye.luhang.ui.user.UserMessageActivity;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;

/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */
public class GTPushIntentService extends GTIntentService {
    private static final String TAG = "GTPushIntentService";

    public GTPushIntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
        Log.d(TAG, "onReceiveServicePid: ");
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        Log.d(TAG, "onReceiveMessageData: ");
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.d(TAG, "onReceiveClientId: " + clientid);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
        Log.d(TAG, "onReceiveOnlineState: ");
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        Log.d(TAG, "onReceiveCommandResult: ");
    }

    @Override
    public void onNotificationMessageArrived(Context context, GTNotificationMessage msg) {
        Log.d(TAG, "onNotificationMessageArrived: ");
    }

    @Override
    public void onNotificationMessageClicked(Context context, GTNotificationMessage msg) {
        Intent intent = new Intent(context, UserMessageActivity.class);
        startActivity(intent);
    }
}
