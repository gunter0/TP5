/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;
import controladores.IVentanaNuevoPedido;
import controladores.controladorNuevoPedido;
import controladores.controladorPrincipal;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.LineaDePedido;
import modelo.Pedido;
import modelo.Producto;
import modelo.Rubro;
import modelo.Negocio;
/**
 *
 * @author Fernanda Ponce
 */

public class VentanaNuevoPedido extends javax.swing.JFrame implements IVentanaNuevoPedido {

    public static double calcTotal(double totalInicial, double precioUprod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Creates new form VNuevoPedido
     */
    private controladorNuevoPedido controlador;
    private java.util.ArrayList<LineaDePedido> lineasPedido;
    private int IDpv = 0001;
    private DefaultTableModel modelProd = new DefaultTableModel();
    private DefaultTableModel modelTot = new DefaultTableModel();
    private boolean v_ini = false;
    private Double total_fac = 0.0;
    
    public VentanaNuevoPedido() {
        initComponents();
        jButton2.setEnabled(true);
        jButton2.setVisible(true);
        //jComboBox2.setEnabled(true);
        setLocationRelativeTo(null);
        
        
        this.IDpv = 1;
        
        controlador = new controladorNuevoPedido(this);        
        modelProd = new DefaultTableModel();   
        modelTot = new DefaultTableModel();
        
        lineasPedido = new java.util.ArrayList<LineaDePedido>();
        controlador.cargarComboRubros();
        jButton2.setEnabled(true);
        //jComboBox2.setEnabled(false);
        
        
        cargarCol();
        armarTabProd(modelProd,"PRODUCTOS");
        v_ini = true;
    }       
    private void cargarCol(){        
        modelProd.addColumn("ID");
        modelProd.addColumn("Producto");
        modelProd.addColumn("Precio");                          
        
        modelTot.addColumn("Producto");
        modelTot.addColumn("Cantidad");        
        modelTot.addColumn("Total");
        
        //JTotal.setAutoResizeMode(JTotal.AUTO_RESIZE_OFF);
        JTotal.getTableHeader().setReorderingAllowed(false);
        JTotal.setModel(modelTot);
    }
    
    private void armarTabProd(DefaultTableModel model,String conc)
    {
        if(conc.equalsIgnoreCase("PRODUCTOS")){            
            String rubroSeleccionado = JRubros.getSelectedItem().toString();
            controlador.cargarTablaProductos(rubroSeleccionado);
        }else if(conc.equalsIgnoreCase("TOTAL")){
            
        }
    }
    
    public VentanaNuevoPedido(int IDpv) //int IDpv
    {       
        initComponents();
        setLocationRelativeTo(null);
        controlador = new controladorNuevoPedido(this);
        controlador.cargarComboRubros();
        this.IDpv = IDpv;

        lineasPedido = new java.util.ArrayList<LineaDePedido>();

        jButton2.setEnabled(false);
        
        //controlador = new controladorNuevoPedido(this);        
        modelProd = new DefaultTableModel();   
        modelTot = new DefaultTableModel();
                
        //controlador.cargarComboRubros();
        jButton2.setEnabled(false);                
        
        cargarCol();
        armarTabProd(modelProd,"PRODUCTOS");
        v_ini = true;
        
    }
    
    
    public void cargarComboRubros(java.util.ArrayList<String> list)
    {
        for(String campos : list) 
        {
            JRubros.addItem(campos);
        }    
    }    
    
    public void cargarProductos(List<Producto> list)
    {       
        int tot_fil  = modelProd.getRowCount();
        
        //for(int i = 0 ; i < tot_fil; i ++){
        //    modelProd.removeRow(0);
        // }
        
        for(Producto p : list){            
            modelProd.addRow(new Object[] {p.getId(),p.getConcepto(),p.GetPrecio()});
        }
        JTProd.setModel(modelProd);        
    }
    
    private void agregarLinea (){
        Integer row = JTProd.getSelectedRow();
        int id_prod = 0;
        String producto = "";
        double precio = 0.0;
        double total = 0.0;
        
        int cantidad = 0;
        String msg = "";
        if (row != -1 ){
            id_prod = Integer.parseInt(JTProd.getValueAt(row,0).toString());
            producto = JTProd.getValueAt(row,1).toString();
            precio = Double.parseDouble(JTProd.getValueAt(row,2).toString());
            
            //msg = JOptionPane.showInputDialog(this,"Cantidad","",JOptionPane.INFORMATION_MESSAGE);           
            
            int cant = Integer.parseInt(jTFCantidad.getText().toString());
            
            if (cant >= 0){
                try{
                    cantidad = cant;
                    if (cantidad > 0){
                        total = cantidad * precio;
                    }
                    
                    modelTot.addRow(new Object[] {producto,cantidad,total});
                    JTotal.setModel(modelTot);
                    
                    total_fac += total;
                    jtotal.setText("$ " + total_fac.toString());
                    
                    /******AGREGAR PEDIDO********/
                    //Producto p = new Producto(id_prod,producto,precio,false);
                    //lineasPedido.add(new LineaDePedido(p,cantidad,total));
                }catch(Exception ex){
                    
                }
            }            
        }
    }
    
    static double calcSubTotal(int cantidad, double precioUnitario){
        double subTotal = cantidad * precioUnitario;
        return subTotal;
    }
    
    static double calcTotal (double totalActual, double precioUnitario, int cantidad){
        totalActual = totalActual + precioUnitario * cantidad;
        return totalActual;
    }
    
    public void quitarProducto (){
        
        total_fac = total_fac - Double.parseDouble(JTotal.getValueAt(JTotal.getSelectedRow(),2).toString());
        jtotal.setText("$ " + total_fac.toString());
        
        DefaultTableModel dtm = (DefaultTableModel) JTotal.getModel();
        dtm.removeRow(JTotal.getSelectedRow()); 
    }
    
    public void crearLinea(){
        //Producto p = new Producto(id_prod,producto,precio,false);
        //lineasPedido.add(new LineaDePedido(p,cantidad,total));
        String agd;
        double pre;
        int cant;
            //double rd=0;
            //Producto pd = new Producto(i, agd, 90);
            for(int i=0;i<JTotal.getRowCount();i++){
                pre=Double.parseDouble(JTotal.getValueAt(i,2).toString());
                agd = JTotal.getValueAt(i, 0).toString();
                cant = Integer.parseInt(JTotal.getValueAt(i,1).toString());
                System.out.println(pre);
                Producto p = new Producto(i, agd,pre,false);
                lineasPedido.add(new LineaDePedido(p,1,1*pre));
                  
            }
    }
    
/*    private void cbRubros_SelectedIndexChanged(object sender, EventArgs e) 
    {
        String rubrSelecionado = jComboBox1.getSelectedItem().toString();
        controlador.cargarTablaProductos(rubrSelecionado);
    }
    
    
    
    
    public void cargarProductos(java.util.ArrayList<Producto> list)
    {
        tablaProducto.DataSource = list;
    }
*/
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        JRubros = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTProd = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTotal = new javax.swing.JTable();
        bt_termped = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jtotal = new javax.swing.JLabel();
        JTitle = new javax.swing.JLabel();
        bt_quitar = new javax.swing.JButton();
        bt_agregar = new javax.swing.JButton();
        jTFCantidad = new javax.swing.JTextField();
        JTitle1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana de Pedido");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Seleccionar Rubro:");

