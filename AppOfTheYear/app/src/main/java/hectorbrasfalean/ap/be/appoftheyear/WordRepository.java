package hectorbrasfalean.ap.be.appoftheyear;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Food>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    LiveData<List<Food>> getAllWords() {
        return mAllWords;
    }

    public void insertWord (Food food) {
        new insertAsyncTask(mWordDao).execute(food);
    }

    private static class insertAsyncTask extends AsyncTask<Food, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Food... params) {
            mAsyncTaskDao.insertWord(params[0]);
            return null;
        }
    }

    public void deleteAll()  {
        new deleteAllWordsAsyncTask(mWordDao).execute();
    }

    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /*public void getFoodByName(String foodName)  {
        new getFoodByNameAsyncTask(mWordDao).execute(foodName);
    }

    private static class getFoodByNameAsyncTask extends AsyncTask<String, String, Food> {
        private WordDao mAsyncTaskDao;

        getFoodByNameAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Food doInBackground(String... values) {
            return mAsyncTaskDao.getFoodByName(values[0]);
        }

        @Override
        protected void onPostExecute(Food result){
            super.onPostExecute(result);
        }
    }*/

}
