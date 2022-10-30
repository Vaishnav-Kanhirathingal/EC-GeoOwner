package com.example.ec_geoowner.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ec_geoowner.Bottom_nav_Menus;
import com.example.ec_geoowner.R;
import com.example.ec_geoowner.data.ItemOrdered;
import com.example.ec_geoowner.data.Order;
import com.example.ec_geoowner.databinding.PromtOrderDetailsBinding;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.holder> {
    Order[] orders;
    Context context;

    public OrderAdapter(Order[] orders, Activity context) {
        this.orders = orders;
        this.context=context;
            }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.card_orders_placed,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.orderId.setText(orders[position].getOrderId());
        holder.customer.setText(orders[position].getCustomer());
        holder.totalPrice.setText(String.valueOf(orders[position].getTotalPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //display prompt
                displayDialog(holder);
            }
        });
    }

    private void displayDialog(holder holder) {
        Dialog dialog=new Dialog(context);
        PromtOrderDetailsBinding binding=PromtOrderDetailsBinding.inflate(LayoutInflater.from(context));
        dialog.setContentView(binding.getRoot());
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();
        binding.orderId.setText(orders[holder.getAdapterPosition()].getOrderId());
        ItemOrdered[] ordereds = {new ItemOrdered("a",holder.getAdapterPosition(),100),new ItemOrdered("a",holder.getAdapterPosition(),100),new ItemOrdered("a",holder.getAdapterPosition(),100)};
        binding.rv.setLayoutManager(new LinearLayoutManager(context));
        binding.rv.setAdapter(new OrderItemAdapter(ordereds));
    }

    @Override
    public int getItemCount() {
        return orders.length;
    }

    public class holder extends RecyclerView.ViewHolder{
        TextView customer,orderId,totalPrice;
        public holder(@NonNull View itemView) {
            super(itemView);
            customer=itemView.findViewById(R.id.customer);
            orderId=itemView.findViewById(R.id.orderId);
            totalPrice=itemView.findViewById(R.id.total_price);
        }
    }

}
