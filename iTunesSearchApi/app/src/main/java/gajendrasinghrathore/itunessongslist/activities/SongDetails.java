package gajendrasinghrathore.itunessongslistfordoubleclients.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import gajendrasinghrathore.itunessongslistfordoubleclients.R;

public class SongDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView kind = (TextView)findViewById(R.id.kind);
        TextView wrappertype = (TextView)findViewById(R.id.wrapperType);
        TextView currency = (TextView)findViewById(R.id.currency);
        TextView country = (TextView)findViewById(R.id.country);
        TextView collectionname = (TextView)findViewById(R.id.collectionname);
        TextView artistid = (TextView)findViewById(R.id.artistid);
        TextView artistname = (TextView)findViewById(R.id.artistname);
        TextView trackcensoredname = (TextView)findViewById(R.id.trackcensoredname);
        TextView releasedate = (TextView)findViewById(R.id.releasedate);
        TextView trackname = (TextView)findViewById(R.id.trackname);

        kind.setText(KIND + "  :  " +getIntent().getExtras().getString(KIND));
        wrappertype.setText("wrapperType  :  "+getIntent().getExtras().getString("wrapperType")+"\n");
        artistname.setText(ARTIST_NAME + "  :  "+getIntent().getExtras().getString(ARTIST_NAME)+"\n");
        artistid.setText(ARTIST_ID + "  :  "+getIntent().getExtras().getString(ARTIST_ID)+"\n");
        collectionname.setText(COLLECTION_NAME + "  :  "+getIntent().getExtras().getString(COLLECTION_NAME)+"\n");
        trackname.setText(TRACKNAME + "  :  "+getIntent().getExtras().getString(TRACKNAME)+"\n");
        releasedate.setText(RELEASE_DATE + "  :  "+getIntent().getExtras().getString(RELEASE_DATE)+"\n");
        trackcensoredname.setText(TRACK_CENSORED_NAME + "  :  "+getIntent().getExtras().getString(TRACK_CENSORED_NAME)+"\n");
        currency.setText(CURRENCY + "  :  "+getIntent().getExtras().getString(CURRENCY)+"\n");
        country.setText(COUNTRY + "  :  "+getIntent().getExtras().getString(COUNTRY)+"\n");
        
    }

}
