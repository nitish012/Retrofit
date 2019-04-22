package com.example.retrofit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void getData(View view) {

        Intent intent=new Intent(this,GetDataActivity.class);
        startActivity(intent);
    }

    public void multipart(View view) {

        Intent intent=new Intent(this,MultipartActivity.class);
        startActivity(intent);
    }

    public void multipartHeader(View view) {

        Intent intent=new Intent(this,MultipartHeaderActivity.class);
        startActivity(intent);
    }

    public void demo(View view) {

        Intent intent=new Intent(this,Demo.class);
        startActivity(intent);
    }
}