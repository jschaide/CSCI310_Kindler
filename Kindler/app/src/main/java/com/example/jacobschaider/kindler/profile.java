package com.example.jacobschaider.kindler;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class profile extends AppCompatActivity {
    private Button post;
    private FirebaseAuth fireBaseAuth;
    private String usernameString;
    private Uri profilePic;
    private ArrayAdapter<Post> ListedPosts;
    private ListView lv;
   // private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        //connect to database
        fireBaseAuth = FirebaseAuth.getInstance();
        fireBaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        });

        Post p = new Post();
        p.title = "Harry Potter";
        ListedPosts.add(p);


        //Get username and profile pic from database
        //usernameString = fireBaseAuth.getUsername;
        TextView textView = (TextView) findViewById(R.id.username);
        textView.setText("Natalie Pearson");
        //textView.setText(usernameString);
        //profilePic = fireBaseAuth.getProfilePic;
        //ImageView imageView = (ImageView) findViewById(R.id.profilepic);
       // imageView.setImageURI(profilePic);
        //imageView.setImageURI(https://i.pinimg.com/originals/0a/d2/ec/0ad2ece0cc0c29da963391e5d0e8b521.jpg);

        //Get all the posts that the user has listed from database
        //set the list view to the new adapter
        //ListedPosts = new ArrayAdapter<Post>(this, android.R.layout.simple_dropdown_item_1line, helper.getPostsOfUser());
        lv.setAdapter(ListedPosts);

        //when you click the post button, an intent is made and the screen will go to the post
        // activity
        post =(Button)findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Post.class);
                startActivity(i);
            }
        });



}
