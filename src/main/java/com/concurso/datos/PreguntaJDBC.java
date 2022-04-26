package com.concurso.datos;

import com.concurso.entidades.Pregunta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreguntaJDBC {
    private static final String SQL_SELECT = "SELECT * FROM pregunta";
    private static final String SQL_INSERT = "INSERT INTO pregunta(id, enunciado, nivel, opcion1, opcion2, opcion3, opcion4, respuesta) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE pregunta SET enunciado=?, nivel=?, opcion1=?, opcion2=?, opcion3=?, opcion4=?, respuesta=? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM pregunta WHERE id=?";
    
    /*INSERT INTO public.pregunta (id, enunciado, nivel, opcion1, opcion2, opcion3, respuesta) 
    VALUES ('1'::integer, '¿Pregunta?'::character varying, '1'::integer, 'Opcion 2'::character varying, 'Opcion 3'::character varying, 'Opcion 4'::character varying, 'd'::character varying) returning id;*/
    
    public List<Pregunta> select(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pregunta pregunta = null;
        List<Pregunta> listaPregunta = new ArrayList<Pregunta>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String enunciado = rs.getString("enunciado");
                int nivel = rs.getInt("nivel");
                String opcion1 = rs.getString("opcion1");
                String opcion2 = rs.getString("opcion2");
                String opcion3 = rs.getString("opcion3");
                String opcion4 = rs.getString("opcion4");
                String respuesta = rs.getString("respuesta");
                
                pregunta = new Pregunta();
                pregunta.setId(id);
                pregunta.setEnunciado(enunciado);
                pregunta.setNivel(nivel);
                pregunta.setOpcion1(opcion1);
                pregunta.setOpcion2(opcion2);
                pregunta.setOpcion3(opcion3);
                pregunta.setOpcion4(opcion4);
                pregunta.setRespuesta(respuesta);
                
                listaPregunta.add(pregunta);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return listaPregunta;
    }
    
    public int insert(Pregunta pregunta){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, pregunta.getId());
            stmt.setString(2, pregunta.getEnunciado());
            stmt.setInt(3, pregunta.getNivel());
            stmt.setString(4, pregunta.getOpcion1());
            stmt.setString(5, pregunta.getOpcion2());
            stmt.setString(6, pregunta.getOpcion3());
            stmt.setString(7, pregunta.getOpcion4());
            stmt.setString(8, pregunta.getRespuesta());
            
            
            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }
    
    public int update(Pregunta pregunta){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, pregunta.getEnunciado());
            stmt.setInt(2, pregunta.getNivel());
            stmt.setString(3, pregunta.getOpcion1());
            stmt.setString(4, pregunta.getOpcion2());
            stmt.setString(5, pregunta.getOpcion3());
            stmt.setString(6, pregunta.getOpcion4());
            stmt.setString(7, pregunta.getRespuesta());
            stmt.setInt(8, pregunta.getId());
            
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }
    
    public int delete(Pregunta pregunta){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, pregunta.getId());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }
}
