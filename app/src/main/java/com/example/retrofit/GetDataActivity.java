package com.example.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
import retrofit2.http.Body;

public class GetDataActivity extends AppCompatActivity {

    EditText edittext1, edittext2, edittext3, edittext4;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        findId();
        // getData();


    }

    private void findId() {
        edittext1 = findViewById(R.id.edittext1);
        edittext2 = findViewById(R.id.edittext2);
        edittext3 = findViewById(R.id.edittext3);
        edittext4 = findViewById(R.id.edittext4);
        image = findViewById(R.id.im);

    }


    public void submit(View view) {

        getData();
        postData();
         //putData();
        //putPatchData();
        //deleteData();
    }


    private void postData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.POST_URL).addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
//        Post post = new Post( "New Text");
        // Post post=new Post("name5","123","23");
       // Call<ResponseBody> call = api.postHeroes(new Post("red", "60000", "24"));
        Call<ResponseBody> call=api.postHeroes(5,"akhil","121");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String resp = response.body().string();

                    Log.i("tag", "onResponse: " + resp);

                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(resp);

                        String userid=jsonObject.getString("userId");
                        String title=jsonObject.getString("title");
                        String body=jsonObject.getString("body");
                        String id=jsonObject.getString("id");

                        edittext1.setText(userid);
                        edittext2.setText(title);
                        edittext3.setText(body);
                        edittext4.setText(id);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("tag", "onFailure: " + t.getMessage());
            }
        });


    }

    private void deleteData() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);


        Call<ResponseBody> call = api.deleteHeroes(1050);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.i("delete", "onResponse: ");

                if (response.isSuccessful())
                    Toast.makeText(GetDataActivity.this, "data deleted", Toast.LENGTH_SHORT).show();
                try {
                    String resp = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(resp);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }

    private void putPatchData() {

        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.POST_URL).addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);

        final PatchModal patchModal=new PatchModal("1","5",null,"sdd");
        Call<ResponseBody> call = api.pactchHeroes(1050,patchModal);

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.i("tag", "onResponse: ");
                try {

                    String resp = response.body().string();


                    try {
                        JSONObject jsonObject = new JSONObject(resp);
                        String name = jsonObject.getString("name");
                        String salary = jsonObject.getString("salary");
                        String id = jsonObject.getString("id");
                        String age = jsonObject.getString("age");


                        edittext1.setText(name);
                        edittext2.setText(age);
                        edittext3.setText(salary);
                        edittext4.setText(id);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("tag", "onResponse: " + response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.i("tag", "onFailure: ");

                call.cancel();
            }
        });


    }

    private void putData() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<ResponseBody> call = api.putHeroes("1050", new Post("rishabh", "2000", "22"));


        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.i("tag", "onResponse: ");
                try {
                    String resp = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(resp);
                        String name = jsonObject.getString("name");
                        String age = jsonObject.getString("age");
                        String id = jsonObject.getString("id");
                        String salary = jsonObject.getString("salary");


                        edittext1.setText(name);
//                        edittext2.setText(age);
//                        edittext3.setText(salary);
//                        edittext4.setText(id);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("tag", "onResponse: " + response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.i("tag", "onFailure: ");
            }
        });


    }


    private void getData() {


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .build();

        Api api = retrofit.create(Api.class);

       // Call<ResponseBody> call = api.getHeroes();
        Call<ResponseBody> call=api.getpost("1050");
        Log.i("call", "onCreate: " + call);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.i("tag","on Response "+response);
                try {
                    String resp=response.body().string();
                    Log.i("tag", "onResponse: "+resp);
                    Articles articles=new Articles();
                    JSONObject jsonObject= null;

                    jsonObject = new JSONObject(resp);
                    Log.i("tag", "onResponse: "+jsonObject);

                    String status=jsonObject.getString("status");
                    int totalresult=jsonObject.getInt("totalResults");

                    articles.setTotalresult(totalresult);
                    articles.setStatus(status);

                    Log.i("tag", "onResponse: "+status);
                    Log.i("tag", "onResponse: "+totalresult);

                    JSONArray jsonArray=jsonObject.getJSONArray("articles");
                    Log.i("tag", "onResponse: "+jsonArray.length());

                    ArrayList<Articles> list=new ArrayList<>();
                    for(int i=0;i<jsonArray.length();i++){


                        JSONObject obj=jsonArray.getJSONObject(i);
                        JSONObject source=obj.getJSONObject("source");
                        String id=source.getString("id");
                        String title=obj.getString("title");
                        String uri=obj.getString("urlToImage");

                        Glide.with(GetDataActivity.this).load(uri).into(image);

//                        String publishedAt=obj.getString("publishedAt");
                        Log.i("id", "onResponse: "+id);
                        Log.i("title", "onResponse: "+title);
                        articles.setTitle(title);
                        articles.setUri(uri);
                        Articles.Source source1=new Articles.Source();
                        source1.id=id;
                        source1.name=source.getString("name");
                        articles.setSource(source1);

                        list.add(articles);

                    }

                    articles.getArticleslist(list);
                    Log.i("articles", "onResponse: "+articles);


                } catch (IOException e) {
                    e.printStackTrace();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.i("tag", "onFailure: "+t.getMessage());
            }
        });

    }
}

