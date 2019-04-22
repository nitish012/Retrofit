package com.example.retrofit;

import android.app.Service;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MultipartActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    String TAG = "tag";
    private String path;
    ImageView mimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multipart);
        editText = findViewById(R.id.edittext);
        button = findViewById(R.id.button);
    }


    public void upload(View view) {
        if (path!=null)
        {
            uploadFile(path);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            path=uriToPath(uri);

        }
    }

    private void uploadFile(String path) {

        Log.i(TAG, "uploadFile: "+path);
        // RequestBody descriptionpart=RequestBody.create(MultipartBody.FORM,editText.getText().toString());
        File file = new File(path);

       /* RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);


        // RequestBody filepart=RequestBody.create(MediaType.parse(Objects.requireNonNull(getContentResolver().getType(fileuri)),file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody);

        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ResponseBody> call = api.upload(body,description);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("TAG",String.valueOf(response.code()));
                Log.i("Response","Response "+response);
                Toast.makeText(MultipartActivity.this, "successfully uploaded", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.i("TAG","Failed");
                Toast.makeText(MultipartActivity.this, "retry", Toast.LENGTH_SHORT).show();
            }
        });
*/


        RequestBody fileReqBody = RequestBody.create(MediaType.parse("*/*"), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part part = MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody);
        //Create request body with text description and text media type
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");
        //
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        Call<ResponseBody> call = api.upload(part, description);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("Response","Response "+response);

                try {
                    String resp=response.body().string();

                    JSONObject jsonObject=new JSONObject(resp);
                    String uri =jsonObject.getString("url");
                    Log.i(TAG, "uri: "+uri);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.i(TAG,"Failure "+t.getMessage());
            }
        });



    }

    private String uriToPath(Uri fileuri) {
        String[] proj = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getContentResolver().query(fileuri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        Log.i("TAG", cursor.getString(column_index));
        return cursor.getString(column_index);
    }

    public void selectImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 2);
    }
}
