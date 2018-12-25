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


        // Moet in een onclickListener maar wss met zo'n een balk om aan en af te zetten
        // via het instellingen menu. Dus is nog niet volledig af

        //Get date
        Calendar calendar = Calendar.getInstance();
        // When the notification will pop up
        calendar.set(Calendar.HOUR_OF_DAY,19);
        calendar.set(Calendar.MINUTE,16);
        // intent
        Intent intent = new Intent(getApplicationContext(),Notification_receiver.class);
        // pendingintent
        PendingIntent broadcast = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        //Alarm service
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),alarmManager.INTERVAL_DAY,broadcast);

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
