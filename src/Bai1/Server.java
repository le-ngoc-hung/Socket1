package Bai1;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is running...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());
            
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
            
            String serverInputStr;
            String clientResponse;
            
            while (true) {
                // Đọc dữ liệu từ client và in ra màn hình
                clientResponse = in.readLine();
                System.out.println("Client: " + clientResponse);
                
                // Kiểm tra nếu client muốn thoát khỏi cuộc trò chuyện
                if (clientResponse.equalsIgnoreCase("exit")) {
                    break;
                }
                
                // Đọc dữ liệu từ bàn phím và gửi tới client
                System.out.print("Server: ");
                serverInputStr = serverInput.readLine();
                out.println(serverInputStr);
            }
            
            // Đóng các luồng và kết nối
            out.close();
            in.close();
            serverInput.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


