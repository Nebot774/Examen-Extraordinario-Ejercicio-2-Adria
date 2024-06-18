package com.example.examenfinal.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import com.example.examenfinal.models.*;

public interface PokeAPIService {
    String BASE_URL = "https://pokeapi.co/api/v2/";

    //Endpointpara obtener la lista de items
    @GET("item")
    Call<ItemList> getItemList(@Query("limit") int limit, @Query("offset") int offset);

    //Método para obtener un ítem por su ID
    @GET("item/{id}")
    Call<Item> getItem(@Path("id") int id);


}
