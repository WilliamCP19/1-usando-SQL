/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Dados.Escritor;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author willi
 */
public class EscritorDAO {

    private final Escritor escritor = new Escritor();
    private static EscritorDAO daoEscritor;
    private final DAO conBD;
    private final int tipo = ResultSet.TYPE_SCROLL_SENSITIVE, concorrencia = ResultSet.CONCUR_UPDATABLE;
    
    private final String selecionarPT_ID = "SELECT * FROM princtrabalhos WHERE pt_id = ?;";
    private final String selecionarPR_ID = "SELECT * FROM premio WHERE pr_id = ?;";
    
    private final String selecionaAU_ID = "SELECT au_id FROM autor;";
    
    private final String inserirCargo = 
    "INSERT INTO autor (au_nome, au_nascionalidade, au_dataNasc, au_id, au_desenhista, au_escritor) VALUES (?, ?, ?, ?, ?, ?);";
    private final String inserirTabPrincTrab = 
    "INSERT INTO princtrabalhos (pt_nome, pt_editora, pt_anoLanc, pt_cargo, pt_id) VALUES (?, ?, ?, ?, ?);";
    private final String inserirTabPremio = 
    "INSERT INTO premio (pr_nome, pr_anoRec, pr_organizacao, pr_anoCri, pr_id) VALUES (?, ?, ?, ?, ?);";
    
    private final String alterarCargo = 
    "UPDATE autor SET au_nome = ?, au_nascionalidade = ?, au_dataNasc = ?, au_desenhista = ? , au_escritor = ? WHERE au_id = ?;";
    
    private final String excluirCargo = "DELETE FROM autor WHERE au_id = ?";
    
    private EscritorDAO() {
        conBD = DAO.getDao();
    }

    public static EscritorDAO getDaoEscritor() {
        if (daoEscritor == null) {
            daoEscritor = new EscritorDAO();
        }
        return daoEscritor;
    }
    
    public DAO getConBD() {
        return conBD;
    }

    public Escritor getEscritor() {
        return escritor;
    }
      
    public void setBuilder () {
        escritor.setPremios(new StringBuilder());
        escritor.setPrincTrabalhos(new StringBuilder());
    }
    public final boolean inserir () {
        try {
            conBD.conjuntos = conBD.getConexao().createStatement(1005, 1008).executeQuery(selecionaAU_ID);
            conBD.pComandos = conBD.getConexao().prepareStatement(inserirCargo, tipo, concorrencia);
            
            conBD.pComandos.setString(1, escritor.getNome());
            conBD.pComandos.setString(2, escritor.getNacionalidade());
            conBD.pComandos.setDate(3, Date.valueOf(escritor.getAnoNasc()+"-"+escritor.getMesNasc()+"-"+escritor.getDiaNasc()));
            if (conBD.conjuntos != null) {
                if (!conBD.conjuntos.first()) {
                    escritor.setId(1);
                } else {
                    if (conBD.conjuntos.isLast() || conBD.conjuntos.last()) {
                        escritor.setId(conBD.conjuntos.getInt("au_id") + 1);
                    }
                }
            }
            conBD.pComandos.setInt(4, escritor.getId());
            if (escritor.isDesenhista()) {
                conBD.pComandos.setString(5, "SIM");
            } else {
                conBD.pComandos.setString(5, "NÃO");
            }
            if (escritor.isEscritor()) {
                conBD.pComandos.setString(6, "SIM");
            } else {
                conBD.pComandos.setString(6, "NÃO");
            }
            conBD.conjuntos.close();
            return conBD.concretizaOperacao();
        } catch (SQLException inserirSQL) {
            conBD.errosSQL("Erro ao inserir editora no banco de dados", inserirSQL);
            return false;
        }
    }
    
    public final boolean editar (int id) {
        try {
            if (buscar(id)) {
                conBD.pComandos = conBD.getConexao().prepareStatement(alterarCargo, tipo, concorrencia);
                    
                conBD.pComandos.setString(1, escritor.getNome());
                conBD.pComandos.setString(2, escritor.getNacionalidade());
                conBD.pComandos.setDate(3, Date.valueOf(escritor.getAnoNasc()+"-"+escritor.getMesNasc()+"-"+escritor.getDiaNasc()));
            } 
            if (escritor.isDesenhista()) {
                conBD.pComandos.setString(4, "SIM");
            } else {
                conBD.pComandos.setString(4, "NÃO");
            }
            if (escritor.isEscritor()) {
                conBD.pComandos.setString(5, "SIM");
            } else {
                conBD.pComandos.setString(5, "NÃO");
            }
            escritor.setId(id);
            conBD.pComandos.setInt(6, escritor.getId());
            return conBD.concretizaOperacao();
        } catch (SQLException sql) {
            conBD.errosSQL("Erro ao alterar editora no banco de dados", sql);
            return false;
        }
    }
    
