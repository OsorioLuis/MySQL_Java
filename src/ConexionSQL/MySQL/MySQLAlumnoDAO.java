/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSQL.MySQL;

import ConexionSQL.DAO.AlumnoDAO;
import ConexionSQL.DAO.DAOException;
import ConexionSQL.MVC.Alumno;
import java.util.List;
import java.text.SimpleDateFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Statement;


/**
 *
 * @author User
 */
public class MySQLAlumnoDAO implements AlumnoDAO {
    
    //sentencias generales de valores que seran inyectados por parametro, metodos o lo que sea
    final String INSERT = "INSERT INTO ejemplo.alumnos ( nombre, apellidos, fecha_nacimiento) VALUES (?,?,?)";
    final String UPDATE = "UPDATE ejemplo.alumnos SET nombre = ?, apellidos = ?, fecha_nacimiento = ? WHERE id = ?";
    final String DELETE = "DELETE FROM ejemplo.alumnos WHERE id = ?";
    final String GET_ALL = "SELECT id, nombre, apellidos, fecha_nacimiento FROM ejemplo.alumnos";
    final String GET_ONE = "SELECT id, nombre, apellidos, fecha_nacimiento FROM ejemplo.alumnos WHERE id = ?";
    
    private Connection conn;
    
    public MySQLAlumnoDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insertar(Alumno alumno) throws DAOException {
        PreparedStatement state = null;
        ResultSet rs = null;
        try{
            state = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            //recibe el mismo id del alumno
            
            //estos metodos vienen de los getters de los atributos hechos en la clase alumno
            state.setString(1, alumno.getNombre());
            state.setString(2, alumno.getApellidos());
            //los gets de la clase mvc alumno que seran definidos por parametro
            
            //hay que castear porque el dato fecha de java no es el mismo dato fecha
            //de sql
            state.setDate(3, new java.sql.Date(alumno.getFechaNacimiento().getTime()));
            
            
            //verifica cuando se actualice la tabla, retorna 0 cuando no han habido 
            //cambios en ninguna columna
            
            
            if(state.executeUpdate() == 0){
                throw new DAOException("No se ha actualizado la tabla");
            }
            
            rs = state.getGeneratedKeys(); //un resultset que contiene las claves que se han generado basicamente el id autoincrementado
            //con este extrameos la clave, se sabe que la clave esta en la posicion 
            //1 de nuestras tablas si hay un siguiente valor
            if(rs.next()){
                alumno.setId(rs.getInt(1)); //con esto extraemos el id
            }else{
                throw new DAOException("No se puede asignar id");
            }
            
        }catch (SQLException ex){
            throw new DAOException("Error SQL en: " + ex);
        }
        finally{
            MySQLUtilities.cerrar(state, rs);
        }
    
    }
    

    @Override
    public void modificar(Alumno alumno) throws DAOException{
        PreparedStatement state = null;
        try{
            state = conn.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
            state.setString(1, alumno.getNombre());
            state.setString(2, alumno.getApellidos());
            state.setDate(3, new java.sql.Date(alumno.getFechaNacimiento().getTime()));
            state.setInt(4, alumno.getId());
            
            //no usar otro execute update porque se registran 2 veces
            
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
    public void eliminar(Alumno alumno) throws DAOException {
        PreparedStatement state = null;
        try{
            //enviamos el statement delete y le pasamos el id del alumno que se pase
            //por parametro, eliminara ese alumno segun esa id
            state = conn.prepareStatement(DELETE);
            state.setInt(1, alumno.getId());
            
            if(state.executeUpdate() == 0){ //cuando es 0 es porque se han modificado 0 filas
                throw new DAOException("El alumno especificado no ha sido borrado");
            }
   
        }catch(SQLException ex){
            throw new DAOException("Error en SQL " + ex);
        }
        finally{
            MySQLUtilities.cerrar(state);
        }
    }
    
    //metodo para convertir en un alumno un resultset que como sabemos 
    //es un tipo de resultado que se arroja cuando se hacen conexiones 
    //con la base de datos
    private Alumno convertir(ResultSet rs) throws SQLException{
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");
        java.sql.Date fechaNac = rs.getDate("fecha_nacimiento");
        //nos trae los datos de la tabla que es rs en ese momento
        
        Alumno alumno = new Alumno(nombre, apellidos, fechaNac);
        //le pasamos por constructor los get que haya traido el rs
        alumno.setId(rs.getInt("id")); //definimos el id que haya en la tabla en el momento que rs tenga el valor de la tabla
        
        return alumno;
    }

    @Override
    public List<Alumno> obtenerTodos() throws DAOException {
        //lo mismo pero dentro de un while
        PreparedStatement state = null;
        ResultSet rs = null;
        List<Alumno> alumno = new ArrayList<>();
        
        
        try{
            state = conn.prepareStatement(GET_ALL, Statement.RETURN_GENERATED_KEYS);
            rs = state.executeQuery();
            
            while(rs.next()){
                //hacemos la conversion de los datos 
                //que encuentre si next es verdadero.
                //ahora no nos retornará un resultset si no un dato 
                //tipo alumno gracias al metodo convertir.
                
                alumno.add(convertir(rs)); //agrega a la lista alumno luego de haberlos convertido
            }
            
        }catch(SQLException ex){
            throw new DAOException("Error en SQL " + ex);
        }finally{
            MySQLUtilities.cerrar(state, rs);
        }
        return alumno;
        
    }

    @Override
    public Alumno obtener(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Alumno a = null;
        try{
            stat = conn.prepareStatement(GET_ONE, Statement.RETURN_GENERATED_KEYS); //enviamos esa peticion al motor de base de datos
            //pasar los datos que recibira el getone
            stat.setInt(1, id); //el id que recibe este metodo por parametro es aquel que queremos hacer la obtencion de datos de la tabla segun id especifico (nombre del metodo)
            rs = stat.executeQuery();
            
            if(rs.next()){ //si esto es verdadero es porque si nos devuelve un resultado
                a = convertir(rs); //le pasamos el rs que tiene los valores de la consulta realizada y con el metodo
                //convertir lo transformamos a un tipo alumno y este se guarda en a que también es tipo alumno 
            }else{
                throw new DAOException("No se ha encontrado datos de alumno.");
            }
            
        }catch(SQLException ex){
            throw new DAOException("Error en SQL" + ex);
        }
        finally{
            MySQLUtilities.cerrar(stat, rs);
            
        }
        return a;
    }
    
    //prueba sintetico
    
    public static void main(String[] args) throws DAOException{
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ejemplo" , "root", "borrado");
            AlumnoDAO dao = new MySQLAlumnoDAO(conn);
            List<Alumno> alumnos = dao.obtenerTodos();
            
            //consulta de alumnos
            for(Alumno i: alumnos){
                System.out.println(i.toString());
            }
            
            //insertar alumno
            
//            Alumno nuevo = new Alumno("Hernesto", "aaaaaaaaaaaa", (java.sql.Date) new Date(1991,01,01));
//            nuevo.setId(52);
//            dao.insertar(nuevo);

            Alumno nuevo2 = new Alumno("miguel", "eage" ,(java.sql.Date) new Date(1997,1,1));
            dao.insertar(nuevo2);
            System.out.println("se ha registrado el alumno exitosamente con id " + nuevo2.getId());
            
            
        }catch(SQLException ex){
            throw new DAOException("Error en SQL " + ex);
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException ex){
                    throw new DAOException("Error al cerrar conexión. " + ex);
                }
                
            }
        }
    }
    
}
