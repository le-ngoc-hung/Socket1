package Bai2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JLabel timeLabel;
    private Timer timer;

    public Client() {
        setTitle("Clock Client");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        timeLabel = new JLabel("", JLabel.CENTER);
        panel.add(timeLabel, BorderLayout.CENTER);

        add(panel);

        setVisible(true);

        startTimer();
    }

    private void startTimer() {
        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateTime();
        requestTimeFromServer();
    }

    private void updateTime() {
        timeLabel.setText(getCurrentTime());
    }

    private String getCurrentTime() {
        return new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
    }

    private void requestTimeFromServer() {
        try {
            Socket socket = new Socket("localhost", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("time");
            String serverResponse = in.readLine();
            timeLabel.setText(serverResponse);

            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Client::new);
    }
}
