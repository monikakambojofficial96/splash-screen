package com.example.taskwithsplashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taskwithsplashscreen.utils.Constants;

public class SecondStep extends AppCompatActivity {
    DatabaseHelper mydb;
    public String Hobbies;


    final int READING = 1;
    final int NEWS = 2;
    final int SPORTS = 3;
    final int MOVIES = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_second_step );

        final Button next = findViewById ( R.id.next );
        final EditText addrss = findViewById ( R.id.address );
        final EditText oAddress = findViewById ( R.id.office );
        final EditText mobile = findViewById ( R.id.mobile );
        final CheckBox books = findViewById ( R.id.booksreading );
        final CheckBox news = findViewById ( R.id.newspaper );
        final CheckBox sports = findViewById ( R.id.sports );
        final CheckBox movies = findViewById ( R.id.movies );

        mydb = new DatabaseHelper ( this );

        next.setOnClickListener ( new View.OnClickListener () {


            @Override
            public void onClick(View v) {

                final String pAddress, office, mobileNumber;

                pAddress = addrss.getText ().toString ();
                office = oAddress.getText ().toString ();
                mobileNumber = mobile.getText ().toString ();

                String checkboxResult = "selected hobies";
                if (books.isChecked ()) {
                    checkboxResult += "\n Books Reading";
                }
                if (news.isChecked ()) {
                    checkboxResult += "\n NewsPaper Reading ";
                }

                if (sports.isChecked ()) {
                    checkboxResult += "\n Sports";
                }
                if (movies.isChecked ()) {
                    checkboxResult += "\n Movies";
                }
                Toast.makeText ( getApplicationContext (), checkboxResult, Toast.LENGTH_SHORT ).show ();


                if (pAddress.length () == 0) {

                    addrss.requestFocus ();
                    addrss.setError ( "Please Enter Your Address" );

                } else if (office.length () == 0) {

                    oAddress.requestFocus ();
                    oAddress.setError ( "Please Enter Your Office Address" );

                } else if (mobileNumber.length () == 0) {

                    mobile.requestFocus ();
                    mobile.setError ( "Please Enter Your Mobile Number" );
                } else if (mobileNumber.length () < 10) {

                    mobile.requestFocus ();
                    mobile.setError ( "Please enter your valid mobile number" );
                } else {
                    String name = "", email = "", city = "", gender = "";
                    Bundle bundle = getIntent ().getExtras ();
                    if (bundle != null) {
                        name = bundle.getString ( Constants.USERNAME );
                        email = bundle.getString ( Constants.USEREMAIL );
                        city = bundle.getString ( Constants.USERCITY );
                        gender = bundle.getString ( Constants.GENDER );
                    }
                    boolean inserted = mydb.insertData ( name, mobileNumber, email, pAddress, city, gender );

                    if (inserted) {
                        Toast.makeText ( SecondStep.this, "data inserted successfully", Toast.LENGTH_SHORT ).show ();

                    } else {
                        Toast.makeText ( SecondStep.this, "data not inserted", Toast.LENGTH_SHORT ).show ();
                    }

                    boolean insertFinal=mydb.insertFinal ( email, Hobbies );


                    if (insertFinal) {
                        Toast.makeText ( SecondStep.this, "data inserted successfully", Toast.LENGTH_SHORT ).show ();

                    } else {
                        Toast.makeText ( SecondStep.this, "data not inserted", Toast.LENGTH_SHORT ).show ();
                    }


                    Intent intent = new Intent ( SecondStep.this, MyOtpActivity.class );
                    startActivity ( intent );


                }
            }

        } );

    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked ();


        switch (view.getId ()) {
            case R.id.booksreading:
                Hobbies = checked ? "booksreading " : " unselected";
//                boolean insert = mydb.insertHobbies ( READING,Hobbies );
//                if (insert) {
//                    Toast.makeText ( SecondStep.this, "data inserted successfully", Toast.LENGTH_SHORT ).show ();
//
//                } else {
//                    Toast.makeText ( SecondStep.this, "data not inserted", Toast.LENGTH_SHORT ).show ();
//                }


                break;
            case R.id.newspaper:
                Hobbies = checked ? "newspaper " : "unselected";
//                boolean insert1 = mydb.insertHobbies (NEWS, Hobbies );
//                if (insert1) {
//                    Toast.makeText ( SecondStep.this, "data inserted successfully", Toast.LENGTH_SHORT ).show ();
//
//                } else {
//                    Toast.makeText ( SecondStep.this, "data not inserted", Toast.LENGTH_SHORT ).show ();
//                }
                break;
            case R.id.sports:
                Hobbies = checked ? "sports" : "unselected";
//                boolean insert2 = mydb.insertHobbies (SPORTS, Hobbies );
//                if (insert2) {
//                    Toast.makeText ( SecondStep.this, "data inserted successfully", Toast.LENGTH_SHORT ).show ();
//
//                } else {
//                    Toast.makeText ( SecondStep.this, "data not inserted", Toast.LENGTH_SHORT ).show ();
//                }
                break;
            case R.id.movies:
                Hobbies = checked ? "movies " : "unselected";
//                boolean insert3 = mydb.insertHobbies (MOVIES, Hobbies );
//                if (insert3) {
//                    Toast.makeText ( SecondStep.this, "data inserted successfully", Toast.LENGTH_SHORT ).show ();
//
//                } else {
//                    Toast.makeText ( SecondStep.this, "data not inserted", Toast.LENGTH_SHORT ).show ();
//                }
                break;
        }
    }


}
