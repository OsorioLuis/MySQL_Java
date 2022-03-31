
package ConexionSQL.Frames.Asignaturas;

import ConexionSQL.DAO.DAOException;
import ConexionSQL.DAO.DAOManager;
import ConexionSQL.MVC.Asignatura;
import ConexionSQL.MySQL.MySQLDAOManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ListAsignaturasFrame extends javax.swing.JFrame {
    private AsignaturasTableModel modelo;
    
    private DAOManager manager;
    
    
    public ListAsignaturasFrame(DAOManager manager) throws DAOException {
        initComponents();
        //el dao manager del constructor es el que nos conecta con las clases de los 
        //daos principales
        
        //asignamos valor al manager
        this.manager = manager;
        this.modelo = new AsignaturasTableModel(manager.getAsignaturaDAO()); //este requiere un dao 
        //ahora hacemos que esta tabla utilice ese modelo que acabamos de pasarle
        this.tabla.setModel(modelo);
        actualizarTabla();//siempre en el constructor hay que llamar al actualizar
        //porque este nos construira la tabla con la lista del tablemodel
        
        //dos lineas para que no se inicie al momento de abrir la interfaz(precuación)
        this.detalle.setEditable(false);
        this.detalle.setAsignatura(null);
        
        //comboBox detalle
        this.detalle.setModel(new ProfesoresComboBox(manager.getProfesoresDAO()));
        
        activarBotonesCRUD(false);
        activarBotonesGuardar(false);
        
        tabla.getSelectionModel().addListSelectionListener(e ->{
           boolean valida = tabla.getSelectedRow() != -1; //sabemos que si es diferente de 1 es porque no identifica el elemento seleccionado
            activarBotonesCRUD(valida);
        });
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        detalle = new ConexionSQL.Frames.Asignaturas.DetalleAsignaturaPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        jPanel1.add(detalle, java.awt.BorderLayout.LINE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        activarBotonesGuardar(true); //activamos los botones
        detalle.setEditable(true);
        detalle.setAsignatura(null); //es null porque es una asignatura nueva que se 
        //esta metiendo ahora 
        
        //llamamos al load data
        detalle.cargarDatos();
    }//GEN-LAST:event_nuevoActionPerformed

    Asignatura obtenerAsignaturaSeleccionada() throws DAOException{
        int identificador = (int) tabla.getValueAt(tabla.getSelectedRow(), 0);
        
        //le pedimos al manager quenos traiga el id de la asignatura, accediendo desde
        //el dao de esta clase
        return  manager.getAsignaturaDAO().obtener(identificador);
    }
    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        try {
            activarBotonesGuardar(true); //activamos los botones
            detalle.setEditable(true);
            //llamamos al load data para que nos traiga los valores seleccionados 
            //a los campos de detalles
            
            //le pasamos el obtener de la clase obtenerasginatura( este tiene el dao de asignatura)
            detalle.setAsignatura(obtenerAsignaturaSeleccionada()); 
            detalle.cargarDatos();
            
        } catch (DAOException ex) {
            Logger.getLogger(ListAsignaturasFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_editarActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane,"¿Seguro que quieres borrar esta asignatura?", 
                "Borrar asignatura", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            try {
                Asignatura asig = obtenerAsignaturaSeleccionada();
                manager.getAsignaturaDAO().eliminar(asig);
                //llamamos al metodo update table
                actualizarTabla();
                activarBotonesCRUD(false);
                
            } catch (DAOException ex) {
                Logger.getLogger(ListAsignaturasFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_borrarActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        try{
            detalle.guardarDatos();
            Asignatura asignatura = detalle.getAsignatura();
            //cuando el id no es null se trata de una operacion modificar porque ya existe
            if(asignatura.getId() != null){
                
                manager.getAsignaturaDAO().modificar(asignatura);
                
            }else{ //este else es porque la asignatura no existe por lo cual inertara un nuevo usuario
                
                manager.getAsignaturaDAO().insertar(asignatura);
                
            }
            //una vez guardado:
            detalle.setAsignatura(null); //el campo asignatura se vacia 
            detalle.setEditable(false);  //ya detalle no es editable
            detalle.cargarDatos(); //se actualizan los cambios en la base de datos
            actualizarTabla(); //actualizamos la tabla y desactivamos botones
            activarBotonesCRUD(false);
            activarBotonesGuardar(false);
            
        }catch(DAOException ex){
            Logger.getLogger(ListAsignaturasFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_guardarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        detalle.setAsignatura(null); //vaciamos los campos de detalle
        detalle.setEditable(false);
        detalle.cargarDatos();
        //desactivamos botones
        activarBotonesCRUD(false);
        activarBotonesGuardar(false);
        
        //limpiar seleccion de la tabla 
        tabla.clearSelection();
        
    }//GEN-LAST:event_cancelarActionPerformed

    
    public static void main(String args[]) throws SQLException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    MySQLDAOManager manager = new MySQLDAOManager("127.0.0.1", "root", "borrado", "ejemplo" );
                    new ListAsignaturasFrame(manager).setVisible(true);
            }   catch (SQLException | DAOException ex ) {
                    Logger.getLogger(ListAsignaturasFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }
    //metodo usado para activar botones especificos sin hacer codigo redundate
    
    void activarBotonesCRUD(boolean activo){
        this.borrar.setEnabled(activo);
        this.editar.setEnabled(activo);
    }
    
    void activarBotonesGuardar(boolean activo){
        this.guardar.setEnabled(activo);
        this.cancelar.setEnabled(activo);
    }
    
    //update table
    public void actualizarTabla() throws DAOException{
        this.modelo.actualizar();
        this.modelo.fireTableDataChanged(); //este metodo identifica los cambios
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrar;
    private javax.swing.JButton cancelar;
    private ConexionSQL.Frames.Asignaturas.DetalleAsignaturaPanel detalle;
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
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

}

