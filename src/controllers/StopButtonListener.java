package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.MusicPlayerModelInt;

import views.MusicPlayerPanelView;

/**
 * Listener(Controller) responds when the "Stop Song" is pressed.
 * 
 * @author bba278
 *
 */
public class StopButtonListener implements ActionListener {

	/*
	 * Fields
	 */
	private MusicPlayerModelInt playerModel;
	private MusicPlayerPanelView musicPlayerPanel;

	/**
	 * Constructor uses the MusicPlayerPanelView and assigns a reference to the
	 * MusicPlayerModel
	 * 
	 * @param musicPlayerPanelView
	 */
	public StopButtonListener(MusicPlayerPanelView musicPlayerPanelView) {

		this.musicPlayerPanel = musicPlayerPanelView;

		this.playerModel = musicPlayerPanel.getPlayerModel();

	}

	/**
	 * On button click
	 * 1. Cancel the SongTimerTask 
	 * 2. Set the value of the currentTimeLabel to 00:00
	 * 3. Cancel the progressBar task
	 * 4. Set the value of the progressBar to 0
	 * 5. Stop the current song
	 * 6. Enable the "Play Song" and "Resume Song" buttons
	 * 7. Clear the waveForm view
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		musicPlayerPanel.cancelSongTimerTask();
		musicPlayerPanel.getCurrentTimeLabel().setText("00:00");
		
		musicPlayerPanel.cancelProgressBarTask();
		musicPlayerPanel.getSongProgressBar().setValue(0);
		playerModel.stop();
		musicPlayerPanel.getPlayButton().setEnabled(true);
		musicPlayerPanel.getResumeButton().setEnabled(true);
		
		musicPlayerPanel.getWaveFormPanelView().setClear(true);
		musicPlayerPanel.getWaveFormPanelView().repaint();

	}

}
