package com.example.ec_geoowner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.example.ec_geoowner.adapters.OrderAdapter;
import com.example.ec_geoowner.data.Order;
import com.example.ec_geoowner.databinding.FragmentOrdersBinding;

public class Fragment_Orders extends Fragment {

    FragmentOrdersBinding binding;
    public Fragment_Orders() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentOrdersBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Order[] orders = {new Order("a","a",100),
                new Order("b","a",100),
                new Order("c","a",100)};;
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(new OrderAdapter(orders));

    }
}






