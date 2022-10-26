package com.example.ec_geoowner;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ec_geoowner.databinding.ActivityMenusBinding;
import com.google.android.material.navigation.NavigationBarView;

public class Menus extends AppCompatActivity {

    ActivityMenusBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ReplaceFragment(new Update());
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.update:
                        ReplaceFragment(new Update());
                        break;
                    case R.id.orders:
                        ReplaceFragment(new Orders());
                        break;
                    case R.id.profile:
                        ReplaceFragment(new Profile());
                        break;
                }
                return false;
            }
        });
    }

    public void ReplaceFragment(Fragment fragment) {
        // TODO: 22-10-2022 replace each fragment 
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framex, fragment);
        fragmentTransaction.commit();
    }
}