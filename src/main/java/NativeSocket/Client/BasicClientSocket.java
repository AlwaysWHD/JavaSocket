package NativeSocket.Client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author Magnus
 * @Desciption Added basic solution to client Socket
 */
public class BasicClientSocket {
    private final static String host = "127.0.0.1";
    private final static int port = 8080;

    public static void main(String[] args) {
        try {
            //根据host和port创建一个socket对象
            Socket socket = new Socket(host, port);
            System.out.println("Client is ready to connect...");
            //创建输出流，写入数据
            OutputStream outputStream = socket.getOutputStream();
            String string = "Hello this is Client!";
            outputStream.write(string.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            //通过shutdownOutput告诉服务器客户端已经关闭输出方法，后续只能接受数据传输
            socket.shutdownOutput();
            //创建输入流，获取服务器数据
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            StringBuilder stringBuilder = new StringBuilder();
            while(true){
                //读取缓冲区数据
                int len = inputStream.read(bytes);
                if (len != -1) {
                    stringBuilder.append(new String(bytes, 0, len, "UTF-8"));
                }else{
                    break;
                }
            }
            System.out.println("Message Got From Server : " + stringBuilder.toString());
            //关闭输入流
            inputStream.close();
            outputStream.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
