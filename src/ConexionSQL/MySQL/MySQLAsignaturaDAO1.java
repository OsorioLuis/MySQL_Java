/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSQL.MySQL;

import ConexionSQL.DAO.AsignaturaDAO;
import ConexionSQL.MVC.Asignatura;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ConexionSQL.DAO.DAOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



/**
 *
 * @author User
 */

public class MySQLAsignaturaDAO1 implements AsignaturaDAO {
    
    private Connection conn;
    
    //consultas sql:
    final String INSERT = "INSERT INTO ejemplo.asignaturas(nombre, profesor) VALUES (?,?)";
    final String UPDATE = "UPDATE ejemplo.asignaturas SET nombre = ?, profesores = ? WHERE id = ?";
    final String DELETE = "DELETE FROM ejemplo.asignaturas WHERE id = ?";
    final String GET_ALL = "SELECT id, nombre, profesor FROM ejemplo.asignaturas";
    final String GET_ONE = GET_ALL + " WHERE id = ?";
    
    public MySQLAsignaturaDAO1(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Asignatura asignatura) throws DAOException{
        PreparedStatement state = null;
        ResultSet rs = null;
        
        try{
            state = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            
            state.setString(1, asignatura.getNombre());
            
            //usamos el set int porque getIdprofesor es una clave foranea.
            state.setInt(2, asignatura.getId_Profesor());
            
            
            if(state.executeUpdate() == 0){
                throw new DAOException("No se ha podido introducir los datos. ");
            }
            
            rs = state.getGeneratedKeys();
            if(rs.next()){
                asignatura.setId(rs.getInt(1));
            }else{
                throw new DAOException("Error en la generación de keys");
            }
            
            
        }catch(SQLException ex){
            throw new DAOException("Error en la conexión SQL " + ex);
        }finally{
            MySQLUtilities.cerrar(state, rs);
        }
    }

    @Override
    public void modificar(Asignatura asignatura) throws DAOException{
        PreparedStatement state = null;
        try{
            state = conn.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
            state.setString(1, asignatura.getNombre());
            state.setInt(2, asignatura.getId_Profesor());
            state.setInt(3, asignatura.getId());
            
            
            
            if(state.executeUpdate() == 0){
                throw new DAOException("Error en la modificación de dato. ");
            }
            
        }catch(SQLException x){
            throw new DAOException("Error en la conexión SQL " + x);
        }finally{
            MySQLUtilities.cerrar(state);
        }
    }

    @Override
    public void eliminar(Asignatura asignatura) throws DAOException{
        PreparedStatement state = null;
        
        try{
            state = conn.prepareStatement(DELETE);
            state.setInt(1, asignatura.getId());
            
            if(state.executeUpdate() == 0){
                throw new DAOException("No se ha podido eliminar el dato");
            }else{
                System.out.println("Se ha eliminado la asignatura con ID :" + asignatura.getId());
            }
            
        }catch(SQLException m){
            throw new DAOException("Error en la conexión SQL " + m);
        }finally{
            MySQLUtilities.cerrar(state);
        }
    }
    
    private Asignatura convertir(ResultSet rs) throws DAOException, SQLException{
        
        //le mandamos los get del tipo resultset de los atributos que queremos convertir
        
        String nombre = rs.getString("nombre");
        Integer idProfesor = rs.getInt("profesor");
        
        //conversion y uso de la clase asignatura
        Asignatura asignatura = new Asignatura(nombre, idProfesor);
        asignatura.setId(rs.getInt("id"));
        
        return asignatura;
    }

    @Override
    public List<Asignatura> obtenerTodos() throws DAOException {
        List<Asignatura> asignatura = new ArrayList<>();
        PreparedStatement state = null;
        ResultSet rs = null;
        
        try{
            state = conn.prepareStatement(GET_ALL);
            rs = state.executeQuery();
            
            while(rs.next()){
                //conversion del resultset usando el metodo privado, para guardar la lista 
                //de tipo profesores con valores de tipo asignatura.
                asignatura.add(convertir(rs));
                //nos retorna un arraylist de asignatura
            }
        }catch(SQLException ex){
            throw new DAOException("Error al obtener los datos " + ex);
        }finally{
            MySQLUtilities.cerrar(state, rs);
        }
        return asignatura;  
    }

    @Override
    public Asignatura obtener(Integer id) throws DAOException {
        Asignatura asignatura = null;
        PreparedStatement state = null;
        ResultSet rs = null;
        
        try{
            state = conn.prepareStatement(GET_ONE, Statement.RETURN_GENERATED_KEYS);
            state.setInt(1, id);
            
            rs = state.executeQuery();
            
            if(rs.next()){
                asignatura = convertir(rs);
            }else{
                throw new DAOException("Error al consultar asignatura");
            }
            
        }catch(SQLException ex){
            throw new DAOException("Error al conectar con SQL " + ex);
        }finally{
            MySQLUtilities.cerrar(state, rs);
        }
        return asignatura;
        
    }

    
    
}
