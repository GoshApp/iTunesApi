package gajendrasinghrathore.itunessongslistfordoubleclients.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import gajendrasinghrathore.itunessongslistfordoubleclients.R;

import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.ARTIST_ID;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.ARTIST_NAME;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.COLLECTION_NAME;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.COUNTRY;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.CURRENCY;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.KIND;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.LINE;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.RELEASE_DATE;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.SPACE;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.TRACK_CENSORED_NAME;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.TRACK_NAME;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.WRAPPER_TYPE;

public class SongDetails extends AppCompatActivity {
    TextView kind, wrappertype, currency, country, collectionname, artistid,
             artistname, trackcensoredname, releasedate, trackname;
    String kindStr, wrappertypeStr, currencyStr, countryStr, collectionnameStr, artistidStr,
             artistnameStr, trackcensorednameStr, releasedateStr, tracknameStr;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        kind = (TextView)findViewById(R.id.kind);
        wrappertype = (TextView)findViewById(R.id.wrapperType);
        currency = (TextView)findViewById(R.id.currency);
        country = (TextView)findViewById(R.id.country);
        collectionname = (TextView)findViewById(R.id.collectionname);
        artistid = (TextView)findViewById(R.id.artistid);
        artistname = (TextView)findViewById(R.id.artistname);
        trackcensoredname = (TextView)findViewById(R.id.trackcensoredname);
        releasedate = (TextView)findViewById(R.id.releasedate);
        trackname = (TextView)findViewById(R.id.trackname);

        kindStr =  getIntent().getExtras().getString(KIND);
        wrappertypeStr = getIntent().getExtras().getString(WRAPPER_TYPE)+ LINE;
        currencyStr = getIntent().getExtras().getString(ARTIST_NAME)+LINE;
        countryStr = getIntent().getExtras().getString(ARTIST_ID)+LINE;
        collectionnameStr = getIntent().getExtras().getString(COLLECTION_NAME)+LINE;
        artistidStr = getIntent().getExtras().getString(TRACK_NAME)+LINE;
        artistnameStr = getIntent().getExtras().getString(RELEASE_DATE)+LINE;
        trackcensorednameStr = getIntent().getExtras().getString(TRACK_CENSORED_NAME)+LINE;
        releasedateStr = getIntent().getExtras().getString(CURRENCY)+LINE;
        tracknameStr = getIntent().getExtras().getString(COUNTRY)+LINE;

        kind.setText(String.format("%s%s%s", KIND, SPACE, kindStr));
        wrappertype.setText(String.format("%s%s%s", WRAPPER_TYPE, SPACE, wrappertypeStr));
        artistname.setText(String.format("%s%s%s", ARTIST_NAME, SPACE, currencyStr));
        artistid.setText(String.format("%s%s%s", ARTIST_ID, SPACE, countryStr));
        collectionname.setText(String.format("%s%s%s", COLLECTION_NAME, SPACE, collectionnameStr));
        trackname.setText(String.format("%s%s%s", TRACK_NAME, SPACE, artistidStr));
        releasedate.setText(String.format("%s%s%s", RELEASE_DATE, SPACE, artistnameStr));
        trackcensoredname.setText(String.format("%s%s%s", TRACK_CENSORED_NAME, SPACE, trackcensorednameStr));
        currency.setText(String.format("%s%s%s", CURRENCY, SPACE, releasedateStr));
        country.setText(String.format("%s%s%s", COUNTRY, SPACE, tracknameStr));
        
    }

}//SongDetails
