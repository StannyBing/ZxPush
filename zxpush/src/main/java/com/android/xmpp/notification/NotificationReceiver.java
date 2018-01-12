/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.xmpp.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


/**
 * Broadcast receiver that handles push notification messages from the server.
 * This should be registered as receiver in AndroidManifest.xml.
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public final class NotificationReceiver extends BroadcastReceiver {

    private static final String LOGTAG = LogUtil
            .makeLogTag(NotificationReceiver.class);

    public NotificationReceiver() {
    }


    @SuppressWarnings("deprecation")
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LOGTAG, "NotificationReceiver.onReceive()...");
        String action = intent.getAction();
        Log.d(LOGTAG, "action=" + action);

        if (Constants.ACTION_SHOW_NOTIFICATION.equals(action)) {
            PushEntity pushEntity = new PushEntity();
            pushEntity.setNotificationId(intent.getStringExtra(Constants.NOTIFICATION_ID));
            pushEntity.setNotificationApiKey(intent.getStringExtra(Constants.NOTIFICATION_API_KEY));
            pushEntity.setNotificationTitle(intent.getStringExtra(Constants.NOTIFICATION_TITLE));
            pushEntity.setNotificationMessage(intent.getStringExtra(Constants.NOTIFICATION_MESSAGE));
            pushEntity.setNotificationUri(intent.getStringExtra(Constants.NOTIFICATION_URI));
            pushEntity.setNotificationFrom(intent.getStringExtra(Constants.NOTIFICATION_FROM));
            pushEntity.setPacketId(intent.getStringExtra(Constants.PACKET_ID));

            if (PushUtil.getInstance(context).getOnPushListener() != null) {
                PushUtil.getInstance(context).getOnPushListener().onPushReceive(pushEntity);
            } else if (PushUtil.getInstance(context).getDefaultIntent() != null) {
                showNitify(context, pushEntity);
            } else {
                throw new RuntimeException("OnPushListener or DefaultIntent, you need to set up at least one");
            }
        }


    }

    private void showNitify(Context context, PushEntity pushEntity) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent openintent = PushUtil.getInstance(context).getDefaultIntent();
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, openintent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(PushUtil.getInstance(context).getIcon())
                .setTicker("您有新的消息！")
                .setContentTitle(pushEntity.getNotificationTitle().length() == 0 ? PushUtil.getInstance(context).getAppName() : pushEntity.getNotificationTitle())
                .setContentText(pushEntity.getNotificationMessage())
                .setContentIntent(contentIntent)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND);
        mNotificationManager.notify(0, builder.build());
    }

}
