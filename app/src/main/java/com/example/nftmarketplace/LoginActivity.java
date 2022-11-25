package com.example.nftmarketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
{
    TextView registerNow;
    Button signInButton;
    EditText userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerNow = findViewById(R.id.registerNow);

        registerNow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(LoginActivity.this, Registration.class));
            }
        });

        signInButton = findViewById(R.id.signInButton);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);

        signInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if("Mounia".equalsIgnoreCase(userName.getText().toString()) && "nizar".equalsIgnoreCase(password.getText().toString()))
                {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Invalid Username or password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}