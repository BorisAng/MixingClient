package models;

import java.awt.EventQueue;

import views.MainFrameView;

/**
 * A Model class that starts the application on a new Thread.This is the model
 * class holding the main frame
 * 
 * @author bba278
 *
 */
public class MusicAppModel implements Runnable {

	@Override
	public void run() {
		try {

			new MainFrameView();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Main Thread starts the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new MusicAppModel());
	}

}
