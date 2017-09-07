package controllers;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.BrowserTableModel;
import views.MusicPlayerPanelView;
import views.WaveFormPanelView;
import views.BrowserTablePanelView;

/**
 * Listener(Controller) responds when a selection on the table is made.
 * 
 * @author bba278
 *
 */
public class TableSelectionListnener implements ListSelectionListener {

	/*
	 * Fields
	 */

	private BrowserTablePanelView browserTablePanel;
	private BrowserTableModel browserTableModel;
	private MusicPlayerPanelView musicPlayerPanel;
	private WaveFormPanelView waveFormPanel;

	private JTable table;

	private int rowIndex;
	private int colIndex;

	public String songDirectoryName;

	private String songName;

	/**
	 * Constructor uses the BrowserTablePanelView and the MusicPlayerPanelView
	 * and assigns a reference to the BrowserTableModel and the JTable
	 * 
	 * @param tPanel,
	 *            musicPlayerPanelView
	 */
	public TableSelectionListnener(BrowserTablePanelView tPanel, MusicPlayerPanelView musicPlayerPanelView) {
		this.browserTablePanel = tPanel;
		this.musicPlayerPanel = musicPlayerPanelView;

		this.waveFormPanel = musicPlayerPanelView.getWaveFormPanelView();
		this.browserTableModel = browserTablePanel.getBrowserTableModel();
		this.table = browserTablePanel.getbTable();

	}

	/**
	 * Method responds to the selection of files in the table
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {

		// if the cell can be selected
		if (table.getCellSelectionEnabled()) {
			// if the mouse button is still pressed
			if (e.getValueIsAdjusting()) {
				return;
			}

			// Get the row selected by the user
			rowIndex = table.getSelectedRow();
			if (rowIndex < 0) {
				return;
			}

			// Get column selected by the user
			colIndex = table.getSelectedColumn();
			if (colIndex < 0) {
				return;
			}

			// String album = JOptionPane.showInputDialog("Enter album");
			//
			// browserTableModel.isCellEditable(rowIndex, colIndex);
			// browserTableModel.setValueAt(album, rowIndex, colIndex);

			// get the directory name
			songDirectoryName = (String) browserTableModel.getValueAt(rowIndex, 6);
			System.out.println("TableSelectionListener says chosen directory is :" + songDirectoryName);
			System.out.println("----------------------------------------------------------------------");
			waveFormPanel.getWaveFormModel().setSongDirectoryName(songDirectoryName);
			musicPlayerPanel.getPlayerModel().setSongDirectoryName(songDirectoryName);
			

			// get the song name
			songName = (String) browserTableModel.getValueAt(rowIndex, 0);

			//bottomPlayerPanel.setSongName(songName);
			
			String artistName = (String) browserTableModel.getValueAt(rowIndex, 1);
			
			//bottomPlayerPanel.setArtistName(artistName);
		}
	}

	/*
	 * GETTERS AND SETTERS
	 */

	public String getSongName() {
		return songName;
	}

	public String getDirectoryName() {
		return songDirectoryName;
	}
}
