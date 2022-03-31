/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSQL.Frames.Profesores;

import ConexionSQL.DAO.DAOException;
import ConexionSQL.DAO.ProfesoresDAO;
import ConexionSQL.MVC.Profesores;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ProfesoresTablaModelo extends AbstractTableModel{
    
    private ProfesoresDAO dao;
    private List<Profesores> profesores = new ArrayList<>();
    
    public ProfesoresTablaModelo(ProfesoresDAO dao){
        this.dao = dao;
    }
    public void ActualizarModelo() throws DAOException{
        profesores = dao.obtenerTodos();
    }
    

    @Override
    public int getRowCount() {
        return profesores.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Profesores prof = profesores.get(rowIndex);
        switch(columnIndex){
            case 0: return prof.getId();
            case 1: return prof.getApellidos();
            case 2: return prof.getNombre();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "ID";
            case 1: return "Apellidos";
            case 2: return "Nombre";
            default: return null;
        }
    }
    
    
}
