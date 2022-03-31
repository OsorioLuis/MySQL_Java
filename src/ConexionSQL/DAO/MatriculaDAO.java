/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSQL.DAO;


import ConexionSQL.MVC.Matricula;
import java.util.List;



/**
 *
 * @author User
 */
public interface MatriculaDAO extends DAO<Matricula, Matricula.idMatricula> {
    
    
    //obtenemos todas las matriculas de un elemento concreto(alumno, asignatura, curso)
    List<Matricula> obtenerPorAlumno(int alumno) throws DAOException;
    
    List<Matricula> obtenerPorAsignatura(int asignatura) throws DAOException;
    
    List<Matricula> obtenerPorCurso(int curso) throws DAOException;

}
