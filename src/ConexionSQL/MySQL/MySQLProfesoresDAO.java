/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSQL.MySQL;

import ConexionSQL.DAO.DAOException;
import ConexionSQL.DAO.ProfesoresDAO;
import ConexionSQL.MVC.Profesores;

import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class MySQLProfesoresDAO implements ProfesoresDAO {
    
    private Connection conn;
    
    //atributos de sql statements
    final String INSERT = "INSERT INTO ejemplo.profesores(nombre, apellidos) VALUES (?,?)";
    final String UPDATE = "UPDATE ejemplo.profesores SET nombre = ?, apellidos = ? WHERE id = ?";
    final String DELETE = "DELETE FROM ejemplo.profesores WHERE id = ?";
    final String GET_ALL = "SELECT id, nombre, apellidos FROM ejemplo.profesores";
    final String GET_ONE = GET_ALL + " WHERE id = ?";
    
    public MySQLProfesoresDAO(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insertar(Profesores profesor) throws DAOException{
        PreparedStatement state = null;
        ResultSet rs = null;
        
        try{
            
            state = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            
            state.setString(1, profesor.getNombre());
            state.setString(2, profesor.getApellidos());
            
            //no repetir executeUpdate

            if(state.executeUpdate() == 0){
                throw new DAOException("No se ha actualizado la tabla");
            }
            //insertamos las claves
            rs = state.getGeneratedKeys();
            if(rs.next()){
                profesor.setId(rs.getInt(1));
            }else{
                throw new DAOException("Error en la generacion de id");
            }

        }catch(SQLException ex){
            throw new DAOException("Error con la inserción en la base de datos " + ex);
        }finally{
            MySQLUtilities.cerrar(state, rs);
        }
        
    }

    @Override
    public void modificar(Profesores profesor) throws DAOException{
        PreparedStatement state = null;
        try{
            state = conn.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
            state.setString(1, profesor.getNombre());
            state.setString(2, profesor.getApellidos());
            state.setInt(3, profesor.getId());

            if(state.executeUpdate() == 0){
                throw new DAOException("No se pudo actualizar el registro");
            }
        }catch(SQLException ex){
            throw new DAOException("Error en la modificacion de datos " + ex);
        }finally{
            MySQLUtilities.cerrar(state);
        }
    }

    @Override
    public void eliminar(Profesores profesor)throws DAOException {
        PreparedStatement state = null;
        try{
            state = conn.prepareStatement(DELETE);
            state.setInt(1, profesor.getId());
            
            
            if(state.executeUpdate() == 0){
                throw new DAOException("No se ha eliminado ningún dato del registro");
            }else{
                System.out.println("Se ha eliminado el profesor con id " + profesor.getId());
            }
            
        }catch(SQLException ex){
            throw new DAOException("Error al eliminar un dato: " + ex);
        }finally{
            MySQLUtilities.cerrar(state);
        }
        
    }
    //metodo que convierte un resultset en un profesor
    private Profesores convertir(ResultSet rs) throws DAOException, SQLException{
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");
        
        Profesores profesor = new Profesores(nombre, apellidos);
        profesor.setId(rs.getInt("id"));
        
        return profesor;
    }

    @Override
    public List<Profesores> obtenerTodos() throws DAOException{
        List<Profesores> profesores = new ArrayList<>();
        PreparedStatement state = null;
        ResultSet rs = null;
        
        try{
            state = conn.prepareStatement(GET_ALL);
            rs = state.executeQuery();
            
            while(rs.next()){
                //conversion del resultset usando el metodo privado, para guardar la lista 
                //de tipo profesores con valores de tipo profesor.
                profesores.add(convertir(rs));
            }
        }catch(SQLException ex){
            throw new DAOException("Error al obtener los datos " + ex);
        }finally{
            MySQLUtilities.cerrar(state, rs);
        }
        return profesores;  
    }

    @Override
    public Profesores obtener(Integer id) throws DAOException{
        PreparedStatement state = null;
        ResultSet rs = null;
        Profesores p = null;
        
        try{
            state = conn.prepareStatement(GET_ONE);
            state.setInt(1, id);
            rs = state.executeQuery();
            
            if(rs.next()){
                p = convertir(rs);
            }else{
                throw new DAOException("No se ha encontrado el profesor ");
            }  
        }catch(SQLException ex){
            throw new DAOException("Error al consultar registro " + ex);
        }finally{
            MySQLUtilities.cerrar(state, rs);
        }
        return p;
    }
    public static void main(String[] args) throws DAOException{
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ejemplo" , "root" , "borrado");
            ProfesoresDAO dao = new MySQLProfesoresDAO(conn); //instanciamos la clase en si misma para pasar la conexion 
            
            //consultas de los datos;
            List<Profesores> profesores = dao.obtenerTodos();
            
            for(Profesores i:profesores){
                System.out.println(i.toString()); //usamos el toString para traer los datos de las tablas
            }
            System.out.println("Funciona :D");
            
            //insertar datos:
            Profesores nuevo = new Profesores("Eduardo" , "lol");
            
            
            //eliminar datos:
            
            
//            nuevo.setId(55);
//            dao.eliminar(nuevo);
            

        }catch(SQLException ex){
            throw new DAOException("Error en SQL " + ex);
        }finally{
            if(conn != null){
               try{
                   conn.close();
               }catch(SQLException ex){
                   throw new DAOException("Error al cerrar la conexión" + ex);
               }
            }
            
               
        }
    }
    
}
