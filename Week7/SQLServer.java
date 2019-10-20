

import java.sql.*;
import java.util.Date;

public class SQLServer {
    public static void main(String [] args)
    {
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test";
        String userName="sa";
        String userPwd="123456";
        Connection conn = null;
        Statement stmt = null;
        try
        {
            Class.forName(driverName);
            System.out.println("加载驱动成功！");
            conn = DriverManager.getConnection(dbURL,userName,userPwd);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT name, sex, birth FROM week";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                // 通过字段检索
                String id  = rs.getString("name");
                String name = rs.getString("sex");
                Time url = rs.getTime("birth");

                // 输出数据
                System.out.print("性别: " + id);
                System.out.print(", 姓名: " + name);
                System.out.print(", 生日: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("加载驱动失败！");
        }
        try{
            Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
            System.out.println("连接数据库成功！");
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.print("SQL Server连接失败！");
        }
    }

}