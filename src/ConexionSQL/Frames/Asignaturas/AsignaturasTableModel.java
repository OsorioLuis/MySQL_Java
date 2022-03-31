
package ConexionSQL.Frames.Asignaturas;

import ConexionSQL.DAO.AsignaturaDAO;
import ConexionSQL.DAO.DAOException;
import ConexionSQL.MVC.Asignatura;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AsignaturasTableModel extends AbstractTableModel{
    
    private AsignaturaDAO dao;
    
    //como siempre un listado:
    private List<Asignatura> asignaturas;

    public AsignaturasTableModel(AsignaturaDAO dao) {
        this.dao = dao;
        this.asignaturas = new ArrayList<>();
    }
    
    public void actualizar() throws DAOException{
        asignaturas = dao.obtenerTodos(); //esta lista es la que tendra la tabla
    }

    @Override
    public String getColumnName(int column) {
        
        //basicamente los nombres de la asignatura, este metodo trabaja con el numero de 
        //columnas que tengba el getcolumncount
        if (column == 0){
            return "Identificador";
            
        }else{
            return "Nombre de la Asignatura";
        }
    }
    
    

    @Override
    public int getRowCount() {
        return asignaturas.size(); //tamaño de la lista
    }

    @Override
    public int getColumnCount() {
       return 2; //solo dos columnas, identificador y el propio nombre de la asignatura
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //le damos un objeto que enviara la id y el nombre, los values
        //de cada fila y columna
        Asignatura a = asignaturas.get(rowIndex); //sacamos la asignatura
        if(columnIndex == 0){
            return a.getId();
            
        }else{
            return a.getNombre();
        }
    }
    
    
}
