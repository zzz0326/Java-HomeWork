package exercise3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    static Scanner input = new Scanner(System.in).useDelimiter("\n");
    //有空格就是一句 与我们的要求不符合
    static List<Socket> list = new ArrayList();
    //加一个list 便于调用当前的socket
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("127.0.0.1", 9998);
        System.out.println("已经连上了服务器！");
        list.add(socket);
        new GetData(new DataInputStream(socket.getInputStream())).start();
        //使用socket 然后再调用构造函数  知道该传到哪一个服务器当中去
        new SendData(new DataOutputStream(socket.getOutputStream())).start();
    }

    //收取服务端发来的信息
    public static class GetData extends Thread {
        private DataInputStream dInput;


        public GetData(DataInputStream _dInput) {
            dInput = _dInput;
        }
        //调用构造函数


        @Override
        public void run() {
            while (true) {
                try {
                    String msg = dInput.readUTF();
                    DataOutputStream dOutput= new DataOutputStream(list.get(0).getOutputStream());
                    if (msg != null) {
                        System.out.println("服务器来信：" + msg);
                        if(msg.equals("<message from=\"xu\" to=\"zhang\" message = \"this is a test\"/>")){
                            dOutput.writeUTF("result command=\"message\" state =\"ok\"");
                        }
                    }
                } catch (IOException e) {
                    try {
                        dInput.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    //给服务端发送信息
    public static class SendData extends Thread {
        private DataOutputStream dOutput;
        public SendData(DataOutputStream _dOutput) {
            dOutput = _dOutput;
        }
        @Override
        public void run() {
            while (true) {
                try {
                    //System.out.println("给服务器发信：");
                    String msg =input.next();
                    //获取输入信息
                    if (msg != null) {
                        dOutput.writeUTF(msg);
                    }
                } catch (IOException e) {
                    break;
                }
            }
        }
    }
}
