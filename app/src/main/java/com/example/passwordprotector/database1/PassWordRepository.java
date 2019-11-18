package com.example.passwordprotector.database1;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class PassWordRepository {
    private PassWordDao mPassWordDao;
    private LiveData<List<PassWord>> mAllPassWords;
    private static PassWord mPassWord;

    public PassWordRepository(Application application) {
        PassWordRoomDatabase db = PassWordRoomDatabase.getDatabase(application);
        mPassWordDao = db.passwordDao();
        mAllPassWords = mPassWordDao.getAlphabetizedWords();

    }
    public LiveData<List<PassWord>> getAllPassWords() {
        return mAllPassWords;
    }



    public void insert (PassWord word) {
        new insertAsyncTask(mPassWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<PassWord, Void, Void> {

        private PassWordDao mAsyncTaskDao;

        insertAsyncTask(PassWordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PassWord... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void update (PassWord word) {
        new updateAsyncTask(mPassWordDao).execute(word);
    }

    private static class updateAsyncTask extends AsyncTask<PassWord, Void, Void> {

        private PassWordDao mAsyncTaskDao;

        updateAsyncTask(PassWordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PassWord... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    public void delete (PassWord word) {
        new deleteAsyncTask(mPassWordDao).execute(word);
    }

    private static class deleteAsyncTask extends AsyncTask<PassWord, Void, Void> {

        private PassWordDao mAsyncTaskDao;

        deleteAsyncTask(PassWordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PassWord... params) {
            mAsyncTaskDao.deleteWord(params[0]);
            return null;
        }
    }


}

