
package ConexionSQL.Frames.Asignaturas;

import ConexionSQL.MVC.Profesores;
import java.util.Objects;

public class ProfesoresComboView {
    //esta clase adapta un profesor para que sea renderizado en el combobox
    //usado en caso de que no tengamos acceso a la clase profesores, convencion
    //simplemente es un adapter
    
    //esta clase hace casi lo mismo que profesor, usada como remplazo SOLO PARA EL COMBOBOX
    
    private Profesores profesor;

    public ProfesoresComboView(Profesores profesor) {
        this.profesor = profesor;
    }

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profesor) {
        this.profesor = profesor;
    }

    @Override
    public int hashCode() {
        return profesor.hashCode(); //hereda lo que hace el hashcode de profesor para no hacer codigo redundate
    }

    @Override
    public boolean equals(Object obj) {
        return profesor.equals(obj);
    }

    @Override
    public String toString() {
        return profesor.getApellidos() + " " + profesor.getNombre();
    }
    
    
    
}
