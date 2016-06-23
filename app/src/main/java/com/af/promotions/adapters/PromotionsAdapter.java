package com.af.promotions.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.af.promotions.R;
import com.af.promotions.models.Promotion;
import com.af.promotions.viewholders.PromotionsHolder;

import java.util.List;


public class PromotionsAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private List<Promotion> mItems;

    public PromotionsAdapter(Context context, List<Promotion> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.card_view, viewGroup, false);
        return new PromotionsHolder(mContext, v);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((PromotionsHolder) viewHolder).invalidate(mItems.get(i));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addAll(List<Promotion> items) {
        mItems = items;
    }

    public List<Promotion> getItems() {
        return mItems;
    }

}
