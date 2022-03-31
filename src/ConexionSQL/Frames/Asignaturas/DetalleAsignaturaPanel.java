
package ConexionSQL.Frames.Asignaturas;

import ConexionSQL.DAO.DAOException;
import ConexionSQL.DAO.ProfesoresDAO;
import ConexionSQL.MVC.Asignatura;
import ConexionSQL.MVC.Profesores;



public class DetalleAsignaturaPanel extends javax.swing.JPanel {
    
    private ProfesoresDAO dao;
    private boolean editable;
    private Asignatura asignatura;
    private ProfesoresComboBox model;
    
    public DetalleAsignaturaPanel() {
        initComponents();
        model = new ProfesoresComboBox();
    }

    public DetalleAsignaturaPanel(ProfesoresDAO dao) throws DAOException {
        initComponents();
        model = new ProfesoresComboBox(dao);
        model.update();
        profesor.setModel(model); //para asignar el modelo dentro del combobox
    }
    
    //estos getter y setter para que pueda cambiar el modelo del combo según el que se le pase
    
    public ProfesoresComboBox getModel() {
        return model;
    }

    public void setModel(ProfesoresComboBox model) throws DAOException {
        this.model = model;
        profesor.setModel(model); //se le pone lo mismo que se le este proporcionando a este setter
        
        model.update();
    }
    
    
    
    public void setAsignatura(Asignatura a){
        this.asignatura = a;
    }
    
    public Asignatura getAsignatura(){
        return asignatura;
    }
    
    public void setEditable(boolean editable){
        this.editable = editable;
        
        //hacemos que el campo asignatura sea modificable
        this.nombre.setEnabled(editable);
        this.profesor.setEnabled(editable); //este nos dice si podemos hacer click en el para modificar un valor de la lista
    }
    
    public boolean isEditable(){
        return editable;
    }
    
    //metodos para cargar datos consultados en la base de datos en el combobox y el text
    public void cargarDatos(){
        //que los valores de la asginatura se carguen en el cuadro
        if(asignatura == null){
            asignatura = new Asignatura("", null);
        }
        nombre.setText(asignatura.getNombre());
        nombre.requestFocus();
    }
    public void guardarDatos(){
        if (asignatura == null){
            asignatura = new Asignatura("", null);
            
        }
        asignatura.setNombre(nombre.getText());
        //se asigna el profesor
        ProfesoresComboView pcv = (ProfesoresComboView) profesor.getSelectedItem();
        Profesores profesor = pcv.getProfesor(); //tenemos que sacar el profesor para obtenerlo 
        asignatura.setId_Profesor(profesor.getId()); //asignamos el id del profesor seleccionado
        
        
    }   
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        profesor = new javax.swing.JComboBox<>();
        nombre = new javax.swing.JTextField();

        jLabel1.setText("Asignatura");

        jLabel2.setText("Profesor");

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nombre)
                    .addComponent(profesor, 0, 217, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(profesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField nombre;
    private javax.swing.JComboBox<ProfesoresComboView> profesor;
    // End of variables declaration//GEN-END:variables
}
