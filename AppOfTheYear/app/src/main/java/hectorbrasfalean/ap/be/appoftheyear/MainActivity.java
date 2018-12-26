package hectorbrasfalean.ap.be.appoftheyear;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerAlarm();

    }

    private void registerAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE, 29);
        calendar.set(Calendar.SECOND, 0);
        Intent intent1 = new Intent(MainActivity.this, Notification_receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void launchCheckVoorraad(View view) {
        Log.d(LOG_TAG,"Ga naar voorraad check");
        Intent intent = new Intent(this, VoorraadCheck.class);
        startActivity(intent);
    }
}
