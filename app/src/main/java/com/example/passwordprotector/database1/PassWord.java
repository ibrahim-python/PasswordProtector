package com.example.passwordprotector.database1;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "password_table")
public class PassWord {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private byte[] password;
    private String website;



    public PassWord(String username,byte[] password,String website) {
        this.username = username;
        this.password = password;
        this.website = website;

    }


    public void setId(int id) {
        this.id = id;
    }
    public Integer getId(){return id;}
    public String getUsername(){return username;}
    public byte[] getPassword(){return password;}
    public String getWebsite(){return website;}

}

