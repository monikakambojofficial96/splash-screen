package com.example.taskwithsplashscreen.views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.taskwithsplashscreen.R;



public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUserName,etUserPassword;
    TextView signUp;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_login);
        etUserName=findViewById ( R.id.activity_login_et_user_name );
        etUserPassword=findViewById ( R.id.activity_login_et_password );
        findViewById (R.id.activity_login_tb_sign_up_now).setOnClickListener ( this );
        findViewById  (R.id.activity_login_btn_login).setOnClickListener ( this );


    }

    @Override
    public void onClick(View v) {

        switch (v.getId ()) {

            case R.id.activity_login_tb_sign_up_now:
                Intent intent = new Intent ( LoginActivity.this , SignupActivity.class );
                startActivity ( intent );
                break;
            case R.id.activity_login_btn_login:

                if (etUserName.getText ().toString ().isEmpty ()) {
                    etUserName.requestFocus ();
                    etUserName.setError ( "Please Enter Your User Name" );
                } else if (!etUserName.getText ().toString ().matches ( "[a-zA-Z0-9 ]+" )) {
                    etUserName.requestFocus ();
                    etUserName.setError ( "Enter Valid User Name" );
                } else if (etUserPassword.getText ().toString ().isEmpty ()) {
                    etUserPassword.requestFocus ();
                    etUserPassword.setError ( "Please Enter Your Password" );
                } else {

                    Intent intent1 = new Intent ( LoginActivity.this , HomeActivity.class );
                    startActivity ( intent1 );
                }





        }

    }
}
