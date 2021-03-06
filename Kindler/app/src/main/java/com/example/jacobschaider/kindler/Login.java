package com.example.jacobschaider.kindler;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;

    private ProgressDialog progressDialog;

    private FirebaseAuth fireBaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fireBaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.buttonLogin);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail2);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword2);

        buttonRegister.setOnClickListener(this);
    }

    private void loginUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            //Email empty
            // Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).setVisible(true);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //Password empty
            //Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).setVisible(true);
            return;
        }
//        progressDialog.setMessage("Registering User...");
//        progressDialog.show();

        fireBaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // User is successfully registered and logged in
                            // Start profile activity here
//                            Toast toast = Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT);
//                            toast.show();
                        } else {
                            //Toast.makeText(MainActivity.this, "Registered Failed", Toast.LENGTH_SHORT).setVisible(true);
//                            Toast toast = Toast.makeText(MainActivity.this, "Registered Failed", Toast.LENGTH_SHORT);
//                            toast.show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        loginUser();
    }

}
