package com.example.jacobschaider.kindler;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.ArrayList;

public class profile extends AppCompatActivity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Button post;
    private String usernameString;
    private Uri profilePic;
    private ArrayList<Post> databasePosts = new ArrayList<Post>();
    private ArrayAdapter<Post> ListedPosts;
    private ListView lv;
    private FirebaseAuth fAuth;
   // private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //This is the logged in user's profile page. They can see their username and picture as well
        //as all the books they have currently listed. If a book is clicked on, the UserPostProfile
        //page is loaded. The user can also add a post by clicking on the post button, taking them
        //to the CreationPost page.

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /************
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

         //get profile pic from database
         profilePic = fAuth.getCurrentUser().getPhotoUrl();

         //this is wrong
         ImageView imageView = (ImageView) findViewById(R.id.profilepic);
         imageView.setImageURI(profilePic);
         ****************/

        //HARD CODE
        UserProfile me = new UserProfile( "nepearso@usc.edu", "password");
        usernameString = me.getEmail();
        Post p1 = new Post("Harry Potter", me, 5);
        p1.exchange = true;
        Post p2 = new Post("Invisible Man", me, 6);
        p2.sell =  true;
        databasePosts.add(p1);
        databasePosts.add(p2);

        //display username on the screen
        TextView textView = (TextView) findViewById(R.id.Username);
        textView.setText(usernameString);

        //Get all the posts that the user has listed from database
        //set the list view to the new adapter to display data on screen
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
                //add the post in question to the intent so the UserPostProfile page can display it
                i.putExtra("currentPost", p);
                startActivity(i);
            }
        });

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
