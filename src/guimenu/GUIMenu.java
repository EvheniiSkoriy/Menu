/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guimenu;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Evghenii Skoriy
 */
public class GUIMenu extends JFrame {


    private JPanel pCenter = new JPanel();
    private JPanel pRight = new JPanel();
    private JPanel pTop = new JPanel();
    private JPanel pButtom = new JPanel();

    private JLabel table = new JLabel("Table:");
    private JTextField textTable = new JTextField(10);
    private JLabel employ = new JLabel("Employee:");
    private JTextField textEmploy = new JTextField(10);
    private JLabel order = new JLabel("Order:");

    private JButton addBaverage = new JButton("Add baverage");
    private JButton addAppetizer = new JButton("Add appetizer");
    private JButton addMainCourse = new JButton("Add main Course");
    private JButton addDesert = new JButton("Add Desert");
    private JButton totalButton = new JButton("Total");
    private JButton clearButton = new JButton("Clear");
    private JList<Food> listMenu = new JList();
    private List<Food> menu = new ArrayList<Food>();
    private JComboBox<Food> baverage = new JComboBox();
    private JComboBox<Food> appetizer = new JComboBox();
    private JComboBox<Food> mainCourse = new JComboBox();
    private JComboBox<Food> desert = new JComboBox();

    private List<Food> foodArray = FileReader.readFile("Item.txt");

    ImageIcon icon = new ImageIcon("kofe1.jpeg");
    JLabel copyLabel = new JLabel(icon);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUIMenu gui = new GUIMenu();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);

    }

    public GUIMenu() {
        listMenu.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                menu.remove(listMenu.getSelectedValue());
                renderList();
            }
        });
        initWindow();
    }

    public void initWindow() {
        setTitle("Cup coffee");
        setPreferredSize(new Dimension(750, 450));
        setLayout(new BorderLayout());
        createCentrPanel();
        add(pCenter, BorderLayout.WEST);
        createRightPanel();
        add(pRight, BorderLayout.CENTER);
        createTopPanel();
        add(pTop, BorderLayout.NORTH);
        createBottomPanel();
        add(pButtom, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);

    }

    public void createCentrPanel() {
        pCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
        pCenter.setLayout(new GridLayout(4, 4, 30, 50));
        for (Food val : foodArray) {
            if (val.getType().equals("Baverage")) {
                baverage.addItem(val);
            }
            if (val.getType().equals("Appetizer")) {
                appetizer.addItem(val);
            }
            if (val.getType().equals("Main Course")) {
                mainCourse.addItem(val);
            }
            if (val.getType().equals("Desert")) {
                desert.addItem(val);
            }

        }
        pCenter.add(baverage);
        pCenter.add(addBaverage);
        pCenter.add(appetizer);
        pCenter.add(addAppetizer);
        pCenter.add(mainCourse);
        pCenter.add(addMainCourse);
        pCenter.add(desert);
        pCenter.add(addDesert);

        addBaverage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.add((Food) baverage.getSelectedItem());
                renderList();
            }
        });

        addAppetizer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.add((Food) appetizer.getSelectedItem());
                renderList();
            }
        });

        addMainCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.add((Food) mainCourse.getSelectedItem());
                renderList();
            }
        });

        addDesert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.add((Food) desert.getSelectedItem());
                renderList();
            }
        });
    }

    public void removeFood(List<Food> list, int i) {
        list.remove(i);
    }

    public void createTopPanel() {
        pTop.setLayout(new FlowLayout());
        pTop.add(table);
        pTop.add(textTable);
        pTop.add(copyLabel);
        pTop.add(employ);
        pTop.add(textEmploy);

    }

    public void createRightPanel() {
        pRight.setLayout(new FlowLayout());
        JScrollPane pane = new JScrollPane(listMenu);
        pRight.add(order);
        pRight.add(pane);
        listMenu.setLayoutOrientation(JList.VERTICAL);

    }

    public void createBottomPanel() {
        pButtom.setBorder(new EmptyBorder(10, 10, 10, 10));
        pButtom.setLayout(new GridLayout(1, 2));
        pButtom.add(totalButton);
        pButtom.add(clearButton);

        totalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textTable.getText().matches("^[0-9]+$")) {
                    JOptionPane.showMessageDialog(null, "Incorrect table(select number 1-10)");
                } else if (textTable.getText().matches("^[0-9]+$")) {
                    int textTableInt = Integer.parseInt(textTable.getText());
                    if (textTableInt < 1 || textTableInt > 10) {
                        JOptionPane.showMessageDialog(null, "Incorrect table(select number 1-10)");
                    } else if (!textEmploy.getText().matches("^[a-zA-Z\\s]+$")) {
                        JOptionPane.showMessageDialog(null, "Edit name employee");
                    } else if (menu.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "List is empty");
                    } else {
                        Dialog d = new Check(menu, textTable, textEmploy);
                    }
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.clear();
                renderList();

                textTable.setText("");
                textEmploy.setText("");
            }
        });

    }

    private void renderList() {
        DefaultListModel<Food> model = new DefaultListModel<>();
        for (Food val : menu) {
            model.addElement(val);
        }
        listMenu.setModel(model);

    }

}
