package views;

import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import views.BrowserTablePanelView;

import controllers.MenuImportFolderListener;

/**
 * A view class for the Main Frame
 * 
 * @author bba278
 *
 */
public class MainFrameView {

	private JFrame frame;
	private JPanel mainPanel;

	private MusicPlayerPanelView musicPlayerPanel;
	private BrowserTablePanelView browserTablePanel;
	private MenuImportFolderListener menuListener;

	/**
	 * Constructor to create the Main Frame.Makes a call to initializePart()
	 * 
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 */
	public MainFrameView() throws UnsupportedAudioFileException, IOException {

		this.initializePart();
	}

	/**
	 * Initialise the contents of the frame.
	 * 
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 */
	private void initializePart() throws UnsupportedAudioFileException, IOException {

		frame = new JFrame();
		frame.setTitle("Music Library Organiser");
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setLayout(new MigLayout("debug", "[grow]", "[grow][grow]"));
		frame.getContentPane().add(mainPanel);

		// Create the MusicPlayerPanel and add it to the MainPanel
		musicPlayerPanel = new MusicPlayerPanelView();
		mainPanel.add(musicPlayerPanel.getMusicPlayerPanel(), "cell 0 0,grow");

		// Create the BrowserTablePanel and add it to the MainPanel
		browserTablePanel = new BrowserTablePanelView(musicPlayerPanel);
		mainPanel.add(browserTablePanel.getbTableScrollPane(), "cell 0 1,grow");

		// Create the Menu and add it to the Main Frame
		frame.setJMenuBar(this.initializeMenuBarPart());

		frame.setVisible(true);

	}

	/**
	 * Method to create the MenuBar
	 * 
	 * @return
	 */
	private JMenuBar initializeMenuBarPart() {
		final JMenuBar menuBar;
		final JMenu menu, submenu;
		final JMenuItem menuItem;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("File");

		// Add the menu to the menu bar
		menuBar.add(menu);

		// A sub menu
		menu.addSeparator();
		submenu = new JMenu("Import");

		// Add menuItems to the sub menu
		menuItem = new JMenuItem("Import Folder");
		menuListener = new MenuImportFolderListener(browserTablePanel);
		menuItem.addActionListener(menuListener);
		submenu.add(menuItem);

		// Add the sub menu to the menu
		menu.add(submenu);

		return menuBar;

	}

}
