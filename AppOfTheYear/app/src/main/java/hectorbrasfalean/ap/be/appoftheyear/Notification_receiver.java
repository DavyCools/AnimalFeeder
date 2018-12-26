package hectorbrasfalean.ap.be.appoftheyear;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.arch.lifecycle.LiveData;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.util.List;

public class Notification_receiver extends BroadcastReceiver {
    private WordDao mWordDao;
    private boolean notificationEnabled;
    private List<Food> mAllWords;
    private String CHANNEL_ID;
    @Override
    public void onReceive(Context context, Intent intent) {



        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            CHANNEL_ID = "my_channel_01";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mNotifyBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.drawable.horse)
                .setContentTitle("Animals have been fed")
                .setContentText("Food stock has decreased where enabled")
                .setAutoCancel(true)
                .setWhen(when)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        notificationManager.notify(0, mNotifyBuilder.build());


        //NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //WordRoomDatabase db = WordRoomDatabase.getDatabase(context);
        //mWordDao = db.wordDao();
        //mAllWords = (List<Food>) mWordDao.getAllWords();

        //Intent  repeating_intent = new Intent(context,MainActivity.class);
        //repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //PendingIntent pendingIntent = PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);

        //NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
        //        .setAutoCancel(true)
        //        .setContentIntent(pendingIntent)
        //        .setSmallIcon(android.R.drawable.sym_def_app_icon)
        //        .setContentText("All animals have been fed")
        //        .setContentTitle("Food stock decreased");
        //notificationEnabled = false;
        //for (int i = 0; i < mAllWords.size(); i++) {
        //    if(mAllWords.get(i).getDailyDecrease());
        //        notificationEnabled = true;
        //}
        //if(notificationEnabled)
        //    notificationManager.notify(100,builder.build());

    }
}
