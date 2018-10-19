package com.example.jacobschaider.kindler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class FriendsProfile extends AppCompatActivity {
    //This page is when a logged in user views another users page. They can look at the users
    //username and picture as well as the list of books posted. If the user clicks on a book posted
    //by another user, the PostProfile page will load.

    private UserProfile currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_profile);

        //get current user from the intent
        Intent i = getIntent();
        currentUser = (UserProfile) i.getSerializableExtra("currentUser");

        //set username to the current user's username
        TextView username = (TextView) findViewById(R.id.username);
        username.setText(currentUser.getUserName());

        //populate the listview with the user's listed posts
        ListView lv = (ListView) findViewById(R.id.listOfPosts);
        ArrayAdapter<Post> arrayAdapter = new ArrayAdapter<Post>(this, android.R.layout.simple_dropdown_item_1line, currentUser.getBooksPosted());
        lv = (ListView) findViewById(R.id.listOfPosts);
        lv.setAdapter(arrayAdapter);

        //when user clicks on the book in the list of books, will go to the post's page
        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                Post p = (Post) parent.getItemAtPosition(position);
                Intent i = new Intent(getApplicationContext(), PostProfile.class);
                i.putExtra("currentPost", p);
                startActivity(i);
            }
        });

    }
}
