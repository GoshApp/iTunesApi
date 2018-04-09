package gajendrasinghrathore.itunessongslistfordoubleclients.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import gajendrasinghrathore.itunessongslistfordoubleclients.R;
import gajendrasinghrathore.itunessongslistfordoubleclients.pojos.Artist;

public class ArtistAdapter extends ArrayAdapter<Artist> {

    private ArrayList<Artist> artists;
    private Context context;
    private int resource;

    public ArtistAdapter(Context context, int resource, ArrayList<Artist> artists) {
        super(context, resource, artists);
        this.artists = artists;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            assert layoutInflater != null;
            convertView = layoutInflater.inflate(R.layout.custom_list_artist_layout, null, true);

        }
        Artist artist = getItem(position);

        TextView collectionName = (TextView) convertView.findViewById(R.id.collectionName);
        assert artist != null;
        collectionName.setText(artist.getCollectionName());

        TextView artistName = (TextView) convertView.findViewById(R.id.artistName);
        artistName.setText(artist.getArtistName());

        return convertView;
    }
}
