package com.function.api;

import net.mamoe.mirai.Bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Api extends Thread{
    @Override
    public void run() {
        try {
            this.api();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //监听方法
    public void api() throws IOException {
        int portNumber = 9090;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("Listening on port " + portNumber + "...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Accepted connection from " + clientSocket);

            // 创建一个新线程来处理客户端请求
            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.startsWith("GET")) {
                            String[] tokens = inputLine.split(" ");
                            String filename = tokens[1].substring(1);

                            if (filename.equals("api/data")) { // 如果请求的路径为 /api/data
                                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                                out.println("HTTP/1.1 200 OK");
                                out.println("Content-Type: application/json");
                                out.println("");
                                out.println("{\"data\":\""+ Bot.findInstance(3377658377L).getFriends()+"\"}"); // 返回 JSON 数据

                                out.close();
                                break;
                            } else if (filename.equals("api/date")) { // 如果请求的路径为 /api/date
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = new Date();

                                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                                out.println("HTTP/1.1 200 OK");
                                out.println("Content-Type: application/json");
                                out.println("");
                                out.println("{\"date\":\"" + formatter.format(date) + "\"}"); // 返回当前日期

                                out.close();
                                break;
                            }
                        }
                    }

                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
