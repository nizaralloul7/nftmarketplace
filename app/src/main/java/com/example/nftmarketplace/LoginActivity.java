package com.example.nftmarketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nftmarketplace.models.User;

public class LoginActivity extends AppCompatActivity
{
    public static final String MyPREFERENCES = "MyPrefs" ;
    TextView registerNow;
    Button signInButton;
    EditText userName, password;
    DBHelper db;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerNow = findViewById(R.id.registerNow);

        db = new DBHelper(this);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);



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
        userName.setText(getIntent().getStringExtra("username"));
        SharedPreferences.Editor editor = sharedPreferences.edit();

        signInButton.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("Range")
            @Override
            public void onClick(View view)
            {
                boolean isValidUser = db.loginAction(userName.getText().toString(), password.getText().toString());
                if(isValidUser)
                {
                    User loggedUser = db.getUser(userName.getText().toString());
                    editor.putString("userName",userName.getText().toString());
                    editor.commit();
                    Log.i("loggedUser", loggedUser.getUserName());
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("userName",loggedUser.getUserName());
                    i.putExtra("email",loggedUser.getEmail());
                    i.putExtra("balance",loggedUser.getBalance());
                    i.putExtra("userLogo",loggedUser.getUserLogo());
                    startActivity(i);
                    finish();
                }
                else
                    Toast.makeText(LoginActivity.this, "Invalid Username or password", Toast.LENGTH_LONG).show();
            }
        });
    }
}