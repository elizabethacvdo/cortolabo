/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelicula;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LN710Q
 */
public class DaoPelicula implements interfas <Pelicula> {
    
    private static final String SQL_INSERT="INSERT INTO cine (nombre,director,pais,clasificacion,año,proyeccion)VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE="UPDATE cine SET proyeccion=? WHERE nombre=?";
    private static final String SQL_DELETE="DELETE FROM cine WHERE nombre=?";
    private static final String SQL_READ ="SELECT * FROM cine WHERE nombre=?";
    private static final String SQLREADALL = "SELECT * FROM cine";
    
    private static final Conexion con = Conexion.conectar();
    

    @Override
    public boolean create(Pelicula g) {
        PreparedStatement ps;
        try{
            ps=con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1,g.getNombre());
            ps.setString(2, g.getDirector());
            ps.setString(3,g.getPais());
            ps.setString(4, g.getClasificacion());
            ps.setString(5, g.getAño());
            ps.setBoolean(6, g.getProyeccion());
            if(ps.executeUpdate()>0){
                return true;
            }
            
        
    }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DaoPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
        
        
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
           ps=con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if(ps.executeUpdate()>0){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DaoPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
        
        
        
        
    }

    @Override
    public boolean update(Pelicula c) {
        PreparedStatement ps;
        try{
            System.out.println(c.getNombre());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
           
            ps.setString(2,c.getNombre());
            ps.setBoolean(1, c.getProyeccion());
            if(ps.executeUpdate()>0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Pelicula read(Object key) {
        Pelicula f =null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps=con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1,key.toString());
            rs =ps.executeQuery();
            while(rs.next()){
                f=new Pelicula(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getBoolean(7));
                
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DaoPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return f;
    }

    @Override
    public ArrayList<Pelicula> readAll() {
        ArrayList<Pelicula> all = new ArrayList();
        Statement s;
        ResultSet rs ;
        try{
            s=con.getCnx().prepareStatement(SQLREADALL);
            rs =s.executeQuery(SQLREADALL);
            while(rs.next()){
                all.add(new Pelicula(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getBoolean(7)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();/////parte modificada
        }
        return all;
    }
    
    
    
    
    
}
