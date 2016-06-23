package com.af.promotions.network.services;


import com.af.promotions.dtos.PromotionsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PromotionsService {
    @GET("anf/nativeapp/Feeds/promotions.json")
    Call<PromotionsResponse> getPromotions();
}
