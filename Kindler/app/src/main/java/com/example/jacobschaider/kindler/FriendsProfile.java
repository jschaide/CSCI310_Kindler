package com.example.jacobschaider.kindler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FriendsProfile extends AppCompatActivity {
    //This page is when a logged in user views another users page. They can look at the users
    //username and picture as well as the list of books posted. If the user clicks on a book posted
    //by another user, the PostProfile page will load.

    private UserProfile currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_profile);
        Intent i = getIntent();
        currentUser = (UserProfile) i.getSerializableExtra("currentUser");
    }
}
