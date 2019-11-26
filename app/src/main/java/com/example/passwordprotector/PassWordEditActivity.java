package com.example.passwordprotector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PassWordEditActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "extra_id";
    public static final String EXTRA_PASSWORD = "password";
    public static final String EXTRA_USERNAME = "username";
    public static final String EXTRA_WEBSITE = "website";


    // Extra for the task ID to be received in the intent
    private static final int DEFAULT_TASK_ID = -1;

    private static final String TAG = PassWordActivity.class.getSimpleName();

    private int mTaskId = DEFAULT_TASK_ID;


    private EditText mEditUsername;
    private EditText mEditPassword;
    private EditText mEditWebsite;

    private Button button;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_passwords);
        mEditUsername = findViewById(R.id.edit_username);
        mEditPassword = findViewById(R.id.edit_password);
        mEditWebsite = findViewById(R.id.edit_website);
        button = findViewById(R.id.button_save);



        Intent receivedIntent = getIntent();
        if (receivedIntent != null && receivedIntent.hasExtra(EXTRA_USERNAME)) {
            button.setText(R.string.button_update);
            mEditUsername.setText(receivedIntent.getStringExtra(EXTRA_USERNAME));
            byte[] password1 =  receivedIntent.getByteArrayExtra(EXTRA_PASSWORD);

            try{
                byte[] originalPassword = PasswordEncryptDecrypt.decrypt(PasswordEncryptDecrypt.generateKey("hehehehe33131"),password1);
                mEditPassword.setText(new String(originalPassword));
                Log.v("origin", "3");


            }
            catch (Exception e) {
                e.printStackTrace();
            }

            mEditWebsite.setText(receivedIntent.getStringExtra(EXTRA_WEBSITE));



            OnSaveButtonClicked();
        }
        else{
            OnSaveButtonClicked();
        }



    }


    private void OnSaveButtonClicked(){
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditUsername.getText()) && TextUtils.isEmpty(mEditPassword.getText()) ) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String username = mEditUsername.getText().toString();
                    String password = mEditPassword.getText().toString();
                    String website = mEditWebsite.getText().toString();
                    byte[] encryptedPassword;

                    replyIntent.putExtra(EXTRA_USERNAME, username);
                    try{
                        encryptedPassword = PasswordEncryptDecrypt.encrypt(PasswordEncryptDecrypt.generateKey("hehehehe33131"),password.getBytes());
                        replyIntent.putExtra(EXTRA_PASSWORD, encryptedPassword);

                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    replyIntent.putExtra(EXTRA_WEBSITE, website);

                    int id = getIntent().getIntExtra(EXTRA_ID, -1);
                    if (id != -1) {


                        replyIntent.putExtra(EXTRA_ID, id);

                    }

                    setResult(RESULT_OK, replyIntent);

                }
                finish();
            }
        });
    }


}



