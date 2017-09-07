package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.MusicPlayerModelInt;
import views.MusicPlayerPanelView;

/**
 * Listener(Controller) responds when the "Pause Song" is pressed.
 * 
 * @author bba278
 *
 */
public class PauseButtonListener implements ActionListener {

	/*
	 * Fields
	 */
	private MusicPlayerModelInt playerModel;
	private MusicPlayerPanelView musicPlayerPanel;

	/**
	 * Constructor uses the MusicPlayerPanelView and assigns a reference to
	 * the MusicPlayerModel
	 * 
	 * @param musicPlayerPanelView
	 */
	public PauseButtonListener(MusicPlayerPanelView musicPlayerPanelView) {
		this.musicPlayerPanel = musicPlayerPanelView;
		this.playerModel = musicPlayerPanel.getPlayerModel();

	}

	/**
	 * On button click
	 * 1. Get the current value of the ProgressBar in bytes
	 * 2. Set the offSet that is used by the ProgressBar UI to determine where it should start after pausing
	 * 3. Pause the current song
	 * 4. Enable the "Resume Song" button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		int progressBarPauseValue = musicPlayerPanel.getSongProgressBar().getValue();
		System.out.println("The progress bar value after pausing is " + progressBarPauseValue);

		playerModel.setOffSet(progressBarPauseValue);

		playerModel.pause();
		musicPlayerPanel.getResumeButton().setEnabled(true);
	}

}
