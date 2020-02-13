package com.example.taskwithsplashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.taskwithsplashscreen.utils.Constants;
import java.util.ArrayList;


public class SignupActivity extends AppCompatActivity {

//    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final Spinner city = findViewById(R.id.spinner);
        final EditText uName = findViewById(R.id.Name);
        final EditText upass = findViewById(R.id.pass);
        final EditText email = findViewById(R.id.email);
        RadioGroup radioGroup=findViewById(R.id.radioGroup);
        int selected=radioGroup.getCheckedRadioButtonId();
        final RadioButton gender=findViewById(selected);
//        mydb = new DatabaseHelper ( this );









        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("SELECT CITY");
        arrayList.add("Abohar");
        arrayList.add("Chandigarh");
        arrayList.add("Moga");
        arrayList.add("Ludhiana");
        arrayList.add("Patiala");
        arrayList.add("Jalandhar");
        arrayList.add("Fazilka");
        arrayList.add("Firozpur");
        arrayList.add("Jalandhar");
        arrayList.add("Bathinda");
        arrayList.add("Amritsar");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner, arrayList);
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_spinner);
        city.setAdapter(arrayAdapter);

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Button next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                 String name, password, emailId,genderId="",cityId;

                int selected = radioGroup.getCheckedRadioButtonId();
                if (selected == R.id.male) {

                    Toast.makeText(SignupActivity.this,"male selected",Toast.LENGTH_SHORT).show();
                    genderId="Male";
                } else if (selected == R.id.female) {
                    genderId="Female";
                    Toast.makeText(SignupActivity.this, "female selected", Toast.LENGTH_SHORT).show();
                }

                name = uName.getText().toString();
                password = upass.getText().toString();
                emailId = email.getText().toString();
                cityId=city.getSelectedItem().toString();

                if (name.length() == 0) {
                    uName.requestFocus();
                    uName.setError(" Enter Your Name");
                } else if (!name.matches("[a-zA-Z ]+")) {
                    uName.requestFocus();
                    uName.setError("Enter Only Alphabetical Character");
                }
                if (password.length() == 0) {
                    upass.requestFocus();
                    upass.setError("Enter Your password");
                } else if (!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$")) {
                    upass.requestFocus();
                    upass.setError("Enter Stronger Password");
                }

                if (emailId.length() == 0) {
                    email.requestFocus();
                    email.setError("Enter Your Email Address");
                } else if (!emailId.matches("^([a-zA-Z0-9_\\-.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$+")) {
                    email.requestFocus();
                    email.setError("Invalid Email Address");

                }
                else {
                    SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE);
                    String newUser = uName.getText().toString();
                    String newPass = upass.getText().toString();
                    String newEmail = email.getText().toString();
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString(Constants.NAME, newUser);
                    editor.putString(Constants.PASS, newPass);
                    editor.putString(Constants.EMAIL, newEmail);

                    editor.apply();

                    Bundle bundle=new Bundle();
                    bundle.putString(Constants.USERNAME,name);
                    bundle.putString(Constants.USEREMAIL,emailId);
                    bundle.putString( Constants.USERCITY ,cityId);
                    bundle.putString(Constants.GENDER,genderId);
                    Intent intent = new Intent(SignupActivity.this, SecondStep.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    intent.putExtra(Constants.GENDER, (Parcelable) gender);


                }

            }
        });
    }
}
