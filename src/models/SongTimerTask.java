package models;

import java.text.NumberFormat;

import javax.swing.SwingWorker;

import views.MusicPlayerPanelView;

/**
 * A Model class that the SongTimer uses to update itself.
 * 
 * @author bba278
 *
 */
public class SongTimerTask extends SwingWorker<Void, Void> {

	/*
	 * Fields
	 */
	private MusicPlayerPanelView musicMusicPlayerPanel;
	private MusicPlayerModelInt playerModel;
	private int currentMiliSecPosition;

	/**
	 * Constructor uses the MusicPlayerPanelView and assigns a reference to the
	 * MusicPlayerModel
	 * 
	 * @param musicPlayerPanelView
	 */
	public SongTimerTask(MusicPlayerPanelView musicPlayerPanelView) {

		this.musicMusicPlayerPanel = musicPlayerPanelView;
		this.playerModel = musicPlayerPanelView.getPlayerModel();

	}

	/**
	 * Method to update the SongTimer in the background 1.For the duration of
	 * the song in Mili Seconds 2.Get current position in Mili Seconds 3.Get the
	 * offSet(in bytes) from pressing the ProgressBar and convert it to Mili
	 * Seconds 4.Add this offSet to the current position in Mili Seconds
	 * 5.Convert current position in Mili Seconds to sec and min so it can be
	 * displayed in the time label
	 * 
	 */
	@Override
	protected Void doInBackground() throws Exception {

		for (int i = 0; i < playerModel.getMiliSec(); i++) {
			currentMiliSecPosition = playerModel.getPlayer().getPosition();

			int offSet = playerModel.getOffSet();
			offSet /= playerModel.getLengthInBytes() / playerModel.getMiliSec();
			currentMiliSecPosition += offSet;

			int sec = (currentMiliSecPosition / 1000) % 60;
			int min = (currentMiliSecPosition / 1000) / 60;

			NumberFormat format = NumberFormat.getNumberInstance();
			format.setMinimumIntegerDigits(2);

			if (!isCancelled()) {
				musicMusicPlayerPanel.getCurrentTimeLabel().setText(format.format(min) + ":" + format.format(sec));
			}

		}

		if (playerModel.getPlayer().isComplete()) {
			musicMusicPlayerPanel.cancelSongTimerTask();
			musicMusicPlayerPanel.getCurrentTimeLabel().setText("00:00");
		}

		if (isCancelled()) {
			System.out.println("SongTimer Canceled");
		} else {
			doInBackground();
		}
		Thread.sleep(1000);

		return null;
	}

}
