package com.example.retrofit;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class Articles {


//    private int id;
//    private String name;
    private String author;
    private String title;
    private String status;
    private int totalresult;
    Source source;
    List articleslist;
    String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List getArticleslist(ArrayList<Articles> list) {
        return articleslist;
    }

    public void setArticleslist(List articleslist) {
        this.articleslist = articleslist;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public int getTotalresult() {
        return totalresult;
    }

    public void setTotalresult(int totalresult) {
        this.totalresult = totalresult;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class Source{

        String id;
        String name;
    }
}
