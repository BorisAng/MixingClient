package thisPackageIsNotUsed;

// package models;
//
// import java.io.IOException;
//
// import javax.sound.sampled.UnsupportedAudioFileException;
// import javax.swing.JFrame;
// import javax.swing.JMenu;
// import javax.swing.JMenuBar;
// import javax.swing.JMenuItem;
// import javax.swing.JPanel;
//
// import controllers.MenuImportFolderListener;
// import net.miginfocom.swing.MigLayout;
//
/// **
// * A View class for the Main Frame
// */
// public class MainFrameView {
//
// /*
// * Fields
// */
// private JFrame frame;
// private JPanel mainPanel;
//
// private BrowserTablePanelView browserTablePanel;
// private BottomPlayerPanelView bottomPlayerPanel;
// private TopPlayerPanelView topPlayerPanel;
// private MenuImportFolderListener menuListener;
//
// /**
// * Constructor to create the MainFrame
// *
// * @throws IOException
// * @throws UnsupportedAudioFileException
// */
// public MainFrameView() throws UnsupportedAudioFileException, IOException {
//
// initializePart();
//
// }
//
// /**
// * Initialise the contents of the frame.
// *
// * @throws IOException
// * @throws UnsupportedAudioFileException
// */
// private void initializePart() throws UnsupportedAudioFileException,
// IOException {
//
// frame = new JFrame();
// frame.setTitle("Music Library Organiser");
// frame.setBounds(100, 100, 1000, 800);
// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
// createMainPanel();
//
// frame.setJMenuBar(this.initializeMenuBarPart());
//
// frame.add(mainPanel);
// frame.setVisible(true);
//
// }
//
// /**
// * Initialise the contents of the main panel by adding the
// * BrowserTablePanelView, BottomPlayerPanelView
// *
// * @throws IOException
// * @throws UnsupportedAudioFileException
// */
// private void createMainPanel() throws UnsupportedAudioFileException,
// IOException {
//
// mainPanel = new JPanel();
// mainPanel.setLayout(new MigLayout("DEBUG", "", ""));
// //mainPanel.setLayout(new MigLayout("DEBUG", "[grow]",
// "[grow][grow][grow]"));
//
// // // Create the TopPlayerPanel and add it to the MainPanel
// // topPlayerPanel = new TopPlayerPanelView(this);
// // mainPanel.add(topPlayerPanel.getTopPlayerPanel(), "cell 0 0, grow");
//
// // Create the MusicPlayerPanel and add it to the MainPanel
// bottomPlayerPanel = new BottomPlayerPanelView(this);
// mainPanel.add(bottomPlayerPanel.getmPlayerPanel(), "cell 0 1, grow,push");
//
// // Create the ExplorerTablePanel and add it to the MainPanel
// browserTablePanel = new BrowserTablePanelView(bottomPlayerPanel);
// mainPanel.add(browserTablePanel.getbTableScrollPane(), "cell 0 2, grow");
//
// }
//
// /**
// * Method to create the MenuBar
// *
// */
// private JMenuBar initializeMenuBarPart() {
// final JMenuBar menuBar;
// final JMenu menu, submenu;
// final JMenuItem menuItem;
//
// // Create the menu bar.
// menuBar = new JMenuBar();
//
// // Build the first menu.
// menu = new JMenu("File");
//
// // Add the menu to the menu bar
// menuBar.add(menu);
//
// // A sub menu
// menu.addSeparator();
// submenu = new JMenu("Import");
//
// // Add menuItems to the sub menu
// menuItem = new JMenuItem("Import Folder or File");
// menuListener = new MenuImportFolderListener(browserTablePanel);
// menuItem.addActionListener(menuListener);
// submenu.add(menuItem);
//
// // Add the sub menu to the menu
// menu.add(submenu);
//
// return menuBar;
//
// }
//
// public BrowserTablePanelView getBrowserTablePanel() {
// return browserTablePanel;
// }
//
// }
