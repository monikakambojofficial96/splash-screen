package com.example.taskwithsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.taskwithsplashscreen.utils.Constants;


public class LoginActivity extends AppCompatActivity {


    public EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        Button login = findViewById(R.id.login);
        TextView signup = findViewById(R.id.signupnow);
        password = findViewById(R.id.pass);


        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE);
                String uName = preferences.getString(Constants.NAME, user);
                String uPass = preferences.getString(Constants.PASS, pass);

                if (user.length() == 0) {
                    username.requestFocus();
                    username.setError("Please Enter Your User Name");
                } else if (!user.matches("[a-zA-Z0-9 ]+")) {
                    username.requestFocus();
                    username.setError("Enter Valid User Name");
                } else if (pass.length() == 0) {
                    password.requestFocus();
                    password.setError("Please Enter Your Password");
                } else if (!user.equals(uName) || !pass.equals(uPass)) {
                    password.requestFocus();
                    password.setError("user name and password not matched with last credentials ");
                } else {


                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(Constants.DISPLAY, uName);
                    editor.putString(Constants.DISPLAY, uPass);
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }


            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }


        });
    }
}
