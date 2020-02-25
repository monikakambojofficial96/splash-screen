package com.example.taskwithsplashscreen.views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.taskwithsplashscreen.R;

public class MyOtpActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_my_otp);

        final Button btnSubmit=findViewById(R.id.activity_my_otp_btn_submit );
        final EditText etFirstDigit=findViewById(R.id.activity_my_otp_et_one );
        final EditText etSecondDigit =findViewById(R.id.activity_my_otp_et_two );
        final EditText etThirdDigit=findViewById(R.id.activity_my_otp_et_three );
        final EditText etFourthDigit=findViewById(R.id.activity_my_otp_et_four);


        etFirstDigit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(etFirstDigit.getText().length()==1)
                {
                    etFirstDigit.clearFocus();
                    etSecondDigit.requestFocus();
                    etSecondDigit.setCursorVisible(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });


        etSecondDigit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(etSecondDigit.getText().length()==1)
                {
                    etSecondDigit.clearFocus();
                    etThirdDigit.requestFocus();
                    etThirdDigit.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        etThirdDigit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(etThirdDigit.getText().length()==1)
                {
                    etThirdDigit.clearFocus();
                    etFourthDigit.requestFocus();
                    etFourthDigit.setCursorVisible(true);
                }


            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etFourthDigit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(etFourthDigit.getText().length()==1)
                {
                    etFourthDigit.clearFocus();
                    btnSubmit.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyOtpActivity.this,HomeActivity.class);
                startActivity ( intent );

            }
        });

    }

}
