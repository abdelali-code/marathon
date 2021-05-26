package com.example.marathon_singup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SingupActivity extends AppCompatActivity {
    EditText firstname, lastname, email, phone;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PHONE_NUMBER_REGEX =
            Pattern.compile("^0+[5-7]{1}+[0-9]{8}$", Pattern.CASE_INSENSITIVE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup2);


        Button singupBtn = findViewById(R.id.singup);

        singupBtn.setOnClickListener(view -> {

            System.out.println("singup btn clicked");
           firstname = findViewById(R.id.firstname);
           lastname = findViewById(R.id.lastname);
           email = findViewById(R.id.email);
           phone = findViewById(R.id.phone);
           boolean isValid = CheckAllFields();
           if (isValid) {
                startActivity(new Intent(SingupActivity.this, FelicitationsActivity.class));
           }
        });
    }


    private boolean CheckAllFields() {

        boolean isValid = true;
        if (firstname.getText().length() < 0) {
            firstname.setError("firstname must be greater than 3 and less than 20 character");
            isValid = false;
        }
        if (lastname.getText().length() == 0) {
            lastname.setError("lastname must be greater than 3 and less than 20 character");
            isValid = false;
        }
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText());
        if (email.getText().length() == 0) {
            email.setError("the email is required");
            isValid = false;
        }else if (!matcher.matches()) {
            email.setError("is not a valid email");
            isValid = false;
        }
        Matcher matchPhoneNumber = PHONE_NUMBER_REGEX.matcher(phone.getText());
        if (!matchPhoneNumber.matches()) {
            phone.setError("phone number is not valid");
            isValid = false;
        }

        // after all validation return true.
        return isValid;
    }


}