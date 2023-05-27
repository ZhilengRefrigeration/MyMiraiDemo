package com.function.api;

import java.io.*;
import java.net.*;

public class Api extends ServerSocket {
    private static final int SERVER_PORT = 9090;

    public Api() throws IOException {
        super(SERVER_PORT);
        try {
            System.out.println("启动服务器");
            while (true) {
                Socket socket = this.accept();
                new ServerThread(socket).start(); // 每当收到一个 socket 就创建一个线程并启动
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
    }
}

class ServerThread extends Thread {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket client) {
        super();
        this.client = client;
        try {
            this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.out = new PrintWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String request = in.readLine(); // 读取客户端发送的请求

            // 解析请求路径
            String[] requestParts = request.split(" ");
            if (requestParts.length >= 2 && requestParts[1].equals("/api/data")) {
                String response = "{\"data\":\"你好\"}";
                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: application/json"); // 返回 JSON 类型数据
                out.println("");
                out.println(); // 发送空行，表示响应头结束
                out.println(response); // 发送响应体
            }

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
