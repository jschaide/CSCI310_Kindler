package com.example.jacobschaider.kindler;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Post implements Serializable {
    public boolean exchange;
    public boolean sell;
    public String title;
    public UserProfile owner;
    public int price;

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    public Post(String title, UserProfile owner, int price) {
        exchange = false;
        sell = false;
        this.price = price;
        this.owner = owner;
    }

    public void addBookPost() {

        DatabaseReference postsRef = database.getReference().child("AllPosts");
        postsRef.setValue(this);
        //postsRef.child(this.owner).setValue(this);

    }

    public void removePost() {
        //implement
    }
    public void showInterest() {
        //implement
    }
}
