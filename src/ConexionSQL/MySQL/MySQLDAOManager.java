/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSQL.MySQL;
import ConexionSQL.DAO.AlumnoDAO;
import ConexionSQL.DAO.AsignaturaDAO;
import ConexionSQL.DAO.DAOException;
import ConexionSQL.DAO.DAOManager;
import ConexionSQL.DAO.MatriculaDAO;
import ConexionSQL.DAO.ProfesoresDAO;
import ConexionSQL.MVC.Alumno;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author User
 */
public class MySQLDAOManager implements DAOManager{
    
    private Connection conn;
    
    private AlumnoDAO alumnos = null;
    private ProfesoresDAO profesores = null;
    private MatriculaDAO matricula = null;
    private AsignaturaDAO asignatura = null;
    

    public MySQLDAOManager(String host, String username, String pass, String database) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database,
                username,
                pass);
       
    }

    @Override
    public AlumnoDAO getAlumnoDAO() {
        if(alumnos == null){
            alumnos = new MySQLAlumnoDAO(conn);
        }
        return alumnos;
    }

    @Override
    public AsignaturaDAO getAsignaturaDAO() {
        if(asignatura == null){
            asignatura = new MySQLAsignaturaDAO1(conn);
        }
        return asignatura;
    }

    @Override
    public MatriculaDAO getMatriculaDAO() {
        if(matricula == null){
            matricula = new MySQLMatriculaDAO(conn);
        }
        return matricula;
    }   

    @Override
    public ProfesoresDAO getProfesoresDAO() {
        if(profesores == null){
            profesores = new MySQLProfesoresDAO(conn);
        }
        return profesores;
    }
    public static void main(String[] args) throws SQLException, DAOException{
        //implementacion de los metodos de la misma clase cuando le introducimos +
        //una lista 
        MySQLDAOManager man = new MySQLDAOManager("127.0.0.1" , "root", "borrado", "ejemplo");
        
        //esta clase implementa la interfaz que tiene metodos abstractos necesarios y esta
        //interfaz nos comunica con el metodo obtener datos(que es un metodo abstracto en esta
        //interfaz) de la clase MySQLAlumnoDAO
        
        List<Alumno> alumnos = man.getAlumnoDAO().obtenerTodos();
        System.out.println(alumnos);
    }
    
    
}
