import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface extends JFrame {
    private JTextField amountField;
    private JButton depositButton;
    private JButton withdrawButton;
    private JLabel balanceLabel;

    private double balance = 1000; // this is initial balance

    public ATMInterface() {
        super("Advanced ATM Interface");

        // Setting  up the frame
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setResizable(false);

        // we are Creating components
        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Welcome to Our ATM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(titleLabel);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        balanceLabel = new JLabel("Your balance: $" + balance);
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        centerPanel.add(balanceLabel);

        JPanel amountPanel = new JPanel();
        amountField = new JTextField(10);
        amountPanel.add(new JLabel("Enter Amount:"));
        amountPanel.add(amountField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });
        buttonPanel.add(depositButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });
        buttonPanel.add(withdrawButton);

        centerPanel.add(amountPanel);
        centerPanel.add(buttonPanel);

        // Add components to content pane
        Container container = getContentPane();
        container.setLayout(new BorderLayout(10, 10));
        container.add(topPanel, BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            balance += amount;
            balanceLabel.setText("Your balance: $" + balance);
            JOptionPane.showMessageDialog(this, "Deposit successful!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
        }
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= balance) {
                balance -= amount;
                balanceLabel.setText("Your balance: $" + balance);
                JOptionPane.showMessageDialog(this, "Withdrawal successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMInterface().setVisible(true);
            }
        });
    }
}