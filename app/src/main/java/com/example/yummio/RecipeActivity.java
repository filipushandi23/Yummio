package com.example.yummio;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yummio.Global.Global;
import com.example.yummio.model.Dessert;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class RecipeActivity extends AppCompatActivity {

    private TextView textView;
    private SimpleExoPlayer mPlayer;
    private SimpleExoPlayerView mPlayerView;

    private NotificationManager mNotificationManager;

    private static MediaSessionCompat mMediaSession;
    private PlaybackStateCompat.Builder mStateBuilder;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();
        String dessertName = intent.getStringExtra("dessertName");
        textView = findViewById(R.id.recipe_title);
        textView.setText(dessertName);

        String userAgent = Util.getUserAgent(this, "Yummio");

        Bundle data = getIntent().getExtras();
        Dessert dessert = (Dessert) data.getParcelable("dessert");
        //Dessert dessert = Global.g_loadedDessert;

        if(dessert != null){

            textView.setText(dessert.getName());

//        //create media source
//

            if (mPlayer == null) {
                //create player
                TrackSelector tmpTrackSelector = new DefaultTrackSelector();
                LoadControl tmpLoadControl = new DefaultLoadControl();

                //link player
                mPlayer = ExoPlayerFactory.newSimpleInstance(this, tmpTrackSelector, tmpLoadControl);

                mPlayerView = findViewById(R.id.player_view);
                mPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground));

                mPlayerView.setPlayer(mPlayer);
                MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(dessert.getSteps().get(0).getVideoURL()),
                        new DefaultDataSourceFactory(this, userAgent),
                        new DefaultExtractorsFactory(),
                        null,
                        null);

//        //load media source
                mPlayer.prepare(mediaSource);

                mPlayer.setPlayWhenReady(true);
            }
        }
    }
}

