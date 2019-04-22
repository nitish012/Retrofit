package com.example.retrofit;

public class PatchModal {

    String userid;
    String id;
    String title;
    String body;

    public PatchModal(String userid, String id, String title, String body) {
        this.userid = userid;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public String getUserid() {
        return userid;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
