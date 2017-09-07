package thisPackageIsNotUsed;

import java.io.File;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import thisPackageIsNotUsed.TreeNodeModel;

/**
 * REFERENCED FROM Swing Second Edition Chapter 17
 * 
 * @author user
 *
 */
public class BrowserTreeModel {

	private FileSystemView fileSystemView;

	private DefaultTreeModel treeModel;

	// Constructor
	public BrowserTreeModel() {
		this.fileSystemView = FileSystemView.getFileSystemView();

	}

	// Method
	public DefaultTreeModel createTreeModel() {

		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();

		// Get roots in array
		File[] roots = fileSystemView.getRoots(); // with fileSystemView

		DefaultMutableTreeNode node;
		for (int i = 0; i < roots.length; i++) {
			node = new DefaultMutableTreeNode(new TreeNodeModel(roots[i]));
			rootNode.add(node);

			node.add(new DefaultMutableTreeNode(new TreeNodeModel(roots[i])));
		}

		return treeModel = new DefaultTreeModel(rootNode);
	}

	public FileSystemView getFileSystemView() {
		return fileSystemView;
	}

	public DefaultTreeModel getMy_model() {
		return treeModel;
	}

}
