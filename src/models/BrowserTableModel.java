package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * REFERENCES FROM http://java-articles.info/articles/?p=637
 * 
 * A Model class for the BrowserTablePanelView. Holds the data of the table
 * 
 * @author bba278
 *
 */
public class BrowserTableModel extends AbstractTableModel {

	/*
	 * Fields
	 */

	private static final long serialVersionUID = -6561728307549988245L;

	private MusicPlayerModelInt playerModel;

	private String[] columnNames = { "Track Title", "Artist", "Album", "Year of Release", "Genre", "Duration", "Path" };

	private List<List<Object>> rows;
	private List<Object> listForASong;
	private List<String> songsDirectoriesList;

	private String songName;
	private String artistName;
	private String albumName;
	private String yearOfRelease;
	private String genre;
	private long miliSec;
	private long sec;
	private int min;
	private int lengthInBytes;

	private String trackTitleXML;
	private String artistXML;
	private String albumXML;
	private String yearOfReleaseXML;
	private String genreXML;
	private String durationXML;
	private String pathXML;
	private String lengthInBytesXML;

	/**
	 * Constructor uses the MusicPlayerModel and creates the Rows list and the
	 * SongsDirectoriesList
	 * 
	 * @param playerModel
	 */
	public BrowserTableModel(MusicPlayerModelInt playerModel) {
		this.setRows(new ArrayList<List<Object>>());
		this.setSongDirectoriesList(new ArrayList<String>());

		this.playerModel = playerModel;

	}

	@Override
	public void setValueAt(Object value, int rowIndex, int colIndex) {

		List<Object> row = this.getRows().get(rowIndex);
		System.out.println("The row number is " + rowIndex);
		System.out.println("The column number is " + colIndex);

		if (colIndex == 0) {
			Object cell = row.get(colIndex);
			System.out.println(cell.getClass().toString());

			if (cell.equals("")) {
				cell = value;
			} else if (cell.equals("null")) {
				cell = value;
			} else if (cell.equals(null)) {

			}

			System.out.println(cell.toString());

		} else if (colIndex == 1) {
			Object cell = row.get(colIndex);
			System.out.println(cell.getClass().toString());
		} else if (colIndex == 2) {
			Object cell = row.get(colIndex);
			System.out.println(cell.getClass().toString());

			if (cell.equals("")) {
				cell = value;
			} else if (cell.equals("null")) {
				cell = value;
			} else if (cell.equals(null)) {

			}

		} else if (colIndex == 3) {
			Object cell = row.get(colIndex);
			System.out.println(cell.getClass().toString());
		} else if (colIndex == 4) {
			Object cell = row.get(colIndex);
			System.out.println(cell.getClass().toString());
		} else if (colIndex == 5)

			// getRows.get(rowIndex).get(colIndex) = value;
			// rowData[row][col] = value;
			fireTableCellUpdated(rowIndex, colIndex);
	}

	/**
	 * Allow every cell to be editable
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	/**
	 * Get the number of rows
	 */
	@Override
	public int getRowCount() {
		return getRows().size();
	}

	/**
	 * Get the number of columns
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * Get the value at the specified cell
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return getRows().get(rowIndex).get(columnIndex);
	}

	/**
	 * Get the name of the specified column
	 */
	@Override
	public String getColumnName(int column) {
		return columnNames[column].toString();
	}

	/**
	 * Method to fill the SongsDirectoriesList with all the files in the
	 * selected directory which end with .mp3
	 * 
	 * @param selectedDirectory
	 */
	public void createSongsDirectoriesList(String selectedDirectory) {

		System.out.println("BrowserTableModel.setTempList: The selected directory is the folder: " + selectedDirectory);

		// convert the String object to a File one
		final File selectedDirectoryFile = new File(selectedDirectory);

		if (selectedDirectoryFile.isDirectory()) {

			for (File childPath : selectedDirectoryFile.listFiles()) {

				if (childPath.getName().endsWith(".mp3")) {
					if (this.getSongDirectoriesList().contains(childPath.getAbsolutePath())) {
						System.out.println("directory already exists into the song directories list");
					} else {
						this.addSongDirToList(childPath.getPath());
					}

				}
			}

		}

	}

	/**
	 * Method to add a String Directory into the SongsDirectoriesList
	 * 
	 * @param childrenPath
	 */
	private void addSongDirToList(String childPath) {

		this.getSongDirectoriesList().add(childPath);
		//System.out.println("Your song directories list is " + this.getSongDirectoriesList());

	}

