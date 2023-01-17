/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Dados.Quadrinho;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author willi
 */
public class QuadrinhoDAO {
    
    private final Quadrinho quadrinho =  new Quadrinho();
    private static QuadrinhoDAO daoQuadrinho;
    private final DAO conBD;
    private final int tipo = ResultSet.TYPE_SCROLL_SENSITIVE, concorrencia = ResultSet.CONCUR_UPDATABLE;
    
    private final String selecionaID = "SELECT qu_id FROM quadrinho";
    
    private final String inserirQuadrinho = 
    "INSERT INTO quadrinho (qu_nome, qu_lingua, qu_formato, qu_publi, qu_editora, qu_escritor, qu_desenhista, qu_edicoes, qu_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final String alterarQuadrinho = 
    "UPDATE quadrinho SET qu_nome = ?, qu_lingua = ?, qu_formato = ?, qu_publi = ?, qu_editora = ?, qu_escritor = ?, qu_desenhista = ?, qu_edicoes = ? WHERE qu_id = ?;";
    private final String excluirQuadrinho = "DELETE FROM quadrinho WHERE qu_id = ?;";
    
    private QuadrinhoDAO() {
        conBD = DAO.getDao();
    }

    public static QuadrinhoDAO getDaoQuadrinho() {
        if (daoQuadrinho == null) {
            daoQuadrinho = new QuadrinhoDAO();
        }
        return daoQuadrinho;
    }

    public DAO getConBD() {
        return conBD;
    }

    public Quadrinho getQuadrinho() {
        return quadrinho;
    }
    
    public final boolean inserir () {
        try {
            conBD.conjuntos = conBD.getConexao().createStatement(tipo, concorrencia).executeQuery(selecionaID);
            conBD.pComandos = conBD.getConexao().prepareStatement(inserirQuadrinho, tipo, concorrencia);
            
            conBD.pComandos.setString(1, quadrinho.getNomeHq());
            conBD.pComandos.setString(2, quadrinho.getLingua());
            conBD.pComandos.setString(3, quadrinho.getFormato());
            conBD.pComandos.setString(4, quadrinho.getPubli());
            conBD.pComandos.setString(5, quadrinho.getEditora());
            conBD.pComandos.setString(6, quadrinho.getEscritor());
            conBD.pComandos.setString(7, quadrinho.getDesenhista());
            conBD.pComandos.setInt(8, quadrinho.getEdicoes());
            if (conBD.conjuntos != null) {
                if (!conBD.conjuntos.first()) {
                    quadrinho.setId(1);
                } else {
                    if (conBD.conjuntos.isLast() || conBD.conjuntos.last()) {
                        quadrinho.setId(conBD.conjuntos.getInt("qu_id") + 1);
                    }
                }
            }
            conBD.pComandos.setInt(9, quadrinho.getId());
            conBD.conjuntos.close();
            return conBD.concretizaOperacao();
        } catch (SQLException ex) {
            conBD.errosSQL("Erro ao inserir autor no banco de dados", ex); return false;
        }
    }
    
    public final boolean buscar (int id) {
        return conBD.buscar(id, selecionaID, "qu_id");
    }
    
    public final boolean editar (int id) {
        try {
            if (buscar(id)) {
                conBD.pComandos = conBD.getConexao().prepareStatement(alterarQuadrinho, tipo, concorrencia);
                conBD.pComandos.setString(1, quadrinho.getNomeHq());
                conBD.pComandos.setString(2, quadrinho.getLingua());
                conBD.pComandos.setString(3, quadrinho.getFormato());
                conBD.pComandos.setString(4, quadrinho.getPubli());
                conBD.pComandos.setString(5, quadrinho.getEditora());
                conBD.pComandos.setString(6, quadrinho.getEscritor());
                conBD.pComandos.setString(7, quadrinho.getDesenhista());
                conBD.pComandos.setInt(8, quadrinho.getEdicoes());
                conBD.pComandos.setInt(9, quadrinho.getId());
            }
            return conBD.concretizaOperacao();
        } catch (SQLException esql) {
            conBD.errosSQL("Erro ao alterar autor no banco de dados", esql);
            return false;
        }
    }
    
    public final boolean excluir (int id) {
        try {
            conBD.pComandos = conBD.getConexao().prepareStatement(excluirQuadrinho, tipo, concorrencia);        
            
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
            conBD.errosSQL("Erro ao exluir autor no banco de dados", ex); 
            return false;
        }
    }
    
    public final void atualizaID (int id) throws SQLException {
        conBD.conjuntos = conBD.getConexao().createStatement(1005,1008).executeQuery("SELECT qu_id FROM quadrinho WHERE qu_id > "+id); 
        
        if (conBD.conjuntos.first()) {
            while (!conBD.conjuntos.isAfterLast()) {
                conBD.pComandos = conBD.getConexao().prepareStatement("UPDATE quadrinho SET qu_id = "+id+" WHERE qu_id = "+
                conBD.conjuntos.getInt("qu_id")+";", tipo, concorrencia);

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
    
    public final void combosVazios (javax.swing.JComboBox combo, int swi) {
        combo.removeAllItems();
        if (swi == 0) {
            combo.addItem("Selecione");
        }
    } 
    
    public final boolean preencherComboBox (javax.swing.JComboBox lista, int identifica, int desc) {
        combosVazios(lista, desc);
        switch (identifica) {
            case 1:
                try {
                    conBD.conjuntos = conBD.getConexao().prepareStatement("SELECT ed_nome FROM editora", tipo, concorrencia).executeQuery();
                    while (conBD.conjuntos.next()) {
                            lista.addItem(conBD.conjuntos.getString("ed_nome"));
                    }
                } catch (SQLException ex) {
                    conBD.errosSQL("Erro ao preencher os combobox", ex); return true;
                } break;
            case 2:
                try {
                    conBD.conjuntos = conBD.getConexao().prepareStatement("SELECT au_nome FROM autor WHERE au_escritor = 'SIM'", tipo, concorrencia).executeQuery();
                    while (conBD.conjuntos.next()) {
                        lista.addItem(conBD.conjuntos.getString("au_nome"));
                    }
                } catch (SQLException ex) {
                    conBD.errosSQL("Erro ao preencher os combobox", ex); return true;
                } break;
            case 3:
                try {
                    conBD.conjuntos = conBD.getConexao().prepareStatement("SELECT au_nome FROM autor WHERE au_desenhista = 'SIM'", tipo, concorrencia).executeQuery();
                    while (conBD.conjuntos.next()) {
                        lista.addItem(conBD.conjuntos.getString("au_nome"));
                    }
                } catch (SQLException ex) {
                    conBD.errosSQL("Erro ao preencher os combobox", ex); return true;
                } break;
        }
        return false;
    }
}

