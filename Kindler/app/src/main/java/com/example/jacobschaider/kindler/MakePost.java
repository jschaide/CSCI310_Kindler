package com.example.jacobschaider.kindler;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class MakePost extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void sendFeedback(View button) {
        final EditText titleField = (EditText) findViewById(R.id.EditTextName);
        String name = titleField.getText().toString();

        final Spinner SpinnerFeedback = (Spinner) findViewById(R.id.SpinnerFeedback);
        String postType = SpinnerFeedback.getSelectedItem().toString();

        Post post = new Post();
        if (postType.equals("Sell")) {
            post.sell = true;
            post.exchange = false;
        } else {
            post.sell = false;
            post.exchange = true;
        }
        post.title = name;
        //post.owner = firebaseAuth.getCurrentUser().getDisplayName();
        post.addBookPost();
    }
}
