package com.af.promotions.viewholders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.af.promotions.R;
import com.af.promotions.activities.DetailActivity;
import com.af.promotions.models.Promotion;
import com.af.promotions.utils.AppConstants;
import com.squareup.picasso.Picasso;

public class PromotionsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final Context mContext;
    private final ImageView image;
    private final TextView text;

    private Promotion promotion;

    public PromotionsHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        itemView.setOnClickListener(this);
        image = (ImageView) itemView.findViewById(R.id.image);
        text = (TextView) itemView.findViewById(R.id.title);
    }

    @Override
    public void onClick(View view) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.B_DATA, promotion);

        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    public void invalidate(Promotion promotion) {
        this.promotion = promotion;
        text.setText(promotion.getTitle());
        Picasso.with(this.mContext)
                .load(promotion.getImage())
                .into(image);
    }

}
