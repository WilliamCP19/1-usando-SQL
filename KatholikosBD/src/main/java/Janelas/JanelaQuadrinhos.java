/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Janelas;

import DAO.QuadrinhoDAO;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author willi
 */
public class JanelaQuadrinhos extends javax.swing.JFrame {
    
    private QuadrinhoDAO hq =  QuadrinhoDAO.getDaoQuadrinho();
    public static JanelaQuadrinhos janelaQuadrinho;
    private int pos;
    
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    private JanelaQuadrinhos()  {
        initComponents();
    }
    /**
     * Creates new form JanelaQuadrinho1
     */

    public static JanelaQuadrinhos getJanelaQuadrinho () {
        if (janelaQuadrinho == null) {
            janelaQuadrinho = new JanelaQuadrinhos();
        }
        return janelaQuadrinho;
    }
    
    public final boolean preencheHQ () {
        try {
            hq.getQuadrinho().setNomeHq(cxNomeHq.getText());
            hq.getQuadrinho().setLingua(cxLingua.getText());
            hq.getQuadrinho().setEditora(String.valueOf(li_Editora.getSelectedItem()));
            hq.getQuadrinho().setFormato(cxFormato.getText());
            hq.getQuadrinho().setPubli(cxPubli.getText());
            hq.getQuadrinho().setEdicoes(Integer.parseInt(cxEdicoes.getText()));
            hq.getQuadrinho().setPubli(cxPubli.getText());
            hq.getQuadrinho().setEscritor(String.valueOf(li_Escritor.getSelectedItem()));
            hq.getQuadrinho().setDesenhista(String.valueOf(li_Desenhista.getSelectedItem()));
            if (li_Editora.getSelectedItem().equals("Selecione") || li_Escritor.getSelectedItem().equals("Selecione") || li_Desenhista.getSelectedItem().equals("Selecione")) {
                throw new IOException();
            }
            return true;
        } catch (NumberFormatException num) {
            hq.getConBD().informativo("O número de edições dever ser digitado em número!!!");
            return false;
        } catch (IOException erro) {
            hq.getConBD().informativo("Deve-se preencher os comboboxs"); return false;
        }
    }
    
    public void setCampos (int id) {
        try {
            hq.getConBD().setConjuntos(hq.getConBD().getConexao().createStatement(1005, 1008).executeQuery("SELECT * FROM quadrinho WHERE qu_id = "+id));
            
            if (hq.getConBD().getConjuntos().first()) {
                cxNomeHq.setText(hq.getConBD().getConjuntos().getString("qu_nome"));
                cxLingua.setText(hq.getConBD().getConjuntos().getString("qu_lingua"));
                cxFormato.setText(hq.getConBD().getConjuntos().getString("qu_formato"));
                cxPubli.setText(hq.getConBD().getConjuntos().getString("qu_publi"));
                cxEdicoes.setText(hq.getConBD().getConjuntos().getString("qu_edicoes"));
                if (li_Editora.getItemCount() == 0) {
                    li_Escritor.addItem(hq.getConBD().getConjuntos().getString("qu_escritor"));
                }
                li_Escritor.setSelectedItem(hq.getConBD().getConjuntos().getString("qu_escritor"));
                if (li_Editora.getItemCount() == 0) {
                    li_Editora.addItem(hq.getConBD().getConjuntos().getString("qu_editora"));
                }
                li_Editora.setSelectedItem(hq.getConBD().getConjuntos().getString("qu_editora"));
                if (li_Desenhista.getItemCount() == 0) {
                    li_Desenhista.addItem(hq.getConBD().getConjuntos().getString("qu_desenhista"));
                }
                li_Desenhista.setSelectedItem(hq.getConBD().getConjuntos().getString("qu_desenhista"));
                hq.getQuadrinho().setId(id); preencheHQ();
                hq.getConBD().getConjuntos().close();
            }
        } catch (SQLException ex) {
            hq.getConBD().errosSQL("Não foi possível alterar os campos com as informações do banco de dados", ex);
        }
    }
    public void zerarCampos () {
        cxNomeHq.setText("");
        cxLingua.setText("");
        cxFormato.setText("");
        cxEdicoes.setText("");
        cxPubli.setText("");
        li_Editora.setSelectedItem("Selecione");
        li_Escritor.setSelectedItem("Selecione");
        li_Desenhista.setSelectedItem("Selecione");
    }

    public void inserirCombos (int desc) {
        janelaQuadrinho.hq.preencherComboBox(janelaQuadrinho.li_Editora, 1, desc);
        janelaQuadrinho.hq.preencherComboBox(janelaQuadrinho.li_Escritor, 2, desc);
        janelaQuadrinho.hq.preencherComboBox(janelaQuadrinho.li_Desenhista, 3, desc);
    }
    
    public void esvaziarCombos (int desc) {
        janelaQuadrinho.hq.combosVazios(janelaQuadrinho.li_Editora, desc);
        janelaQuadrinho.hq.combosVazios(janelaQuadrinho.li_Escritor, desc);
        janelaQuadrinho.hq.combosVazios(janelaQuadrinho.li_Desenhista, desc);    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cxLingua = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cxFormato = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cxPubli = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cxEdicoes = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cxNomeHq = new javax.swing.JTextField();
        btSalvar = new javax.swing.JButton();
        li_Editora = new javax.swing.JComboBox<>();
        li_Escritor = new javax.swing.JComboBox<>();
        li_Desenhista = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel1.setText("Informações dos Quadrinhos");

        jLabel2.setText("Idioma Original");

        jLabel3.setText("Editora de Publicação");

        jLabel4.setText("Formato de Publicação");

        jLabel5.setText("Período de Lançamento");

        jLabel6.setText("Escritor:");

        jLabel7.setText("Desenhista:");

        jLabel8.setText("Nº de Edições:");

        jLabel9.setText("Nome da História em Quadrinhos");

        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(li_Editora, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(li_Escritor, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(li_Desenhista, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btSalvar)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addComponent(cxNomeHq, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(52, 52, 52)
                                    .addComponent(jLabel4)
                                    .addGap(81, 81, 81)
                                    .addComponent(jLabel8))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(cxFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(21, 21, 21)
                                    .addComponent(cxEdicoes, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cxPubli, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cxLingua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(41, 41, 41))))
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(124, 124, 124))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cxNomeHq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cxLingua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cxPubli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cxEdicoes)
                    .addComponent(cxFormato))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(li_Editora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(li_Escritor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(li_Desenhista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(btSalvar)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
            switch (hq.getConBD().getValidar()) {
            case 1:
                if (preencheHQ()) {
                    if (hq.inserir()) {
                        hq.getConBD().mensagens(hq.getConBD().getValidar());
                    }
                    
                }
                break;
            case 2:
                 if (preencheHQ()) {
                     if (hq.editar(pos)) {
                        hq.getConBD().mensagens(hq.getConBD().getValidar());
                     }
                    
                 }
                break;
            case 3:
                btSalvar.enableInputMethods(false);
                break;
        }
            this.dispose(); zerarCampos();
    }//GEN-LAST:event_btSalvarActionPerformed

    /**
     * @param args the command line arguments
     
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaQuadrinhos().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSalvar;
    private javax.swing.JTextField cxEdicoes;
    private javax.swing.JTextField cxFormato;
    private javax.swing.JTextField cxLingua;
    private javax.swing.JTextField cxNomeHq;
    private javax.swing.JTextField cxPubli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> li_Desenhista;
    private javax.swing.JComboBox<String> li_Editora;
    private javax.swing.JComboBox<String> li_Escritor;
    // End of variables declaration//GEN-END:variables
}