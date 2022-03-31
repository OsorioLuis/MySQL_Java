/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSQL.MySQL;

import ConexionSQL.DAO.DAOException;
import ConexionSQL.DAO.MatriculaDAO;
import ConexionSQL.MVC.Matricula;
import java.sql.Connection;
import java.util.List;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class MySQLMatriculaDAO implements MatriculaDAO{
    
    static final String INSERT = "INSERT INTO ejemplo.matriculas(alumno, asignatura, fecha, nota) VALUES (?,?,?,?)";
    static final String UPDATE = "UPDATE ejemplo.matriculas SET nota = ? WHERE alumno = ? AND asignatura = ? AND fecha = ?";
    static final String DELETE = "DELETE FROM ejemplo.matriculas WHERE alumno = ? AND asignatura = ? AND fecha =?";
    static final String GET_ALL = "SELECT alumno, asignatura, fecha, nota FROM ejemplo.matriculas";
    static final String GET_ONE = GET_ALL + "WHERE alumno = ? AND asignatura = ? AND fecha = ?";
    
    //accesores a las claves primarias unicas
    static final String GET_ALUM = GET_ALL + "WHERE alumno = ?";
    static final String GET_CURSO = GET_ALL + "WHERE fecha = ?";
    static final String GET_ASIG = GET_ALL + "WHERE asignatura = ?";
    
    
    private Connection conn;
    
    public MySQLMatriculaDAO(Connection conn){
        this.conn = conn;
    }
    
    
    @Override
    public List<Matricula> obtenerPorAlumno(int alumno) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Matricula> obtenerPorAsignatura(int asignatura) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Matricula> obtenerPorCurso(int curso) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertar(Matricula m) throws DAOException {
        PreparedStatement state = null;
        try{
            state = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            state.setInt(1, m.getId().getAlumno()); //aqui usamos los metodos de la clase interna
            state.setInt(2, m.getId().getAsignatura());
            state.setInt(3, m.getId().getYear());
            
            if(m.getNota() != null){
                //sabemos que la nota puede ser nula pero cuando no lo es le insertamos el get de este
                state.setInt(4, m.getNota());
            }else{
                state.setNull(4, Types.INTEGER); //le asigna null y el type indica que es un null entero
            }
            
            //realizar insercion:
            if(state.executeUpdate() == 0){
                throw new DAOException("Error en la insercion de datos ");
            }
        }catch(SQLException ex){
            throw new DAOException("Error al conectar a SQL " + ex);
        }finally{
            MySQLUtilities.cerrar(state);
        }
    }

    @Override
    public void modificar(Matricula alumno) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Matricula alumno) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    Matricula convertir(ResultSet rs) throws DAOException, SQLException{
        int alumno = rs.getInt("alumno");
        int asignatura = rs.getInt("asignatura");
        int year = rs.getInt("fecha");
        
        //la nota puede ser null por lo que ahora es distinta la manipulacion 
        Integer nota = rs.getInt("nota");
        
        //linea para identificar si la nota que acaba de consultar es null, porque sin esto 
        //retornaria 0 al ser nulo y una nota tambien puede ser 0 pero no null
        if(rs.wasNull()) nota = null;
        
        //matricula
        Matricula mat = new Matricula(alumno, asignatura, year);
        mat.setNota(nota);
        return mat;
        
    }
    
    @Override
    public List<Matricula> obtenerTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Matricula obtener(Matricula.idMatricula id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
