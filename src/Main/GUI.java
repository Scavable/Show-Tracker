package Main;

import Files.ShowsFile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI {

    public static void main(String[] args) {
        new GUI();
    }

    FrameBehavior frameBehavior;
    ButtonBehaviors buttons;
    JTableBehavior table;


    private final JPanel panelList = new JPanel();
    private final JPanel panelInfo = new JPanel();

    JTextField searchBar = new JTextField("Search");
    static ArrayList<ShowInfo> showInfoArrayList;

    public JLabel titleLabel = new JLabel("Title");
    public JLabel seasonLabel = new JLabel("Season");
    public JLabel episodeLabel = new JLabel("Episode");
    public JTextField titleField = new JTextField("Title");
    public JTextField seasonField = new JTextField("Season");
    public JTextField episodeField = new JTextField("Episode");

    public JLabel episodeTimeLabel = new JLabel("Episode Length");
    public JTextField episodeTimeField = new JTextField("hh:mm:ss");

    GUI() {
        //Populate shows array
        showInfoArrayList = ShowsFile.readFile();

        table = new JTableBehavior(titleField, seasonField, episodeField, episodeTimeField);

        //Set names
        panelList.setName("List");
        panelInfo.setName("Info");
        searchBar.setName("Search Bar");

        //Behaviors/init
        frameBehavior = new FrameBehavior();
        panelListBehavior();
        panelInfoBehavior();
        buttons = new ButtonBehaviors(titleField, seasonField, episodeField, episodeTimeField, panelInfo, table.table, table.model, showInfoArrayList);
        searchBarBehavior();
        titleLabelBehavior();
        titleFieldBehavior();
        seasonLabelBehavior();
        seasonFieldBehavior();
        episodeLabelBehavior();
        episodeFieldBehavior();
        episodeTimeLabelBehavior();
        episodeTimeFieldBehavior();

        frameBehavior.addToFrame(panelList, panelInfo);
        addToPanelList();
        addToPanelInfo();

        for (ShowInfo show : showInfoArrayList) {
            addShowToTable(show);
        }

    }

    private void episodeTimeLabelBehavior() {
        episodeTimeLabel.setPreferredSize(new Dimension((int) panelInfo.getPreferredSize().getWidth() / 3, (int) panelInfo.getPreferredSize().getHeight() / 10));
        String font = episodeTimeLabel.getFont().getFontName();
        int style = episodeTimeLabel.getFont().getStyle();
        int size = episodeTimeLabel.getFont().getSize();
        size = 20;
        episodeTimeLabel.setFont(new Font(font, style, size));
    }

    private void episodeTimeFieldBehavior() {
        episodeTimeField.setPreferredSize(new Dimension((int) panelInfo.getPreferredSize().getWidth() / 3, (int) panelInfo.getPreferredSize().getHeight() / 12));

        String font = episodeTimeField.getFont().getFontName();
        int style = episodeTimeField.getFont().getStyle();
        int size = episodeTimeField.getFont().getSize();
        size = 20;
        episodeTimeField.setFont(new Font(font, style, size));

        episodeTimeField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                episodeTimeField.addKeyListener(new KeyAdapter() {
                    String temp = episodeTimeField.getText();

                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (!(e.getKeyCode() >= KeyEvent.VK_0 && e.getKeyCode() <= KeyEvent.VK_9) && !(e.getKeyCode() >= KeyEvent.VK_NUMPAD0 && e.getKeyCode() <= KeyEvent.VK_NUMPAD9) && !(e.getKeyCode() == KeyEvent.VK_BACK_SPACE))
                            episodeTimeField.setText(temp);

                        temp = episodeTimeField.getText();
                    }
                });
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(episodeTimeField.getText().isBlank()){
                    episodeTimeField.setText("0");
                }
            }
        });
    }

    private void panelInfoBehavior() {
        panelInfo.setPreferredSize(new Dimension((int) (frameBehavior.frame.getContentPane().getWidth() * 0.8), frameBehavior.frame.getContentPane().getHeight()));
        panelInfo.setBackground(Color.LIGHT_GRAY);
    }

    private void panelListBehavior() {
        panelList.setPreferredSize(new Dimension((int) (frameBehavior.frame.getContentPane().getWidth() * 0.2), frameBehavior.frame.getContentPane().getHeight()));

    }

    private void searchBarBehavior() {
        searchBar.setPreferredSize(new Dimension((int) panelList.getPreferredSize().getWidth() - 2, (int) (panelList.getPreferredSize().getHeight() * 0.05)));

        searchBar.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                searchBar.selectAll();
                searchBar.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        if(searchBar.getText().isBlank()){
                            table.sorter.setRowFilter(null);
                        }else{
                            table.sorter.setRowFilter(RowFilter.regexFilter(searchBar.getText()));
                        }
                    }
                });
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(searchBar.getText().isBlank()){
                    searchBar.setText("Search");
                }
            }
        });
    }

    private void titleLabelBehavior() {
        titleLabel.setPreferredSize(new Dimension((int) panelInfo.getPreferredSize().getWidth() / 3, (int) panelInfo.getPreferredSize().getHeight() / 10));
        String font = titleLabel.getFont().getFontName();
        int style = titleLabel.getFont().getStyle();
        int size = titleLabel.getFont().getSize();
        size = 30;
        titleLabel.setFont(new Font(font, style, size));

    }

    private void titleFieldBehavior() {
        titleField.setPreferredSize(new Dimension((int) panelInfo.getPreferredSize().getWidth() / 3, (int) panelInfo.getPreferredSize().getHeight() / 12));
        String font = titleField.getFont().getFontName();
        int style = titleField.getFont().getStyle();
        int size = titleField.getFont().getSize();
        size = 20;
        titleField.setFont(new Font(font, style, size));
    }

    private void seasonLabelBehavior() {
        seasonLabel.setPreferredSize(new Dimension((int) panelInfo.getPreferredSize().getWidth() / 3, (int) panelInfo.getPreferredSize().getHeight() / 10));
        String font = seasonLabel.getFont().getFontName();
        int style = seasonLabel.getFont().getStyle();
        int size = seasonLabel.getFont().getSize();
        size = 20;
        seasonLabel.setFont(new Font(font, style, size));
    }

    private void seasonFieldBehavior() {
        seasonField.setPreferredSize(new Dimension((int) panelInfo.getPreferredSize().getWidth() / 3, (int) panelInfo.getPreferredSize().getHeight() / 12));

        String font = seasonField.getFont().getFontName();
        int style = seasonField.getFont().getStyle();
        int size = seasonField.getFont().getSize();
        size = 20;
        seasonField.setFont(new Font(font, style, size));

        seasonField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                seasonField.addKeyListener(new KeyAdapter() {
                    String temp = seasonField.getText();

                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (!(e.getKeyCode() >= KeyEvent.VK_0 && e.getKeyCode() <= KeyEvent.VK_9) && !(e.getKeyCode() >= KeyEvent.VK_NUMPAD0 && e.getKeyCode() <= KeyEvent.VK_NUMPAD9) && !(e.getKeyCode() == KeyEvent.VK_BACK_SPACE))
                            seasonField.setText(temp);

                        temp = seasonField.getText();
                    }
                });
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (seasonField.getText().isBlank()) {
                    seasonField.setText("0");
                }
            }

        });
    }

    private void episodeLabelBehavior() {
        episodeLabel.setPreferredSize(new Dimension((int) panelInfo.getPreferredSize().getWidth() / 3, (int) panelInfo.getPreferredSize().getHeight() / 10));
        String font = episodeLabel.getFont().getFontName();
        int style = episodeLabel.getFont().getStyle();
        int size = episodeLabel.getFont().getSize();
        size = 20;
        episodeLabel.setFont(new Font(font, style, size));
    }

    private void episodeFieldBehavior() {
        episodeField.setPreferredSize(new Dimension((int) panelInfo.getPreferredSize().getWidth() / 3, (int) panelInfo.getPreferredSize().getHeight() / 12));

        String font = episodeField.getFont().getFontName();
        int style = episodeField.getFont().getStyle();
        int size = episodeField.getFont().getSize();
        size = 20;
        episodeField.setFont(new Font(font, style, size));

        episodeField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                episodeField.addKeyListener(new KeyAdapter() {
                    String temp = episodeField.getText();

                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (!(e.getKeyCode() >= KeyEvent.VK_0 && e.getKeyCode() <= KeyEvent.VK_9) && !(e.getKeyCode() >= KeyEvent.VK_NUMPAD0 && e.getKeyCode() <= KeyEvent.VK_NUMPAD9) && !(e.getKeyCode() == KeyEvent.VK_BACK_SPACE))
                            episodeField.setText(temp);

                        temp = episodeField.getText();
                    }
                });
            }
        });
    }



    private void addToPanelList() {
        panelList.setBorder(new LineBorder(Color.BLACK));
        panelList.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        panelList.add(searchBar, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.weighty = 1;
        c.weightx = 1;
        panelList.add(table.table, c);

        c.gridy = 2;
        c.weighty = 0.05;
        panelList.add(buttons.addButton, c);
    }

    private void addToPanelInfo() {
        panelInfo.setBorder(new LineBorder(Color.BLACK));
        panelInfo.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5, 5, 5, 5);

        c.gridy = 0;
        c.gridx = 0;
        panelInfo.add(titleLabel, c);
        c.gridx = 1;
        panelInfo.add(titleField, c);

        c.gridy = 1;
        c.gridx = 0;
        panelInfo.add(seasonLabel, c);
        c.gridx = 1;
        panelInfo.add(seasonField, c);
        c.gridx = 2;
        panelInfo.add(buttons.seasonPlusButton, c);
        c.gridx = 3;
        panelInfo.add(buttons.seasonMinusButton, c);

        c.gridy = 2;
        c.gridx = 0;
        panelInfo.add(episodeLabel, c);
        c.gridx = 1;
        panelInfo.add(episodeField, c);
        c.gridx = 2;
        panelInfo.add(buttons.episodePlusButton, c);
        c.gridx = 3;
        panelInfo.add(buttons.episodeMinusButton, c);

        c.gridy = 3;
        c.gridx = 0;
        panelInfo.add(episodeTimeLabel, c);
        c.gridx = 1;
        panelInfo.add(episodeTimeField, c);

        c.gridwidth = 2;
        c.gridy = 4;
        c.gridx = 0;
        panelInfo.add(buttons.saveButton, c);
        c.gridwidth = 1;
        c.gridx = 1;
        panelInfo.add(buttons.cancelButton, c);
        c.gridx = 2;
        panelInfo.add(buttons.deleteButton, c);
    }

    private void addShowToTable(ShowInfo show) {
        if (show.getTitle() != null) {
            table.model.addRow(new ShowInfo[]{show});
        }
    }
}
