package com.example.ec_geoowner.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ec_geoowner.R;
import com.example.ec_geoowner.data.ItemOrdered;


public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.holder> {

    ItemOrdered[] itemsOrdered;

    public OrderItemAdapter(ItemOrdered[] itemsOrdered) {
        this.itemsOrdered = itemsOrdered;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_order_item, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.productName.setText(itemsOrdered[position].getItemName());
        String temp = itemsOrdered[position].getQunatity() + "x" + itemsOrdered[position].getPrice();
        holder.quantPrice.setText(temp);

    }

    @Override
    public int getItemCount() {
        return itemsOrdered.length;
    }


    public class holder extends RecyclerView.ViewHolder {
        TextView productName, quantPrice;

        public holder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.item_name_txt);
            quantPrice = itemView.findViewById(R.id.quant_price);
        }
    }
}