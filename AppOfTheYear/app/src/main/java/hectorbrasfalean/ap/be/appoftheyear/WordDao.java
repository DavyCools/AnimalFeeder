package hectorbrasfalean.ap.be.appoftheyear;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWord(Food food);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFoodTotalAmount(Food food);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY food ASC")
    LiveData<List<Food>> getAllWords();

    @Query("SELECT * from word_table LIMIT 1")
    Food[] getAnyWord();

    @Query("SELECT * from word_table WHERE food = :foodName")
    Food getFoodByName(String foodName);


}
