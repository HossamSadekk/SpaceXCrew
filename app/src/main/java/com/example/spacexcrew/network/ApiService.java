package com.example.spacexcrew.network;

import com.example.spacexcrew.response.CrewResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("v4/crew")
    Single<List<CrewResponse>> getCrew();

    @GET("v4/crew")
    Call<List<CrewResponse>> getCreww();

}
