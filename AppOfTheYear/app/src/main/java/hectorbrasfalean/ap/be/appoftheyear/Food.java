package hectorbrasfalean.ap.be.appoftheyear;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table")
public class Food {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "food")
    private String food;
    @ColumnInfo(name = "dailyAmount")
    private double dailyAmount = 0;
    @ColumnInfo(name = "totalAmount")
    private double totalAmount = 0;
    @ColumnInfo(name = "notificationAmount")
    private int notificationAmount = 0;
    @ColumnInfo(name = "dailyDecrease")
    private boolean dailyDecrease;

    public Food(@NonNull String food) {this.food = food;}

    public double getDailyAmount() {return this.dailyAmount;}

    public double getTotalAmount() {return this.totalAmount;}

    public String getFood(){return this.food;}

    public void setDailyAmount(double dailyAmount) {
        this.dailyAmount = dailyAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getNotificationAmount() {
        return notificationAmount;
    }

    public void setNotificationAmount(int notificationAmount) {
        this.notificationAmount = notificationAmount;
    }

    public boolean getDailyDecrease() {
        return dailyDecrease;
    }

    public void setDailyDecrease(boolean dailyDecrease) {
        this.dailyDecrease = dailyDecrease;
    }
}




