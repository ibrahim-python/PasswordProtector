package com.example.passwordprotector.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Master.class}, version = 1)

public abstract class MasterRoomDatabase extends RoomDatabase {
    public abstract MasterDao masterDao();
    private static volatile MasterRoomDatabase INSTANCE;

    static MasterRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MasterRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MasterRoomDatabase.class, "masterpassword_database")
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onCreate (@NonNull SupportSQLiteDatabase db){
                    super.onCreate(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MasterDao mDao;

        PopulateDbAsync(MasterRoomDatabase db) {
            mDao = db.masterDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            return null;
        }
    }
}
