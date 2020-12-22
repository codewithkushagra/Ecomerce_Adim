package com.example.agarwal_co;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agarwal_co.Details.order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class details extends AppCompatActivity {
    private ProgressDialog loadingBar;
    private TextView order1,order2,order3,order4,order5,order6,companyname,order7,order8,order9,order10,order11,order12;
    private Button conform,decline;
    private String orderidof;
    private ProgressDialog loading;
    DatabaseReference conformupdate;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(details.this, orders.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        companyname=(TextView) findViewById(R.id.textView5);
        order1=(TextView) findViewById(R.id.textView6);
        order2=(TextView) findViewById(R.id.textView7);
        order3=(TextView) findViewById(R.id.textView8);
        order4=(TextView) findViewById(R.id.textView9);
        order5=(TextView) findViewById(R.id.textView10);
        order6=(TextView) findViewById(R.id.textView11);
        order7=(TextView) findViewById(R.id.textView12);
        order8=(TextView) findViewById(R.id.textView13);
        order9=(TextView) findViewById(R.id.textView14);
        order10=(TextView) findViewById(R.id.textView16);
        order11=(TextView) findViewById(R.id.textView18);
        order12=(TextView) findViewById(R.id.textView19);
        conform=(Button) findViewById(R.id.button);
        decline=(Button) findViewById(R.id.button2);
        conform=(Button) findViewById(R.id.button);
        decline=(Button) findViewById(R.id.button2);
        orderidof=getIntent().getStringExtra("orderid");
        generateorder();

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = new ProgressDialog(details.this);
                loading.setTitle("Declining order");
                loading.setMessage("Please wait");
                loading.setCanceledOnTouchOutside(false);
                loading.show();
                conformupdate = FirebaseDatabase.getInstance().getReference("Order");
                conformupdate.child(orderidof).setValue(null);
                Intent intent = new Intent(details.this, orders.class);
                startActivity(intent);
                finish();
            }
        });

        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = new ProgressDialog(details.this);
                loading.setTitle("Placing order");
                loading.setMessage("Please wait...");
                loading.setCanceledOnTouchOutside(false);
                loading.show();
                conformupdate = FirebaseDatabase.getInstance().getReference("Order");
                conformupdate.child(orderidof).child("admin_conform").setValue("Yes");
                Toast.makeText(details.this, "Order Accepted", Toast.LENGTH_SHORT).show();
                loading.dismiss();
                Intent intent = new Intent(details.this, orders.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void generateorder()

    {
        final DatabaseReference RoofRef;
        RoofRef = FirebaseDatabase.getInstance().getReference();
        RoofRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Order").child(orderidof).exists()) {
                    order orderdata = dataSnapshot.child("Order").child(orderidof).getValue(order.class);
                    companyname.setText(orderdata.getCompany_name());
                    if (!(orderdata.getChakki_atta20()==""))
                    {
                        order1.setText("Chakki Atta 50Kg- " + orderdata.getChakki_atta50());
                    }
                    else
                    {
                        order1.setText("Chakki Atta 50Kg-  0" );
                    }
                    if (!(orderdata.getChakki_atta20()==""))
                    {
                        order2.setText("Chakki Atta 20Kg- " + orderdata.getChakki_atta20());
                    }
                    else
                    {
                        order2.setText("Chakki Atta 20Kg-  0");
                    }
                    if (!(orderdata.getMaida50()==""))
                    {
                        order3.setText("Maida 50Kg- " + orderdata.getMaida50());
                    }
                    else
                    {
                        order3.setText("Maida 50Kg-  0");
                    }
                    if (!(orderdata.getMaida20()==""))
                    {
                        order4.setText("Maida 20Kg- " + orderdata.getMaida20());
                    }
                    else
                    {
                        order4.setText("Maida 20Kg-  0");
                    }
                    if (!(orderdata.getSuji50()==""))
                    {
                        order5.setText("Suji 50Kg- " + orderdata.getSuji50());
                    }
                    else
                    {
                        order5.setText("Suji 50Kg-  0");
                    }
                    if (!(orderdata.getSuji20()==""))
                    {
                        order6.setText("Suji 20Kg- " + orderdata.getSuji20());
                    }
                    else
                    {
                        order6.setText("Suji 20Kg-  0");
                    }
                    if (!(orderdata.getChakki_attan50()==""))
                    {
                        order7.setText("Chakki Atta 50Kg- " + orderdata.getChakki_attan50());
                    }
                    else
                    {
                        order7.setText("Chakki Atta 50Kg-  0" );
                    }
                    if (!(orderdata.getChakki_attan20()==""))
                    {
                        order8.setText("Chakki Atta 20Kg- " + orderdata.getChakki_attan20());
                    }
                    else
                    {
                        order8.setText("Chakki Atta 20Kg-  0");
                    }
                    if (!(orderdata.getMaidan50()==""))
                    {
                        order9.setText("Maida 50Kg- " + orderdata.getMaidan50());
                    }
                    else
                    {
                        order9.setText("Maida 50Kg-  0");
                    }
                    if (!(orderdata.getMaidan20()==""))
                    {
                        order10.setText("Maida 20Kg- " + orderdata.getMaidan20());
                    }
                    else
                    {
                        order10.setText("Maida 20Kg-  0");
                    }
                    if (!(orderdata.getSujin50()==""))
                    {
                        order11.setText("Suji 50Kg- " + orderdata.getSujin50());
                    }
                    else
                    {
                        order11.setText("Suji 50Kg-  0");
                    }
                    if (!(orderdata.getSujin20()==""))
                    {
                        order12.setText("Suji 20Kg- " + orderdata.getSujin20());
                    }
                    else
                    {
                        order12.setText("Suji 20Kg-  0");
                    }

                }
                else {
                    Toast.makeText(details.this, "Unable to load the order", Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
