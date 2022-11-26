package com.example.nftmarketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nftmarketplace.models.User;

public class Registration extends AppCompatActivity
{
    TextView logIn;
    DBHelper db;
    EditText userName, email, password, verifyPassword;
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DBHelper(this);

        logIn = findViewById(R.id.registerNow);
        userName = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        verifyPassword = findViewById(R.id.verifyPassword);
        signInButton = findViewById(R.id.signInButton);

        logIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Registration.this, LoginActivity.class));
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                User user = new User("nizar", "alloul", userName.getText().toString(),password.getText().toString(),
                        email.getText().toString(), 2000,R.drawable.hape);
                if(password.getText().toString().equalsIgnoreCase(verifyPassword.getText().toString()))
                {
                    boolean result = db.insertUser(user);

                    if(result)
                    {
                        Toast.makeText(Registration.this,"Account created succssesfully !", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Registration.this, LoginActivity.class);
                        i.putExtra("username", userName.getText().toString());
                        startActivity(i);
                    }
                    else
                        Toast.makeText(Registration.this,"Account not created !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}