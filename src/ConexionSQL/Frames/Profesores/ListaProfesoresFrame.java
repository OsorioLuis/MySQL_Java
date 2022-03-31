
package ConexionSQL.Frames.Profesores;

import ConexionSQL.DAO.DAOException;
import ConexionSQL.DAO.DAOManager;
import ConexionSQL.MVC.Profesores;
import ConexionSQL.MySQL.MySQLDAOManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class ListaProfesoresFrame extends javax.swing.JFrame {

    private ProfesoresTablaModelo modelo;
    private DAOManager manager;
    
    public ListaProfesoresFrame(DAOManager manager) throws DAOException {
        initComponents();
        //conectar el table model
        this.manager = manager;
        this.modelo = new ProfesoresTablaModelo(manager.getProfesoresDAO());
        //le ponemos el modelo que creamos en la clase modelo
        this.tabla.setModel(modelo);
        actualizarTabla();
        tabla.getSelectionModel().addListSelectionListener(e -> {
            boolean valido = (tabla.getSelectedRow() != -1);
            //activa los botones segun el boolean que retorne, si identifica
            //que una fila esta seleccionada
                editar.setEnabled(valido);
                borrar.setEnabled(valido);
        });
    }
    
    void actualizarTabla() throws DAOException{
        modelo.ActualizarModelo();
        modelo.fireTableDataChanged();
        progreso.setText(modelo.getRowCount() + " Profesores visibles");
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        progreso = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        nuevo = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        editar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        borrar = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        guardar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        cancelar = new javax.swing.JButton();
        detalle = new ConexionSQL.Frames.Profesores.DetalleProfesorPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de profesore");

        progreso.setText("Preparado.");
        progreso.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        getContentPane().add(progreso, java.awt.BorderLayout.PAGE_END);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setEnabled(false);

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

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        nuevo.setText("Nuevo");
        nuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        jToolBar1.add(nuevo);
        jToolBar1.add(jSeparator1);

        editar.setText("Editar");
        editar.setEnabled(false);
        editar.setFocusable(false);
        editar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        jToolBar1.add(editar);
        jToolBar1.add(jSeparator2);

        borrar.setText("Borrar");
        borrar.setEnabled(false);
        borrar.setFocusable(false);
        borrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        borrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        jToolBar1.add(borrar);
        jToolBar1.add(jSeparator4);

        guardar.setText("Guardar");
        guardar.setEnabled(false);
        guardar.setFocusable(false);
        guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jToolBar1.add(guardar);
        jToolBar1.add(jSeparator3);

        cancelar.setText("Cancelar");
        cancelar.setEnabled(false);
        cancelar.setFocusable(false);
        cancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        jToolBar1.add(cancelar);

        jPanel1.add(jToolBar1, java.awt.BorderLayout.PAGE_START);
        jPanel1.add(detalle, java.awt.BorderLayout.LINE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        detalle.setProfesor(null); //para indicarle que fuese nuevo
        detalle.setEditable(true);
        detalle.cargarDatos();
        //se activam estos botones
        guardar.setEnabled(true);
        cancelar.setEnabled(true);
    }//GEN-LAST:event_nuevoActionPerformed
    
    Profesores obtenerProfesores() throws DAOException{
        //nos permite obtener el profesor que esta seleccionado de la tabla
        int id = (int) modelo.getValueAt(tabla.getSelectedRow(), 0);
        return manager.getProfesoresDAO().obtener(id);
    }

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        try {
            //cuando le damos a editar se cargan los datos de ese profesor.
            detalle.setProfesor(obtenerProfesores()); //para indicarle que fuese nuevo
            detalle.setEditable(true);
            detalle.cargarDatos();
            //se activam estos botones
            guardar.setEnabled(true);
            cancelar.setEnabled(true);
        } catch (DAOException ex) {
            Logger.getLogger(ListaProfesoresFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_editarActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, 
                "¿Seguro que quiere borrar este profesor?", 
                "Borrar Profesor", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            
            Profesores pborrar;
            try {
                pborrar = obtenerProfesores(); //el profesor que hemos seleccionado de la tabla
                manager.getProfesoresDAO().eliminar(pborrar);
                actualizarTabla(); //actualiza los datos de la tabla
                tabla.clearSelection();
                
                //desactivar botones
                editar.setEnabled(false);
                borrar.setEnabled(false);
                guardar.setEnabled(false);
                cancelar.setEnabled(false);
                
            } catch (DAOException ex) {
                Logger.getLogger(ListaProfesoresFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_borrarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        detalle.setProfesor(null); //para que se borren los datos del textfield
        detalle.setEditable(false); //para que no se puede modificar
        detalle.cargarDatos(); //para que se actualice todo
        
        guardar.setEnabled(false);
        cancelar.setEnabled(false);
    }//GEN-LAST:event_cancelarActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        try{
            detalle.guardarDatos(); //primero guardamos los datos que esten en el 
            //text field
            Profesores datosprofesor = detalle.getProfesor();
            if(datosprofesor.getId() == null){
                manager.getProfesoresDAO().insertar(datosprofesor);
            
            }else{
                manager.getProfesoresDAO().modificar(datosprofesor);
            }
            actualizarTabla();
            detalle.setProfesor(null);
            detalle.setEnabled(false);
            detalle.cargarDatos();
            guardar.setEnabled(false);
            cancelar.setEnabled(false);
            
        } catch (DAOException ex) {
            Logger.getLogger(ListaProfesoresFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_guardarActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MySQLDAOManager manager1 = new MySQLDAOManager("127.0.0.1" , "root", "borrado", "ejemplo");
                    new ListaProfesoresFrame(manager1).setVisible(true);
                } catch (DAOException | SQLException ex) {
                    Logger.getLogger(ListaProfesoresFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrar;
    private javax.swing.JButton cancelar;
    private ConexionSQL.Frames.Profesores.DetalleProfesorPanel detalle;
    private javax.swing.JButton editar;
    private javax.swing.JButton guardar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton nuevo;
    private javax.swing.JLabel progreso;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
