package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 * (import net.proteanit.sql.DbUtils;)importa recursos da biblioteca rs2xml.jar
  para fazer coisas relacionadas as pesquisas, toipo auto complete;
 * @author Bernardo
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    //método para adicionar clientes

    private void adicionar() {
        String sql = "insert into tbclientes(nomecli,endcli,fonecli,emailcli)values(?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliFone.getText());
            pst.setString(4, txtCliEmail.getText());

            //validação dos campos obrigatórios 
            if ((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty())) {

                JOptionPane.showMessageDialog(null, " Preencha todos os campos obrigatórios");

            } else {

                //a linha abaixo atualiza a tabela usuário com os dados do formulário
                // pst.executeUpdate();
                // a estrutura abaixo é usada para confirmar a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, " Cliente adicionado com sucesso");
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliFone.setText(null);
                    txtCliEmail.setText(null);

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //toda vez que trabalhamos com banco de dados precisamos trabalhar com try catch
        }
    }

    //método para pesquisar clientes pelo nome com filtro
    private void pesquisar_cliente() {
        String sql = "select * from tbclientes where nomecli like ? ";
        
        try {
            pst=conexao.prepareStatement(sql);
            //passando  o contéudo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da String sql
            pst.setString(1,txtCliPesquisar.getText() + "%");
            rs=pst.executeQuery();
            
            //a linha abaixo usa a biblioteca rs2xml.jar para preencher atabela
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //método para setar os campos do formulário com o conteúdo da tabela
    
    public void setar_campos(){
        int setar=tblClientes.getSelectedRow();
        txtCliID.setText(tblClientes.getModel().getValueAt(setar,0).toString());
        txtCliNome.setText(tblClientes.getModel().getValueAt(setar,1).toString());
        txtCliEndereco.setText(tblClientes.getModel().getValueAt(setar,2).toString());
        txtCliFone.setText(tblClientes.getModel().getValueAt(setar,3).toString());
        txtCliEmail.setText(tblClientes.getModel().getValueAt(setar,4).toString());
        
        // alinha abaixo desabilita o botão adiconar
        btnCliAdicionar.setEnabled(false);
    }
    
       //método para alterar dados do cliente
    private void alterar(){
        String sql="update tbclientes set nomecli=?,endcli=?,fonecli=?,emailcli=? where idcli=?";
        
        try {
           pst=conexao.prepareStatement(sql);
           pst.setString(1,txtCliNome.getText());
           pst.setString(2,txtCliEndereco.getText());
           pst.setString(3,txtCliFone.getText());
           pst.setString(4,txtCliEmail.getText());
           pst.setString(5,txtCliID.getText()); //criterio
           
           if ((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty()) )  {
                
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {
           
             // a estrutura abaixo é usada para confirmar a alteração dos dados na tabela

            int adicionado = pst.executeUpdate();//executeUpdate(); usado para adicionar quanto para alterar

            if (adicionado >0) {
                JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso");
                txtCliNome.setText(null);
                txtCliEndereco.setText(null);
                txtCliFone.setText(null);
                txtCliEmail.setText(null);
                
                btnCliAdicionar.setEnabled(true);
          }
       }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //método responsavel pela remoção do cliente
     private void remover(){
        //a estrutura abaixo confirma a remoção do usuario 
        int confirma=JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este cliente", "Atenção", JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
            String sql="delete from tbclientes where idcli=?";
            try {
                //preparar a conexão
                pst=conexao.prepareStatement(sql);
                pst.setString(1, txtCliID.getText());
                
                int apagado=pst.executeUpdate();
                
                if(apagado>0){
                    JOptionPane.showMessageDialog(null,"Cliente Removido com sucesso ");
                txtCliNome.setText(null);
                txtCliEndereco.setText(null);
                txtCliFone.setText(null);
                txtCliEmail.setText(null);
                
                btnCliAdicionar.setEnabled(true);
                    }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        txtCliEndereco = new javax.swing.JTextField();
        txtCliFone = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        btnCliAdicionar = new javax.swing.JButton();
        btnCliAlterar = new javax.swing.JButton();
        btnCliRemover = new javax.swing.JButton();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtCliID = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

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

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(640, 480));

        jLabel1.setText("* Campos obrigatorios");

        jLabel2.setText("* Nome");

        jLabel3.setText("Endereço");

        jLabel4.setText("* Telefone");

        jLabel5.setText("email");

        btnCliAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/Create.png"))); // NOI18N
        btnCliAdicionar.setToolTipText("Clique Aqui Para Adicionar Cliente");
        btnCliAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCliAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAdicionarActionPerformed(evt);
            }
        });

        btnCliAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/Update.png"))); // NOI18N
        btnCliAlterar.setToolTipText("Clique Aqui Para Alterar Algum Dado Sobre Algum Cliente");
        btnCliAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliAlterar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCliAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAlterarActionPerformed(evt);
            }
        });

        btnCliRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/Delete.png"))); // NOI18N
        btnCliRemover.setToolTipText("Clique Aqui Para Excluir um Cliente");
        btnCliRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliRemover.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCliRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliRemoverActionPerformed(evt);
            }
        });

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/search.png"))); // NOI18N

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblClientes);

        jLabel6.setText("ID");

        txtCliID.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel2))
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addComponent(txtCliID, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCliFone, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(191, 191, 191)
                                .addComponent(jLabel1))
                            .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(btnCliAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCliAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCliRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCliID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCliFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCliAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCliAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCliRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        setBounds(0, 0, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCliAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAdicionarActionPerformed
        //método para adicionar clientes
        adicionar();
    }//GEN-LAST:event_btnCliAdicionarActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // o evento é do tipo "emquanto for digitando ":
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // evento usado para setar oas campo da tabela (clicando com o botão esq.do mouse)
        setar_campos();
        
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnCliAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAlterarActionPerformed
        // chamando método pra alterar cliente
        alterar();
    }//GEN-LAST:event_btnCliAlterarActionPerformed

    private void btnCliRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliRemoverActionPerformed
        // chamndo o método pra remover um cliente
        remover();
    }//GEN-LAST:event_btnCliRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliAdicionar;
    private javax.swing.JButton btnCliAlterar;
    private javax.swing.JButton btnCliRemover;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliFone;
    private javax.swing.JTextField txtCliID;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    // End of variables declaration//GEN-END:variables
}
