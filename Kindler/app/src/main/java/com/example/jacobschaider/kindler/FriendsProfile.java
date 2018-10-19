package com.example.jacobschaider.kindler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendsProfile extends AppCompatActivity {
    //This page is when a logged in user views another users page. They can look at the users
    //username and picture as well as the list of books posted. If the user clicks on a book posted
    //by another user, the PostProfile page will load.

    private UserProfile currentUser;
    private ArrayList<Post> databasePosts;
    private ArrayAdapter<Post> ListedPosts;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_profile);
        Intent i = getIntent();
        currentUser = (UserProfile) i.getSerializableExtra("currentUser");

        //HARD CODE
        currentUser = new UserProfile("Jacob", "hello", "jacob@usc.edu");
        String usernameString = currentUser.getUserName();
        Post p1 = new Post();
        p1.title = "Hamlet";
        p1.exchange = true;
        p1.owner = currentUser;
        Post p2 = new Post();
        p2.title = "To Kill a Mockingbird";
        p2.sell =  true;
        p2.owner = currentUser;
        databasePosts.add(p1);
        databasePosts.add(p2);



        TextView textView = (TextView) findViewById(R.id.contact);
        textView.setText(usernameString);

        //Get all the posts that the user has listed from database
        //set the list view to the new adapter
        ListedPosts = new ArrayAdapter<Post>(this, android.R.layout.simple_dropdown_item_1line, databasePosts);
        lv = (ListView) findViewById(R.id.listOfPosts);
        lv.setAdapter(ListedPosts);

        //when user clicks on the book in the list of books, will go to the post's page
        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                Post p = (Post) parent.getItemAtPosition(position);
                Intent i = new Intent(getApplicationContext(), UserPostProfile.class);
                i.putExtra("currentPost", p);
                startActivity(i);
            }
        });
    }
}
