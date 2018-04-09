package gajendrasinghrathore.itunessongslistfordoubleclients.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import gajendrasinghrathore.itunessongslistfordoubleclients.R;
import gajendrasinghrathore.itunessongslistfordoubleclients.adapter.ArtistAdapter;
import gajendrasinghrathore.itunessongslistfordoubleclients.adapter.CustomListAdapter;
import gajendrasinghrathore.itunessongslistfordoubleclients.pojos.Artist;
import gajendrasinghrathore.itunessongslistfordoubleclients.pojos.Songs;
import gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants;

import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.ARTIST_ID;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.ARTIST_NAME;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.ARTWORK_URL_30;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.COLLECTION_NAME;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.COUNTRY;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.CURRENCY;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.KIND;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.LAST_DATA;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.LINE_CLEAR;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.PRIMARY_GENRE_NAME;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.RELEASE_DATE;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.RESULTS;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.SHARED_PREFERENCES_APPS;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.TRACK_CENSORED_NAME;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.TRACK_NAME;
import static gajendrasinghrathore.itunessongslistfordoubleclients.utilities.Constants.WRAPPER_TYPE;

public class List extends AppCompatActivity {

    private ArrayList<Songs> arrayListSongs;
    private ArrayList<Artist> arrayListArtist;
    private ListView lv;
    private JSONObject songObject;
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private String wrapperType, kind, artistId, artistName, collectionName, trackCensoredName,
    releaseDate, country, currency, trackName;
    private int numberArtist;
    private boolean isSongs = false, isDetails = false;
    private int firstSong;
    private SharedPreferences itunesShPr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);
        arrayListArtist = new ArrayList<>();
        arrayListSongs = new ArrayList<>();
        itunesShPr = getSharedPreferences(SHARED_PREFERENCES_APPS, Context.MODE_PRIVATE);
        lv = (ListView) findViewById(R.id.list);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(isDetails){// если загрузили уже список песен то открываем подробное описание
                    if(numberArtist == 1){
                        firstSong = position + 102;
                    }else {
                        firstSong = position + 1;
                    }
                    getDataForThisPosition(firstSong);
                }else {
                    numberArtist = position; // присваиваем номер выбранного артиста
                    runOnUi();
                }

            }
        });
        // вызываем при первом создании
        runOnUi();

    }//onCreate




    public void runOnUi (){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute(Constants.BASE_URL);
            }
        });
    }

    private void getDataForThisPosition(Integer position) {
        try {
            jsonArray =  jsonObject.getJSONArray(RESULTS);

            songObject = jsonArray.getJSONObject(position);
            kind =  songObject.getString(KIND);
            wrapperType =  songObject.getString(WRAPPER_TYPE);
            trackName = songObject.getString(TRACK_NAME);
            artistId = songObject.getString(ARTIST_ID);
            artistName = songObject.getString(ARTIST_NAME);
            collectionName = songObject.getString(COLLECTION_NAME);
            trackCensoredName = songObject.getString(TRACK_CENSORED_NAME);
            releaseDate = songObject.getString(RELEASE_DATE);
            country = songObject.getString(COUNTRY);
            currency = songObject.getString(CURRENCY);

            Intent intent = new Intent(List.this,SongDetails.class);
            intent.putExtra(KIND,kind);
            intent.putExtra(WRAPPER_TYPE,wrapperType);
            intent.putExtra(TRACK_NAME,trackName);
            intent.putExtra(ARTIST_ID,artistId);
            intent.putExtra(ARTIST_NAME,artistName);
            intent.putExtra(COLLECTION_NAME,collectionName);
            intent.putExtra(TRACK_CENSORED_NAME,trackCensoredName);
            intent.putExtra(RELEASE_DATE,releaseDate);
            intent.putExtra(COUNTRY,country);
            intent.putExtra(CURRENCY,currency);
            startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String data = readURL(params[0]);
            if(data != LINE_CLEAR){
                itunesShPr.edit()
                        .putString(LAST_DATA, data)
                        .apply();
                // тут сохраняем строку если не пусто иначе подгружаем старую
            }else {

                if(itunesShPr.contains(LAST_DATA)) {
                    data = itunesShPr.getString(LAST_DATA, LINE_CLEAR);
                }
            }
            return data;

        }

        @Override
        protected void onPostExecute(String content) {
            if(isSongs){
                getDataForSongs(content, numberArtist); // контекст и номер артиста
            }else {
                getDataForArtists(content);
            }



        }
    }

    public void getDataForArtists (String content) {
        try {
            jsonObject = new JSONObject(content);
            jsonArray =  jsonObject.getJSONArray(RESULTS);

            //  for(int i = 0; i < jsonArray.length(); i++){
            int number = 0;
            for(int i = 0; i < 2; i++){
                if(i == 1){number += 101;}
                songObject = jsonArray.getJSONObject(number);
                arrayListArtist.add(new Artist(
                        songObject.getString(ARTIST_NAME),
                        songObject.getString(PRIMARY_GENRE_NAME)

                ));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArtistAdapter adapter = new ArtistAdapter(
                getApplicationContext(), R.layout.custom_list_artist_layout, arrayListArtist
        );
        lv.setAdapter(adapter);
        isSongs = true; // даем разрешение на загрузку песен
    }
    public void getDataForSongs (String content, int numberArtist) {
        try {

            jsonObject = new JSONObject(content);
            jsonArray =  jsonObject.getJSONArray(RESULTS);
            int firstSong = 0;
            if(numberArtist == 1){
                firstSong = 101;
            }

            for(int i = 1; i < 101; i++){
                firstSong++;
                songObject = jsonArray.getJSONObject(firstSong);
                arrayListSongs.add(new Songs(
                        songObject.getString(ARTWORK_URL_30),
                        songObject.getString(TRACK_NAME),
                        songObject.getString(ARTIST_NAME),
                        songObject.getString(COLLECTION_NAME)

                ));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomListAdapter adapter = new CustomListAdapter(
                getApplicationContext(), R.layout.content_song_details, arrayListSongs
        );
        lv.setAdapter(adapter);
        isDetails = true; // разрешаем открыть подробное описание
    }

    private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public void onBackPressed() {
        isDetails = false;
        isSongs = false;
        runOnUi();
        arrayListSongs.clear();
        arrayListArtist.clear();
    }//onBackPressed
}
