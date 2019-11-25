package NativeSocket.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicServerSocket {
    public final static int port = 8080;

    public static void main(String[] args) {
        try {
            //定义一个ServerSocket对象，用port初始化
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is waiting for Message .....");
            while(true){
                //监听端口，获取准备连接的客户端
                Socket socket = serverSocket.accept();
                //从监听的socket中读取字节流数据
                InputStream inputStream = socket.getInputStream();
                byte[] bytes = new byte[1024];
                //处理读入的数据流
                StringBuilder stringBuilder = new StringBuilder();
                while(true){
                    //读取客户端数据
                    int len = inputStream.read(bytes);
                    if (len != -1){
                        stringBuilder.append(new String(bytes, 0,len,"UTF-8"));
                    }else {
                        break;
                    }
                }
                System.out.println("Get Message From Client : " + stringBuilder.toString());
                //定义输出流
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write("Hello, Client! This is server responding!".getBytes("UTF-8"));
                //刷新缓冲区
                outputStream.flush();

                inputStream.close();
                socket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
