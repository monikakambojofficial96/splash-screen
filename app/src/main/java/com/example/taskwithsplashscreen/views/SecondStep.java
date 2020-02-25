package com.example.taskwithsplashscreen.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.taskwithsplashscreen.R;
import com.example.taskwithsplashscreen.database.DatabaseHelper;
import com.example.taskwithsplashscreen.utils.Constants;

import java.util.ArrayList;

public class SecondStep extends AppCompatActivity implements View.OnClickListener {
    private DatabaseHelper myDb;
    public String HOBBIES;
    public String pAddress,mobileNumber;
    public EditText etAddress,etOfficeAddress,etMobileNumber;
    public CheckBox cbBooks,cbNews,cbSports,cbMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_second_step );

        ArrayList <String> selectionCheckBox=new ArrayList<String> ( );

        etAddress = findViewById ( R.id.activity_second_step_txt_address );
        etOfficeAddress = findViewById ( R.id.activity_second_step_txt_office );
        etMobileNumber = findViewById ( R.id.activity_second_step_txt_mobile );
        cbBooks = findViewById ( R.id.activity_second_step_et_booksreading );
        cbNews = findViewById ( R.id.activity_second_step_et_newspaper );
        cbSports = findViewById ( R.id.activity_second_step_et_sports );
        cbMovies = findViewById ( R.id.activity_second_step_et_movies );
        findViewById ( R.id.activity_second_step_et_next ).setOnClickListener ( this );
        myDb = new DatabaseHelper ( this );


    }

    private boolean checkValidate(){
        boolean checkvalidate=true;


        if ( etAddress.getText ().toString ().isEmpty ()) {

            etAddress.requestFocus ();
            etAddress.setError ( "Please Enter Your Address" );

        } else if (etOfficeAddress.getText ().toString ().isEmpty ()) {

            etOfficeAddress.requestFocus ();
            etOfficeAddress.setError ( "Please Enter Your Office Address" );

        } else if ( etMobileNumber.getText ().toString ().isEmpty ()) {

            etMobileNumber.requestFocus ();
            etMobileNumber.setError ( "Please Enter Your Mobile Number" );
        } else if ( etMobileNumber.getText ().toString ().length () < 10) {

            etMobileNumber.requestFocus ();
            etMobileNumber.setError ( "Please enter your valid mobile number" );
        }

        return  checkvalidate;
    }

    public void onCheckboxClicked(View view) {

//        String checkboxSelected;
//
//        for (int i = 0; i < CheckBox.Items.Count; i++)
//        {
//            if (CBLList.Items[i].Selected == true)
//            {
//                checkboxSelected += CBLList.Items[i].Value + ",";
//            }
//        }



        boolean checked = ((CheckBox) view).isChecked ();

        ArrayList <String> selectionCheckBox=new ArrayList<String> ( );


        switch (view.getId ()) {
            case R.id.activity_second_step_et_booksreading:

                if(checked){


                    selectionCheckBox.add("booksreading");
                }
                else
                {
                    selectionCheckBox.remove ( "booksreading" );
                }

                break;
            case R.id.activity_second_step_et_newspaper:
                if(checked){


                    selectionCheckBox.add("newspaper");
                }
                else
                {
                    selectionCheckBox.remove ( "newspaper" );
                }

                break;
            case R.id.activity_second_step_et_sports:
                if(checked){


                    selectionCheckBox.add("sports");
                }
                else
                {
                    selectionCheckBox.remove ( "sports" );
                }

                break;
            case R.id.activity_second_step_et_movies:
                if(checked){


                    selectionCheckBox.add("movies");
                }
                else
                {
                    selectionCheckBox.remove ( "movies" );
                }

                break;

//
//        switch (view.getId ()) {
//            case R.id.activity_second_step_et_booksreading:
//               if(checked){
//                   HOBBIES="booksreading";
//               }
//
//                break;
//            case R.id.activity_second_step_et_newspaper:
//
//                if (checked){
//                    HOBBIES+="newspaper";
//                }
//                break;
//            case R.id.activity_second_step_et_sports:
//               if(checked){
//                   HOBBIES+="sports";
//               }
//                break;
//            case R.id.activity_second_step_et_movies:
//               if(checked){
//                   HOBBIES+="movies";
//               }
//                break;
       }
    }


    @Override
    public void onClick(View v) {

        switch(v.getId ()){

            case R.id.activity_second_step_et_next:


                String checkboxResult = "selected hobies";
                if (cbBooks.isChecked ()) {
                    checkboxResult += "\n Books Reading";
                }
                if (cbNews.isChecked ()) {
                    checkboxResult += "\n NewsPaper Reading ";
                }

                if (cbSports.isChecked ()) {
                    checkboxResult += "\n Sports";
                }
                if (cbMovies.isChecked ()) {
                    checkboxResult += "\n Movies";
                }
                Toast.makeText ( getApplicationContext (), checkboxResult, Toast.LENGTH_SHORT ).show ();

                if(checkValidate ()){

                    String name = "", email = "",password="", city = "", gender = "";
                    Bundle bundle = getIntent ().getExtras ();
                    if (bundle != null) {
                        name = bundle.getString ( Constants.USERNAME );
                        email = bundle.getString ( Constants.USEREMAIL );
                        password = bundle.getString ( Constants.PASSWORD );
                        city = bundle.getString ( Constants.USERCITY );
                        gender = bundle.getString ( Constants.GENDER );

                        mobileNumber=etMobileNumber.getText ().toString ();
                        pAddress=etAddress.getText ().toString ();


                        boolean inserted = myDb.insertData ( name, mobileNumber, email,password, pAddress, city, gender );

                        if (inserted) {
                            Toast.makeText ( SecondStep.this, "data inserted successfully", Toast.LENGTH_SHORT ).show ();

                        } else {
                            Toast.makeText ( SecondStep.this, "data not inserted", Toast.LENGTH_SHORT ).show ();
                        }

                        boolean insertFinal= myDb.insertFinal ( email, HOBBIES );


                        if (insertFinal) {
                            Toast.makeText ( SecondStep.this, "data inserted successfully", Toast.LENGTH_SHORT ).show ();

                        } else {
                            Toast.makeText ( SecondStep.this, "data not inserted", Toast.LENGTH_SHORT ).show ();
                        }


                        Intent intent = new Intent ( SecondStep.this, MyOtpActivity.class );
                        startActivity ( intent );

                    }
                }
        }

    }
}
