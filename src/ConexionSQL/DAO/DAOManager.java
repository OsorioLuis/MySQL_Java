/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSQL.DAO;

/**
 *
 * @author User
 */
public interface DAOManager {
    
    //este dao particular devuelve los daos de cada tabla de base de datos
    //recordemos que los dao son objeto de acceso da datos que es un componente 
    //que suministra unainferfaz comun entre aplicacion y uno o mas dispositivos de 
    //almacenamiento de datos, siendo este un patron de diseños util a la hora 
    //de migrar base de datos o aplicaciones
    
    //nos permite acceder a los daos de nuestra aplicacion 
    AlumnoDAO getAlumnoDAO();
    
    AsignaturaDAO getAsignaturaDAO();
    
    MatriculaDAO getMatriculaDAO();
    
    ProfesoresDAO getProfesoresDAO();
}
