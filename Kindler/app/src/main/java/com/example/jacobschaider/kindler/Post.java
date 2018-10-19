package com.example.jacobschaider.kindler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Post implements Serializable {
    public boolean exchange;
    public boolean sell;
    public String title;
    public UserProfile owner;
    public int price;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef = database.getReference();

    public Post() {
        exchange = false;
        sell = false;
    }

    public Post(String title, UserProfile owner, int price) {
        exchange = false;
        sell = false;
        this.price = price;
        this.owner = owner;
    }

    public void addBookPost() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        myRef.child(firebaseUser.getUid()).child("AllPosts").child("Posts").setValue(this);
//        DatabaseReference postsRef = database.getReference().child("AllPosts");
//        postsRef.setValue(this);
        //postsRef.child(this.owner).setValue(this);

    }

    public void removePost() {
        //implement
    }

    public void showInterest() {
        //implement

    }
}
