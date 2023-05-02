package frontend.table;

import frontend.UI.newReservationUI;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationStatusTable extends JPanel {
    private String[] columnNames = {
            "Room Number",
            "Room Size",
            "Room Condition"
    };
    private Object[][] data = {
//            {"123", "Suite"},
//            {"69", "Single King"},
//            {"20", "Double King"}
    };
    private boolean DEBUG = false;
    private JTable table;

    private TableRowSorter<DefaultTableModel> sorter;

    public ReservationStatusTable() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //DefaultTableModel model = new DefaultTableModel(data, columnNames);
        final Class<?>[] columnClass = new Class[] {
                String.class, String.class, String.class
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
            @Override
            public Class<?> getColumnClass(int columnIndex) { return columnClass[columnIndex]; }
        };

        sorter = new TableRowSorter<DefaultTableModel>(model);
        table = new JTable(model);
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(400, 50));
        table.setFillsViewportHeight(true);


        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);


        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        TableFilterHeader filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);

        JButton createNewReservationButton = new JButton("New Reservation");
        createNewReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newReservationUI newReservationUI = new newReservationUI();
                newReservationUI.createAndShowGui();
            }
        });
        createNewReservationButton.setBounds(50, 60, 200, 30);
        add(createNewReservationButton);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener( new RemoveLineActionLister());
        add(removeButton);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("AvaliableRoomTable");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        ReservationStatusTable newContentPane = new ReservationStatusTable();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public final class RemoveLineActionLister implements ActionListener {
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


    public static class MyDialog extends JDialog {
        private JTable table;
        public MyDialog(JTable owner) {
            super(SwingUtilities.windowForComponent(owner));
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

            JButton addButton = new JButton("Add row");
            addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((DefaultTableModel) table.getModel()).addRow(new Object[]{"Coolio", "Noman", "Karate", 1, true});
                    dispose();
                    JOptionPane.showMessageDialog(table, "Added new record");
                }
            });
            listPane.add(addButton);
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
