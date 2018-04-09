package gajendrasinghrathore.itunessongslistfordoubleclients.pojos;

public class Artist {
    private String artistName;
    private String primaryGenreName;

    public Artist(String artistName, String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return primaryGenreName;
    }

    public void setCollectionName(String collectionName) {
        this.primaryGenreName = collectionName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
