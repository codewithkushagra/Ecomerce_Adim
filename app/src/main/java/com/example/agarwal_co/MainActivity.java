package com.example.agarwal_co;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button done,orders;
    private EditText editText, editText4, editText5, editText3;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orders=(Button) findViewById(R.id.button);
        done = (Button) findViewById(R.id.done);
        editText = (EditText) findViewById(R.id.editText);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        editText3 = (EditText) findViewById(R.id.editText3);
        loadingBar = new ProgressDialog(this);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                createaccount();
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, orders.class);
                startActivity(intent);

            }
        });
    }
    private void createaccount()
    {
        String company_name = editText.getText().toString();
        String gst_number = editText4.getText().toString();
        String password = editText3.getText().toString();
        String phone = editText5.getText().toString();

        if(TextUtils.isEmpty(company_name))
        {
            Toast.makeText(this, "Please Enter your Name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(gst_number))
        {
            Toast.makeText(this, "Please Enter your GST Number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please Enter your Phone Number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Creating Account");
            loadingBar.setMessage("Please wait uploading the details to database");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            Validatephone(company_name, phone, gst_number, password);
        }
    }

    private void Validatephone(final String company_name, final String phone, final String gst_number, final String password)
    {
        final DatabaseReference RoofRef;
        RoofRef = FirebaseDatabase.getInstance().getReference();
        RoofRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(gst_number).exists()))
                {
                    HashMap<String, Object> userdata = new HashMap<>();
                    userdata.put("phone", phone);
                    userdata.put("gst_number", gst_number);
                    userdata.put("company_name", company_name);
                    userdata.put("password", password);
                    RoofRef.child("Users").child(gst_number).updateChildren(userdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this, "Your account has been created", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                editText.setText("");
                                editText3.setText("");
                                editText4.setText("");
                                editText5.setText("");
                            }
                            else
                            {
                                loadingBar.dismiss();
                                Toast.makeText(MainActivity.this, "Chech you Internet connection", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please Enter your Name", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

