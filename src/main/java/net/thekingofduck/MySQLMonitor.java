package net.thekingofduck;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.cli.*;

/**
 * Project: MySQLMonitor
 * Date:2020/10/8 8:58 下午
 * Email:CoolCat@gzsec.org
 * Github:https://github.com/TheKingOfDuck
 *
 * @author CoolCat
 * @version 1.0.0
 */
public class MySQLMonitor {

    public static String ftime() {
        SimpleDateFormat ftime = new SimpleDateFormat("HH:mm:ss");
        return ftime.format(new Date());
    }

    public static void banner() {
        String banner = "\n" +
                " __  __        _____  ____  _      __  __             _ _             \n" +
                "|  \\/  |      / ____|/ __ \\| |    |  \\/  |           (_) |            \n" +
                "| \\  / |_   _| (___ | |  | | |    | \\  / | ___v1.0_ __ _| |_ ___ _ __ \n" +
                "| |\\/| | | | |\\___ \\| |  | | |    | |\\/| |/ _ \\| '_ \\| | __/ _ \\| '__|\n" +
                "| |  | | |_| |____) | |__| | |____| |  | | (_) | | | | | || (_) | |   \n" +
                "|_|  |_|\\__, |_____/ \\___\\_\\______|_|  |_|\\___/|_| |_|_|\\__\\___/|_|   \n" +
                "         __/ |      https://github.com/TheKingOfDuck/MySQLMonitor       \n" +
                "        |___/                                                         ";
        System.out.println(banner);
    }

    public static void main(String[] args) throws ClassNotFoundException, ParseException {
        banner();

        CommandLineParser parser = new BasicParser();
        Options options = new Options();

        options.addOption("h", "host", true, "mysql host");

        options.addOption("p", "port", true, "mysql port");

        options.addOption("user", "username", true, "mysql username");

        options.addOption("pass", "password", true, "mysql password");

        options.addOption("help", "help", false, "Help Info");

        CommandLine commandLine = parser.parse(options, args);

        String helpinfo = String.format("[?]CommandLine:\n" +
                "\t-h\t--host\t\tmysql host\n" +
                "\t-p\t--port\t\tmysql port\n" +
                "\t-user\t--username\tmysql username\n" +
                "\t-pass\t--password\tmysql password\n" +
                "\t-help\t--help\t\thelp info\n\n" +
                "eg:java -jar MySQLMonitor.jar -h 127.0.0.1 -user CoolCat -pass mysqlmonitor");
        if (commandLine.hasOption("help")) {
            System.out.println(helpinfo);
            System.exit(0);
        }
        if (args.length < 3){
            System.out.println(helpinfo);
            System.exit(0);
        }

        String dbhost = "127.0.0.1";
        String dbport = "3306";
        String dbuser = "root";
        String dbpass = "root";

        if (commandLine.hasOption("h")) {
            dbhost = commandLine.getOptionValue("h");
        }
        if (commandLine.hasOption("p")) {
            dbport = commandLine.getOptionValue("p");
        }
        if (commandLine.hasOption("user")) {
            dbuser = commandLine.getOptionValue("user");
        }
        if (commandLine.hasOption("pass")) {
            dbpass = commandLine.getOptionValue("pass");
        }


        String JDBC_DRIVER = null;
        String DB_URL = null;

        // 注册 JDBC 驱动
        try {
            // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
            JDBC_DRIVER = "com.mysql.jdbc.Driver";
            DB_URL = String.format("jdbc:mysql://%s:%s/mysql",dbhost,dbport);
            Class.forName(JDBC_DRIVER);
        }catch (Exception e){
            // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
            JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            DB_URL = String.format("jdbc:mysql://%s:%s/mysql?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",dbhost,dbport);
            Class.forName(JDBC_DRIVER);
        }

        Connection conn = null;
        Statement stmt = null;


        try{

            // 打开链接
            System.out.println(String.format("[%s] %s",ftime(),"Try connect to mysql..."));
            conn = DriverManager.getConnection(DB_URL,dbuser,dbpass);

            // 执行查询
            stmt = conn.createStatement();
            DatabaseMetaData dbinfo = conn.getMetaData();

            System.out.println(String.format("[%s] Database version: %s",ftime(),dbinfo.getDatabaseProductVersion()));
            stmt.executeQuery("set global general_log='ON'");


            ResultSet r = stmt.executeQuery("show variables like 'log_output'");

            String log_output = null;
            while (r.next()){
                log_output = r.getString("Value");
            }
            System.out.println(String.format("[%s] Log output: %s",ftime(),log_output));
            if (!log_output.equals("TABLE")){
                System.out.println(String.format("[%s] Set global log_output='table'",ftime()));
                stmt.executeQuery("set global log_output='table'");
            }
            r.close();
            stmt.close();

        } catch(Exception se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }// 处理 Class.forName 错误
        finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
                se2.printStackTrace();
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

        try {
            Connection conn2 = null;
            Statement stmt2 = null;

            conn2 = DriverManager.getConnection(DB_URL,dbuser,dbpass);

            // 执行查询
            stmt2 = conn2.createStatement();

            while (true){

                String logsql = "select * from mysql.general_log where command_type =\"Query\" OR command_type =\"Execute\" order by event_time desc limit 2";

                ResultSet log = stmt2.executeQuery(logsql);
                while (log.next()){
                    String logres = log.getString("argument");
                    if (!logres.equals(logsql)){
                        System.out.println(String.format("[%s] %s",ftime(),logres));
                    }
                    //不适当休眠一下会疯狂查询 占用cpu资源。
                    Thread.sleep(100);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
