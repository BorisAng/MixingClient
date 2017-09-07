package models;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * Interface implemented by the MusicPlayerModel
 * 
 * @author bba278
 *
 */
public interface MusicPlayerModelInt {

	/**
	 * Method to stop playing a song
	 */
	void stop();

	/**
	 * Method to play a song
	 * 
	 * @param path
	 * @throws IOException
	 * @throws JavaLayerException
	 * @throws UnsupportedAudioFileException
	 */
	void play(String path) throws IOException, JavaLayerException, UnsupportedAudioFileException;

	/**
	 * Method to pause a song
	 */
	void pause();

	/**
	 * Method to resume a song
	 * 
	 * @throws IOException
	 */
	void resume() throws IOException;

	/**
	 * Method to create a Player Object from the BufferedInputStream
	 * 
	 * @param path
	 * @throws FileNotFoundException
	 * @throws JavaLayerException
	 */
	void startInput(String path) throws FileNotFoundException, JavaLayerException;

	/**
	 * Method to decode a song
	 * 
	 * @param tempSongDir
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 */
	public void decodeSong(String tempSongDir) throws UnsupportedAudioFileException, IOException;

	/*
	 * GETTERS AND SETTERS
	 */
	int getOffSet();

	void setOffSet(int offSet);

	Player getPlayer();

	String getSongName();

	String getArtistName();

	String getAlbumName();

	String getYearOfRelease();

	String getGenre();

	int getMiliSec();

	long getSec();

	int getMin();

	int getLengthInFrames();

	int getLengthInBytes();

	File getFile();

	BufferedInputStream getBis();

	void setBis(BufferedInputStream bis);

	int getSongTotalLenght();

	void setSongTotalLenght(int songTotalLenght);

	void setSongDirectoryName(String songDirectoryName);

	String getSongDirectoryName();

}