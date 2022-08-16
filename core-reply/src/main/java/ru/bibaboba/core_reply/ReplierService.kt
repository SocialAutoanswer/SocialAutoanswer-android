package ru.bibaboba.core_reply

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import ru.bibaboba.core_android.TAG

class ReplierService : NotificationListenerService() {

    //private lateinit var model : NotificationReplierModel

    override fun onCreate() {
        super.onCreate()
        //model = NotificationReplierModel(this)
        Log.d(TAG, "service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "service started")
        return START_STICKY
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        Log.d(TAG, "notification posted")
        //model.sendNotificationReply(sbn!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "service destroyed")
    }
}