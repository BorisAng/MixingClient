package views;

import java.io.File;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controllers.TableSelectionListnener;
import models.BrowserTableModel;
import models.MusicPlayerModelInt;

/**
 * A View class to create the BrowserTablePanel
 * 
 * @author bba278
 *
 */
public class BrowserTablePanelView {

	/*
	 * Fields
	 */
	private MusicPlayerPanelView musicPlayerPanel;

	private MusicPlayerModelInt playerModel;

	private BrowserTableModel browserTableModel;

	private TableSelectionListnener tableListener;

	private JScrollPane bTableScrollPane;
	private JTable bTable;

	/**
	 * Constructor to create the BrowserTablePanel. Uses the
	 * MusicPlayerPanelView to get the MusicPlayerModel
	 * Also creates the BrowserTableModel
	 *
	 * @param bPlPanel
	 */
	public BrowserTablePanelView(MusicPlayerPanelView playerPanel) {

		this.musicPlayerPanel = playerPanel;
		this.playerModel = musicPlayerPanel.getPlayerModel();
		
		// Create the BrowserTableModel
		this.setBrowserTableModel(new BrowserTableModel(this.playerModel));

		initializePart();
	}

	/**
	 * Initialise the contents of the panel and add the controllers i.e
	 * listeners
	 */
	private void initializePart() {

		// code to check if the "file.xml" exists in the "user.dir" i.e
		// the absolute from where the application was initialised.
		File homeDir = new File(System.getProperty("user.dir"));
		if (homeDir.isDirectory()) {
			for (File child : homeDir.listFiles()) {
				if (child.getName().equals("file.xml")) {
					File xmlFile = new File(child.getName());
					this.getBrowserTableModel().readXML(xmlFile);
				} else {
					continue;
				}
			}
		}

		// Create the Table with the Model
		this.setbTable(new JTable(getBrowserTableModel()));

		this.getbTable().getTableHeader().setReorderingAllowed(false);

		this.getbTable().setCellSelectionEnabled(true);

		// Hide the Path column from the View without removing it from the model
		this.getbTable().removeColumn(this.getbTable().getColumn("Path"));

		this.getbTable().setAutoCreateRowSorter(true);

		this.getbTable().setCellSelectionEnabled(true);
		this.getbTable().setRowSelectionAllowed(true);
		this.getbTable().setColumnSelectionAllowed(true);

		// add List selection Listener to the table
		ListSelectionModel lsm = getbTable().getSelectionModel();
		tableListener = new TableSelectionListnener(this, musicPlayerPanel);
		lsm.addListSelectionListener(tableListener);

		// Put the table in a scrollPane
		setbTableScrollPane(new JScrollPane(getbTable()));
	}

	/*
	 * GETTERS AND SETTERS
	 */
	public BrowserTableModel getBrowserTableModel() {
		return browserTableModel;
	}

	public void setBrowserTableModel(BrowserTableModel browserTableModel) {
		this.browserTableModel = browserTableModel;
	}

	public JTable getbTable() {
		return bTable;
	}

	public void setbTable(JTable bTable) {
		this.bTable = bTable;
	}

	public JScrollPane getbTableScrollPane() {
		return bTableScrollPane;
	}

	public void setbTableScrollPane(JScrollPane bTableScrollPane) {
		this.bTableScrollPane = bTableScrollPane;
	}

}
