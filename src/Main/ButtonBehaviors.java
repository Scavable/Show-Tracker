package Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ButtonBehaviors {

    JButton seasonPlusButton = new JButton("+");
    JButton seasonMinusButton = new JButton("-");
    JButton episodePlusButton = new JButton("+");
    JButton episodeMinusButton = new JButton("-");
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");
    JButton deleteButton = new JButton("Delete");
    JButton addButton = new JButton("Add");

    JTextField titleField, seasonField, episodeField;
    JTable table;
    DefaultTableModel model;
    JPanel panelInfo;
    ArrayList<ShowInfo> showInfoArrayList;

    ButtonBehaviors(JTextField titleField, JTextField seasonField, JTextField episodeField, JPanel panelInfo, JTable table, DefaultTableModel model, ArrayList<ShowInfo> showInfoArrayList){

        this.showInfoArrayList = showInfoArrayList;
        this.titleField = titleField;
        this.seasonField = seasonField;
        this.episodeField = episodeField;
        this.panelInfo = panelInfo;
        this.table = table;
        this.model = model;

        seasonMinusButtonBehavior();
        seasonPlusButtonBehavior();
        episodeMinusButtonBehavior();
        episodePlusButtonBehavior();
        addButtonBehavior();
        saveButtonBehavior();
        cancelButtonBehavior();
        deleteButtonBehavior();

    }

    private void seasonPlusButtonBehavior() {
        seasonPlusButton.addActionListener(e -> {
            if (seasonField.getText().matches("[0-9]+")) {
                int season = Integer.parseInt(seasonField.getText());
                season = season + 1;
                seasonField.setText(String.valueOf(season));
            }
        });
    }

    private void seasonMinusButtonBehavior() {
        seasonMinusButton.addActionListener(e -> {
            if (seasonField.getText().matches("[0-9]+")) {
                if (!seasonField.getText().matches("0")) {
                    int season = Integer.parseInt(seasonField.getText());
                    season = season - 1;
                    seasonField.setText(String.valueOf(season));
                }
            }
        });
    }

    private void episodePlusButtonBehavior() {
        episodePlusButton.addActionListener(e -> {
            if (episodeField.getText().matches("[0-9]+")) {
                int episode = Integer.parseInt(episodeField.getText());
                episode = episode + 1;
                episodeField.setText(String.valueOf(episode));
            }
        });
    }

    private void episodeMinusButtonBehavior() {
        episodeMinusButton.addActionListener(e -> {
            if (episodeField.getText().matches("[0-9]+")) {
                if (!episodeField.getText().matches("0")) {
                    int episode = Integer.parseInt(episodeField.getText());
                    episode = episode - 1;
                    episodeField.setText(String.valueOf(episode));
                }
            }
        });
    }

    private void saveButtonBehavior() {
        saveButton.setName("Save Button");
        saveButton.setPreferredSize(new Dimension((int) panelInfo.getPreferredSize().getWidth() / 6, (int) panelInfo.getPreferredSize().getHeight() / 12));

        saveButton.addActionListener(e -> {
            if(model.getRowCount() != 0){
                if(table.getSelectedRow() != -1){
                    ShowInfo show = showInfoArrayList.get(table.getSelectedRow());
                    show.title = titleField.getText();
                    show.season = Integer.parseInt(seasonField.getText());
                    show.episode = Integer.parseInt(episodeField.getText());
                    table.updateUI();
                }
            }
        });
    }

    private void cancelButtonBehavior() {
        cancelButton.setName("Cancel Button");
        cancelButton.setPreferredSize(new Dimension((int) panelInfo.getPreferredSize().getWidth() / 6, (int) panelInfo.getPreferredSize().getHeight() / 12));

        cancelButton.addActionListener(e -> {
            if (model.getRowCount() != 0) {
                if(table.getSelectedRow() != -1){
                    ShowInfo temp = showInfoArrayList.get(table.getSelectedRow());
                    titleField.setText(temp.getTitle());
                    seasonField.setText(String.valueOf(temp.getSeason()));
                    episodeField.setText(String.valueOf(temp.getEpisode()));
                }
            }
        });
    }

    private void deleteButtonBehavior() {
        deleteButton.setName("Delete Button");
        deleteButton.setPreferredSize(new Dimension((int) panelInfo.getPreferredSize().getWidth() / 6, (int) panelInfo.getPreferredSize().getHeight() / 12));

        deleteButton.addActionListener(e -> {
            if (model.getRowCount() != 0) {
                if(table.getSelectedRow() != -1){
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete this show?", "Delete Show", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (choice == 0) {
                        int index = table.getSelectedRow();
                        showInfoArrayList.remove(index);
                        model.removeRow(index);
                    }
                }
            }
        });
    }

    private void addButtonBehavior() {
        addButton.setName("Add Button");
        addButton.addActionListener(e -> {
            ShowInfo show = new ShowInfo();
            show.setTitle("Season");
            showInfoArrayList.add(show);
            int size = showInfoArrayList.size();
            model.addRow(new ShowInfo[]{showInfoArrayList.get(size - 1)});
            table.changeSelection(model.getRowCount() - 1, 1, false, false);
        });
    }
}
