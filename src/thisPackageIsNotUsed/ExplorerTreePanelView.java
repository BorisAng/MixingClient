package thisPackageIsNotUsed;

// package notUsed;
//
// import javax.swing.JScrollPane;
// import javax.swing.JTree;
// import javax.swing.tree.TreeSelectionModel;
//
// import views.MainFrameView;
//// import controllers.NodeExpansionListener;
//// import controllers.NodeSelectionListener;
//
/// **
// * A View class for the Explorer Tree Panel
// *
// * @author user
// *
// */
// public class ExplorerTreePanelView {
//
// /**
// * Variables
// */
// private MainFrameView appFrame;
// private BrowserTreeModel treeModel;
//
// private JScrollPane bTreeScrollPane;
// private JTree bTree;
//
// /**
// *
// * Constructor to create the ExplorerTreePanel for a specified JFrame and
// * Tree Model
// *
// * @param frame
// * @param browserModel
// * @param model
// */
// public ExplorerTreePanelView(MainFrameView frame, BrowserTreeModel model) {
// this.appFrame = frame;
// this.treeModel = model;
//
// initializePart();
// }
//
// /**
// * Initialise the contents of the panel and add the controllers i.e
// * listeners
// */
// private void initializePart() {
//
// bTree = new JTree(treeModel.createTreeModel());
//
// bTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
//
// bTree.addTreeExpansionListener(new NodeExpansionListener(treeModel));
//
// bTree.addTreeSelectionListener(new NodeSelectionListener(appFrame,
// treeModel));
//
// bTreeScrollPane = new JScrollPane(bTree);
//
// }
//
// /**
// * Getter for the browserTree
// * @return
// */
// public JTree getTree() {
// return bTree;
// }
//
// /**
// * Getter for the browserTree ScrollPane
// * @return
// */
// public JScrollPane getTreeScrollPane() {
// return bTreeScrollPane;
// }
//
// }
