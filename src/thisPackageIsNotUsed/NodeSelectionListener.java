package thisPackageIsNotUsed;

// package notUsed;
//
// import java.io.File;
//
// import javax.swing.event.TreeSelectionEvent;
// import javax.swing.event.TreeSelectionListener;
// import javax.swing.tree.DefaultMutableTreeNode;
// import javax.swing.tree.TreePath;
//
// import views.MainFrameView;
//
// public class NodeSelectionListener implements TreeSelectionListener {
//
// private MainFrameView appFrame;
// private BrowserTreeModel treeModel;
//
// private DefaultMutableTreeNode node;
// private TreeNodeModel treeNode;
// private File file;
//
// public NodeSelectionListener(MainFrameView frm, BrowserTreeModel mdl) {
// this.appFrame = frm;
// this.treeModel = mdl;
// }
//
// @Override
// public void valueChanged(TreeSelectionEvent event) {
//
// // Get the last component of the path
// node = getTreeNode(event.getPath());
//
// // Get the user object from node and save it as a treeNode
// treeNode = getUserObject(node);
//
// // Get the treeNode path into a file
// file = treeNode.getPath();
//
//// if (file.isDirectory()) {
//// appFrame.setDefaultTableModel(node);
//// }
//
// }
//
// private TreeNodeModel getUserObject(DefaultMutableTreeNode node) {
// if (node == null)
// return null;
// Object obj = node.getUserObject();
//
// if (obj instanceof TreeNodeModel)
// return (TreeNodeModel) obj;
// else
// return null;
// }
//
// private DefaultMutableTreeNode getTreeNode(TreePath path) {
// return (DefaultMutableTreeNode) path.getLastPathComponent();
// }
//
// }
