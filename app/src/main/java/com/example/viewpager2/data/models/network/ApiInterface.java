package com.example.viewpager2.data.models.network;


import com.example.viewpager2.data.models.Poost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    //region get
    @GET("posts")
    Call<List<Poost>> getPosts();

    @GET("posts/{postId}")
    Call<Poost> getPostById(
            @Path("postId") String postId
    );

    @POST("posts")
    Call<Poost> setPost(
        @Body Poost post
    );

    @PUT("posts/{putId}")
    Call<Poost> changePutById(
            @Path("putId") int putId,
            @Body Poost poost
    );

    @DELETE("posts/{deleteId}")
    Call<Poost> deleteId(
            @Path("deleteId") int delete
    );
//endregion
}
