package com.example.se114.model;

public class Post {
    private String authorName;
    private String content;
    private String date;
    private String avatarUrl;

    public Post(String authorName, String content, String date, String avatarUrl) {
        this.authorName = authorName;
        this.content = content;
        this.date = date;
        this.avatarUrl = avatarUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}