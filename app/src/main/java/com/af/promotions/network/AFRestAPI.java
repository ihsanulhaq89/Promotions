package com.af.promotions.network;

import android.util.Log;

import com.af.promotions.interfaces.RestAPIListener;
import com.af.promotions.dtos.PromotionsResponse;
import com.af.promotions.network.services.PromotionsService;
import com.af.promotions.utils.AppConstants;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AFRestAPI implements Callback<PromotionsResponse> {
    private final RestAPIListener listener;
    private PromotionsService service;

    public AFRestAPI(RestAPIListener listener) {
        this.listener = listener;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();

        service = retrofit.create(PromotionsService.class);
    }

    public void getPromotions() {
        Call<PromotionsResponse> call = service.getPromotions();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<PromotionsResponse> call, Response<PromotionsResponse> response) {
        int statusCode = response.code();
        PromotionsResponse responseObj = response.body();
        Log.e(this.getClass().getSimpleName(), response.toString());
        listener.onSuccess(responseObj);
    }

    @Override
    public void onFailure(Call<PromotionsResponse> call, Throwable t) {
        listener.onFailure(t.getLocalizedMessage());
    }

}
