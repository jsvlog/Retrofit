package com.example.retrofit;




import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface JsonPlaceholderApi {

    // because of the url post
    @GET("posts")
    Call<List<Data>> getPost();

    @GET("/posts/{id}/comments")
    Call<List<Data>> getComments(@Path("id") int postID);
}
