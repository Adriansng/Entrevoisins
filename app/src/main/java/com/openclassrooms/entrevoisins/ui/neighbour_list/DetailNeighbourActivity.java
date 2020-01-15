package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.Objects;


public class DetailNeighbourActivity extends AppCompatActivity{
    private NeighbourApiService mApiService;
    private boolean favoriteNeighbour;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        setSupportActionBar( findViewById(R.id.toolbar2));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        mApiService= DI.getNeighbourApiService();

        /* ID */

        int idNeighbour = getIntent().getIntExtra("ID_NEIGHBOUR", 1);

        /* NAME NEIGHBOUR */

        String nameAvatar = getIntent().getStringExtra("NAME_NEIGHBOUR");
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingTollBar);
        collapsingToolbarLayout.setTitle(nameAvatar);

        /* NAME DESCRIPTION */

        TextView nameDescription =  findViewById(R.id.name_description_txt);
        nameDescription.setText(nameAvatar);

        /* URL AVATAR */

        String neighbourAvatar = getIntent().getStringExtra("AVATAR_NEIGHBOUR");
        ImageView avatarNeighbour =  findViewById(R.id.detail_avatar_neighbour);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground);

        Glide.with(this).load(neighbourAvatar).apply(options).into(avatarNeighbour);



        /* BUTTON FAVOR */
        favoriteNeighbour = mApiService.getNeighbours(idNeighbour).getFavorite();
        ImageButton buttonFavor = findViewById(R.id.favory_button);

        if(favoriteNeighbour){
            buttonFavor.setImageDrawable(getResources().getDrawable(R.drawable.yellow_star));
            }else {
            buttonFavor.setImageDrawable(getResources().getDrawable(R.drawable.no_yellow_star));
             }

        buttonFavor.setOnClickListener(v -> {

                if (!favoriteNeighbour) {
                    buttonFavor.setImageDrawable(getResources().getDrawable(R.drawable.yellow_star));
                    mApiService.getNeighbours(idNeighbour).setFavorite(true);
                    favoriteNeighbour =true;


                } else {
                    buttonFavor.setImageDrawable(getResources().getDrawable(R.drawable.no_yellow_star));
                    mApiService.getNeighbours(idNeighbour).setFavorite(false);
                    favoriteNeighbour=false;
                }

        });

    }
}
