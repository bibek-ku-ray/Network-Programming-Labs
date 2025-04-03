package chapter6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class Pro4WhoisClientSwingApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("WHOIS Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Enter Domain Name:");
        JTextField domainField = new JTextField(20);
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JButton submitButton = new JButton("Fetch WHOIS");

        label.setFont(new Font("Arial", Font.BOLD, 14));
        domainField.setPreferredSize(new Dimension(300, 30));
        submitButton.setPreferredSize(new Dimension(150, 30));
        resultArea.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center alignment with spacing
        inputPanel.add(label);
        inputPanel.add(domainField);
        inputPanel.add(submitButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String domainName = domainField.getText().trim();
                if (domainName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a domain name.");
                    return;
                }

                try {
                    String whoisServer = "whois.internic.net";
                    int port = 43;
                    Socket socket = new Socket(whoisServer, port);

                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    writer.println(domainName);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line).append("\n");
                    }

                    resultArea.setText(response.toString());

                    reader.close();
                    writer.close();
                    socket.close();
                } catch (IOException ex) {
                    resultArea.setText("Error: " + ex.getMessage());
                }
            }
        });

        frame.setVisible(true);
    }
}

// Output
//Img
