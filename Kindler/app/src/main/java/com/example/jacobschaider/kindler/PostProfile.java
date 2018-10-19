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

        Intent currentI = getIntent();
        final Post currentPost = (Post)currentI.getSerializableExtra("currentPost");

        TextView bookTitle = (TextView) findViewById(R.id.bookTitle);
        bookTitle.setText(currentPost.title);

        Button interested = (Button) findViewById(R.id.interested);
        if(currentPost.sell) {
            //set the button to say BUY
        }
        else if (currentPost.exchange) {
            //set button to say EXCHANGE
        }

        interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change color
                if (currentPost.sell) {
                    Intent i = new Intent(getApplicationContext(), Sale.class);
                    i.putExtra("seller", currentPost.owner);
                    i.putExtra("currentPost", currentPost);
                    startActivity(i);
                }
                else {

                }
            }
        });
    }
}
