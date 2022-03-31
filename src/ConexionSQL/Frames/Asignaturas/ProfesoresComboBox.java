
package ConexionSQL.Frames.Asignaturas;

import ConexionSQL.DAO.DAOException;
import ConexionSQL.DAO.ProfesoresDAO;
import ConexionSQL.MVC.Profesores;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

public class ProfesoresComboBox extends DefaultComboBoxModel<ProfesoresComboView>{
    
    private ProfesoresDAO profesores;
    private List<Profesores> lista;

    public ProfesoresComboBox(ProfesoresDAO profesores) {
        this.profesores = profesores;
        this.lista = new ArrayList<>();
    }

    public ProfesoresComboBox() {
        
    }
    
    public void update() throws DAOException{
        if(profesores != null){
            lista = profesores.obtenerTodos();
            //actualizamos selección
            removeAllElements();
        
            for(Profesores p: lista){
                //hacemos esta instancia porque este metodo tiene que usar un tipo profesor
                addElement(new ProfesoresComboView(p)); //añadimos a cada item el tostring de profesores
            }
        }
        
    }
    
    
    
}
