package com.example.jacobschaider.kindler;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreationPost extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    protected static ImageView ivImage;
    private static int RESULT_LOAD_IMG = 1;
    int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    // this is me adding random shit so I can re commit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button button = (Button) findViewById(R.id.PostBookButton);
        button.setOnClickListener(this);
        Button photoButton = (Button) findViewById(R.id.SelectPhotoButton);
        photoButton.setOnClickListener(this);
    }

    public void sendFeedback() {
        final EditText titleField = (EditText) findViewById(R.id.EditTextName);
        String name = titleField.getText().toString();

        final Spinner SpinnerFeedback = (Spinner) findViewById(R.id.SpinnerFeedback);
        String postType = SpinnerFeedback.getSelectedItem().toString();

        final EditText tagsField = (EditText) findViewById(R.id.tags);
        String fullTags = tagsField.getText().toString();
        String tagsNoWhiteSpace = fullTags.replaceAll("\\s","");

        String [] separateTags = tagsNoWhiteSpace.split(",");

        Post post = new Post();
        if (postType.equals("Sell")) {
            post.sell = true;
            post.exchange = false;
        } else {
            post.sell = false;
            post.exchange = true;
        }
        post.title = name;
        UserProfile owner = new UserProfile(firebaseAuth.getCurrentUser().getEmail(), firebaseAuth.getCurrentUser().getUid());
        post.owner = owner;
        post.addBookPost();
        post.addBookTags(separateTags);
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(CreationPost.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            private DialogInterface dialog;
            private int item;

            @Override
            public void onClick(DialogInterface dialog, int item) {
                this.dialog = dialog;
                this.item = item;

                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);

                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent (
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ivImage.setImageBitmap(bm);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ivImage.setImageBitmap(thumbnail);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.PostBookButton:
                sendFeedback();
                break;
            case R.id.SelectPhotoButton:
                selectImage();
                break;
        }
        System.out.println("Clicked success");
    }
}

