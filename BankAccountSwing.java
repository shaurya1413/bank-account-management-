package Bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankAccountSwing extends JFrame {
    private JTextField nameField, balanceField, amountField;
    private JLabel balanceLabel;
    private JButton depositBtn, withdrawBtn, displayBtn;
    private double balance = 0.0;
    private String accountHolder = "";

    public BankAccountSwing() {
        setTitle("Bank Account Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Account Holder:");
        nameField = new JTextField(20);
        JLabel initialLabel = new JLabel("Initial Balance:");
        balanceField = new JTextField(20);
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(20);
        balanceLabel = new JLabel("Balance: 0.0");
        depositBtn = new JButton("Deposit");
        withdrawBtn = new JButton("Withdraw");
        displayBtn = new JButton("Display Details");

        gbc.gridx = 0; gbc.gridy = 0; add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(initialLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; add(balanceField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(amountLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2; add(amountField, gbc);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; add(balanceLabel, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 4; add(depositBtn, gbc);
        gbc.gridx = 1; gbc.gridy = 4; add(withdrawBtn, gbc);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; add(displayBtn, gbc);

        depositBtn.addActionListener(e -> {
            if (accountHolder.isEmpty()) {
                accountHolder = nameField.getText();
                try {
                    balance = Double.parseDouble(balanceField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid initial balance.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            try {
                double amt = Double.parseDouble(amountField.getText());
                if (amt > 0) {
                    balance += amt;
                    balanceLabel.setText("Balance: " + balance);
                } else {
                    JOptionPane.showMessageDialog(this, "Enter a positive amount.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        withdrawBtn.addActionListener(e -> {
            if (accountHolder.isEmpty()) {
                accountHolder = nameField.getText();
                try {
                    balance = Double.parseDouble(balanceField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid initial balance.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            try {
                double amt = Double.parseDouble(amountField.getText());
                if (amt > 0 && amt <= balance) {
                    balance -= amt;
                    balanceLabel.setText("Balance: " + balance);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid withdrawal amount or insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        displayBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "Account Holder: " + accountHolder +
                "\nBalance: " + balance,
                "Account Details", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BankAccountSwing().setVisible(true);
        });
    }
}
