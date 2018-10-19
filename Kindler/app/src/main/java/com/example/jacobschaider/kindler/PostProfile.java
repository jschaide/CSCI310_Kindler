package com.example.jacobschaider.kindler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PostProfile extends AppCompatActivity {
    //The PostProfile is shown when a user clicks on a book in another users listed books. The current
    //user can view the picture of the book and the title as well as click on show interest or buy.

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_profile);
    }
}
