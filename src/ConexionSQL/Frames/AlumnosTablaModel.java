/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSQL.Frames;
import ConexionSQL.DAO.AlumnoDAO;
import ConexionSQL.DAO.DAOException;
import ConexionSQL.MVC.Alumno;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
class AlumnosTablaModel extends AbstractTableModel {
    
    //manejo de tablas
    //usamos alumno dao porque este nos comunica con la clase
    private AlumnoDAO alumnos;
    
    //lista para guardar datos del alumno
    private List<Alumno> datos = new ArrayList<>();
    
    public AlumnosTablaModel(AlumnoDAO alumno){
        this.alumnos = alumno;
        
    }
    public void updateModelo() throws DAOException{
        datos = alumnos.obtenerTodos();
        //una lista de la tabla alumnos, se sabe que datos es una lista de alumnos
        //obtenertodos retorna un tipo alumnos por eso este debe ser alumno
    }

    @Override
    public String getColumnName(int column) {
        
        //con el switch case le asignamos los nombres de las columnas, funciona como un 
        //condicional que evalua las posiciones que tenga
        switch(column){
            case 0: return "ID";
            case 1: return "Apellidos";
            case 2: return "Nombre";
            case 3: return "Fecha de Nacimiento";
            default: return "[no";
        }
    }        

    @Override
    public int getRowCount() {
        //si hay 20 alumnos en el arraylist pues las filas son 20
       return datos.size(); //es adaptable al tamaño de la lista
    }

    @Override
    public int getColumnCount() {
        return 4; //porque son las columnas de nuestra tabla alumnos
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Alumno preguntado = datos.get(rowIndex);
        
        //preguntado es un tipo alummo y aloja la lista datos de alumnos
        //por lo cual daremos los datos por cada columna segun las filas
        //que se estén especificando internamente
        //basicamente este metodo nos puede obtener los datos
        switch(columnIndex){
            case 0: return preguntado.getId();
            case 1: return preguntado.getApellidos();
            case 2: return preguntado.getNombre();
            case 3: 
                DateFormat df = DateFormat.getDateInstance();
                return df.format(preguntado.getFechaNacimiento());
            default: return null;
            
        }
    }
}
