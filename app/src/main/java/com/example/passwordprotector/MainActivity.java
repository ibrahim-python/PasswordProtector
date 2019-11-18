package com.example.passwordprotector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.passwordprotector.database.MasterRepository;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MasterRepository mRepo = new MasterRepository(getApplication());
        int num = mRepo.getNumFiles();
        if (num > 0){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }


    }
}