    public final boolean buscar (int id) {
        return conBD.buscar(id, selecionaAU_ID, "au_id");
        
    }
    
    public final boolean excluir (int id) {
        try {
            excluirTabelaBD(id, 0);
            conBD.pComandos = conBD.getConexao().prepareStatement(excluirCargo, tipo, concorrencia);     
            
            if (buscar(id)) {
                conBD.pComandos.setInt(1, id);
                if (conBD.concretizaOperacao()) {
                    atualizaID(id); return true;
                } else {
                    conBD.pComandos.close(); return false;
                }
            }
            return false;
        } catch (SQLException ex) {
            conBD.errosSQL("Erro ao exluir editora no banco de dados", ex); 
            return false;
        }
    }
    
    public final void atualizaID (int id) throws SQLException {
        
        conBD.conjuntos = conBD.getConexao().createStatement(1005, 1008).executeQuery("SELECT au_id FROM autor WHERE au_id > "+id);            
        
        if (conBD.conjuntos.first()) {
            while (!conBD.conjuntos.isAfterLast()) {                
                conBD.pComandos = conBD.getConexao().prepareStatement("UPDATE autor SET au_id = "+id+" WHERE au_id = ?", tipo, concorrencia);
                conBD.pComandos.setInt(1, conBD.conjuntos.getInt("au_id"));
                
                if (conBD.concretizaOperacao()) {
                    conBD.conjuntos.next(); id++;
                } else {
                    conBD.informativo("Não foi possível atualizar os índices");
                    break;
                }
            }   
        }
        conBD.conjuntos.close();
    }
    
    public final void inserirTabelaBD (TableModel tabela) {
        try {
            for (int i=0, cont = tabela.getRowCount();i < cont;i++) {
                if (tabela.getColumnName(0).equals("Nome da HQ")) {
                    conBD.pComandos = conBD.getConexao().prepareStatement(inserirTabPrincTrab, tipo, concorrencia);
                } else {
                    conBD.pComandos = conBD.getConexao().prepareStatement(inserirTabPremio, tipo, concorrencia);
                }
                for (int j=0;j < tabela.getColumnCount();j++) {
                    conBD.pComandos.setString(j+1, tabela.getValueAt(i, j).toString());
               }
                conBD.pComandos.setInt(5, escritor.getId());
                conBD.concretizaOperacao();
            }            
        } catch (SQLException ex) {
            conBD.errosSQL("Erro ao inserir tabela no banco de dados", ex);
        }
    }
    public final void excluirTabelaBD (int id, int desc) {
        try {
            if (desc == 0) {
                conBD.pComandos = conBD.getConexao().prepareStatement("DELETE FROM princtrabalhos WHERE pt_id = "+id, tipo, concorrencia);
                conBD.pComandos.execute(); conBD.getConexao().commit(); setBuilder();
            
                conBD.pComandos = conBD.getConexao().prepareStatement("DELETE FROM premio WHERE pr_id = "+id, tipo, concorrencia);
                conBD.pComandos.execute(); conBD.getConexao().commit(); setBuilder();
            } else {
                if (desc == 1) {
                    conBD.pComandos = conBD.getConexao().prepareStatement("DELETE FROM princtrabalhos WHERE pt_id = "+id, tipo, concorrencia);
                } else if (desc == 2) {
                    conBD.pComandos = conBD.getConexao().prepareStatement("DELETE FROM premio WHERE pr_id = "+id, tipo, concorrencia);
                }
                conBD.pComandos.execute(); conBD.getConexao().commit(); setBuilder();
            }
            
        } catch (SQLException ex) {
            conBD.errosSQL("Erro ao excluir os dados dos principais trabralhos", ex);
        }
    }
    
    public final void buscarTabelaBD (int id, DefaultTableModel tabela) throws SQLException {
        boolean table = tabela.getColumnName(0).equals("Nome da HQ"); 
        if (table) {
            conBD.pComandos = conBD.getConexao().prepareStatement(selecionarPT_ID, tipo, concorrencia);
        } else {
            conBD.pComandos = conBD.getConexao().prepareStatement(selecionarPR_ID, tipo, concorrencia);
        }
        conBD.pComandos.setInt(1, id); 
        conBD.conjuntos = conBD.pComandos.executeQuery();
        int pos = 0;
        while (conBD.conjuntos.next()) {
            if (table) {
                tabela.insertRow(pos, new Object[] {
                conBD.conjuntos.getString("pt_nome"),
                conBD.conjuntos.getString("pt_editora"),
                conBD.conjuntos.getString("pt_anoLanc"),
                conBD.conjuntos.getString("pt_cargo")}); pos++;    
            } else {
                tabela.insertRow(pos, new Object[] {
                conBD.conjuntos.getString("pr_nome"),
                conBD.conjuntos.getString("pr_anoRec"),
                conBD.conjuntos.getString("pr_organizacao"),
                conBD.conjuntos.getString("pr_anoCRi")}); pos++;
            }
            setBuilder();
        }
        atualizaAtributosBuilders(tabela);
    }
    
