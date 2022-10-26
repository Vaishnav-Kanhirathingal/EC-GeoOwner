package com.example.ec_geoowner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ec_geoowner.databinding.ActivityOtpVerificationBinding;

public class OtpVerification extends AppCompatActivity {

    ActivityOtpVerificationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOtpVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 25-10-2022 Check if otp is correct
                startActivity(new Intent(OtpVerification.this,MainActivity.class));
            }
        });
    }
}