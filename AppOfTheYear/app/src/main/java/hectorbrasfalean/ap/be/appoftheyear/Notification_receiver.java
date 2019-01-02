package hectorbrasfalean.ap.be.appoftheyear;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;

public class Notification_receiver extends BroadcastReceiver  {

    private String CHANNEL_ID;
    private boolean isEmpty;
    private int notificationID;
    private ArrayList<String> mAllFoodNames = new ArrayList<>();
    private WordDao mWordDao;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent){

        mAllFoodNames = intent.getStringArrayListExtra("ListFoodNames");
        WordRoomDatabase db = WordRoomDatabase.getDatabase(context.getApplicationContext());
        mWordDao = db.wordDao();

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

        for (String currentFoodName : mAllFoodNames) {
            Food currentFood = mWordDao.getFoodByName(currentFoodName);
            if (currentFood.getDailyDecrease()) {
                if (currentFood.getTotalAmount() == 0)
                    isEmpty = true;
                else if (currentFood.getTotalAmount() < currentFood.getDailyAmount() && !isEmpty) {
                    currentFood.setTotalAmount(0);
                    mWordDao.updateFood(currentFood);
                }
                else if (!isEmpty) {
                    currentFood.setTotalAmount(currentFood.getTotalAmount() - currentFood.getDailyAmount());
                    mWordDao.updateFood(currentFood);
                }
                isEmpty = false;
            }
            if(currentFood.getTotalAmount() == 0){
                NotificationCompat.Builder mNotifyBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context,CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_stock_low)
                        .setContentTitle("Food stock is empty")
                        .setContentText("Food stock for " + currentFoodName + " is empty")
                        .setAutoCancel(true)
                        .setWhen(when)
                        .setContentIntent(pendingIntent);
                notificationManager.notify(notificationID, mNotifyBuilder.build());
                notificationID++;
            }
            else if (currentFood.getTotalAmount() <= currentFood.getNotificationAmount()){
                NotificationCompat.Builder mNotifyBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context,CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_stock_low)
                        .setContentTitle("Food stock low")
                        .setContentText("Food stock for " + currentFoodName + " is low")
                        .setAutoCancel(true)
                        .setWhen(when)
                        .setContentIntent(pendingIntent);
                notificationManager.notify(notificationID, mNotifyBuilder.build());
                notificationID++;
            }
        }

        NotificationCompat.Builder mNotifyBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_daily_notification)
                .setContentTitle("Animals have been fed")
                .setContentText("Food stock has decreased where enabled")
                .setAutoCancel(true)
                .setWhen(when)
                .setContentIntent(pendingIntent);
        notificationManager.notify(notificationID, mNotifyBuilder.build());
        notificationID++;
    }
}
