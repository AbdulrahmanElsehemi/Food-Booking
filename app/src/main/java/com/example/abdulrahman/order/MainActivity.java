package com.example.abdulrahman.order;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdulrahman.order.Common.Common;
import com.example.abdulrahman.order.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    Button btnSignIn,btnSignUp;
    TextView txtSlogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        btnSignUp=(Button)findViewById(R.id.btnSignUp);
        txtSlogan=(TextView)findViewById(R.id.txtSlogan);
        Typeface face =Typeface.createFromAsset(getAssets(),"fonts/nabila.otf");
        txtSlogan.setTypeface(face);
        Paper.init(this);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignIn.class);
                startActivity(intent);


            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUP.class);
                startActivity(intent);

            }
        });
        //Check remember
        String user=Paper.book().read(Common.USER_KEY);
        String pwd=Paper.book().read(Common.PWD_KEY);
        if (user !=null && pwd !=null)
        {
            if (!user.isEmpty()&& !pwd.isEmpty())
                login(user,pwd);
        }


    }

    private void login(final String phone, final String pwd) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Waiting");
        dialog.show();
        table_user.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Check if user not exist in database
                if (dataSnapshot.child(phone).exists()) {
                    //get User Information
                    dialog.dismiss();
                    User user = dataSnapshot.child(phone).getValue(User.class);
                    user.setPhone(phone); //setPhone
                    if (user.getPassword().equals(pwd)) {
                        //  Toast.makeText(SignIn.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Home.class);
                        Common.currentuser = user;
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(MainActivity.this, "Wrong PassWord", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    dialog.dismiss();

                    Toast.makeText(MainActivity.this, "User not exist in Database", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
