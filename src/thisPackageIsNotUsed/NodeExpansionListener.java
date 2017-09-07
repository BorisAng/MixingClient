package thisPackageIsNotUsed;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class NodeExpansionListener implements TreeExpansionListener {

	private DefaultMutableTreeNode node;
	private BrowserTreeModel treeModel;
	private TreeNodeModel treeNode;

	public NodeExpansionListener(BrowserTreeModel treeModel) {
		this.treeModel = treeModel;

	}

	@Override
	public void treeExpanded(TreeExpansionEvent event) {

		// LastPathComponent
		node = getTreeNode(event.getPath());

		treeNode = getUserObject(node);

		// GENERATE NODES
		if (treeNode != null && treeNode.generateChildren(node)) {
			treeModel.getMy_model().reload(node);
		}

		if (node == null) {
			return;
		}

	}

	@Override
	public void treeCollapsed(TreeExpansionEvent event) {

	}

	private TreeNodeModel getUserObject(DefaultMutableTreeNode node) {
		if (node == null)
			return null;
		Object obj = node.getUserObject();

		if (obj instanceof TreeNodeModel)
			return (TreeNodeModel) obj;
		else
			return null;
	}

	private DefaultMutableTreeNode getTreeNode(TreePath path) {
		return (DefaultMutableTreeNode) path.getLastPathComponent();
	}
}
