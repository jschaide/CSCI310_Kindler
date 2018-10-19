package com.example.jacobschaider.kindler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class PostProfile extends AppCompatActivity {
    //The PostProfile is shown when a user clicks on a book in another users listed books. The current
    //user can view the picture of the book and the title as well as click on show interest or buy.

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_profile);

        //get current post from the intent
        Intent currentI = getIntent();
        final Post currentPost = (Post)currentI.getSerializableExtra("currentPost");

        //set the title of the book
        TextView title = (TextView) findViewById(R.id.bookTitle);
        title.setText(currentPost.title);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        final Button interested = (Button) findViewById(R.id.interested);
        if (currentPost.sell) {
            String buttonName = "Buy";
            interested.setText(buttonName);
        }
        if (currentPost.exchange) {
            String buttonName = "Show Interest";
            interested.setText(buttonName);
        }
        interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPost.showInterest(user);
                //change color
                //change text
            }
        });




    }
}
