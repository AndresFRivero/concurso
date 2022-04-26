package com.concurso.datos;

import com.concurso.entidades.Jugador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorJDBC {
    private static final String SQL_SELECT = "SELECT * FROM jugador";
    private static final String SQL_INSERT = "INSERT INTO jugador(id, nombre, apellido, nivel_alcanzado, premio, retiro_voluntario) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE jugador SET nombre=?, apellido=?, nivel_alcanzado=?, premio=?, retiro_voluntario=?, respuesta=? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM jugador WHERE id=?";
    
    /*INSERT INTO public.pregunta (id, enunciado, nivel, opcion1, opcion2, opcion3, respuesta) 
    VALUES ('1'::integer, '¿Pregunta?'::character varying, '1'::integer, 'Opcion 2'::character varying, 'Opcion 3'::character varying, 'Opcion 4'::character varying, 'd'::character varying) returning id;*/
    
    public List<Jugador> select(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Jugador jugador = null;
        List<Jugador> listaJugador = new ArrayList<Jugador>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int nivel_alcanzado = rs.getInt("nivel_alcanzado");
                float premio = rs.getFloat("premio");
                boolean retiro_v = rs.getBoolean("retiro_voluntario");
                
                jugador = new Jugador();
                jugador.setId(id);
                jugador.setNombre(nombre);
                jugador.setApellido(apellido);
                jugador.setNivelAlcanzado(nivel_alcanzado);
                jugador.setPremio(premio);
                jugador.setRetiroVoluntario(retiro_v);
                
                listaJugador.add(jugador);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return listaJugador;
    }
    
    public int insert(Jugador jugador){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, jugador.getId());
            stmt.setString(2, jugador.getNombre());
            stmt.setString(3, jugador.getApellido());
            stmt.setInt(4, jugador.getNivelAlcanzado());
            stmt.setFloat(5, jugador.getPremio());
            stmt.setBoolean(6, jugador.getRetiroVoluntario());
            
            
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
    
    public int update(Jugador jugador){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, jugador.getNombre());
            stmt.setString(2, jugador.getApellido());
            stmt.setInt(3, jugador.getNivelAlcanzado());
            stmt.setFloat(4, jugador.getPremio());
            stmt.setBoolean(5, jugador.getRetiroVoluntario());
            stmt.setInt(6, jugador.getId());
            
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
    
    public int delete(Jugador jugador){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, jugador.getId());
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
