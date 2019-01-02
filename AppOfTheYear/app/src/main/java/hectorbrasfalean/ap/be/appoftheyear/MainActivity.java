package hectorbrasfalean.ap.be.appoftheyear;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    private WordViewModel mWordViewModel;
    private ArrayList<String> mAllFoodNames = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        final Observer<List<Food>> mObserver = new Observer<List<Food>>() {
            @Override
            public void onChanged(@Nullable List<Food> foods) {
                registerAlarm(foods);
            }
        };
        mWordViewModel.getAllWords().observe(this,mObserver);

    }

    private void registerAlarm(List<Food> foods) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 25);
        calendar.set(Calendar.SECOND,0);
        Intent intent1 = new Intent(MainActivity.this, Notification_receiver.class);

        for (Food currentFood : foods) {
           mAllFoodNames.add(currentFood.getFood());
        }
        intent1.putExtra("ListFoodNames",mAllFoodNames);
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
