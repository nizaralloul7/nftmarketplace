package com.example.nftmarketplace;

import static com.example.nftmarketplace.LoginActivity.MyPREFERENCES;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nftmarketplace.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class GoogleRegistrationActivity extends AppCompatActivity
{
    TextView greetingText, registerNow;
    EditText userName, password, verifyPassword;

    Button signInButton;

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_registration);

        db = new DBHelper(this);

        String email = " ";
        String firstName = " ";
        String lastName = "";

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);

        greetingText = findViewById(R.id.greetingText);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        verifyPassword = findViewById(R.id.verifyPassword);
        signInButton = findViewById(R.id.signInButton);
        registerNow = findViewById(R.id.registerNow);

        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

        if(googleSignInAccount != null)
        {
            email = googleSignInAccount.getEmail();
            firstName = googleSignInAccount.getDisplayName();
            lastName = googleSignInAccount.getFamilyName();

            greetingText.setText("Hello "+firstName +", \n" + "Complete registration below :)");
        }

        String finalFirstName = firstName;
        String finalLastName = lastName;
        String finalEmail = email;
        signInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                User user = new User(finalFirstName, finalLastName, userName.getText().toString(),password.getText().toString(), finalEmail,
                         2000,R.drawable.ryden);
                if(password.getText().toString().equalsIgnoreCase(verifyPassword.getText().toString()))
                {
                    boolean result = db.insertUser(user);

                    if(result)
                    {
                        editor.putString("userName",userName.getText().toString());
                        editor.commit();
                        Toast.makeText(GoogleRegistrationActivity.this,"Account created succssesfully !", Toast.LENGTH_LONG).show();
                        finish();
                        Intent i = new Intent(GoogleRegistrationActivity.this, MainActivity.class);
                        i.putExtra("username", userName.getText().toString());
                        startActivity(i);
                    }
                    else
                        Toast.makeText(GoogleRegistrationActivity.this,"Account not created !", Toast.LENGTH_LONG).show();
                }
            }
        });

        registerNow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(GoogleRegistrationActivity.this, LoginActivity.class));
            }
        });
    }
}