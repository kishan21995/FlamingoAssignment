package com.app.flamingoassignment.retrofit;


import com.app.flamingoassignment.response.ArtistResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface ApiInterface {


    @GET("https://facesolution.in/dibble/public/api/artist-list")
    Call<ArtistResponse>artistList(@Header("Authorization")String token);
}