package com.example.jacobschaider.kindler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sale extends AppCompatActivity {
    //this page is shown when a user buys a book from someone. It will ask them to confirm the price
    //and then it will show the contact info and remove the post from the list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);

        Intent currentI = getIntent();
        UserProfile seller = (UserProfile)currentI.getSerializableExtra("seller");
        final Post currentPost = (Post)currentI.getSerializableExtra("currentPost");

        //Set congrats and contact info to invisible until the customer confirms
        final TextView congrats = (TextView) findViewById(R.id.congrats);
        congrats.setVisibility(View.INVISIBLE);
        final TextView contact = (TextView) findViewById(R.id.Username);
        contact.setVisibility(View.INVISIBLE);
        final Button okay = (Button) findViewById(R.id.okay);
        okay.setVisibility(View.INVISIBLE);

        //display the price and title of the book for the customer to confirm
        TextView bookTitle = (TextView) findViewById(R.id.bookTitle);
        bookTitle.setText(currentPost.title);
        TextView price = (TextView) findViewById(R.id.price);
        price.setText("$" + currentPost.price);

        final Button confirm = (Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change color of button and show contact info
                congrats.setVisibility(View.VISIBLE);
                contact.setVisibility(View.VISIBLE);
                okay.setVisibility(View.VISIBLE);
                //remove the post from the database
                okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //go to homepage
                        //Intent i = new Intent(getApplicationContext(), Homepage.class);
                        //startActivity(i);
                    }
                });
            }
        });
        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //return to previous page - PostProfile
                Intent i = new Intent(getApplicationContext(), PostProfile.class);
                i.putExtra("currentPost", currentPost);
                startActivity(i);

            }
        });
    }
}
