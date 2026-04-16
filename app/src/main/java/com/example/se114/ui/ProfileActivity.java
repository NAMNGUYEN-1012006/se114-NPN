package com.example.se114.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.se114.R;
import com.example.se114.data.SessionManager;
import com.example.se114.model.User;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvProfileTitle;
    private EditText edtName, edtEmail, edtAddress, edtAvatarUrl, edtDescription;
    private Button btnSave, btnLogout;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager = new SessionManager(this);

        tvProfileTitle = findViewById(R.id.tvProfileTitle);
        edtName = findViewById(R.id.edtProfileName);
        edtEmail = findViewById(R.id.edtProfileEmail);
        edtAddress = findViewById(R.id.edtProfileAddress);
        edtAvatarUrl = findViewById(R.id.edtProfileAvatar);
        edtDescription = findViewById(R.id.edtProfileDescription);
        btnSave = findViewById(R.id.btnSaveProfile);
        btnLogout = findViewById(R.id.btnLogout);

        loadUserData();

        btnSave.setOnClickListener(v -> saveProfile());
        btnLogout.setOnClickListener(v -> logout());
    }

    private void loadUserData() {
        User user = sessionManager.getUser();
        if (user != null) {
            tvProfileTitle.setText(user.getName() + "!");
            edtName.setText(user.getName());
            edtEmail.setText(user.getEmail());
            edtAddress.setText(user.getAddress());
            edtAvatarUrl.setText(user.getAvatarUrl());
            edtDescription.setText(user.getDescription());
        }
    }

    private void saveProfile() {
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String avatar = edtAvatarUrl.getText().toString().trim();
        String description = edtDescription.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            edtName.setError("Nhập tên");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Nhập email");
            return;
        }

        User user = sessionManager.getUser();
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setAddress(address);
            user.setAvatarUrl(avatar);
            user.setDescription(description);
            sessionManager.saveUser(user);
            Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show();
        }
    }

    private void logout() {
        sessionManager.logout();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}