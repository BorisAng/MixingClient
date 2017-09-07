package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import models.MusicPlayerModelInt;
import views.MusicPlayerPanelView;

/**
 * Listener(Controller) responds when the "Resume Song" is pressed.
 * 
 * @author bba278
 *
 */
public class ResumeButtonListener implements ActionListener {

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
	public ResumeButtonListener(MusicPlayerPanelView musicPlayerPanelView) {
		this.musicPlayerPanel = musicPlayerPanelView;
		this.playerModel = musicPlayerPanel.getPlayerModel();

	}

	
	/**
	 * On button click
	 * 1. Resume the current song
	 * 2. Enable the "Resume Song" Button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			playerModel.resume();
			musicPlayerPanel.getResumeButton().setEnabled(false);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
