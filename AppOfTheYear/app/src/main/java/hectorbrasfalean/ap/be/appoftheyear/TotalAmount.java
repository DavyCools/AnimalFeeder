package hectorbrasfalean.ap.be.appoftheyear;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table")
public class TotalAmount {

        @NonNull
        @PrimaryKey
        @ColumnInfo(name = "amount")
        private int mAmount;

        public TotalAmount(@NonNull int amount) {this.mAmount = amount;}

        public int getAmount(){return this.mAmount;}
}
