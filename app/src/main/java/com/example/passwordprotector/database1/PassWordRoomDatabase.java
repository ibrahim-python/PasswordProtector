package com.example.passwordprotector.database1;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {PassWord.class}, version = 1)

public abstract class PassWordRoomDatabase extends RoomDatabase{
    public abstract PassWordDao passwordDao();
    private static volatile PassWordRoomDatabase INSTANCE;

    static PassWordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PassWordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PassWordRoomDatabase.class, "password_database")
                            .addCallback(sRoomDatabaseCallback)
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

        private final PassWordDao mDao;

        PopulateDbAsync(PassWordRoomDatabase db) {
            mDao = db.passwordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            return null;
        }
    }
}