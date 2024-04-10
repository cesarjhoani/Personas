/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cesar
 */
public class Conexion {
    String bd = "personas";
    String url = "jdbc:mysql://localhost:3306/";
    String user =  "root";
    String password = "";
    String driver = "com.mysql.cj.jdbc.Driver"; 
    Connection con;
    
    public Connection conectar(){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url+bd, user, password);
            System.out.println("se conecto a la base de datos");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
         return con;
    }
    
    public static void main(String []args){
        Conexion dao = new Conexion();
        dao.conectar();
    }
}