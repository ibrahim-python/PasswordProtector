package com.example.passwordprotector.database;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
public class MasterRepository {
    private MasterDao mMasterDao;

    public MasterRepository(Application application) {
        MasterRoomDatabase db = MasterRoomDatabase.getDatabase(application);
        mMasterDao = db.masterDao();

    }



    public void insert (Master master) {
        new insertAsyncTask(mMasterDao).execute(master);
    }

    private static class insertAsyncTask extends AsyncTask<Master, Void, Void> {

        private MasterDao mAsyncTaskDao;

        insertAsyncTask(MasterDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Master... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    public int getNumFiles() {
        return mMasterDao.getCount();
    }

    public String getMainpassword(){ return mMasterDao.getMainpassword();}

}
