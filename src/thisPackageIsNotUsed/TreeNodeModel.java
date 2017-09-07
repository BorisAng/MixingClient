package thisPackageIsNotUsed;

import java.io.File;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * This Class is a wrapper around a file and provides a method for generating a
 * tree node children
 * 
 * @author user
 *
 */
public class TreeNodeModel {

	protected File path;

	private DefaultMutableTreeNode treeNode;

	public TreeNodeModel(File file) {

		this.setPath(file);

	}

	public File getPath() {
		return path;
	}

	public void setPath(File path) {
		this.path = path;
	}

	@Override
	public String toString() {
		if (path.getName().length() > 0) {
			return path.getName();

		} else {
			return path.getPath();
			// return file.getAbsolutePath();
		}
	}

	/**
	 * REFERENCED FROM Swing Second Edition, Chapter 17
	 * 
	 * Used by the Jtree Constructor
	 */

	public boolean generateChildren(DefaultMutableTreeNode parent) {

		DefaultMutableTreeNode flag = (DefaultMutableTreeNode) parent.getFirstChild();

		if (flag == null) {
			return false;
		}

		File[] files = listFiles();
		// System.out.println(Arrays.toString(files));

		if (files == null) {
			return true;
		}

		Vector<TreeNodeModel> v = new Vector<TreeNodeModel>();

		for (int k = 0; k < files.length; k++) {
			File f = files[k];
			if (!(f.isDirectory()))
				continue;

			TreeNodeModel newNode = new TreeNodeModel(f);

			boolean isAdded = false;
			for (int i = 0; i < v.size(); i++) {
				TreeNodeModel nd = v.elementAt(i);
				if (newNode.compareTo(nd) < 0) {
					v.insertElementAt(newNode, i);
					isAdded = true;
					break;
				}
			}
			if (!isAdded)
				v.addElement(newNode);
		}

		for (int i = 0; i < v.size(); i++) {
			TreeNodeModel nd = v.elementAt(i);
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(nd);
			parent.add(node);

			if (nd.hasSubDirs()) {
				node.add(new DefaultMutableTreeNode(new Boolean(true)));
			}
		}
		return true;

	}

	/**
	 * REFERENCED FROM Swing Second Edition, Chapter 17
	 * 
	 * Used by generateChildren.
	 * 
	 * @return
	 */
	private boolean hasSubDirs() {
		File[] files = listFiles();
		if (files == null)
			return false;
		for (int k = 0; k < files.length; k++) {
			if (files[k].isDirectory())
				return true;
		}
		return false;
	}

	/**
	 * REFERENCED FROM Swing Second Edition, Chapter 17
	 * 
	 * Used by generateChildren.
	 * 
	 * @return
	 */
	private int compareTo(TreeNodeModel toCompare) {

		return path.getName().compareToIgnoreCase(toCompare.path.getName());

	}

	/**
	 * REFERENCED FROM Swing Second Edition, Chapter 17
	 * 
	 * Used by generateChildren.
	 * 
	 * @return
	 */
	private File[] listFiles() {
		if (!path.isDirectory())
			// System.out.println(path.getName());
			return null;
		try {
			return path.listFiles();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error reading directory " + path.getAbsolutePath(), null,
					JOptionPane.WARNING_MESSAGE);
			return null;
		}

	}

}
