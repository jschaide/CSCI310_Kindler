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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;
import java.util.ArrayList;

public class profile extends AppCompatActivity {
    private Button post;
    private String usernameString;
    private Uri profilePic;
    private ArrayList<Post> databasePosts;
    private ArrayAdapter<Post> ListedPosts;
    private ListView lv;
    private FirebaseAuth fAuth;
   // private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //connect to database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("AllPosts/Posts");

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databasePosts = new ArrayList<Post>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                   databasePosts.add(snapshot.getValue(Post.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });


        //Get username from database
        fAuth = FirebaseAuth.getInstance();
        usernameString = fAuth.getCurrentUser().getDisplayName();
        TextView textView = (TextView) findViewById(R.id.username);
        textView.setText(usernameString);

        //get profile pic from database
        profilePic = fAuth.getCurrentUser().getPhotoUrl();
        //this is wrong
        ImageView imageView = (ImageView) findViewById(R.id.profilepic);
        imageView.setImageURI(profilePic);


        //Get all the posts that the user has listed from database
        //set the list view to the new adapter
        ListedPosts = new ArrayAdapter<Post>(this, android.R.layout.simple_dropdown_item_1line, databasePosts);
        lv = (ListView) findViewById(R.id.listOfPosts);
        lv.setAdapter(ListedPosts);

        //when you click the post button, an intent is made and the screen will go to the post
        // activity
        post = (Button) findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreationPost.class);
                startActivity(i);
            }
        });


    }
}
