package com.example.taskwithsplashscreen.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taskwithsplashscreen.R;
import com.example.taskwithsplashscreen.utils.Constants;

import java.util.ArrayList;


public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText etUserName, etUserPassword, etUserEmail;
    public RadioButton rbGender;
    public String userName, userPassword, userEmailId, genderId = "", userCityId;
    public  int selected;
    public  Spinner city;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_signup );

        city = findViewById ( R.id.activity_sign_up_et_spinner );
        etUserName = findViewById ( R.id.activity_sign_up_et_name );
        etUserPassword = findViewById ( R.id.activity_sign_up_et_pass );
        etUserEmail = findViewById ( R.id.activity_sign_up_et_email );
        radioGroup = findViewById ( R.id.activity_sign_up_et_radioGroup );


        ArrayList<String> arrayList = new ArrayList<> ();

        arrayList.add ( "SELECT CITY" );
        arrayList.add ( "Abohar" );
        arrayList.add ( "Chandigarh" );
        arrayList.add ( "Moga" );
        arrayList.add ( "Ludhiana" );
        arrayList.add ( "Patiala" );
        arrayList.add ( "Jalandhar" );
        arrayList.add ( "Fazilka" );
        arrayList.add ( "Firozpur" );
        arrayList.add ( "Jalandhar" );
        arrayList.add ( "Bathinda" );
        arrayList.add ( "Amritsar" );

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<> ( this, R.layout.spinner, arrayList );
        arrayAdapter.setDropDownViewResource ( R.layout.dropdown_spinner );
        city.setAdapter ( arrayAdapter );

        findViewById ( R.id.activity_second_step_et_next ).setOnClickListener ( this );
    }

    @Override
    public void onClick(View v) {

        switch (v.getId ()) {

            case R.id.activity_second_step_et_next: {

                selected = radioGroup.getCheckedRadioButtonId ();
                rbGender = findViewById ( selected );

                if (selected == R.id.activity_sign_up_et_male) {

                    Toast.makeText(SignupActivity.this,"male selected",Toast.LENGTH_SHORT).show();
                    genderId="Male";
                } else if (selected == R.id.activity_sign_up_et_female) {
                    genderId="Female";
                    Toast.makeText(SignupActivity.this, "female selected", Toast.LENGTH_SHORT).show();
                }
                if (checkValidation ()) {

                    userName = etUserName.getText().toString().trim ();
                    userPassword = etUserPassword.getText().toString().trim ();
                    userEmailId = etUserEmail.getText().toString().trim ();
                    userCityId =city.getSelectedItem().toString().trim ();

                    Bundle bundle = new Bundle ();
                    bundle.putString ( Constants.USERNAME, userName );
                    bundle.putString ( Constants.USEREMAIL, userEmailId );
                    bundle.putString ( Constants.PASSWORD, userPassword );
                    bundle.putString ( Constants.USERCITY, userCityId );
                    bundle.putString ( Constants.GENDER, genderId );
                    Intent intent = new Intent ( SignupActivity.this, SecondStep.class );
                    intent.putExtras ( bundle );
//                    intent.putExtra(Constants.GENDER, (Parcelable) rbGender);
                    startActivity ( intent );
                }
                break;
            }


        }

    }


    private boolean checkValidation() {
        boolean checkValidation = true;

        if (etUserName.getText ().toString ().trim ().isEmpty ()) {
            etUserName.requestFocus ();
            etUserName.setError ( " Enter Your Name" );
        } else if (etUserPassword.getText ().toString ().trim ().isEmpty ()) {
            etUserPassword.requestFocus ();
            etUserPassword.setError ( "Enter Your User Password" );
        } else if (!etUserPassword.getText ().toString ().matches ( "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$" )) {
            etUserPassword.requestFocus ();
            etUserPassword.setError ( "Enter Stronger Password" );
        } else if (etUserEmail.getText ().toString ().trim ().isEmpty ()) {
            etUserEmail.requestFocus ();
            etUserEmail.setError ( "Enter Your Email Address" );
        } else if (!etUserEmail.getText ().toString ().matches ( "^([a-zA-Z0-9_\\-.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$+" )) {
            etUserEmail.requestFocus ();
            etUserEmail.setError ( "Invalid Email Address" );

        }

        return checkValidation;
    }


}
