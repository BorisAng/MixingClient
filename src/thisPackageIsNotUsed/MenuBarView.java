package thisPackageIsNotUsed;

// package notUsed;
//
// import javax.swing.JMenu;
// import javax.swing.JMenuBar;
// import javax.swing.JMenuItem;
//
// import controllers.MenuImportFolderListener;
//
// public class MenuBarView {
//
// private MainFrameView appFrame;
//
// public MenuBarView(MainFrameView frm) {
// this.appFrame = frm;
// }
//
// /**
// * Method to set the GUI of the menu
// * @return
// */
// public JMenuBar initializePart() {
// JMenuBar menuBar;
// JMenu menu, submenu;
// JMenuItem menuItem;
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
// //Add menuItems to the sub menu
// menuItem = new JMenuItem("Import Folder or File");
// menuItem.addActionListener(new MenuImportFolderListener(appFrame));
// submenu.add(menuItem);
//
// //Add the sub menu to the menu
// menu.add(submenu);
//
// return menuBar;
//
// }
//
// }
