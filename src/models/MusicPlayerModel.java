package models;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;

import org.tritonus.share.sampled.file.TAudioFileFormat;

/**
 * Model class for the BottomPlayerPanelView. Holds the data of the music
 * player.Implements MusicPlayerModelInt
 * 
 * @author bba278
 *
 */
public class MusicPlayerModel implements MusicPlayerModelInt {

	/*
	 * Fields
	 */

	private FileInputStream fis;
	private BufferedInputStream bis;

	private File songFile;

	private Player player;

	private int pauseLocation;
	private int songTotalLenght;

	private String fileLocation;

	private String songName;
	private String artistName;
	private String albumName;
	private String yearOfRelease;
	private String genre;
	private int miliSec;
	private long sec;
	private int min;
	private int lengthInFrames;
	private int lengthInBytes;
	private int offSet;
	private String songDirectoryName;

	public MusicPlayerModel() throws UnsupportedAudioFileException, IOException {

	}

	/**
	 * Method to stop playing a song
	 * 1. If the player still exists , close it
	 * 2. Reset the pauseLocation
	 * 3. Set the acquired from the BufferedInputStream songTotalLenght to 0 bytes
	 */
	@Override
	public void stop() {
		if (player != null) {
			player.close();

			pauseLocation = 0;
			setSongTotalLenght(0);
		}
	}

	/**
	 * Method to start playing a song
	 * 1. Set the offSet to 0
	 * 2. Decode the song so that it is in a File object
	 * 3. Place the song in a Player Object
	 * 4. Show the format and other information of the song (NOT SHOWN TO THE USER ONLY VISIBLE TO THE PROGRAMMER)
	 * 5. The BufferedInputStream sets the song total length in bytes
	 * 6. Play the song on a new Thread
	 */
	@Override
	public void play(String path) throws IOException, JavaLayerException, UnsupportedAudioFileException {

		this.setOffSet(0);

		this.decodeSong(path);
		this.startInput(path);

		AudioFileFormat baseFileFormat = new MpegAudioFileReader().getAudioFileFormat(songFile);
		System.out.println("MusicPlayerModel says that the audio format is:");
		System.out.println(baseFileFormat.toString());
		System.out.println("---------------------------------");
		

		setSongTotalLenght(fis.available());
		fileLocation = path + "";

		// Play every song on a new Thread
		new Thread() {

			@Override
			public void run() {
				try {
					player.play();
				} catch (JavaLayerException e) {
				}
			}

		}.start();

	}

	
	/**
	 * Method to pause a playing song
	 * 1. If the player is not closed
	 * 2. Acquire the available bytes of the BufferedInputStream after pausing a song
	 * 3. Close the player
	 */
	@Override
	public void pause() {
		if (player != null) {

			try {
				// Checks how much of the song is left available to play
				pauseLocation = fis.available();
				player.close();
			} catch (IOException e) {

			}

		}
	}

	/**
	 * Method to resume a song
	 * 1. Place the paused song in a Player Object
	 * 2. Calculate how many bytes to skip
	 * 3. Play the song from the skipped bytes
	 */
	@Override
	public void resume() throws IOException {

		try {

			startInput(fileLocation);

			// gives us the current location
			int bytesToSkip = getSongTotalLenght() - pauseLocation;
			System.out.println("Resume button says: Num of Bytes to skip is " + bytesToSkip);
			fis.skip(bytesToSkip);

		} catch (FileNotFoundException | JavaLayerException e) {
		}

		// Play every song on a new Thread
		new Thread() {

			@Override
			public void run() {
				try {
					player.play();
				} catch (JavaLayerException e) {
				}
			}

		}.start();

	}

	/**
	 * Method to create a Player Object from the BufferedInputStream
	 */
	@Override
	public void startInput(String path) throws FileNotFoundException, JavaLayerException {
		fis = new FileInputStream(path);
		setBis(new BufferedInputStream(fis));

		player = new Player(getBis());
	}

	/**
	 * Method to decode a song
	 * 1. Place the song in a File Object
	 * 2. Extract information from the song
	 */
	@Override
	public void decodeSong(String path) throws UnsupportedAudioFileException, IOException {
		songFile = new File(path);

		AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(songFile);
		if (fileFormat instanceof TAudioFileFormat) {
			Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();

			final String songNameKey = "title";
			final String artistNameKey = "author";
			final String albumNameKey = "album";
			final String yearOfReleaseKey = "date";
			final String genreKey = "mp3.id3tag.genre";
			final String durationKey = "duration";
			final String lengthInFramesKey = "mp3.length.frames";
			final String lengthInBytesKey = "mp3.length.bytes";

			songName = (String) properties.get(songNameKey);
			artistName = (String) properties.get(artistNameKey);
			albumName = (String) properties.get(albumNameKey);
			yearOfRelease = (String) properties.get(yearOfReleaseKey);
			genre = (String) properties.get(genreKey);

			Long microseconds = (Long) properties.get(durationKey);

			miliSec = (int) (microseconds / 1000);
			sec = (miliSec / 1000) % 60;
			min = (miliSec / 1000) / 60;

			lengthInFrames = (int) properties.get(lengthInFramesKey);

			lengthInBytes = ((int) properties.get(lengthInBytesKey));

		} else {
			throw new UnsupportedAudioFileException();
		}
	}

	/*
	 * GETTERS AND SETTERS
	 */
	
	@Override
	public int getOffSet() {
		return offSet;
	}

	@Override
	public void setOffSet(int offSet) {

		this.offSet = offSet;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public String getSongName() {
		return songName;
	}

	@Override
	public String getArtistName() {
		return artistName;
	}

	@Override
	public String getAlbumName() {
		return albumName;
	}

	@Override
	public String getYearOfRelease() {
		return yearOfRelease;
	}

	@Override
	public String getGenre() {
		return genre;
	}

	@Override
	public int getMiliSec() {
		return miliSec;
	}

	@Override
	public long getSec() {
		return sec;
	}

	@Override
	public int getMin() {
		return min;
	}

	@Override
	public int getLengthInFrames() {
		return lengthInFrames;
	}

	@Override
	public int getLengthInBytes() {
		return lengthInBytes;
	}

	@Override
	public File getFile() {
		return songFile;
	}

	@Override
	public BufferedInputStream getBis() {
		return bis;
	}

	@Override
	public void setBis(BufferedInputStream bis) {
		this.bis = bis;
	}

	@Override
	public int getSongTotalLenght() {
		return songTotalLenght;
	}

	@Override
	public void setSongTotalLenght(int songTotalLenght) {
		this.songTotalLenght = songTotalLenght;
	}

	@Override
	public String getSongDirectoryName(){
		return songDirectoryName;
	}
	
	@Override
	public void setSongDirectoryName(String songDirectoryName) {
		this.songDirectoryName = songDirectoryName;
		
	}

}// End of class MusicPlayerModel
