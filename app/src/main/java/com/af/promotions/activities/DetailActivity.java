package com.af.promotions.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.af.promotions.R;
import com.af.promotions.models.Promotion;
import com.af.promotions.utils.AppConstants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

public class DetailActivity extends Activity {

    private Button button;
    private TextView description;
    private ImageView image;
    private Promotion promotion;
    private TextView title;
    private String footer;
    private String footerLink;
    private String target;
    private String buttonTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image = (ImageView) findViewById(R.id.image);
        description = (TextView) findViewById(R.id.description);
        button = (Button) findViewById(R.id.button);
        title = (TextView) findViewById(R.id.title);
        Bundle bundle = getIntent().getExtras();
        promotion = (Promotion) bundle.getSerializable(AppConstants.B_DATA);


        parseFooter();
        parseButton();

        title.setText(promotion.getTitle());

        String largeText = promotion.getDescription();
        if(footer != null){
            largeText += "\n" + footer;
        }

        if(footerLink != null){
            description.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openUrl(footerLink);
                }
            });
        }

        description.setText(largeText);
        button.setText(buttonTitle);
        Picasso.with(this)
                .load(promotion.getImage())
                .into(image);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl(target);
            }
        });
    }

    private void openUrl(String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    private void parseFooter(){
        if(promotion.getFooter() != null) {
            footer = promotion.getFooter().substring(0, promotion.getFooter().indexOf("<a"));
            footerLink = promotion.getFooter().substring(promotion.getFooter().indexOf("http"), promotion.getFooter().indexOf("\" class="));
        }
    }
    private void parseButton(){
        String jsonString = new Gson().toJson(promotion.getButton());
        JsonParser parser = new JsonParser();
        try {

            JsonObject o = parser.parse(jsonString).getAsJsonObject();
            target = o.get("target").getAsString();
            buttonTitle = o.get("title").getAsString();
        }
        catch (Exception e){
            JsonArray array = parser.parse(jsonString).getAsJsonArray();
            JsonObject o = (JsonObject) array.get(0);
            target = o.get("target").getAsString();
            buttonTitle = o.get("title").getAsString();
        }

    }

}
