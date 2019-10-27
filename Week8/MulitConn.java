import java.sql.*;
import java.util.Vector;


public class MulitConn {


    private int initialConnections = 10; // 连接池的初始大小
    private int incrementalConnections = 5;// 连接池自动增加的大小
    private int maxConnections = 50;
    private Vector connections = null;


    private void createPool1() throws Exception {
        // 确保连接池没有创建
        // 如果连接池己经创建了，保存连接的向量 connections 不会为空
        if (connections != null) {
            return; // 如果己经创建，则返回
        }
        // 实例化 JDBC Driver 中指定的驱动类实例


        connections = new Vector();

        // 根据 initialConnections 中设置的值，创建连接。
        createConnections(10);
        // System.out.println(" 数据库连接池创建成功！ ");
    }


    private void createConnections(int numConnections) throws SQLException {
        // 循环创建指定数目的数据库连接
        for (int x = 0; x < numConnections; x++) {
            // 是否连接池中的数据库连接的数量己经达到最大？最大值由类成员 maxConnections
            // 指出，如果 maxConnections 为 0 或负数，表示连接数量没有限制。
            // 如果连接数己经达到最大，即退出。
            if (this.maxConnections > 0
                    && this.connections.size() >= this.maxConnections) {
                break;
            }
            // add a new PooledConnection object to connections vector
            // 增加一个连接到连接池中（向量 connections 中）
            try {
                if(x<5) {
                    connections.addElement(new MulitConn.PooledConnection(newConnection()));
                }
                else{
                    connections.addElement(new MulitConn.PooledConnection(newConnection1()));
                }
                //这里添加数据库
            } catch (SQLException e) {
                System.out.println(" 创建数据库连接失败！ " + e.getMessage());
                throw new SQLException();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            // System.out.println(" 数据库连接己创建 ......");
        }
    }

    private Connection newConnection1() throws SQLException, Exception{

        Driver driver1 = (Driver) (Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance());
        DriverManager.registerDriver(driver1); // 注册 JDBC 驱动程序
        // 创建一个数据库连接
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test", "sa",
                "123456");
        // 如果这是第一次创建数据库连接，即检查数据库，获得此数据库允许支持的
        // 最大客户连接数目
        // connections.size()==0 表示目前没有连接己被创建
        if (connections.size() == 0) {
            DatabaseMetaData metaData = conn.getMetaData();
            int driverMaxConnections = metaData.getMaxConnections();
            // 数据库返回的 driverMaxConnections 若为 0 ，表示此数据库没有最大
            // 连接限制，或数据库的最大连接限制不知道
            // driverMaxConnections 为返回的一个整数，表示此数据库允许客户连接的数目
            // 如果连接池中设置的最大连接数量大于数据库允许的连接数目 , 则置连接池的最大
            // 连接数目为数据库允许的最大数目
            if (driverMaxConnections > 0
                    && this.maxConnections > driverMaxConnections) {
                this.maxConnections = driverMaxConnections;
            }
        }
        System.out.println("SqlServer连接成功");
        return conn; // 返回创建的新的数据库连接
    }

    private Connection newConnection() throws SQLException, Exception{


        Driver driver1 = (Driver) (Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance());
        DriverManager.registerDriver(driver1); // 注册 JDBC 驱动程序
        // 创建一个数据库连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&useSSL=false","root",
                "123456");
        // 如果这是第一次创建数据库连接，即检查数据库，获得此数据库允许支持的
        // 最大客户连接数目
        // connections.size()==0 表示目前没有连接己被创建
        if (connections.size() == 0) {
            DatabaseMetaData metaData = conn.getMetaData();
            int driverMaxConnections = metaData.getMaxConnections();
            // 数据库返回的 driverMaxConnections 若为 0 ，表示此数据库没有最大
            // 连接限制，或数据库的最大连接限制不知道
            // driverMaxConnections 为返回的一个整数，表示此数据库允许客户连接的数目
            // 如果连接池中设置的最大连接数量大于数据库允许的连接数目 , 则置连接池的最大
            // 连接数目为数据库允许的最大数目
            if (driverMaxConnections > 0
                    && this.maxConnections > driverMaxConnections) {
                this.maxConnections = driverMaxConnections;
            }
        }
        System.out.println("MySql连接成功");
        return conn; // 返回创建的新的数据库连接
    }

    public static void main(String[] args) throws Exception {
        MulitConn mulitConn  = new MulitConn();
        mulitConn.createPool1();
    }



    class PooledConnection {
        Connection connection = null;// 数据库连接
        boolean busy = false; // 此连接是否正在使用的标志，默认没有正在使用

        // 构造函数，根据一个 Connection 构告一个 PooledConnection 对象
        public PooledConnection(Connection connection) {
            this.connection = connection;
        }

        // 返回此对象中的连接
        public Connection getConnection() {
            return connection;
        }

        // 设置此对象的，连接
        public void setConnection(Connection connection) {
            this.connection = connection;
        }

        // 获得对象连接是否忙
        public boolean isBusy() {
            return busy;
        }

        // 设置对象的连接正在忙
        public void setBusy(boolean busy) {
            this.busy = busy;
        }
    }
}
