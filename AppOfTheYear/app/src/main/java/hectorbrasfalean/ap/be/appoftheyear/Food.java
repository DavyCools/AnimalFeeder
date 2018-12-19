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
    private String mWord;
    @ColumnInfo(name = "dailyAmount")
    private int dailyAmount = 0;
    @ColumnInfo(name = "totalAmount")
    private double totalAmount = 0;
    @ColumnInfo(name = "notificationAmount")
    private int notificationAmount = 0;

    public Food(@NonNull String word) {this.mWord = word;}

    public int getDailyAmount() {return this.dailyAmount;}

    public double getTotalAmount() {return this.totalAmount;}

    public String getWord(){return this.mWord;}

    public void setDailyAmount(int dailyAmount) {
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
}




