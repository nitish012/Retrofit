package com.example.retrofit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

  //  String BASE_URL="https://newsapi.org/v2/";
   // String BASE_URL="http://dummy.restapiexample.com/api/v1/";

  String BASE_URL="http://dummy.restapiexample.com/api/v1/";
  String POST_URL="https://jsonplaceholder.typicode.com/";
  String HEADER_URL="http://uchatqa.appskeeper.com";
  String DEMO_URL="http://hairinfernodev.appinventive.com/";

  //String BASE_URL="https://vaibhav15.000webhostapp.com/";


//    @GET("everything?q=bitcoin&from=2019-03-04&sortBy=publishedAt&apiKey=0a30fe3ad9a04fc58094a214530513e9")
//    Call<ResponseBody> getHeroes();
//
//  @GET("employee/{id}")
//  Call<ResponseBody> getpost(@Path("id") String id);
//

  @GET("employees")
  Call<ResponseBody> getpost(@Query("id") String id);

//    @POST("create")
//    Call<ResponseBody> postHeroes(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<ResponseBody> postHeroes(@Field("userId") int userId,@Field("title") String title, @Field("body") String body);

    @PUT("update/{id}")
  Call<ResponseBody> putHeroes(@Path ("id") String id,@Body Post post);

  @PATCH("posts/{id}")
  Call<ResponseBody>  pactchHeroes(@Path ("id") int id,@Body PatchModal post);

  @DELETE("delete/{id}")
  Call<ResponseBody> deleteHeroes(@Path("id") int id);



  @Multipart
  @POST("uploads/upload_file.php")
  Call<ResponseBody> upload(
          @Part MultipartBody.Part file,
          @Part("file") RequestBody description);


  @Multipart
  @PUT("/v1/upload/file")
  Call<ResponseBody> uploadMultipart(

         @HeaderMap Map<String,RequestBody> headers,
          @Part MultipartBody.Part file
  );



  @GET("category")
  Call<ResponseBody> getDemo(

          @Header("dynamic_header") String header,
          @Query("type") int type
  );

}
