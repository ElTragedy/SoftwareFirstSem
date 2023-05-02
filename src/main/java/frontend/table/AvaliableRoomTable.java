package frontend.table;

import backend.Controller;
import backend.Room;
import frontend.UIBlackBox;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AvaliableRoomTable extends JPanel {
    private String[] columnNames = {
            "Room Number",
            "Room Size",
            "Room Condition"
    };
    private Object[][] data = {};
    private boolean DEBUG = false;
    private JTable table;
    private JTextField filterText;
    private JTextField statusText;

    private DefaultTableModel model;
    private TableRowSorter<DefaultTableModel> sorter;

    public AvaliableRoomTable() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //DefaultTableModel model = new DefaultTableModel(data, columnNames);
        final Class<?>[] columnClass = new Class[] {
                String.class, String.class, String.class, Integer.class, Boolean.class
        };
        model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
            @Override
            public Class<?> getColumnClass(int columnIndex) { return columnClass[columnIndex]; }
        };

        sorter = new TableRowSorter<DefaultTableModel>(model);
        table = new JTable(model);
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

//        Box b1 = Box.createHorizontalBox();
//        b1.add(initMenu(model));
//        b1.add(Box.createHorizontalGlue());
//        add(b1);

//        DeleteRowFromTableAction deleteAction = new DeleteRowFromTableAction(table, (DefaultTableModel)table.getModel());
//        JToolBar toolbar = new JToolBar();
//        Box floatRightBox = Box.createHorizontalBox();
//        floatRightBox.add(Box.createHorizontalGlue());
//        toolbar.add(deleteAction);
//        floatRightBox.add(toolbar);
//        add(floatRightBox);


        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {
                        int viewRow = table.getSelectedRow();
                        if (viewRow < 0) {
                            //Selection got filtered away.
                            statusText.setText("");
                        } else {
                            int modelRow =
                                    table.convertRowIndexToModel(viewRow);
                            statusText.setText(
                                    String.format("Selected Row in view: %d. " + "Selected Row in model: %d.", viewRow, modelRow));
                        }
                    }
                }
        );

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        JPanel form = new JPanel(new SpringLayout());

        JLabel l1 = new JLabel("Filter Text:", SwingConstants.TRAILING);
        form.add(l1);
        filterText = new JTextField();
        filterText.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
        l1.setLabelFor(filterText);
        form.add(filterText);

        JLabel l2 = new JLabel("Status:", SwingConstants.TRAILING);
        form.add(l2);
        statusText = new JTextField();
        l2.setLabelFor(statusText);
        form.add(statusText);

        SpringUtilities.makeCompactGrid(form, 2, 2, 6, 6, 6, 6);
        add(form);
        TableFilterHeader filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);

        JButton button = new JButton("Remove");
        button.addActionListener( new RemoveLineActionLister());
        add(button);
    }

    public void updateTable(Vector<Vector<String>> rooms){
        model.setRowCount(0);
        if(rooms.isEmpty()){
            return;
        }
        for(Vector<String> i: rooms){
            model.addRow(i);
        }
    }

    public JTable getTable(){
        return table;
    }

    private void newFilter() {
        RowFilter<DefaultTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filterText.getText(), 0, 1, 2);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("AvaliableRoomTable");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        AvaliableRoomTable newContentPane = new AvaliableRoomTable();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static Room getSelectedRoom(){
        Room r = null;


        return r;
    }

    private final class RemoveLineActionLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int viewRow = table.getSelectedRow();
            if (viewRow < 0) {
                JOptionPane.showMessageDialog(null, "No Row Selected!");
            } else {
                int modelRow = table.convertRowIndexToModel(viewRow);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int answer = JOptionPane.showConfirmDialog(null, "Do you want to remove " +
                                model.getValueAt(modelRow, 0) + " " + model.getValueAt(modelRow, 1) + "?",
                        "Warning", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    model.removeRow(modelRow);
                }
            }
        }
    }

//    private JMenuBar initMenu(DefaultTableModel model) {
//        JMenuBar menuBar;
//        JMenu menu;
//        JMenuItem header, menuRemove, menuCSV;
//
//        menuBar = new JMenuBar();
//        menu = new JMenu("Menu");
//        menuBar.add(menu);
//
//        header = new JMenuItem("COMMANDS:");
//        header.setEnabled(false);
//        menu.add(header);
//
//        menuRemove = new JMenuItem("Remove");
//        menuRemove.addActionListener(new RemoveLineActionLister());
//        menu.addMenuListener(new MenuListener() {
//            @Override
//            public void menuSelected(MenuEvent e) {
//                int viewRow = table.getSelectedRow();
//                if (viewRow < 0) {
//                    menuRemove.setEnabled(false);
//                } else {
//                    menuRemove.setEnabled(true);
//                }
//            }
//
//            @Override
//            public void menuDeselected(MenuEvent e) {}
//            @Override
//            public void menuCanceled(MenuEvent e) {}
//        });
//        menu.add(menuRemove);
//        menu.addSeparator();
//
//        menuCSV = new JMenuItem("Load CSV");
//        menu.add(menuCSV);
//        menuCSV.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DefaultTableModel model = (DefaultTableModel) table.getModel();
//                model.setRowCount(0);
//                try (BufferedReader br = new BufferedReader(new FileReader(new File(this.getClass().getResource(
//                        "/data.csv").getFile())))) {
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        // System.out.println(line);
//                        String[] row = line.split(",");
//                        Vector<Object> correction = new Vector<>();
//                        for (int i = 0; i < 3; i++) {
//                            correction.add(row[i]);
//                        }
//                        correction.add(Integer.parseInt(row[3]));
//                        correction.add(Boolean.parseBoolean(row[4]));
//                        model.addRow(correction);
//                    }
//                } catch (IOException exception) {
//                    throw new RuntimeException(exception);
//                }
//            }
//        });
//        return menuBar;
//    }

    public static class MyDialog extends JDialog {
        private JTable table;
        public MyDialog(JTable owner) {
            super(javax.swing.SwingUtilities.windowForComponent(owner));
            table = owner;
            createGUI();
        }
        private void createGUI() {
            setPreferredSize(new Dimension(600, 400));
            setTitle(getClass().getSimpleName());

            JPanel listPane = new JPanel();
            listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));

            JLabel label = new JLabel("Hello:");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPane.add(label);

            int row = table.getSelectedRow();
            JLabel dataLabel = null;
            if(row < 0) {
                dataLabel = new JLabel("no row selected");
            } else {
                dataLabel = new JLabel(table.getModel().getValueAt(row, 0)+" "+table.getModel().getValueAt(row, 1));
            }
            dataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPane.add(dataLabel);

//            JButton addButton = new JButton("Add row");
//            addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//            addButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    ((DefaultTableModel) table.getModel()).addRow(new Object[]{"Coolio", "Noman", "Karate", 1, true});
//                    dispose();
//                    JOptionPane.showMessageDialog(table, "Added new record");
//                }
//            });
//            listPane.add(addButton);
            pack();

            setContentPane(listPane);
            setLocationRelativeTo(getParent());
            setVisible(true);
        }
        @Override
        public void dispose() {
            super.dispose();
        }
    }

    public abstract static class AbstractTableAction<T extends JTable, M extends TableModel> extends AbstractAction {

        private T table;
        private M model;

        public AbstractTableAction(T table, M model) {
            this.table = table;
            this.model = model;
        }

        public T getTable() {
            return table;
        }

        public M getModel() {
            return model;
        }
    }
}
