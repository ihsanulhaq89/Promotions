package com.af.promotions.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.af.promotions.R;
import com.af.promotions.adapters.PromotionsAdapter;
import com.af.promotions.interfaces.RestAPIListener;
import com.af.promotions.models.Promotion;
import com.af.promotions.dtos.PromotionsResponse;
import com.af.promotions.network.AFRestAPI;
import com.af.promotions.utils.AppConstants;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RestAPIListener {

    private RecyclerView recyclerView;
    private PromotionsAdapter mAdapter;

    Snackbar snackbar;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new PromotionsAdapter(this, new ArrayList<Promotion>());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        fetchData();
    }


    private void fetchData() {
        String data = PreferenceManager.getDefaultSharedPreferences(this).getString(AppConstants.B_DATA, null);
        if(data == null){
            findViewById(R.id.progress).setVisibility(View.VISIBLE);
            new AFRestAPI(this).getPromotions();
        } else {
            PromotionsResponse promoObject = new Gson().fromJson(data, PromotionsResponse.class);
            populateData(promoObject);
        }
    }

    @Override
    public void onSuccess(Object responseObj) {
        PromotionsResponse data = (PromotionsResponse) responseObj;
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(AppConstants.B_DATA, data.toString()).commit();
        populateData(data);
    }

    private void populateData(PromotionsResponse data) {
        mAdapter.addAll(data.getPromotions());
        mAdapter.notifyDataSetChanged();
        findViewById(R.id.progress).setVisibility(View.GONE);
        if (snackbar != null) {
            snackbar.dismiss();
            snackbar = null;
        }
    }

    @Override
    public void onFailure(String localizedMessage) {
        findViewById(R.id.progress).setVisibility(View.GONE);
        showMessage("Oops! something went wrong. \nPlease check your internet connection and retry.");
    }

    private void showMessage(String message) {
        if (snackbar != null) {
            snackbar.dismiss();
            snackbar = null;
        }
        snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fetchData();
                    }
                });

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }
}
