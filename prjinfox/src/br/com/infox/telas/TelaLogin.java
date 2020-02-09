/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Bernardo
 */
public class TelaLogin extends javax.swing.JFrame {
    
    //usando a variável conexao do DAL
    Connection conexao = null;
    PreparedStatement pst = null;//framework pra manipular instruções sql
    ResultSet rs = null;//framework pra exibir o resultado das instruções sql

    //esse 3 são variaveis que contem frameworks p/ executar as instruções sql
    public void logar() {
        String sql = "select * from tbusuarios where login=? and senha=?";

        try {
            //tudo que envolve banco de dados eu tenho que usar o try/ pode ocorrer execessão
            //as linhas abaixo preparam a consulta ao banco 
            // o ? é substituido pelo conteudo das váriaveis

            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuario.getText());
            pst.setString(2, txtSenha.getText());

            //a linha abaixo executa a query
            rs = pst.executeQuery();

            //se existir usuário e senha correspondente
            if (rs.next()) {

                //a linha abaixo obtem o contéudo do campo perfil da tabela tbusuarios
                //lembrar das colunas do banco de dados->(1)iduser/(2)usuário/(3)fone/(4)login/(5)senha/(6)perfil
                String perfil = rs.getString(6);
                //System.out.println(perfil);->se alógica estiver correta ele printa o contéudo da variável

                //tratamento de perfil do usário
                if (perfil.equals("admin")) {
                    //caso exista usário e senha corresponte ele libera o acesso
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.menRel.setEnabled(true);
                    TelaPrincipal.menCadUsu.setEnabled(true);
                    TelaPrincipal.lblUsuario.setText(rs.getString(2));
                    TelaPrincipal.lblUsuario.setForeground(Color.red);
                    this.dispose();//fecha o formulário quando chamar a  tela principal
                    conexao.close();
                }else{
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    this.dispose();
                }

                

            } else {
                JOptionPane.showMessageDialog(null, "usuário e/ou senha inválido(s)");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    public TelaLogin() {
        initComponents(); //iniciação de todos os componentes
        conexao = ModuloConexao.conector();
        //a linha abaixo serve de apoio a o status da conexao        
        //System.out.println(conexao);

        if (conexao != null) {
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/dbok.png")));
        } else {
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/dberror.png")));

        }

    }

    /**
     * lblStatus-> propriedade da classe JLabel
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        lblStatus = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("X System login");
        setResizable(false);

        jLabel1.setText("Usuário");

        jLabel2.setText("Senha");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/dberror.png"))); // NOI18N
        lblStatus.setMaximumSize(new java.awt.Dimension(256, 256));
        lblStatus.setMinimumSize(new java.awt.Dimension(256, 256));
        lblStatus.setPreferredSize(new java.awt.Dimension(40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(200, 200, 200)
                        .addComponent(btnLogin)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(467, 254));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // chamando o método logar
        logar();
    }//GEN-LAST:event_btnLoginActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
