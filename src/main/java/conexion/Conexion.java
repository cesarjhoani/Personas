/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Personas;

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
    
    
    public void insertarPersona(String nombre, String apellido) {
        try {
            String query = "INSERT INTO persona (nombre, apellido) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, nombre);
            pst.setString(2, apellido);
            pst.executeUpdate();
            System.out.println("Se insertó correctamente la persona.");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarPersonas() {
        try {
            String query = "SELECT * FROM persona";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("===================lista de personas :========================================================================================================================");
                System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre") + ", Apellido: " + rs.getString("apellido"));
                JOptionPane.showMessageDialog(null,"ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre") + ", Apellido: " + rs.getString("apellido"));
                System.out.println("================================================================================================================================================");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editarPersona(int idPersona, String nombre, String apellido) {
        try {
            String query = "UPDATE persona SET nombre = ?, apellido = ? WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, nombre);
            pst.setString(2, apellido);
            pst.setInt(3, idPersona);
            pst.executeUpdate();
            System.out.println("Se actualizó correctamente la persona con id " + idPersona);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarPersona(int idPersona) {
        try {
            String query = "DELETE FROM persona WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, idPersona);
            pst.executeUpdate();
            System.out.println("Se eliminó correctamente la persona con id " + idPersona);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void main(String []args){
        Conexion dao = new Conexion();
        dao.conectar();
        
        Personas persona1 = new Personas("jhoani", "molano");
        
        //dao.insertarPersona(persona1.getNombre(), persona1.getApellido());
        //dao.listarPersonas();
        //dao.editarPersona(2, persona1.getNombre(), persona1.getApellido());
        //dao.eliminarPersona(2);
    }
}
