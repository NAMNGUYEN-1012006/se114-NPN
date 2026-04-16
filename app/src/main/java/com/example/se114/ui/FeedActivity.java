package com.example.se114.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se114.R;
import com.example.se114.data.SessionManager;
import com.example.se114.model.Post;
import com.example.se114.model.User;
import com.example.se114.ui.adapter.PostAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FeedActivity extends AppCompatActivity {

    private EditText edtPostContent;
    private Button btnPost;
    private RecyclerView rvPosts;
    private TextView tvHello;
    private ImageView imgGoProfile;

    private final List<Post> postList = new ArrayList<>();
    private PostAdapter adapter;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        sessionManager = new SessionManager(this);
        if (!sessionManager.isLogin()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        edtPostContent = findViewById(R.id.edtPostContent);
        btnPost = findViewById(R.id.btnPost);
        rvPosts = findViewById(R.id.rvPosts);
        tvHello = findViewById(R.id.tvHello);
        imgGoProfile = findViewById(R.id.imgGoProfile);

        User user = sessionManager.getUser();
        if (user != null) {
            tvHello.setText("Hello, " + user.getName());
        }

        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PostAdapter(postList);
        rvPosts.setAdapter(adapter);

        btnPost.setOnClickListener(v -> createPost());
        imgGoProfile.setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });
    }

    private void createPost() {
        String content = edtPostContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "Enter content", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = sessionManager.getUser();
        String time = new SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.getDefault()).format(new Date());
        
        Post post = new Post(user.getName(), content, time, user.getAvatarUrl());
        postList.add(0, post);
        adapter.notifyItemInserted(0);
        rvPosts.scrollToPosition(0);
        edtPostContent.setText("");
    }
}