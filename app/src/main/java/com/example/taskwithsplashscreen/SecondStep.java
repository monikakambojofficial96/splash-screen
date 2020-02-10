package com.example.taskwithsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondStep extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_step);

        Button next=findViewById(R.id.next);
        final EditText addrss=findViewById(R.id.address);
        final EditText oAddress=findViewById(R.id.office);
        final EditText mobile=findViewById(R.id.mobile);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String pAddress,office,mobileNumber;

                pAddress=addrss.getText().toString();
                office=oAddress.getText().toString();
                mobileNumber=mobile.getText().toString();


                if(pAddress.length()==0){

                    addrss.requestFocus();
                    addrss.setError("Please Enter Your Address");

                }

                else if(office.length()==0){

                    oAddress.requestFocus();
                    oAddress.setError("Please Enter Your Office Address");

                }

                else if(mobileNumber.length()==0){

                    mobile.requestFocus();
                    mobile.setError("Please Enter Your Mobile Number");
                }

                else if(mobileNumber.length()<10){

                    mobile.requestFocus();
                    mobile.setError("Please enter your valid mobile number");
                }

                else {

                    Toast.makeText(SecondStep.this,"Submitted Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SecondStep.this, MyOtpActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
