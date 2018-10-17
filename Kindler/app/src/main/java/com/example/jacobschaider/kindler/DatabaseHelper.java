package com.example.jacobschaider.kindler;

import android.widget.ArrayAdapter;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DatabaseHelper {
    public FirebaseAuth fb;
    public DatabaseHelper(FirebaseAuth fb) {
        this.fb = fb;
    }

    public ArrayList<Post> getPostsOfUser() {
        ArrayList<Post> listOfPosts = new ArrayList<Post>();
        //interact with database
        return listOfPosts;
    }
}
