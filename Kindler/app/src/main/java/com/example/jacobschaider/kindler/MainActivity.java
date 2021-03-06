package com.example.jacobschaider.kindler;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void goToSignupPage(View view) {
        Intent intent = new Intent(this, CreationPost.class);
        startActivity(intent);
    }

    public void goToLoginPage(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
