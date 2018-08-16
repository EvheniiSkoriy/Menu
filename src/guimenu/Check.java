/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guimenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Evghenii Skoriy
 */
public class Check extends JDialog {

    private JPanel pLeft = new JPanel();
    private JPanel pCheck = new JPanel();
    private JList<Food> totalMenu = new JList();

    private JLabel a = new JLabel("Table:");
    private JLabel b = new JLabel();
    private JLabel c = new JLabel("Employee:");
    private JLabel d = new JLabel();
    private JLabel tax = new JLabel("Tax:");
    private JLabel textTax = new JLabel("10%");
    private JLabel subtotal = new JLabel("Subtotal:");
    private JLabel subtotalText = new JLabel();
    private JLabel g = new JLabel("Order:");

    private JScrollPane jsp = new JScrollPane(totalMenu);

    private JLabel total = new JLabel("Total price:");
    private JLabel totalPrice = new JLabel();

    public Check(List<Food> menu, JTextField textTable, JTextField textEmploy) {
        setTitle("Check");
        setPreferredSize(new Dimension(425, 275));
        setResizable(false);
        setLayout(new BorderLayout());

        pLeft.setBorder(new EmptyBorder(1, 5, 30, 1));
        pLeft.setLayout(new GridLayout(5, 2, 5, 1));;
        DefaultListModel<Food> model = new DefaultListModel<>();
        for (Food val : menu) {
            model.addElement(val);
        }
        totalMenu.setModel(model);

        BigDecimal total1 = BigDecimal.ZERO;
        double total2;
        for (Food val : menu) {
            total1 = total1.add(new BigDecimal(val.getPrice()));
        }
        totalPrice.setText("$" + String.format("%.2f", total1.doubleValue()));
        total2 = total1.doubleValue() * 0.1 + total1.doubleValue();
        b.setText(textTable.getText());
        d.setText(textEmploy.getText());
        a.setText("Table:");
        c.setText("Employee:");
        total.setText("Total price:");
        subtotalText.setText("$" + String.format("%.2f", total2));
        jsp.setSize(50, 250);
        pLeft.add(c);
        pLeft.add(d);
        pLeft.add(a);
        pLeft.add(b);
        pLeft.add(tax);
        pLeft.add(textTax);
        pLeft.add(subtotal);
        pLeft.add(totalPrice);
        pLeft.add(total);
        pLeft.add(subtotalText);
        pCheck.add(g);
        pCheck.add(jsp);
        add(pLeft, BorderLayout.WEST);
        add(pCheck, BorderLayout.CENTER);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
