package controllers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.text.NumberFormat;

import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.decoder.JavaLayerException;
import models.MusicPlayerModelInt;
import views.MusicPlayerPanelView;
import views.WaveFormPanelView;

/**
 * Listener(Controller) responds when the "Play Song" is pressed.
 * 
 * @author bba278
 *
 */
public class PlayButtonListener implements ActionListener {

	/*
	 * Fields
	 */
	private MusicPlayerPanelView musicPlayerPanel;
	private WaveFormPanelView waveFormPanel; // this is a JPanel
	private MusicPlayerModelInt playerModel;
	private ProgressBarListener pBarListnener;

	/**
	 * Constructor uses the MusicPlayerPanelView and assigns a reference to the
	 * MusicPlayerModel and the WaveFormPanelView(which is a JPanel itself)
	 * 
	 * @param musicPlayerPanelView
	 */
	public PlayButtonListener(MusicPlayerPanelView musicPlayerPanelView) {

		this.musicPlayerPanel = musicPlayerPanelView;
		this.waveFormPanel = musicPlayerPanel.getWaveFormPanelView();
		this.playerModel = musicPlayerPanel.getPlayerModel();

	}

	/**
	 * On button click 1. Play the selected song 2. Disable the "Play Song" and
	 * "Resume Song" buttons 3. Set the progress bar minimum to 0 and it's
	 * maximum to the length of the song in bytes 4. Create the
	 * ProgressBarListener 5. Register it on the ProgressBar 6. Execute the task
	 * on the ProgressBar 7. Execute the task on the currentTime JLabel 8.
	 * Update the totalTime JLabel 9.Create a new thread and getMP3Amplitude,
	 * drawWaveForm and repaint the waveFormPanel
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			playerModel.play(playerModel.getSongDirectoryName());

			musicPlayerPanel.getPlayButton().setEnabled(false);
			musicPlayerPanel.getResumeButton().setEnabled(false);

		} catch (IOException | JavaLayerException e1) {
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		}

		musicPlayerPanel.getSongProgressBar().setMinimum(0);
		musicPlayerPanel.getSongProgressBar().setMaximum(playerModel.getLengthInBytes());
		System.out.println("PlayButtonListener says that the Progress Bar max is: ");
		System.out.println("The ProgressBar max is: " + musicPlayerPanel.getSongProgressBar().getMaximum() + " bytes");
		System.out.println("-----------------------------------------------------");

		// Create the progress bar listener and add it to UI
		pBarListnener = new ProgressBarListener(musicPlayerPanel);
		musicPlayerPanel.getSongProgressBar().addMouseListener(pBarListnener);

		musicPlayerPanel.newProgressBarTask().execute();

		musicPlayerPanel.newSongTimerTask().execute();

		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMinimumIntegerDigits(2);

		musicPlayerPanel.getTotalTimeLabel()
				.setText(format.format(playerModel.getMin()) + ":" + format.format(playerModel.getSec()));

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				System.out.println("Loading image");
				waveFormPanel.getWaveFormModel().getMP3Amplitude();
				waveFormPanel.getWaveFormModel().drawWaveForm(waveFormPanel.getWaveFormModel().getSamples());
				
				musicPlayerPanel.getWaveFormPanelView().setClear(false);
				musicPlayerPanel.getWaveFormPanelView().repaint();
				musicPlayerPanel.getWaveFormPanelView().requestFocus();

			

			}
		});
		thread.start();
	}
}
