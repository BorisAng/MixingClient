package views;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import controllers.PauseButtonListener;
import controllers.PlayButtonListener;
import controllers.ResumeButtonListener;
import controllers.StopButtonListener;
import models.MusicPlayerModel;
import models.MusicPlayerModelInt;
import models.ProgressBarTask;
import models.SongTimerTask;

/**
 * A View class for the Music Player Panel
 * 
 * @author bba278
 *
 */
public class MusicPlayerPanelView {

	/*
	 * Fields
	 */
	private WaveFormPanelView waveFormPanelView;

	private ProgressBarTask progressBarTask;
	private SongTimerTask songTimerTask;
	private MusicPlayerModelInt playerModel;

	private JPanel musicPlayerPanel;
	private JButton playButton;
	private JProgressBar songProgressBar;
	private JButton pauseButton;
	private JButton resumeButton;
	private JButton stopButton;
	private JLabel currentTime;
	private JLabel totalTime;

	/**
	 * Constructor to create the MusicPlayerPanel. It also creates the
	 * MusicPlayerModel
	 * 
	 * @param mainFrameView
	 * 
	 * @param a
	 *            frame to add the panel to
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	public MusicPlayerPanelView() throws UnsupportedAudioFileException, IOException {

		this.setPlayerModel(new MusicPlayerModel());

		initializePart();
	}

	/**
	 * Initialise the contents of the music player panel and add the controllers
	 * i.e listeners
	 */
	private void initializePart() {

		musicPlayerPanel = new JPanel();
		this.getMusicPlayerPanel().setLayout(new MigLayout("", "[grow][454.00,grow][454.00,grow]", "[][][][][grow]"));

		this.setWaveFormPanelView(new WaveFormPanelView());
		this.getMusicPlayerPanel().add(this.getWaveFormPanelView(), "cell 0 4 3 1,grow");
		
		this.setPlayButton(new JButton("Play Song"));
		this.getMusicPlayerPanel().add(this.getPlayButton(), "cell 0 0,grow");
		this.getPlayButton().addActionListener(new PlayButtonListener(this));

		this.setSongProgressBar(new JProgressBar(0));
		this.getSongProgressBar().setValue(0);
		this.getMusicPlayerPanel().add(this.getSongProgressBar(), "cell 1 0 2 3,grow");

		this.setPauseButton(new JButton("Pause Song"));
		this.getMusicPlayerPanel().add(this.getPauseButton(), "cell 0 1,grow");
		this.getPauseButton().addActionListener(new PauseButtonListener(this));

		this.setResumeButton(new JButton("Resume Song"));
		this.getMusicPlayerPanel().add(this.getResumeButton(), "cell 0 2,grow");
		this.getResumeButton().addActionListener(new ResumeButtonListener(this));

		this.setStopButton(new JButton("Stop Song"));
		this.getMusicPlayerPanel().add(this.getStopButton(), "cell 0 3,grow");
		this.getStopButton().addActionListener(new StopButtonListener(this));

		this.setCurrentTimeLabel(new JLabel("00:00"));
		this.getMusicPlayerPanel().add(this.getCurrentTimeLabel(), "cell 1 3");

		this.setTotalTimeLabel(new JLabel("00:00"));
		this.getMusicPlayerPanel().add(this.getTotalTimeLabel(), "cell 2 3,alignx right");

		
	}

	/**
	 * Method to create a new SongTimer task 1. Cancel the old task 2. Create a
	 * new one and return it
	 * 
	 * @return the SongTimer task
	 */
	public SongTimerTask newSongTimerTask() {

		// cancelSongTimeTask();
		this.songTimerTask = new SongTimerTask(this);

		return this.songTimerTask;

	}

	/**
	 * Method to cancel the SongTimer task
	 */
	public void cancelSongTimerTask() {
		if ((this.songTimerTask != null)) {

			songTimerTask.cancel(true);
			songTimerTask = null;
		}
	}

	/**
	 * Method to check if the SongTimer task exists
	 * 
	 * @return
	 */
	public boolean hasSongTimerTask() {

		return this.songTimerTask != null;
	}

	/**
	 * Method to check if the ProgressBar task exists
	 * 
	 * @return
	 */
	public boolean hasProgressBarTask() {

		return this.progressBarTask != null;
	}

	/**
	 * Method to create a new ProgressBar task 1. Cancel the old task 2. Create
	 * a new one and return it
	 * 
	 * @return the ProgressBar task
	 */
	public ProgressBarTask newProgressBarTask() {
		cancelProgressBarTask();
		this.progressBarTask = new ProgressBarTask(this);

		return this.progressBarTask;
	}

	/**
	 * Method to cancel the ProgressBar task
	 */
	public void cancelProgressBarTask() {
		if ((this.progressBarTask != null)) {

			progressBarTask.cancel(true);
			progressBarTask = null;
		}
	}

	/*
	 * GETTERS AND SETTERS
	 */

	public MusicPlayerModelInt getPlayerModel() {
		return playerModel;
	}

	public void setPlayerModel(MusicPlayerModelInt playerModel) {
		this.playerModel = playerModel;
	}

	public JPanel getMusicPlayerPanel() {
		return musicPlayerPanel;
	}

	public JButton getPlayButton() {
		return playButton;
	}

	public void setPlayButton(JButton playButton) {
		this.playButton = playButton;
	}

	public JProgressBar getSongProgressBar() {
		return songProgressBar;
	}

	public void setSongProgressBar(JProgressBar songProgressBar) {
		this.songProgressBar = songProgressBar;
	}

	public JButton getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(JButton pauseButton) {
		this.pauseButton = pauseButton;
	}

	public JButton getResumeButton() {
		return resumeButton;
	}

	public void setResumeButton(JButton resumeButton) {
		this.resumeButton = resumeButton;
	}

	public JButton getStopButton() {
		return stopButton;
	}

	public void setStopButton(JButton stopButton) {
		this.stopButton = stopButton;
	}

	public JLabel getCurrentTimeLabel() {
		return currentTime;
	}

	public void setCurrentTimeLabel(JLabel currentTime) {
		this.currentTime = currentTime;
	}

	public JLabel getTotalTimeLabel() {
		return totalTime;
	}

	public void setTotalTimeLabel(JLabel totalTime) {
		this.totalTime = totalTime;
	}

	public WaveFormPanelView getWaveFormPanelView() {
		return waveFormPanelView;
	}

	public void setWaveFormPanelView(WaveFormPanelView waveFormPanelView) {
		this.waveFormPanelView = waveFormPanelView;
	}

}
