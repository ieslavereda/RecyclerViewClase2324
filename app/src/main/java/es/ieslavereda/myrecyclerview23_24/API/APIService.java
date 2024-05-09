package es.ieslavereda.myrecyclerview23_24.API;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

interface APIService {


    @GET
    Call<ResponseBody> getCall(@Url String url, @Header("Authorization") String authHeader);

    @POST
    Call<ResponseBody> postCall(@Url String url,@Header("Authorization") String authHeader, @Body RequestBody data);

    @PUT
    Call<ResponseBody> putCall(@Url String url, @Header("Authorization") String authHeader, @Body RequestBody data);

    @DELETE
    Call<ResponseBody> deleteCall(@Url String url,@Header("Authorization") String authHeader);
}