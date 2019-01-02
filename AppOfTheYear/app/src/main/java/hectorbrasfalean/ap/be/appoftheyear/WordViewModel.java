package hectorbrasfalean.ap.be.appoftheyear;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;
    private LiveData<List<Food>> mAllWords;

    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Food>> getAllWords() { return mAllWords; }

    public void insertWord(Food food) { mRepository.insertWord(food); }

    public void deleteAll() {mRepository.deleteAll();}

}
