package com.example.passwordprotector.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "master_table")
public class Master {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "master")
    private String mMaster;
    public Master(String master) {
        this.mMaster = master;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Integer getId(){return id;}
    public String getMMaster(){return mMaster;}

}
