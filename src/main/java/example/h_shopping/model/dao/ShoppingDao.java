package example.h_shopping.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ShoppingDao {
    private String db_url = "jdbc:mysql://localhost:3306/assessment";
    private String db_user = "root";
    private String db_password = "1234";
    public Connection conn;
    public ShoppingDao(){ connect(); }
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection( db_url, db_user, db_password);
        }catch (Exception e ){  System.out.println(e); }
    }// f end

    //

}// class end