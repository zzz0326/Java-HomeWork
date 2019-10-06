package exercise3;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
    /**
     * @param args
     * @throws IOException
     */
    static Scanner input = new Scanner(System.in).useDelimiter("\n");
    static List<Socket> list = new ArrayList();
    static int count =0;
    public static void main(String[] args) throws IOException {
        //创建服务器
        ServerSocket server = new ServerSocket(9998);
        System.out.println("已经启动服务器...");
        while (true) {
            list.add(server.accept());
            System.out.println("有" + (count + 1)+ "个人连接了服务器！");
            //发信息(有多个人)
            if (count == 0) {
                //new SendData().start();
            }
            //收信息
            new GetData(new DataInputStream(list.get(count).getInputStream())).start();
            count++;
        }
    }

    /*
    //给客户端发送信息
    public static class SendData extends Thread {
        private DataOutputStream dOutput;
        @Override
        public void run() {
            while (true) {
                try {
                    String msg =input.next();
                    for (Socket socket : list) {
                        dOutput=new DataOutputStream(socket.getOutputStream());
                        dOutput.writeUTF(msg);
                    }
                } catch (IOException e) {
                    break;
                }
            }
        }
    }
    */

    //收取客户端发来的信息
    public static class GetData extends Thread {
        private DataInputStream dInput;
        private DataOutputStream dOutput;
        public GetData(DataInputStream _dInput) {
            dInput=_dInput;
        }
        @Override
        public void run() {
            while (true) {
                try {

                    String msg = dInput.readUTF();
                    System.out.println(msg);
                    String msg1 = "<register name=\"xu\"/>";
                    String msg2 = "<login name=\"xu\"/>";
                    String msg3 = "<message from=\"xu\" to=\"zhang\" message = \"this is a test\"/>";
                    String msg4 = "<logout name=\"xu\"/>";
                    if (msg.equals(msg4)) {
                        System.out.println("是否允许注销 [Y/N]");
                        String permission = input.next();
                        if (permission != null) {
                            if (permission.equals("Y")) {
                                for (Socket socket : list) {
                                    //遍历所有的端口 对对应的端口发出对应的信息
                                    dOutput = new DataOutputStream(socket.getOutputStream());
                                    dOutput.writeUTF("<result commond = \"logout\" state = \"ok\">");
                                    //同意注册
                                }
                            } else {
                                for (Socket socket : list) {
                                    //遍历所有的端口 对对应的端口发出对应的信息
                                    dOutput = new DataOutputStream(socket.getOutputStream());
                                    dOutput.writeUTF("<result commond = \"loginout\" state = \"error\" messaage =\" \">");
                                    //同意注册
                                }
                            }
                        }
                    }
                    if (msg.equals(msg3)) {
                        dOutput = new DataOutputStream(list.get(0).getOutputStream());
                        dOutput.writeUTF("<result commond = \"message\" state = \"ok\">");
                        dOutput = new DataOutputStream(list.get(1).getOutputStream());
                        dOutput.writeUTF("<message from=\"xu\" to=\"zhang\" message = \"this is a test\"/>");
                    }
                    if (msg.equals(msg1)) {
                        System.out.println("是否允许注册 [Y/N]");
                        String permission = input.next();
                        if (permission != null) {
                            if (permission.equals("Y")) {
                                for (Socket socket : list) {
                                    //遍历所有的端口 对对应的端口发出对应的信息
                                    dOutput = new DataOutputStream(socket.getOutputStream());
                                    dOutput.writeUTF("<result commond = \"register\" state = \"ok\">");
                                    //同意注册
                                }
                            } else {
                                for (Socket socket : list) {
                                    //遍历所有的端口 对对应的端口发出对应的信息
                                    dOutput = new DataOutputStream(socket.getOutputStream());
                                    dOutput.writeUTF("<result commond = \"register\" state = \"error\" messaage =\" \">");
                                    //同意注册
                                }
                            }
                        }
                    }
                    if (msg.equals(msg2)) {
                        System.out.println("是否允许登陆 [Y/N]");
                        String permission = input.next();
                        if (permission != null) {
                            if (permission.equals("Y")) {
                                for (Socket socket : list) {
                                    //遍历所有的端口 对对应的端口发出对应的信息
                                    dOutput = new DataOutputStream(socket.getOutputStream());
                                    dOutput.writeUTF("<result commond = \"login\" state = \"ok\">");
                                    //同意注册
                                }
                            } else {
                                for (Socket socket : list) {
                                    //遍历所有的端口 对对应的端口发出对应的信息
                                    dOutput = new DataOutputStream(socket.getOutputStream());
                                    dOutput.writeUTF("<result commond = \"login\" state = \"error\" messaage =\" \">");
                                    //同意注册
                                }
                            }
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
}
