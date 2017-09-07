package thisPackageIsNotUsed;

// package models;
//
// import java.io.IOException;
//
// import javax.sound.sampled.UnsupportedAudioFileException;
// import javax.swing.JButton;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.JProgressBar;
//
// import controllers.PauseButtonListener;
// import controllers.PlayButtonListener;
// import controllers.ResumeButtonListener;
// import controllers.StopButtonListener;
//
// import models.MusicPlayerModel;
// import models.MusicPlayerModelInt;
// import models.ProgressBarTask;
// import models.SongTimerTask;
// import net.miginfocom.swing.MigLayout;
//
/// **
// * A View class for the MusicPlayerPanel
// *
// * @author bba278
// *
// */
// public class BottomPlayerPanelView {
//
// /*
// * Fields
// */
// private MainFrameView frame;
//
// private ProgressBarTask progressBarTask;
// private SongTimerTask songTimerTask;
// private MusicPlayerModelInt playerModel;
//
// private JPanel mPlayerPanel;
// private JButton playButton;
// private JButton stopButton;
// private JButton pauseButton;
// private JButton resumeButton;
// private JLabel songName;
// private JLabel artistName;
// private JLabel currentTime;
// private JLabel totalTime;
// private JProgressBar songProgressBar;
//
//
//
// /**
// * Constructor to create the MusicPlayerPanel
// *
// * @param mainFrameView
// *
// * @param a
// * frame to add the panel to
// * @throws IOException
// * @throws UnsupportedAudioFileException
// */
// public BottomPlayerPanelView(MainFrameView frm) throws
// UnsupportedAudioFileException, IOException {
//
// this.frame = frm;
// this.setPlayerModel(new MusicPlayerModel());
//
// initializePart();
//
// }
//
// /**
// * Initialise the contents of the panel and add the controllers i.e
// * listeners
// */
// private void initializePart() {
//
// mPlayerPanel = new JPanel();
// mPlayerPanel.setLayout(new MigLayout("fill", "[][][][82.00]",
// "[][][][][][][][]"));
//
// setPlayButton(new JButton("Play Song"));
//
// mPlayerPanel.add(getPlayButton(), "cell 0 0,grow");
//
// PlayButtonListener playButtonListener = new PlayButtonListener(this);
//
// getPlayButton().addActionListener(playButtonListener);
//
// songProgressBar = new JProgressBar(0);
// songProgressBar.setValue(0);
// // songProgressBar.setStringPainted(true);
//
// mPlayerPanel.add(songProgressBar, "cell 1 0 3 2,width 500,height 15,grow");
//
// pauseButton = new JButton("Pause song");
//
// mPlayerPanel.add(pauseButton, "cell 0 1,grow");
//
// pauseButton.addActionListener(new PauseButtonListener(this));
//
// setResumeButton(new JButton("Resume song"));
//
// mPlayerPanel.add(getResumeButton(), "cell 0 2,grow");
//
// getResumeButton().addActionListener(new ResumeButtonListener(this));
//
// currentTime = new JLabel();
//
// mPlayerPanel.add(currentTime, "flowx,cell 1 2,aligny top");
//
// stopButton = new JButton("Stop song");
//
// mPlayerPanel.add(stopButton, "cell 0 3,grow");
//
// stopButton.addActionListener(new StopButtonListener(this));
//
// songName = new JLabel("");
//
// mPlayerPanel.add(songName, "flowx,cell 1 3,aligny top");
//
// artistName = new JLabel("");
//
// mPlayerPanel.add(artistName, "cell 1 4,aligny top");
//
// totalTime = new JLabel();
//
// mPlayerPanel.add(totalTime, "cell 2 4");
//
// }
//
// /**
// * Method to create a new SongTimer task 1. Cancel the old task 2. Create a
// * new one and return it
// *
// * @return the SongTimer task
// */
// public SongTimerTask newSongTimerTask() {
//
// // cancelSongTimeTask();
// this.songTimerTask = new SongTimerTask(this);
//
// return this.songTimerTask;
//
// }
//
// /**
// * Method to cancel the SongTimer task
// */
// public void cancelSongTimerTask() {
// if ((this.songTimerTask != null)) {
//
// songTimerTask.cancel(true);
// songTimerTask = null;
// }
// }
//
// /**
// * Method to check if the SongTimer task exists
// *
// * @return
// */
// public boolean hasSongTimerTask() {
//
// return this.songTimerTask != null;
// }
//
// /**
// * Method to check if the ProgressBar task exists
// *
// * @return
// */
// public boolean hasProgressBarTask() {
//
// return this.progressBarTask != null;
// }
//
// /**
// * Method to create a new ProgressBar task 1. Cancel the old task 2. Create
// * a new one and return it
// *
// * @return the ProgressBar task
// */
// public ProgressBarTask newProgressBarTask() {
// cancelProgressBarTask();
// this.progressBarTask = new ProgressBarTask(this);
//
// return this.progressBarTask;
// }
//
// /**
// * Method to cancel the ProgressBar task
// */
// public void cancelProgressBarTask() {
// if ((this.progressBarTask != null)) {
//
// progressBarTask.cancel(true);
// progressBarTask = null;
// }
// }
//
// /*
// * GETTERS AND SETTERS
// */
// public JPanel getmPlayerPanel() {
// return mPlayerPanel;
// }
//
// public void setSongName(String songName) {
//
// this.songName.setText(songName);
// }
//
// public void setArtistName(String artistName) {
//
// this.artistName.setText(artistName);
//
// }
//
// public JProgressBar getSongProgressBar() {
// return songProgressBar;
// }
//
// public MusicPlayerModelInt getPlayerModel() {
// return playerModel;
// }
//
// public void setPlayerModel(MusicPlayerModelInt playerModel) {
// this.playerModel = playerModel;
// }
//
// public JButton getPlayButton() {
// return playButton;
// }
//
// public void setPlayButton(JButton playButton) {
// this.playButton = playButton;
// }
//
// public JButton getResumeButton() {
// return resumeButton;
// }
//
// public void setResumeButton(JButton resumeButton) {
// this.resumeButton = resumeButton;
// }
//
// public JLabel getCurrentTimeLabel() {
// return this.currentTime;
// }
//
// public JLabel getTotalTimeLabel(){
// return this.totalTime;
// }
//
// }
