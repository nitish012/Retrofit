package com.example.retrofit;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MultipartHeaderActivity extends AppCompatActivity {

    Button upload;
    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multipart_header);
        upload=findViewById(R.id.upload);

    }

    private void putMultipart(String path) {

        File file = new File(path);

        RequestBody fileReqBody = RequestBody.create(MediaType.parse("*/*"), file);

        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileReqBody);

        RequestBody type = RequestBody.create(MediaType.parse("text/plain"), "IMAGE");
        Map<String,RequestBody> map=new HashMap<>();
        map.put("type",type);


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.HEADER_URL)
                .client(getCustomClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

//        Map<String,String> headers=new HashMap<>();
//        headers.put("Content-Type","application/json");
//        headers.put("language","en");
//        //headers.put("Bearer", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVjYTFhYzFhYzg3YmVjMzhlOGJlZDQwZiIsInRva2VuVHlwZSI6IlVTRVIiLCJkZXZpY2VJZCI6IjE1MDI1MzhiYTYzY2RjMTQiLCJ0aW1lc3RhbXAiOjE1NTQ4MDcwMzI1ODksImlhdCI6MTU1NDgwNzAzMn0.S68PL3CGZLTbOJ-OdZE9HR_L7bjYXL0Q8MfAW9cnIuw");
//        headers.put("Bearer","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVjYTFhYzFhYzg3YmVjMzhlOGJlZDQwZiIsInRva2VuVHlwZSI6IlVTRVIiLCJkZXZpY2VJZCI6IjE1MDI1MzhiYTYzY2RjMTQiLCJ0aW1lc3RhbXAiOjE1NTQ4MTUzMTE4MTQsImlhdCI6MTU1NDgxNTMxMX0.INUIxOWw_HUoySuOkRSWwRVD6pHiRLjG4trl4BXb634");
//        headers.put("api_key","1234");

        Call<ResponseBody> call=api.uploadMultipart(map,part);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.i("tag", "onResponse: "+response);
                Log.i("tag", "onResponse: "+response.code());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.i("tag", "onFailure: ");
            }
        });

    }

    private OkHttpClient getCustomClient() {

        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(new ResponseInterceptor())
                .build();
        return okHttpClient;

    }

    private class ResponseInterceptor implements Interceptor{


        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {

            Request request=chain.request();
            request.newBuilder()
                    .header("Content-Type","application/json")
                    .header("language","en")
                    .header("Bearer","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVjYTFhYzFhYzg3YmVjMzhlOGJlZDQwZiIsInRva2VuVHlwZSI6IlVTRVIiLCJkZXZpY2VJZCI6IjE1MDI1MzhiYTYzY2RjMTQiLCJ0aW1lc3RhbXAiOjE1NTQ4MTUzMTE4MTQsImlhdCI6MTU1NDgxNTMxMX0.INUIxOWw_HUoySuOkRSWwRVD6pHiRLjG4trl4BXb634")
                    .header("api_key","1234")
                    .build();

            return chain.proceed(request);
        }
    }

    public void selectImage(View view) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            path=uriToPath(uri);

        }
    }

    private String uriToPath(Uri fileuri) {
        String[] proj = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getContentResolver().query(fileuri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        Log.i("TAG", cursor.getString(column_index));
        return cursor.getString(column_index);
    }

    public void uploadImage(View view) {

        putMultipart(path);
    }
}
