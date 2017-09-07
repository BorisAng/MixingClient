package thisPackageIsNotUsed;

public class Song {

	private String songName;
	private String artistName;
	private String albumName;
	private String yearOfRelease;
	private String genre;
	private long miliSec;
	private long sec;
	private int min;
	private int lengthInBytes;

	public Song(String songName, String artistName, String albumName, String yearOfRelease, String genre, long miliSec,
			long sec, int min, int lengthInBytes) {

		this.setSongName(songName);
		this.setArtistName(artistName);
		this.setAlbumName(albumName);
		this.setYearOfRelease(yearOfRelease);
		this.setMiliSec(miliSec);
		this.setSec(sec);
		this.setMin(min);
		this.setLengthInBytes(lengthInBytes);

	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(String yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public long getMiliSec() {
		return miliSec;
	}

	public void setMiliSec(long miliSec) {
		this.miliSec = miliSec;
	}

	public long getSec() {
		return sec;
	}

	public void setSec(long sec) {
		this.sec = sec;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getLengthInBytes() {
		return lengthInBytes;
	}

	public void setLengthInBytes(int lengthInBytes) {
		this.lengthInBytes = lengthInBytes;
	}

}
