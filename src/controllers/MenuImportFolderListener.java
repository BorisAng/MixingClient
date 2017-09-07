package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import models.BrowserTableModel;
import views.BrowserTablePanelView;

/**
 * Listener(Controller) responds when the "Import Folder" is pressed.
 * 
 * @author bba278
 *
 */
public class MenuImportFolderListener implements ActionListener {

	/*
	 * Fields
	 */
	private String selectedDirectory;

	private BrowserTablePanelView browserTablePanel;
	private BrowserTableModel browserTableModel;

	private JPanel folderChooserPanel;
	private JFileChooser chooser;
	private int result;

	private List<List<Object>> rows;

	private Document doc;

	private File fileXML;

	/**
	 * Constructor uses the BrowserTablePanelView and assigns a reference to the
	 * BrowserTableModel
	 * 
	 * @param bTPanel
	 */
	public MenuImportFolderListener(BrowserTablePanelView bTPanel) {

		this.browserTablePanel = bTPanel;
		this.browserTableModel = browserTablePanel.getBrowserTableModel();
	}

	/**
	 * On Importing a folder
	 * 1. InitializeFolderChooser
	 * 2. Acquire the selected by the user directory as a String
	 * 3. Store all the directories ending with .mp3 into a List of Strings
	 * 4. Decode that String List of Directories and extract song information
	 * 5. Get a reference to the List of rows of the table
	 * 6. Create the structure of the XML file
	 * 7. Create the file.xml
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		System.out.println("Pressed on Import Folder");

		this.initializeFolderChooser();

		if (result == JFileChooser.APPROVE_OPTION) {

			// Put the selected directory into a String field
			selectedDirectory = chooser.getSelectedFile().getAbsolutePath();

		}

		// store all the directories ending with .mp3 into a List of Strings
		browserTableModel.createSongsDirectoriesList(selectedDirectory);

		// decode a String List of Directories and extract song information
		browserTableModel.decodeSongsDirectoriesList(browserTableModel.getSongDirectoriesList());

		rows = browserTableModel.getRows();

		try {
			this.structureXML();
			this.createXMLFile();
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Creates the JFileChooser which allows the user to select a directory where his/hers music is stored
	 * and adds it to a JPanel. It allows the selection of both files and directories but
	 * for now if a single file is selected it is NOT added to the table.
	 * Also ONLY files ending with .mp3 are added to the table
	 */
	private void initializeFolderChooser() {
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		chooser.setDialogTitle("Choose a directory");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		// Show the Dialog Window
		result = chooser.showOpenDialog(folderChooserPanel);
	}

	/**
	 * Method to create the structure of the XML file
	 * 
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	private void structureXML() throws ParserConfigurationException, TransformerException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("library");
		doc.appendChild(rootElement);

		for (int i = 0; i < rows.size(); i++) {
			List<Object> list = rows.get(i);
			for (int j = 0; j < list.size(); j += 8) {

				// staff elements
				Element song = doc.createElement("song");
				rootElement.appendChild(song);

				// set attribute to song element
				Attr attribute = doc.createAttribute("id");
				attribute.setValue(String.valueOf(i));
				song.setAttributeNode(attribute);

				Element songName = doc.createElement("trackTitle");
				songName.appendChild(doc.createTextNode(String.valueOf(list.get(j))));
				song.appendChild(songName);

				Element artist = doc.createElement("artist");
				artist.appendChild(doc.createTextNode(String.valueOf(list.get(j + 1))));
				song.appendChild(artist);

				Element album = doc.createElement("album");
				album.appendChild(doc.createTextNode(String.valueOf(list.get(j + 2))));
				song.appendChild(album);

				Element year = doc.createElement("yearOfRelease");
				year.appendChild(doc.createTextNode(String.valueOf(list.get(j + 3))));
				song.appendChild(year);

				Element genre = doc.createElement("genre");
				genre.appendChild(doc.createTextNode(String.valueOf(list.get(j + 4))));
				song.appendChild(genre);

				Element duration = doc.createElement("duration");
				duration.appendChild(doc.createTextNode(String.valueOf(list.get(j + 5))));
				song.appendChild(duration);

				Element path = doc.createElement("path");
				path.appendChild(doc.createTextNode(String.valueOf(list.get(j + 6))));
				song.appendChild(path);

				Element lengthInBytes = doc.createElement("lengthInBytes");
				lengthInBytes.appendChild(doc.createTextNode(String.valueOf(list.get(j + 7))));
				song.appendChild(lengthInBytes);
			}
		}
	}

	/**
	 * Method to create the file.xml and save it to the system
	 * 
	 * @throws TransformerException
	 */
	private void createXMLFile() throws TransformerException {

		// write the content into xml file
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMSource source = new DOMSource(doc);
		setFileXML(new File("file.xml"));
		StreamResult result = new StreamResult(getFileXML());

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		transformer.transform(source, result);

		System.out.println("File saved!!");
	}

	/*
	 * GETTERS AND SETTERS
	 */
	
	public String getSelectedDirectory() {
		return selectedDirectory;
	}

	public File getFileXML() {
		return fileXML;
	}

	public void setFileXML(File fileXML) {
		this.fileXML = fileXML;
	}

}//End of class MenuImportFolderListener
