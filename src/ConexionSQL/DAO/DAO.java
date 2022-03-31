/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSQL.DAO;

import ConexionSQL.MVC.Alumno;
import java.util.List;

/**
 *
 * @author User
 */
public interface DAO<T, K> {
    
    void insertar(T alumno) throws DAOException;
    
    void modificar(T alumno) throws DAOException;
    
    void eliminar(T alumno) throws DAOException;
    
    List<T> obtenerTodos() throws DAOException;
    
    T obtener(K id) throws DAOException; //este es para la matricula
}
