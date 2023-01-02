package com.example.soruncozuldu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.soruncozuldu.databinding.ActivityLoginBinding;
import com.example.soruncozuldu.databinding.ActivityMainBinding;
import com.example.soruncozuldu.databinding.ActivityPreferenceBinding;

public class preference extends AppCompatActivity {
    ActivityPreferenceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityPreferenceBinding.inflate(getLayoutInflater());


        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding.tekOyuncu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(preference.this,level2x2tek.class));
            }
        });
        binding.ikiOyuncu.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                startActivity(new Intent(preference.this,level2x2.class));

            }
        });


    }


}