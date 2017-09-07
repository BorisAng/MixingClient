package thisPackageIsNotUsed;

// package controllers;
//
// import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;
//
// import javax.swing.JPopupMenu;
// import javax.swing.JTable;
//
// import views.BrowserTablePanelView;
//
/// **
// * REFERENCES FROM :
// *
// http://stackoverflow.com/questions/3558293/java-swing-jtable-right-click-menu
// * -how-do-i-get-it-to-select-aka-highlight-t
// *
// * @author bba278
// *
// */
// public class TableRightClickListener implements MouseListener {
//
// private BrowserTablePanelView browserTablePanel;
// private JTable bTable;
//
// public TableRightClickListener(BrowserTablePanelView bTPanel) {
//
// this.browserTablePanel = bTPanel;
// this.bTable = browserTablePanel.getbTable();
// }
//
// @Override
// public void mouseClicked(MouseEvent arg0) {
// // TODO Auto-generated method stub
//
// }
//
// @Override
// public void mouseEntered(MouseEvent arg0) {
// // TODO Auto-generated method stub
//
// }
//
// @Override
// public void mouseExited(MouseEvent arg0) {
// // TODO Auto-generated method stub
//
// }
//
// @Override
// public void mousePressed(MouseEvent arg0) {
// // TODO Auto-generated method stub
//
// }
//
// @Override
// public void mouseReleased(MouseEvent evt) {
//
// if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
// int r = bTable.rowAtPoint(evt.getPoint());
// System.out.println("Right clicked on row number:" + r);
// if (r >= 0 && r < bTable.getRowCount()) {
// bTable.setRowSelectionInterval(r, r);
// } else {
// bTable.clearSelection();
// }
//
// int rowindex = bTable.getSelectedRow();
// if (rowindex < 0)
// return;
// if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable) {
// browserTablePanel.initializePopupMenu();
// browserTablePanel.getPopUpMenu().show(evt.getComponent(), evt.getX(),
// evt.getY());
//
// }
// }
//
// }
//
// }
