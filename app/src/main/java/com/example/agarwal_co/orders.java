package com.example.agarwal_co;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.agarwal_co.Details.order;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ViewHolder.CompanyViewHolder;

public class orders extends AppCompatActivity {
     private DatabaseReference companyref;
     private RecyclerView recyclerView;
     RecyclerView.LayoutManager layoutManager;
     private ProgressDialog loadingBar;
     private String savecurrentdate;
     ArrayList list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        recyclerView=(RecyclerView) findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //ArrayList<String> lsit=new ArrayList<>();

        companyref = FirebaseDatabase.getInstance().getReference().child("Order");
    }

    @Override
    protected void onStart()
    {
        super.onStart();
            FirebaseRecyclerOptions<order> options =
                    new FirebaseRecyclerOptions.Builder<order>()
                            .setQuery(companyref, order.class)
                            .build();

            FirebaseRecyclerAdapter<order, CompanyViewHolder> adapter =
                    new FirebaseRecyclerAdapter<order, CompanyViewHolder>(options) {
                        @Override
                        protected void onBindViewHolder(@NonNull CompanyViewHolder holder, int position, @NonNull final order model) {
                            holder.companyname.setText(model.getCompany_name()+"        "+model.getAdmin_conform()+"        "+model.getDate());
                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(orders.this, details.class);
                                    intent.putExtra("orderid", model.getOrderid());
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }

                        @NonNull
                        @Override
                        public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company, parent, false);
                            CompanyViewHolder holder = new CompanyViewHolder(view);
                            return holder;
                        }
                    };
            recyclerView.setAdapter(adapter);
            adapter.startListening();


    }
}

