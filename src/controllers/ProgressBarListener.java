package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JProgressBar;

import javazoom.jl.decoder.JavaLayerException;
import models.MusicPlayerModelInt;

import views.MusicPlayerPanelView;

/**
 * Listener(Controller) responds when the Progress Bar is pressed.
 * 
 * @author bba278
 *
 */
public class ProgressBarListener implements MouseListener {

	/*
	 * Fields
	 */
	private MusicPlayerPanelView musicPlayerPanel;
	private MusicPlayerModelInt playerModel;

	private JProgressBar songProgressBar;
	private Thread thread;

	/**
	 * Constructor uses the MusicPlayerPanelView and assigns a reference to the
	 * MusicPlayerModel and the ProgressBar
	 * 
	 * @param musicPlayerPanelView
	 */

	public ProgressBarListener(MusicPlayerPanelView musicPlayerPanelView) {

		this.musicPlayerPanel = musicPlayerPanelView;
		this.playerModel = musicPlayerPanel.getPlayerModel();

		this.songProgressBar = musicPlayerPanel.getSongProgressBar();

	}

	/**
	 * On ProgressBar click
	 * 1. Compute the ProgressBar position in bytes when pressed
	 * 2. If the "Resume Song" button is enabled , disable it
	 * 3. If the ProgressBar Task is not running , create a new one
	 * 4. If the SongTimer Task is not running , create a new one
	 * 5. Set the offSet that is used by the ProgressBar UI to determine where it should start after pressing
	 * 6. Close the old player
	 * 7. Create a new player
	 * 8. Forward the song to where the user has pressed on the ProgressBar
	 * 9. Play the song from that position
	 */
	@Override
	public void mouseClicked(MouseEvent evt) {

		int mouseX = evt.getX();
		// Computes how far along the mouse is relative to the component
		// width then multiply it by the progress bar's maximum value.
		int progressBarVal = (int) Math
				.round(((double) mouseX / (double) songProgressBar.getWidth()) * songProgressBar.getMaximum());
		System.out.println("The progress bar value (i.e. bytes to skips) is: " + progressBarVal + " bytes");

		if(musicPlayerPanel.getResumeButton().isEnabled()){
			musicPlayerPanel.getResumeButton().setEnabled(false);
		}
		
		if (!musicPlayerPanel.hasProgressBarTask()) {
			musicPlayerPanel.newProgressBarTask().execute();
		}
		
		if(!musicPlayerPanel.hasSongTimerTask()){
			musicPlayerPanel.newSongTimerTask().execute();
		}
		
		playerModel.setOffSet(progressBarVal);	

		try {

			playerModel.getPlayer().close();
			playerModel.startInput(playerModel.getSongDirectoryName());

			playerModel.getBis().skip(progressBarVal);
			System.out.println("Bytes available to play after press:" + playerModel.getBis().available() + " bytes");

			thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						playerModel.getPlayer().play();
					} catch (JavaLayerException e) {

						e.printStackTrace();
					}
				}
			});
			thread.start();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}// End of mouseClicked

	@Override
	public void mouseEntered(MouseEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent evt) {
		// TODO Auto-generated method stub

	}
}
