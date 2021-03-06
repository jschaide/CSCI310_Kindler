package com.example.jacobschaider.kindler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserPostProfile extends AppCompatActivity {
    private Button remove;
    private ListView lv;
    private ArrayList<UserProfile> databaseUsers;
    private ArrayAdapter<UserProfile> listOfUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //From the user's profile page, the user can click on one of the books they have listed and
        //they will see this page. This page shows the book title and picture as well as a list
        //of people who have shown interest in the post. You can click on the user that is interested
        //and will be taken to the FriendsProfile page.


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_post_profile);


        //gets the current post from the intent
        Intent currentI = getIntent();
        final Post currentPost = (Post) currentI.getSerializableExtra("currentPost");



        //display title of book
        TextView title = (TextView) findViewById(R.id.bkTitle);
        title.setText(currentPost.title);

        //get and set image too

        /*************
        //connect to database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("AllPosts/Posts");

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databaseUsers = new ArrayList<UserProfile>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    databaseUsers.add(snapshot.getValue(UserProfile.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
        *********/

        //**************
        // hard coding
        UserProfile user = new UserProfile("jacob@usc.edu", "hello");
        UserProfile user1 = new UserProfile("bri@usc.edu", "pass");

        databaseUsers = new ArrayList<UserProfile>();
        databaseUsers.add(user);
        databaseUsers.add(user1);
        // ************** end of hard coding

        //set the arraylist into the adapter to display the data in the listview
        listOfUsers = new ArrayAdapter<UserProfile>(this, android.R.layout.simple_dropdown_item_1line, databaseUsers);
        lv = (ListView) findViewById(R.id.listOfInterestedUsers);
        lv.setAdapter(listOfUsers);

        //when you click on a user, it takes you to the FriendsProfile page
        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                UserProfile up = (UserProfile) parent.getItemAtPosition(position);
                Intent i = new Intent(getApplicationContext(), FriendsProfile.class);
                //add extra so the FriendsProfile can know which user profile to display
                i.putExtra("currentUser", up);
                startActivity(i);

            }
        });

        //user wants to delete one of their posts
        remove = (Button) findViewById(R.id.removePost);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove post from database
                //currentPost.removePost();
            }
        });

    }

}
