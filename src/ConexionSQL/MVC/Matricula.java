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
public class Matricula {
    
    public class idMatricula{
        private int alumno;
        private int asignatura;
        private int year;
        
        public idMatricula(int alumno, int asignatura, int year){
            this.alumno = alumno;
            this.asignatura = asignatura;
            this.year = year;
        }

        public int getAlumno() {
            return alumno;
        }

        public void setAlumno(int alumno) {
            this.alumno = alumno;
        }

        public int getAsignatura() {
            return asignatura;
        }

        public void setAsignatura(int asignatura) {
            this.asignatura = asignatura;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        @Override
        public String toString() {
            return "idMatricula{" + "alumno=" + alumno + ", asignatura=" + asignatura + ", year=" + year + '}';
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 79 * hash + this.alumno;
            hash = 79 * hash + this.asignatura;
            hash = 79 * hash + this.year;
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
            final idMatricula other = (idMatricula) obj;
            if (this.alumno != other.alumno) {
                return false;
            }
            if (this.asignatura != other.asignatura) {
                return false;
            }
            if (this.year != other.year) {
                return false;
            }
            return true;
        }
        
        
        
        
    }
    //le insertamos una clase interna que contiene las construcciones de las 3 claves foraneas
    //que tenemos, asignatura año y alumno, esta dentro para indicar que fomra parte de la clae
    //matricula
    
    private idMatricula id; //id = a tipo idMatricula (varias claves primarias
    private Integer nota = null;
    
    //ahora la clase matricula tiene para operar con esas claves
    
    public Matricula(idMatricula id){
        this.id = id; //constructor básico 
    }
    public Matricula(int alumno, int asignatura, int year){
        this.id = new idMatricula(alumno, asignatura, year);
        //constructor que instancia la clase y asigna los valores pasados por parametro
        //a sus respectivas claves primarias
    }
    //ahora para acceder a las claves se hace por medio de otro getter que tiene la clase idMatricula
    public idMatricula getId() {
        return id;
    }

    public void setId(idMatricula id) {
        this.id = id;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Matricula{" + "id=" + id + ", nota=" + nota + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.nota);
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
        final Matricula other = (Matricula) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nota, other.nota)) {
            return false;
        }
        return true;
    }
    
    
    
    
           
}
