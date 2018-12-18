package hectorbrasfalean.ap.be.appoftheyear;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FoodInformation extends AppCompatActivity {
    private TextView foodNameDisplay,dailyAmountDisplay,totalAmountDisplay,notificationDisplay;
    private WordDao mWordDao;
    private int totalAmount;
    private int dailyAmount;
    private int notificationAmount;
    private String foodName;
    private Food currentFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WordRoomDatabase db = WordRoomDatabase.getDatabase(getApplication());
        mWordDao = db.wordDao();
        setContentView(R.layout.activity_food_information);
        Intent i = getIntent();
        foodName = i.getStringExtra("foodName");
        foodNameDisplay = findViewById(R.id.textViewFood);
        dailyAmountDisplay = findViewById(R.id.editTextDagelijksVerbruik);
        totalAmountDisplay = findViewById(R.id.editTextTotalAmount);
        notificationDisplay = findViewById(R.id.editTextNotification);

        // Get data
        currentFood = mWordDao.getFoodByName(foodName);
        dailyAmount = currentFood.getDailyAmount();
        totalAmount = currentFood.getTotalAmount();
        notificationAmount = currentFood.getNotificationAmount();

        foodNameDisplay.setText(foodName);
        dailyAmountDisplay.setText(Integer.toString(dailyAmount));
        totalAmountDisplay.setText(Integer.toString(totalAmount));
        notificationDisplay.setText(Integer.toString(notificationAmount));

    }
    @Override
    protected void onDestroy() {
        if(totalAmountDisplay.getText().toString().trim().length() > 0){
            currentFood.setDailyAmount(Integer.parseInt(dailyAmountDisplay.getText().toString()));
            currentFood.setNotificationAmount(Integer.parseInt(notificationDisplay.getText().toString()));
            currentFood.setTotalAmount(Integer.parseInt(totalAmountDisplay.getText().toString()));
            mWordDao.updateFoodTotalAmount(currentFood);
        }
        else{
            Toast.makeText(
                    getApplicationContext(),
                    "Waardes van legen velden werden niet opgeslagen.",
                    Toast.LENGTH_LONG).show();
        }
        super.onDestroy();
    }

    public void DecreaseTotalAmount(View view) {

        if(totalAmount > 0){

            if(totalAmountDisplay.getText().toString().trim().length() > 0){
                totalAmount = Integer.parseInt(totalAmountDisplay.getText().toString());
                currentFood.setTotalAmount(--totalAmount);
                mWordDao.updateFoodTotalAmount(currentFood);
                totalAmountDisplay.setText(Integer.toString(totalAmount));
            }
            else{
                totalAmountDisplay.setError("Het veld mag niet leeg zijn, vul een waarde in.");
            }
        }
    }

    public void IncreaseTotalAmount(View view) {
            totalAmount = Integer.parseInt(totalAmountDisplay.getText().toString());
            currentFood.setTotalAmount(++totalAmount);
            mWordDao.updateFoodTotalAmount(currentFood);
            totalAmountDisplay.setText(Integer.toString(totalAmount));
    }
}
