package models;

import javax.swing.SwingWorker;

import javazoom.jl.player.Player;
import views.MusicPlayerPanelView;

/**
 * A Model class that the ProgressBar uses to update itself.
 * 
 * @author bba278
 *
 */
public class ProgressBarTask extends SwingWorker<Void, Void> {

	/*
	 * Fields
	 */
	private MusicPlayerPanelView musicMusicPlayerPanel;
	private MusicPlayerModelInt playerModel;

	private Player player;

	private double offSet;
	private double lengthInBytes;
	private double mili;
	private int currentPositionInMiliSec;
	private double position;
	private int roundedPosition;

	/**
	 * Constructor uses the MusicPlayerPanelView and assigns a reference to the
	 * MusicPlayerModel
	 * 
	 * @param musicPlayerPanelView
	 */
	public ProgressBarTask(MusicPlayerPanelView musicPlayerPanelView) {
		this.musicMusicPlayerPanel = musicPlayerPanelView;

		this.playerModel = musicMusicPlayerPanel.getPlayerModel();

	}

	/**
	 * Method to update the ProgressBar in the background
	 * 1. Acquire the Player Object, the offSet, the lengthInBytes, 
	 * the length in miliSec and the currentPositionInMiliSec
	 * 2. Calculate how much to update.SEE position variable
	 * 3. Round the position so it updates more smoothly
	 * 4. Set the ProgressBar UI value to the roundedPosition
	 * 5. Sleep for some time
	 * 6. if the Player isComplete, cancel the task, set the value of 
	 * the ProgressBar to 0 and enable the PlayButton
	 * 7.If roundedPosition >= lengthInBytes || isCancelled DO SOMETHING
	 * else go back to 1
	 */
	@Override
	protected Void doInBackground() throws Exception {

		player = playerModel.getPlayer();
		offSet = playerModel.getOffSet();

		lengthInBytes = playerModel.getLengthInBytes();
		mili = playerModel.getMiliSec();
		// System.out.println("Mili " + mili);
		currentPositionInMiliSec = player.getPosition();
//		
//		System.out.println("The Progress Bar Task says lenthInBytes is: " + lengthInBytes);
//		System.out.println("The Progress Bar Task says mili is: " + mili);
//		System.out.println("The Progress Bar Task says currentPositionInMiliSec is: " + currentPositionInMiliSec);
		
		double bytesPerMiliSec = lengthInBytes / mili;

		position = (bytesPerMiliSec) * currentPositionInMiliSec;
		position += offSet;
//		System.out.println("Posistion to update the progress bar with " + position);
//		System.out.println("-------------------------------------------------------------");

		roundedPosition = (int) Math.ceil(position);

		musicMusicPlayerPanel.getSongProgressBar().setValue(roundedPosition);
		Thread.sleep(50);

		if (player.isComplete()) {
			musicMusicPlayerPanel.cancelProgressBarTask();
			musicMusicPlayerPanel.getSongProgressBar().setValue(0);
			musicMusicPlayerPanel.getPlayButton().setEnabled(true);

		}

		if (roundedPosition >= lengthInBytes || isCancelled()) {
			System.out.println("Exitied itself");
			//setProgress(100);
		} else {
			return doInBackground();
		}
		return null;
	}
}