        JRubros.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JRubrosItemStateChanged(evt);
            }
        });
        JRubros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JRubrosMouseClicked(evt);
            }
        });
        JRubros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRubrosActionPerformed(evt);
            }
        });

        JTProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(JTProd);

        JTotal.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(JTotal);

        bt_termped.setBackground(new java.awt.Color(255, 204, 51));
        bt_termped.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        bt_termped.setText("Finalizar Pedido");
        bt_termped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_termpedActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("TOTAL:");

        jButton2.setBackground(new java.awt.Color(255, 204, 51));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jButton2.setText("Finalizar Turno");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jtotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtotal.setText("$ ");

        JTitle.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        JTitle.setText("Realizar Pedido");

        bt_quitar.setFont(new java.awt.Font("Tahoma", 1, 27)); // NOI18N
        bt_quitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/restar1.png"))); // NOI18N
        bt_quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_quitarActionPerformed(evt);
            }
        });

        bt_agregar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        bt_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Sumar.png"))); // NOI18N
        bt_agregar.setToolTipText("");
        bt_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_agregarActionPerformed(evt);
            }
        });

        jTFCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFCantidadActionPerformed(evt);
            }
        });

        JTitle1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        JTitle1.setText("Detalle de Pedido");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Ingresar cantidad:");

        jSeparator1.setForeground(new java.awt.Color(255, 153, 0));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comida.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Agregar Producto");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Quitar Producto");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo pedido.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_termped)
                .addGap(50, 50, 50)
                .addComponent(jButton2)
                .addGap(70, 70, 70))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jtotal)
                                            .addGap(239, 239, 239)
                                            .addComponent(bt_quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(24, 24, 24)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(183, 183, 183)
                                .addComponent(JTitle1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(184, 184, 184)
                                        .addComponent(JTitle))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(JRubros, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(83, 83, 83)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTFCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)))
                                        .addGap(40, 40, 40)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(JTitle)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(JRubros, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(1, 1, 1)
                                .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jTFCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JTitle1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jtotal)))))
                    .addComponent(jLabel3))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_termped, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_termpedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_termpedActionPerformed
        crearLinea();
        if(total_fac > 0){
            controlador.mostrarComanda(IDpv, controlador.nuevoPedido(IDpv,lineasPedido));
        }
    }//GEN-LAST:event_bt_termpedActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        // ver *****
        
        
        controlador.finalizarTurno(IDpv);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void JRubrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JRubrosMouseClicked
    }//GEN-LAST:event_JRubrosMouseClicked

    private void JRubrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRubrosActionPerformed
        
    }//GEN-LAST:event_JRubrosActionPerformed

    private void JRubrosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JRubrosItemStateChanged
        if (v_ini){
            String rubroSeleccionado = JRubros.getSelectedItem().toString();
            controlador.cargarTablaProductos(rubroSeleccionado);
        }        
    }//GEN-LAST:event_JRubrosItemStateChanged

    private void bt_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_agregarActionPerformed
        agregarLinea();
    }//GEN-LAST:event_bt_agregarActionPerformed

    private void bt_quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_quitarActionPerformed
        quitarProducto();

        // TODO add your handling code here:
        
        //Integer row = JTotal.getSelectedRow();
        
        /*int id_prod = 0;
        String producto = "";
        double precio = 0.0;
        double total = 0.0;
        int cantidad = 0;*/
        
        //id_prod = Integer.parseInt(JTotal.getValueAt(row,0).toString());
        //producto = JTotal.getValueAt(row,1).toString();
        //precio = Double.parseDouble(JTotal.getValueAt(row,2).toString());
        
        //total_fac -= total;
        //jtotal.setText("$ " + total_fac.toString());
        
        /******QUITAR DE PEDIDO********/
        //Producto p = new Producto(id_prod,producto,precio,false);
        //lineasPedido.remove(new LineaDePedido(p,cantidad,total));
        
        //modelTot.removeRow(row);
        //JTotal.setModel(modelTot);
    }//GEN-LAST:event_bt_quitarActionPerformed

    private void jTFCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFCantidadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaNuevoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaNuevoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaNuevoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaNuevoPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaNuevoPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JRubros;
    private javax.swing.JTable JTProd;
    private javax.swing.JLabel JTitle;
    private javax.swing.JLabel JTitle1;
    private javax.swing.JTable JTotal;
    private javax.swing.JButton bt_agregar;
    private javax.swing.JButton bt_quitar;
    private javax.swing.JButton bt_termped;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTFCantidad;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel jtotal;
    // End of variables declaration//GEN-END:variables

    @Override
    public void cargarComboRubros(List<String> list) {
        for(String s : list){
            JRubros.addItem(s);
        }
    }

    /*@Override
    public void cargarProductos(List<Producto> enumerable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public void motrarComanda(int idpv, Pedido p) {
        VentanaComanda x = new VentanaComanda(idpv,p);
        x.show();
    }
}
