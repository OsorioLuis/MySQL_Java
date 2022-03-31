/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSQL.MySQL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ConexionSQL.DAO.DAOException;
import java.sql.ResultSet;

/**
 *
 * @author User
 */

public class MySQLUtilities {
    
    //metodos estaticos para no tener que crear objetos que queden sin usar 
    //despues
    static void cerrar(PreparedStatement state) throws DAOException{
        if(state != null){
            try{
                state.close();
            }catch(SQLException ex){
                throw new DAOException("Error al cerrar el recurso SQL " + ex);
            }
        }
    }
    static void cerrar(PreparedStatement state, ResultSet rs) throws DAOException{
        if(state != null){
            try{
                state.close();
            }catch(SQLException ex){
                throw new DAOException("Error al cerrar el recurso SQL " + ex);
            }
        }if(rs != null){
            try{
                rs.close();
            }catch(SQLException ex){
                throw new DAOException("Error al cerrar el recurso SQL " + ex);
            }
        }
    }
}
