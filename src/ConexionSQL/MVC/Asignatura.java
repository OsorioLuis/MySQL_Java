/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSQL.MVC;

import java.util.Objects;

/**
 *
 * @author User
 */
public class Asignatura {
    private Integer id = null;
    private String nombre;
    private Integer id_Profesor;

    public Asignatura(String nombre, Integer id_Profesor) {
        this.nombre = nombre;
        this.id_Profesor = id_Profesor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_Profesor() {
        return id_Profesor;
    }

    public void setId_Profesor(Integer id_Profesor) {
        this.id_Profesor = id_Profesor;
    }

    @Override
    public String toString() {
        return "Asignaturas{" + "id=" + id + ", nombre=" + nombre + ", id_Profesor=" + id_Profesor + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.nombre);
        hash = 23 * hash + this.id_Profesor;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Asignatura other = (Asignatura) obj;
        if (this.id_Profesor != other.id_Profesor) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
            
}
