package exercise4;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

public class download {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.w3.org/Consortium/Member/List/");
            URLConnection uconn = url.openConnection();
            uconn.setDoOutput(true);
            uconn.connect();

            String temp;
            final StringBuffer sb = new StringBuffer();
            //注意编码格式 ，查看网页源码中charset的值
            String key = ".*class=\"member\".*";
            final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "GB18030"));
            while ((temp = in.readLine()) != null) {
                //读取每一行的内容
                if(Pattern.matches(key,temp)) {
                    //运用正则表达式查找到需要的数据
                    String[] split = temp.split(">");
                    String[] need = split[1].split("<");
                    //通过两次字符串分割得到自己想要的数据
                    sb.append("\n");
                    sb.append(need[0]);
                    //System.out.println(need[0]);
                }

            }

            in.close();
            System.out.println(sb);
            String targetPath = "1.txt";//目标文件路径
            File f = new File(targetPath);//新建文件
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                bw.write(sb.toString());
                bw.flush();
                bw.close();
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }

         catch (Exception e) {
            e.printStackTrace();
            System.out.println("出错啦！");
        }
    }
}
