package com.example.passwordprotector.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface MasterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Master master);


    @Query("SELECT COUNT(*) FROM master_table")
    int getCount();
    @Query("SELECT master FROM MASTER_TABLE WHERE id=1 ")
    String getMainpassword();
}
