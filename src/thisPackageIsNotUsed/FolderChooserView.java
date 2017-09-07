package thisPackageIsNotUsed;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class FolderChooserView {

	private JPanel folderChooserPanel;
	private JFileChooser chooser;
	private int result;

	// Constructor
	public FolderChooserView() {

		initializePart();
	}

	private void initializePart() {

		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		chooser.setDialogTitle("Choose a directory");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		// Show the Dialog Window
		result = chooser.showOpenDialog(folderChooserPanel);

	}

	public int getResult() {
		return result;
	}

	public JFileChooser getChooser() {
		return chooser;
	}

}
