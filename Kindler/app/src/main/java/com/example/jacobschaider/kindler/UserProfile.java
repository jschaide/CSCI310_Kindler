package com.example.jacobschaider.kindler;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class UserProfile implements Serializable {
    private String password;
    private String email;
    private ArrayList<Post> booksPosted = new ArrayList<Post>();
   // private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    public UserProfile (String e, String p) {
        this.password = p;
        this.email = e;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public ArrayList<Post> getBooksPosted() {
        return this.booksPosted;
    }

    public void addBookPost(Post post) {
       // DatabaseReference postsRef = database.getReference("/AllPosts");
        //postsRef.child(post.owner).setValue(post);
    }
}
