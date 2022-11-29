package com.example.nftmarketplace;

import androidx.annotation.Nullable;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nftmarketplace.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity
{
    public static final String MyPREFERENCES = "MyPrefs" ;
    TextView registerNow;
    Button signInButton;
    EditText userName, password;
    DBHelper db;
    SharedPreferences sharedPreferences;
    ImageView google;

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerNow = findViewById(R.id.registerNow);

        db = new DBHelper(this);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);
        google = findViewById(R.id.google);

        google.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                signIn();
            }
        });

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
                    finish();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("userName",loggedUser.getUserName());
                    i.putExtra("email",loggedUser.getEmail());
                    i.putExtra("balance",loggedUser.getBalance());
                    i.putExtra("userLogo",loggedUser.getUserLogo());
                    startActivity(i);
                }
                else
                    Toast.makeText(LoginActivity.this, "Invalid Username or password", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void signIn()
    {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try
            {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            }
            catch (ApiException e)
            {
                Toast.makeText(LoginActivity.this, "Something went wrong, please try again ...", Toast.LENGTH_LONG);
            }
        }
    }

    public void navigateToSecondActivity()
    {
        finish();
        Intent intent = new Intent(LoginActivity.this, GoogleRegistrationActivity.class);
        startActivity(intent);
    }
}