    public final void preencheTabelaComBuilders (int identificador, int pos, DefaultTableModel tbl) {
        StringTokenizer frase;
        
        if (identificador == 1) {
            frase = new StringTokenizer (escritor.getPrincTrabalhos().toString(), ";");
        } else {
            frase = new StringTokenizer (escritor.getPremios().toString(), ";");
        }
        while (frase.hasMoreElements()) {
            tbl.insertRow(pos, new Object[] {frase.nextToken(), frase.nextToken(), frase.nextToken(), frase.nextToken()}); pos++;
        } 
    }

    public final void dadosParaBuilders (String pergunta, String titulo, int identificador) {
        StringBuilder aux = new StringBuilder();
        aux.append(JOptionPane.showInputDialog(null,pergunta, titulo, JOptionPane.QUESTION_MESSAGE));
        if (identificador == 1) {
            if (escritor.getPrincTrabalhos() != null) {
                escritor.getPrincTrabalhos().append(";"); escritor.getPrincTrabalhos().append(aux);
            } else {
                escritor.setPrincTrabalhos(aux);
            }
        } else if (identificador == 2) {
            if (escritor.getPremios() != null) {
                escritor.getPremios().append(";"); escritor.getPremios().append(aux);             
            } else {
                escritor.setPremios(aux);
            }
        }
    }
    
    public final void atualizaAtributosBuilders (DefaultTableModel tab) {
        for (int i=0;i < tab.getRowCount();i++) {
            for (int j=0;j < tab.getColumnCount();j++) {
                if (tab.getColumnName(0).equals("Nome da HQ")) {
                    if (i == 0 && j == 0) {
                        escritor.setPrincTrabalhos(new StringBuilder().append(tab.getValueAt(i, j)));
                    } else {
                        escritor.getPrincTrabalhos().append(";");
                        escritor.setPrincTrabalhos(escritor.getPrincTrabalhos().append(tab.getValueAt(i, j)));
                    }
                } else {
                    if (i == 0 && j == 0) {
                        escritor.setPremios(new StringBuilder().append(tab.getValueAt(i, j)));
                    } else {
                        escritor.getPremios().append(";");
                        escritor.setPremios(escritor.getPremios().append(tab.getValueAt(i, j)));
                    }
                }
            }
        }
    }
    
    public final void preencheTabela (JTable tabela, int num) {
        DefaultTableModel tab = (DefaultTableModel) tabela.getModel();
        tab.setRowCount(0);
        dadosParaBuilders("Digite "+ tab.getColumnName(0)+":", "História em Quadrinhos", num);
        dadosParaBuilders("Digite "+ tab.getColumnName(1)+":", "Editora da HQ", num);
        dadosParaBuilders("Digite "+tab.getColumnName(2)+":", "Primeira publicação", num);
        dadosParaBuilders("Digite "+tab.getColumnName(3)+":", "Trabalho Conjunto", num);
        
        if (!escritor.getPrincTrabalhos().isEmpty()) {
            escritor.getPrincTrabalhos().append("\n");
        } else {
            escritor.getPremios().append("\n");
        }
        preencheTabelaComBuilders(num, 0, tab); 
    }
    
    public final void atualizarTabelas (JTable tabela) {
        if (tabela.getValueAt(0, 0) == null) {
            conBD.informativo("A tabela está vazia!!!");
        } else {
            String[] colunas = {tabela.getColumnName(0), tabela.getColumnName(1), tabela.getColumnName(2), tabela.getColumnName(3)};    
            
            int col = JOptionPane.showOptionDialog(null, "A informação está em qual coluna?", "Atualizar Dado", JOptionPane.DEFAULT_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, null, colunas, colunas[0]);
            int lin = 0;
            if (tabela.getRowCount() > 1) {
                lin = Integer.parseInt(JOptionPane.showInputDialog("Em qual linha você deseja alterar a coluna "+tabela.getColumnName(col))) - 1;
            }
            if (lin < tabela.getRowCount()) {
                tabela.setValueAt(JOptionPane.showInputDialog("Digite o novo valor"), lin, col);
            }
            atualizaAtributosBuilders((DefaultTableModel) tabela.getModel());
        }
    }
    
    public final void excluirLinhaTab (JTable tab) {
         if (tab.getValueAt(0, 0) == null) {
            conBD.informativo("A tabela está vazia!!!");
        } else {
             DefaultTableModel model = (DefaultTableModel) tab.getModel();
             int lin = 0;
             if (tab.getRowCount() > 1) {
                lin = Integer.parseInt(JOptionPane.showInputDialog("Qual linha você deseja excluir")) - 1;
            }
             model.removeRow(lin);
             atualizaAtributosBuilders(model);
             excluirTabelaBD(escritor.getId(), model.getColumnName(0).equals("Nome da HQ") ? 1 : 2);
             inserirTabelaBD(model);
        }
    }
}