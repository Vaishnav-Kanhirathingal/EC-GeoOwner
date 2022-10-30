package com.example.ec_geoowner.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ec_geoowner.R;
import com.example.ec_geoowner.data.Order;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.holder> {
    Order[] orders;

    public OrderAdapter(Order[] orders) {
        this.orders = orders;
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
