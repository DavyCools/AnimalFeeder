package hectorbrasfalean.ap.be.appoftheyear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FoodInformation extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_information);
        Intent i = getIntent();
        String foodName = i.getStringExtra("foodName");
        textView = findViewById(R.id.textViewFood);
        textView.setText(foodName);
    }
}
