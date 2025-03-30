package Database;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableMouseListener extends MouseAdapter {
    private MyTableModel model;
    private JTable table;

    public TableMouseListener(MyTableModel model, JTable table) {
        this.model = model;
        this.table = table;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            int row = table.rowAtPoint(e.getPoint());
            table.setRowSelectionInterval(row, row);
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem deleteItem = new JMenuItem("Supprimer");
            deleteItem.addActionListener(ev -> {
                int cin = (int) model.getValueAt(row, 0);
                model.deleteStudent(cin);
            });
            popupMenu.add(deleteItem);
            popupMenu.show(table, e.getX(), e.getY());
        }
    }
}
