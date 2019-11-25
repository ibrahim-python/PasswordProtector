package com.example.passwordprotector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.passwordprotector.database.Master;
import com.example.passwordprotector.database.MasterRepository;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class RegisterActivity extends AppCompatActivity {

    EditText master1,master2;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        master1 = (EditText) findViewById(R.id.enter_master_password);
        master2 = (EditText) findViewById(R.id.enter_master_password1);
        b2 = (Button) findViewById(R.id.enter_button1);
        final MasterRepository mRepo = new MasterRepository(getApplication());


        final Intent newpage = new Intent(this, PassWordActivity.class);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(master1.getText().toString().equals(master2.getText().toString()) && !TextUtils.isEmpty(master1.getText())) {

                    String masterpass = master1.getText().toString();
                    byte[] salt = SecureMasterPassword.getSalt();
                    String securedPassword = SecureMasterPassword.getSecuredpassword(masterpass,salt);
                    Master master = new Master(securedPassword);
                    mRepo.insert(master);
                    startActivity(newpage);
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
