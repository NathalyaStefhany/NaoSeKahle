package com.TESTE.TXappteste225205252;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MapsService {
//    @GET("maps/api/geocode/json?latlng={latitude},{longitude}&key=AIzaSyBsEyr2iu6cxZ2p03yWzOcLnfyPdfoDxUE")
//    Call<Location> getLocation(@Query("latitude") String latitude, @Query("longitude") String longitude);
    @GET("maps/api/geocode/json")
    Call<Location> getLocation(@Query("latlng") String coordinates, @Query("key") String key);
}
