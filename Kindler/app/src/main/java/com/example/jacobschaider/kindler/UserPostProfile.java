package com.example.jacobschaider.kindler;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserPostProfile extends AppCompatActivity {
    private Button remove;
    private ListView lv;
    private ArrayList<UserProfile> databaseUsers;
    private ArrayAdapter<UserProfile> listOfUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_post_profile);

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

        listOfUsers = new ArrayAdapter<UserProfile>(this, android.R.layout.simple_dropdown_item_1line, databaseUsers);
        lv = (ListView) findViewById(R.id.listOfInterestedUsers);
        lv.setAdapter(listOfUsers);

        remove = (Button) findViewById(R.id.removePost);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove the post from database
            }
        });

    }

}
