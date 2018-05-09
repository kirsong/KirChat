package chat.kir.com.kirchatandroid.fcm;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;

import chat.kir.com.kirchatandroid.R;

/**
 * Created by 조길성 on 2017. 9. 12..
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    public static int PUSH_MSG_REQUEST=0x041233;
    public static String GOTO_ACTIVITY_INDEX="GOTO_ACTIVITY_INDEX";//0일 때는 메인, 1일 때는 메시지 페이지로 간다

    private int notiID=0;
    /**
     * Called when message is received.
     *
     */
    // [START receive_message]
    private boolean isRunning()
    {
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        // get the info from the currently running task
        List< ActivityManager.RunningTaskInfo > taskInfo = am.getRunningTasks(1);

        ComponentName componentInfo = taskInfo.get(0).topActivity;
        //if  app is running
        if(componentInfo.getPackageName().equalsIgnoreCase(getApplicationContext().getPackageName()))
        {
            return true;
        }

        return false;
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        try {
            // [START_EXCLUDE]
//             There are two types of messages data messages and notification messages. Data messages are handled
//             here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
//             traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
//             is in the foreground. When the app is in the background an automatically generated notification is displayed.
//             When the user taps on the notification they are returned to the app. Messages containing both notification
//             and data payloads are treated as notification messages. The Firebase console always sends notification
//             messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
            // [END_EXCLUDE]

            // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
            Log.d(TAG, "From: " + remoteMessage.getFrom());
            String title = "";
            String message = "";
        }catch (Exception e){}
    }
    // [END receive_message]

    /**
     * 푸시 노출
     * @param title
     * @param message
     */
    private void sendNotification(String title, String message) {

        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, PUSH_MSG_REQUEST /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                ;

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification=notificationBuilder.build();

        notiID +=1;
        notificationManager.notify(0 /* ID of notification */,notification );
    }

}