	/**
	 * Method to decode the SongsDirectoriesList and for every song inside of
	 * it, extract information and add it to the Rows list. This method also
	 * makes sure that there are no duplicate songs
	 * 
	 * @param songDirectoriesList
	 */
	public void decodeSongsDirectoriesList(List<String> songsDirectoriesList) {

		// Loop over the song directories list and decode every song
		for (String tempSongDir : songsDirectoriesList) {

			//System.out.println("BrowserTableModel.decodeSongDirectoriesList: A song dir is " + tempSongDir);

			// Decode song
			try {
				playerModel.decodeSong(tempSongDir);

				songName = playerModel.getSongName();
				artistName = playerModel.getArtistName();
				albumName = playerModel.getAlbumName();
				yearOfRelease = playerModel.getYearOfRelease();
				genre = playerModel.getGenre();

				miliSec = playerModel.getMiliSec();
				sec = playerModel.getSec();
				min = playerModel.getMin();

				lengthInBytes = playerModel.getLengthInBytes();

				//System.out.println("A song miliSecs are " + miliSec);

			} catch (UnsupportedAudioFileException | IOException e) {
				e.printStackTrace();
			}

			// Adding the the song information lists to the rows list
			final File tempSongDirFile = new File(tempSongDir);

			boolean containsSong = false;

			for (List<Object> aSongList : this.getRows()) {
				if (aSongList.contains(tempSongDirFile.getAbsolutePath())) {
					containsSong = true;
					break;
				}
			}

			if (!containsSong) {
				this.addRow(tempSongDirFile);
			} else {
				System.out.println("directory already exists into the Rows list");
				System.out.println("Your rows list is " + this.getRows());
			}
		}

		// update the table
		this.fireTableDataChanged();
	}

	/**
	 * REFERENCED FROM http://java-articles.info/articles/?p=637
	 * 
	 * Method to add every list of extracted song information to the rows list
	 * 
	 * @param model
	 * @param treeNode
	 */
	private void addRow(File file) {

		this.setListForASong(new ArrayList<Object>());

		this.getListForASong().add(songName);
		this.getListForASong().add(artistName);
		this.getListForASong().add(albumName);
		this.getListForASong().add(yearOfRelease);
		this.getListForASong().add(genre);
		this.getListForASong().add(min + ":" + sec);
		this.getListForASong().add(file.getPath());
		this.getListForASong().add(lengthInBytes);

		this.getRows().add(this.getListForASong());
		//System.out.println("Your Rows list is " + this.getRows());
	}

	/**
	 * Method to add the extracted from the XML song information into the list
	 * of rows
	 */
	private void addRowWhenReadingXML() {

		this.setListForASong(new ArrayList<Object>());

		this.getListForASong().add(trackTitleXML);
		this.getListForASong().add(artistXML);
		this.getListForASong().add(albumXML);
		this.getListForASong().add(yearOfReleaseXML);
		this.getListForASong().add(genreXML);
		this.getListForASong().add(durationXML);
		this.getListForASong().add(pathXML);
		this.getListForASong().add(lengthInBytesXML);

		this.getRows().add(this.getListForASong());
		//System.out.println("addRowWhenReadingXML says: ");
		//System.out.println("Your Rows list is " + this.getRows());
	}

	/**
	 * Method to read the XML file and add the extracted tags for every song to
	 * the rows list
	 * 
	 * @param xmlFile
	 */
	public void readXML(File xmlFile) {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			//System.out.println("Root element : " + doc.getDocumentElement().getNodeName());

			NodeList songNodesList = doc.getElementsByTagName("song");
			//System.out.println("----------------------------");

			for (int i = 0; i < songNodesList.getLength(); i++) {
				// get every song node from the songNodesList
				Node songNode = songNodesList.item(i);
				//System.out.println("\nCurrent Element :" + songNode.getNodeName());

				// Get the element from the songNode
				Element songElement = (Element) songNode;

				// Get the text content of all tag names contained inside the
				// songElement
				trackTitleXML = songElement.getElementsByTagName("trackTitle").item(0).getTextContent();
				artistXML = songElement.getElementsByTagName("artist").item(0).getTextContent();
				albumXML = songElement.getElementsByTagName("album").item(0).getTextContent();
				yearOfReleaseXML = songElement.getElementsByTagName("yearOfRelease").item(0).getTextContent();
				genreXML = songElement.getElementsByTagName("genre").item(0).getTextContent();
				durationXML = songElement.getElementsByTagName("duration").item(0).getTextContent();
				pathXML = songElement.getElementsByTagName("path").item(0).getTextContent();
				lengthInBytesXML = songElement.getElementsByTagName("lengthInBytes").item(0).getTextContent();

				// add the information above into the rows list
				this.addRowWhenReadingXML();

			}

			// update the table
			this.fireTableDataChanged();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * GETTERS AND SETTERS
	 */

	public void removeRows() {
		this.getRows().clear();
	}

	public List<List<Object>> getRows() {
		return rows;
	}

	public void setRows(List<List<Object>> rows) {
		this.rows = rows;
	}

	public List<String> getSongDirectoriesList() {
		return songsDirectoriesList;
	}

	public void setSongDirectoriesList(List<String> tempList) {
		this.songsDirectoriesList = tempList;
	}

	public List<Object> getListForASong() {
		return listForASong;
	}

	public void setListForASong(List<Object> list) {
		this.listForASong = list;
	}

	public int getLengthInBytes() {
		return lengthInBytes;
	}

}// End of class BrowserTableModel
