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

    JTextField titleField, seasonField, episodeField, episodeTimeField;

    JTableBehavior(JTextField titleField, JTextField seasonField, JTextField episodeField, JTextField episodeTimeField){
        this.titleField = titleField;
        this.seasonField = seasonField;
        this.episodeField = episodeField;
        this.episodeTimeField = episodeTimeField;

        createNotEditableDefaultTableModel();

        sorter = new TableRowSorter<>(model);

        setTableModel(model);
        setRowSorter(sorter);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setTableName("Shows Table");
        selectionModelListener();

        table.setBorder(new LineBorder(Color.BLACK));

    }

    private void setSelectionMode(int selectionMode) {
        table.setSelectionMode(selectionMode);
    }

    void setTableName(String name){
        table.setName(name);
    }

    void setTableModel(TableModel model){
        table.setModel(model);
    }

    void setRowSorter(TableRowSorter<DefaultTableModel> sorter){
        table.setRowSorter(sorter);
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
                episodeTimeField.setText(show.time);
            }
        });
    }
}
