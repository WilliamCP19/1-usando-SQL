/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Janelas;

import DAO.EscritorDAO;
/**
 *
 * @author willi
 */
public class JanelaEscritor extends javax.swing.JFrame {
    private JanelaEscritores janelaEscritores;
    private EscritorDAO escritor = EscritorDAO.getDaoEscritor();
    private static JanelaEscritor janelaEscritor;

    public static JanelaEscritor getJanelaEscritor() {
        return janelaEscritor;
    }

    public EscritorDAO getEscritor() {
        return escritor;
    }

    public void setEscritor(EscritorDAO escritor) {
        this.escritor = escritor;
    }
    
    /**
     * Creates new form JanelaEscritores
     */

    private JanelaEscritor(String pri, String seg, String nome1, String nome2, String Autor) {
        initComponents();
        lbTitulo.setText(Autor); lbSubTitulo.setText("Alguns dos "+Autor+" mais conhecidos");
        escritor.getConBD().inicializaTudo(pri, nome1, lbImagem, lbNome);
        escritor.getConBD().inicializaTudo(seg, nome2, lbImagem2, lbNome2);
        lbNome.setText (nome1);
        lbNome2.setText (nome2);
    }
    
    public final static JanelaEscritor getJanelaEscritor (boolean isDesenhista) {
        if (janelaEscritor == null) {
            janelaEscritor = new JanelaEscritor("alanMoore.jpg", "jack.jfif", "Alan Moore", "Jack Kirby", "Escritores e Desenhistas");
            janelaEscritor.janelaEscritores = JanelaEscritores.getJanelaCadEscritor(isDesenhista);
        }
        return janelaEscritor;
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbImagem2 = new javax.swing.JLabel();
        lbImagem = new javax.swing.JLabel();
        lbTitulo = new javax.swing.JLabel();
        lbSubTitulo = new javax.swing.JLabel();
        btAdicionar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btBuscar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        lbNome = new javax.swing.JLabel();
        lbNome2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbImagem2.setText("Imagem 2");

        lbImagem.setText("Imagem 1");

        lbTitulo.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        lbTitulo.setText("Titulo");

        lbSubTitulo.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        lbSubTitulo.setText("Sub Titulo");

        btAdicionar.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 12)); // NOI18N
        btAdicionar.setText("Adicionar");
        btAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarActionPerformed(evt);
            }
        });

        btEditar.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 12)); // NOI18N
        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        btBuscar.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 12)); // NOI18N
        btBuscar.setText("Buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        btExcluir.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 12)); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        lbNome.setText("Pessoa 1");

        lbNome2.setText("Pessoa 2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btEditar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btBuscar)
                        .addGap(30, 30, 30)
                        .addComponent(btExcluir))
                    .addComponent(lbImagem2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(lbNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbNome2)
                .addGap(68, 68, 68))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(lbTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(lbSubTitulo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitulo)
                .addGap(3, 3, 3)
                .addComponent(lbSubTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbImagem2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNome2)
                    .addComponent(lbNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btExcluir)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btAdicionar)
                        .addComponent(btEditar)
                        .addComponent(btBuscar)))
                .addGap(19, 19, 19))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        if (escritor.getConBD().verificaConexao()) {
            janelaEscritores.camposVazios(); janelaEscritores.tabelasVazias();
            escritor.setBuilder();
            JanelaInicial.criaThread(janelaEscritores); escritor.getConBD().setValidar(1);
        }
        
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        if (escritor.getConBD().verificaConexao()) {
            janelaEscritores.setId(escritor.getConBD().buscador("Autor"));
            if (janelaEscritores.getId() < 1) {
                escritor.getConBD().informativo("Nenhuma autor possui este número de identificação!!!");
            } else {
                if (escritor.buscar(janelaEscritores.getId())) { 
                    escritor.setBuilder(); janelaEscritores.setCampos(janelaEscritores.getId());
                    escritor.getConBD().setValidar(2);
                    JanelaInicial.criaThread(janelaEscritores);
                }
            }
        }
    }//GEN-LAST:event_btEditarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        if (escritor.getConBD().verificaConexao()) {
            int id = escritor.getConBD().buscador("Autor");
            if (id < 1) {
                escritor.getConBD().informativo("Nenhuma autor possui este número de identificação!!!");
            } else {
                if (escritor.excluir(id)) {
                    escritor.getConBD().mensagens(3); escritor.setBuilder();
                    janelaEscritores.camposVazios(); janelaEscritores.tabelasVazias();
                }
            }
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        if (escritor.getConBD().verificaConexao()) {
            int id = escritor.getConBD().buscador("Autor");
            if (id < 1) {
                escritor.getConBD().informativo("Nenhuma autor possui este número de identificação!!!");
            } else {
                if (escritor.buscar(id)) {
                    escritor.setBuilder(); 
                    janelaEscritores.setCampos(id);
                    escritor.getConBD().setValidar(3); janelaEscritores.botoesInativos();
                    JanelaInicial.criaThread(janelaEscritores);
                }
            }
        }
    }//GEN-LAST:event_btBuscarActionPerformed

    
    
    /**
     * @param args the command line arguments
     
    public static void main(String args[]) {

        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaEscritor().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JLabel lbImagem;
    private javax.swing.JLabel lbImagem2;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNome2;
    private javax.swing.JLabel lbSubTitulo;
    private javax.swing.JLabel lbTitulo;
    // End of variables declaration//GEN-END:variables
}

/*pInit.setValidar(1);*/

/*int identificador = pInit.buscador(quem);
        if (janelaDesenhista.isVisible()) {
            janelaEscritores.setPosD(pInit.buscaDesenhista(identificador));
            if (janelaEscritores.getPosD() == -1) {
                pInit.informativo("Não possui "+quem+" com este número de idenficação");
            } else {
                janelaEscritores.setCampos(); pInit.setValidar(2);
                JanelaInicial.criaThread(janelaEscritores);   
            }
        } else {
            janelaEscritores.setPosE(pInit.buscaEscritor(identificador));
            if (janelaEscritores.getPosE() == -1) {
                pInit.informativo("Não possui "+quem+" com este número de idenficação");
            } else {
                janelaEscritores.setCampos(); pInit.setValidar(2);
                JanelaInicial.criaThread(janelaEscritores);
            }
            if (janelaEscritor.pInit.getCargoAtual().isDesenhista()) {
                janelaEscritores.setPosD(pInit.buscaDesenhista(identificador));
                if (janelaEscritores.getPosD() == -1) {
                    pInit.informativo("Não possui um "+quem+" com este número de idenficação");
                }
            } 
        }*/

/*int identificador = pInit.buscador(quem);
        int D = pInit.buscaDesenhista(identificador), E = pInit.buscaEscritor(identificador);
        if (D == -1 || E == -1) {
            pInit.informativo("Não possui "+quem+" com este número de idenficação");
        } else {
            pInit.exclui(D, E);
            janelaEscritores.camposVazios(); pInit.mensagens(3);
            pInit.getCargoAtual().setIdEscritor(1);
        }*/

/*
if (janelaDesenhista.isVisible()) {
            if (pInit.buscaDesenhista(pInit.buscador(quem)) == -1) {
                pInit.informativo("Não possui "+quem+" com este número de idenficação");
            } else {
                janelaEscritores.setCampos();
                JanelaInicial.criaThread(janelaEscritores);
            }
        } else {
            if (pInit.buscaEscritor(pInit.buscador(quem)) == -1) {
                pInit.informativo("Não possui "+quem+" com este número de idenficação");
            } else {
                janelaEscritores.setCampos();
                JanelaInicial.criaThread(janelaEscritores);
            }
        }
*/