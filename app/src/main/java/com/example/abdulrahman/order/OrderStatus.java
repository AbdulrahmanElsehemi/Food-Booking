package com.example.abdulrahman.order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.abdulrahman.order.Common.Common;
import com.example.abdulrahman.order.Database.Database;
import com.example.abdulrahman.order.InterFace.itemClickListener;
import com.example.abdulrahman.order.Model.Request;
import com.example.abdulrahman.order.ViewHolder.OrederViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderStatus extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference requests;
    FirebaseRecyclerAdapter<Request,OrederViewHolder>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        //firebase
        database=FirebaseDatabase.getInstance();
        requests=database.getReference("Requests");
        recyclerView =(RecyclerView)findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (getIntent()==null)
                      loadOrders(Common.currentuser.getPhone());
        else
                    loadOrders(getIntent().getStringExtra("userPhone"));


    }

    private void loadOrders(String phone) {
        adapter=new FirebaseRecyclerAdapter<Request, OrederViewHolder>(
                Request.class,
                R.layout.order_layout,
                OrederViewHolder.class,
                requests.orderByChild("phone").equalTo(phone)
        ) {
            @Override
            protected void populateViewHolder(OrederViewHolder viewHolder, Request model, int position) {
                viewHolder.txtOrderId.setText(adapter.getRef(position).getKey());
                viewHolder.txtOrderStatus.setText(Common.convertCodeStatus(model.getStatus()));
                viewHolder.txtOrderAddress.setText(model.getAddress());
                viewHolder.txtOrderPhone.setText(model.getPhone());


            }
        };
        recyclerView.setAdapter(adapter);
    }




}
