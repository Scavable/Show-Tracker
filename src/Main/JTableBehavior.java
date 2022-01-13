package Main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class JTableBehavior {

    JTable table = new JTable(0, 1);
    DefaultTableModel model;
    ListSelectionModel selectionModel = table.getSelectionModel();
    TableRowSorter<DefaultTableModel> sorter;

    JTextField titleField, seasonField, episodeField;

    JTableBehavior(JTextField titleField, JTextField seasonField, JTextField episodeField){
        this.titleField = titleField;
        this.seasonField = seasonField;
        this.episodeField = episodeField;

        createNotEditableDefaultTableModel();

        setTableModel(model);
        sorter = new TableRowSorter<>(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSorter(sorter);

        setTableName("Shows Table");

        table.setBorder(new LineBorder(Color.BLACK));
        selectionModelListener();

    }

    void setTableName(String name){
        table.setName(name);
    }

    void setTableModel(TableModel model){
        table.setModel(model);
    }

    void createNotEditableDefaultTableModel(){
        model = new DefaultTableModel(0, 1) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };
    }

    void selectionModelListener(){
        selectionModel.addListSelectionListener(e -> {
            if (table.getSelectedRow() != -1) {
                ShowInfo show = (ShowInfo) model.getValueAt(table.getSelectedRow(), 0);
                titleField.setText(show.title);
                seasonField.setText(String.valueOf(show.season));
                episodeField.setText(String.valueOf(show.episode));
            }
        });
    }
}
