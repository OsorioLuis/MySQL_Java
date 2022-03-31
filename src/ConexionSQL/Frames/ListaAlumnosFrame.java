
package ConexionSQL.Frames;



import ConexionSQL.DAO.DAOException;
import ConexionSQL.DAO.DAOManager;
import ConexionSQL.MVC.Alumno;
import ConexionSQL.MySQL.MySQLDAOManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
        
        
public class ListaAlumnosFrame extends javax.swing.JFrame {

    private DAOManager manager;
    
    private AlumnosTablaModel modelo;
    
    //este constructor lo que hace basicamente es insertar en la tabla 
    //el metodo obtenertodos de alumnos, usando conexiones de interfaces y metodos
    //de diseño de tabla
    public ListaAlumnosFrame(DAOManager manager) throws DAOException{
        initComponents();
        this.manager = manager;
        //al atributo modelo le pasamos una nueva instancia de AlumnosTablaModel que
        //recibe el getAlumnoDAO, este nos conectara con la clase que usa la interfaz
        //alumnoDAO.
        //esto sucede porque daoManager contiene los accesos a las interfaces de 
        //cada dao de nuestras tablas por lo que esto nos vincula con las clases 
        //que usan estas interfaces
        //
        this.modelo = new AlumnosTablaModel(manager.getAlumnoDAO());
        //reemplazamos este update.
        obtenerDatos();
//        this.modelo.updateModelo();
        
        //con esta linea le pasamos el modelo que diseñamos a la tabla del frame
        this.tabla.setModel(modelo);
        
        //seleccion
        this.tabla.getSelectionModel().addListSelectionListener(e -> {
            //hacemos la comprobacion de cuando selecciona algo
            boolean seleccionValida = (tabla.getSelectedRow() != -1); //retorna -1 cuando no encuentra datos
            Editar.setEnabled(seleccionValida);
            Borrar.setEnabled(seleccionValida);
           
        });
        System.out.println(tabla.getSelectedRow());

    }
    final void obtenerDatos() throws DAOException{
        progreso.setText("Actualizando modelo.");
        modelo.updateModelo(); //recuerda que este metodo nos permite obtener la lista de alumnos que aparecen en la tabla
        //tambien actualia la tabla
        progreso.setText(modelo.getRowCount() + " alumnos visibles");
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        Nuevo = new javax.swing.JButton();
        Editar = new javax.swing.JButton();
        Borrar = new javax.swing.JButton();
        Guardar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        Detalle = new ConexionSQL.Frames.DetalleAlumnoPanel();
        progreso = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setRollover(true);

        Nuevo.setText("Nuevo");
        Nuevo.setFocusable(false);
        Nuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Nuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoActionPerformed(evt);
            }
        });
        jToolBar1.add(Nuevo);

        Editar.setText("Editar");
        Editar.setFocusable(false);
        Editar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Editar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        jToolBar1.add(Editar);

        Borrar.setText("Borrar");
        Borrar.setFocusable(false);
        Borrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Borrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarActionPerformed(evt);
            }
        });
        jToolBar1.add(Borrar);

        Guardar.setText("Guardar");
        Guardar.setFocusable(false);
        Guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(Guardar);

        Cancelar.setText("Cancelar");
        Cancelar.setFocusable(false);
        Cancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Cancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });
        jToolBar1.add(Cancelar);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.BorderLayout());

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jPanel1.add(Detalle, java.awt.BorderLayout.LINE_END);

        progreso.setText("Registros visibles");
        jPanel1.add(progreso, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(657, 308));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private Alumno getAlumnoSeleccionado() throws DAOException{
        //tomamos lo que retorne al seleccionar una fila convirtiendolo a entero
        //para usarlo como id
        int id = (int) tabla.getValueAt(tabla.getSelectedRow(), 0); //0 hace referencia la columna que se tomara
        
        //accedemos por medio de la interfaz al mysqlestudiantedao para usar el metodo obtener 
        return manager.getAlumnoDAO().obtener(id);
    }
    
    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        
        //como esta denttro de una clausula va dentro del try: 
        //instancia un objeto con el alumno seleccionado para cuando le demos click en detalle 
        //setee el alumno que retorne el metodo privado de arriba
        //recuerda que detalle es el panel que hicimos dentro de este paquete
        try {
            Alumno alumno = getAlumnoSeleccionado(); //alumno vale el alumno que tenemos seleccionado
            Detalle.setAlumno(alumno);
            Detalle.setEditable(true); //si es editable
            Detalle.cargarDatos(); //cargamos los datos
            
            //ahora cuando seleccionemos el boton editar los otros se activan
            Guardar.setEnabled(true);
            Cancelar.setEnabled(true);
            
        } catch (DAOException ex) {
            Logger.getLogger(ListaAlumnosFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void BorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, "¿Seguro quieres borrar este alumno?", 
                "Borrar alumno", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            
            try {
            //cuando responde sí este procede a borrarlo
            //usando la obtencion del estudiante con el metodo
            //getALumnoSeleccionado
                Alumno alumno = null;
                //actualizamos la tabla
                alumno = getAlumnoSeleccionado();
                manager.getAlumnoDAO().eliminar(alumno);
                obtenerDatos(); //este metodo tiene el update model
                modelo.fireTableDataChanged(); //luego de estas dos lineas la tabla se actualiza luego de borrar el alumno
                
            } catch (DAOException ex) {
                Logger.getLogger(ListaAlumnosFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_BorrarActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        //obvio
        
        Detalle.setAlumno(null);
        Detalle.setEditable(false);
        Detalle.cargarDatos();
        tabla.clearSelection();
        
        Guardar.setEnabled(false);
        Cancelar.setEnabled(false);
    }//GEN-LAST:event_CancelarActionPerformed

    private void NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoActionPerformed
        //lo iniciamos en null para poder iniciar la insercion de datos
        Detalle.setAlumno(null);
        Detalle.setEditable(true);
        Detalle.cargarDatos(); //no escribe ningun dato porque es null
        
        Guardar.setEnabled(true);
        Cancelar.setEnabled(true);
        //el metodo de abajo y este estan relacionados, los dos se tienen que ejecutar para insertar un alumno en la bbdd
    }//GEN-LAST:event_NuevoActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        try {
            Detalle.guardarDatos();
            Alumno alu = Detalle.getAlumno();
            if(alu.getId() == null){
                manager.getAlumnoDAO().insertar(alu); //va a identificar que el alumno que insertamos no existe porque la id es null
            }else{
                manager.getAlumnoDAO().modificar(alu); //si ve que la id no es nula pues lo modifica 
            }
            
            //luego de insertar o actualizar el alumno los botones:
            Detalle.setAlumno(null);
            Detalle.setEditable(false);
            Detalle.cargarDatos();
            tabla.clearSelection();
        
            Guardar.setEnabled(false);
            Cancelar.setEnabled(false);
            
            //luego lee la tabla y actualiza lo que haya sucedido en esta
            obtenerDatos(); //este metodo tiene el update model
            modelo.fireTableDataChanged(); //este metodo le dice al listner que un
            //elemento de la tabla ha cambiado de valor
            
        } catch (ParseException | DAOException ex) {
            Logger.getLogger(ListaAlumnosFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_GuardarActionPerformed

    
    public static void main(String args[]) throws  SQLException{
        DAOManager manager = new MySQLDAOManager("127.0.0.1", "root", "borrado", "ejemplo");
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new ListaAlumnosFrame(manager).setVisible(true);
            } catch (DAOException ex) {
                Logger.getLogger(ListaAlumnosFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Borrar;
    private javax.swing.JButton Cancelar;
    private ConexionSQL.Frames.DetalleAlumnoPanel Detalle;
    private javax.swing.JButton Editar;
    private javax.swing.JButton Guardar;
    private javax.swing.JButton Nuevo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel progreso;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
