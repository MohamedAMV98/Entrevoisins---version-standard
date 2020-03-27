package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.FloatingActionButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class NeighbourProfileActivity extends AppCompatActivity {

    @BindView(R.id.avatar_profile)
    ImageView avatarProfile;
    @BindView(R.id.neighbour_name)
    TextView name;
    @BindView(R.id.neighbour_name2)
    TextView name2;
    @BindView(R.id.localisation)
    TextView adress;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.web)
    TextView web;
    @BindView(R.id.backButton)
    ImageView back;
    @BindView(R.id.favButton)
    FloatingActionButton fav;

    public static String EXTRA_NAME = "name";
    public static String EXTRA_URL = "url";
    public static String EXTRA_MOBILE = "mobile";
    public static String EXTRA_ADRESS = "adress";
    public static String EXTRA_ABOUTME = "aboutMe";
    public static String EXTRA_FAV = "favornot";
    private static boolean favOrNot;


    private String facebookText;
    private String originalUrl;
    private String newUrl;
    private long ID;
    NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_profile);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        this.updateProfileWithIntent();
    }

    /**
     * Load clicked neighbour datas in the profile interface
     * This method is then used in OnCreate
     */
    public void updateProfileWithIntent() {
        /**
         * Get neighbour's data from the intent. We saved datas in it in the ViewAdapter
         * Display neighbour's information
         */
        Intent goProfile = getIntent();
        name.setText(goProfile.getStringExtra(EXTRA_NAME));
        name2.setText(goProfile.getStringExtra(EXTRA_NAME));
        adress.setText(goProfile.getStringExtra(EXTRA_ADRESS));
        mobile.setText(goProfile.getStringExtra(EXTRA_MOBILE));
        favOrNot = goProfile.getBooleanExtra(EXTRA_FAV, false);
        ID = goProfile.getLongExtra("id", 0);

        /**
         * Get neighbour's name and lowercase it then display it
         */
        facebookText = goProfile.getStringExtra(EXTRA_NAME).toLowerCase();
        web.setText("www.facebook.fr/" + facebookText);

        /**
         * Change size of image by replacing characters in the url string
         * Load the image with Picasso library
         */
        originalUrl = goProfile.getStringExtra(EXTRA_URL);
        newUrl = originalUrl.replace("150", "500");
        Picasso.get().load(newUrl).into(avatarProfile);

        if (mApiService.getFavoriteNeighbours().contains(mApiService.getTheNeighbour(ID))) {
            fav.setImageResource(R.drawable.ic_star_white_24dp);
            favOrNot = true;
        }
        else{
                fav.setImageResource(R.drawable.ic_star_border_white_24dp);
                favOrNot = false;
            }
    }

    @OnClick(R.id.backButton)
    public void backToList() {
        finish();
    }

    /**
     * Change FloatingButton image src when clicking on it
     * Add a neighbour to favorite list
     */
    
    @OnClick(R.id.favButton)
    public void changeStar() {
        if(favOrNot){
            mApiService.setNeighbourFav(ID);
            fav.setImageResource(R.drawable.ic_star_border_white_24dp);
            favOrNot = false;
        }
        else {
            mApiService.setNeighbourFav(ID);
            fav.setImageResource(R.drawable.ic_star_white_24dp);
            favOrNot = true;
        }
    }

}
