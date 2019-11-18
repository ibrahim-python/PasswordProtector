package com.example.passwordprotector.database1;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
@Dao
public interface PassWordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PassWord password);

    @Update
    void update(PassWord password);

    @Query("DELETE FROM password_table")
    void deleteAll();

    @Query("SELECT * from password_table ORDER BY password ASC")
    LiveData<List<PassWord>> getAlphabetizedWords();

    @Delete
    void deleteWord(PassWord password);
}
