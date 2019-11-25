package com.example.passwordprotector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.passwordprotector.database.MasterRepository;

public class LoginActivity extends AppCompatActivity {

    Button b1;
    EditText edit_enter_password;
    String loginPassword, hashPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1 = (Button)findViewById(R.id.enter_button);
        edit_enter_password = (EditText)findViewById(R.id.enter_password);

        final Intent newpage = new Intent(this, PassWordActivity.class);
        final MasterRepository mRepo = new MasterRepository(getApplication());


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPassword = edit_enter_password.getText().toString();
                byte[] salt = SecureMasterPassword.getSalt();
                hashPassword = SecureMasterPassword.getSecuredpassword(loginPassword,salt);
                if(hashPassword.equals(mRepo.getMainpassword())) {
                    startActivity(newpage);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Password",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
