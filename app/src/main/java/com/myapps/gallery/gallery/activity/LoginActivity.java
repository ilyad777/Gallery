package com.myapps.gallery.gallery.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myapps.gallery.gallery.R;
import com.myapps.gallery.gallery.models.Album;
import com.myapps.gallery.gallery.models.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.anonAuthBtn)
    Button anonimAuth;
    @BindView(R.id.signIn)
    Button signIn;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.newMember)
    CheckBox newMember;
    @BindView(R.id.password)
    EditText password;
    private FirebaseAuth mAuth;

    SharedPreferences sPref;
    static final String UID = "uid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        ButterKnife.bind(this);

        newMember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

    }

    @OnCheckedChanged(R.id.newMember)
    public void onChecked(boolean checked) {
        Log.d("deb", "" + checked);
        signIn.setText(checked ? "Create user" : "Sign In");
    }

    @OnClick({R.id.signIn, R.id.anonAuthBtn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signIn:
                if (isValidation(email.getText().toString(), password.getText().toString()))
                    if (newMember.isChecked())
                        createUser(email.getText().toString(), password.getText().toString());
                    else
                        signIn(email.getText().toString(), password.getText().toString());
                break;
            case R.id.anonAuthBtn:
                signIn("dilv777@ya.ru", "test123");
                break;

        }
    }

    private boolean isValidation(CharSequence email, String password) {
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Email field is empty", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(getApplicationContext(), "Incorrect email", Toast.LENGTH_SHORT).show();
                return false;
            } else if (password.length() < 6) {
                Toast.makeText(getApplicationContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }


    private void putUId(String uid) {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(UID, uid);
        ed.commit();
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("DEBUG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            putUId(user.getUid());

                            Toast.makeText(getApplicationContext(), "Sign in as", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AlbumsActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("DEBUG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("DEBUG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            putUId(user.getUid());

//                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
//                            String userId = mDatabase.push().getKey();
//                            User user1 = new User("RaviN Tamada", "galllery", new ArrayList< Album>());
//                            mDatabase.child(userId).setValue(user1);

                            Toast.makeText(getApplicationContext(), "User Created",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AlbumsActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("DEBUG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), task.getException().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();

    }

}
