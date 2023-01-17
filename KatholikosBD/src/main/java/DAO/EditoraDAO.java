/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Dados.Editora;
import java.sql.*;

/**
 *
 * @author willi
 */
public class EditoraDAO {
    
    private final Editora editora = new Editora();
    private static EditoraDAO daoEditora;
    private final DAO conBD;
    private final int tipo = ResultSet.TYPE_SCROLL_SENSITIVE, concorrencia = ResultSet.CONCUR_UPDATABLE;
    
    private final String selecionaID = "SELECT ed_id FROM editora";
    
    private final String inserirEditora = 
    "INSERT INTO editora (ed_fundador, ed_sede, ed_presidente, ed_website, ed_nome, ed_anoFund, ed_id, ed_situacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private final String alterarEditora = 
    "UPDATE editora SET ed_fundador = ?, ed_sede = ?, ed_presidente = ?, ed_website = ?, ed_nome = ?, ed_anoFund = ?, ed_situacao = ? WHERE ed_id = ?;";
    private final String excluirEditora = "DELETE FROM editora WHERE ed_id = ?;";

    private EditoraDAO () {
        conBD = DAO.getDao();
    }

    public static EditoraDAO getDaoEditora() {
        if (daoEditora == null) {
            daoEditora = new EditoraDAO();
        }
        return daoEditora;
    }

    public DAO getConBD() {
        return conBD;
    }

    public Editora getEditora() {
        return editora;
    }
    
    public final boolean inserir () {
        try {
            conBD.conjuntos = conBD.getConexao().createStatement(tipo, concorrencia).executeQuery(selecionaID);
            daoEditora.conBD.pComandos = daoEditora.conBD.getConexao().prepareStatement(inserirEditora, tipo, concorrencia);
            
            conBD.pComandos.setString(1, editora.getFundador());
            conBD.pComandos.setString(2, editora.getSede());
            conBD.pComandos.setString(3, editora.getPresidente());
            conBD.pComandos.setString(4, editora.getWebsite());
            conBD.pComandos.setString(5, editora.getNome());
            conBD.pComandos.setInt(6, editora.getAnoFund());
            if (daoEditora.conBD.conjuntos != null) {
                if (!conBD.conjuntos.first()) {
                    editora.setId(1);
                } else {
                    if (daoEditora.conBD.conjuntos.isLast() || daoEditora.conBD.conjuntos.last()) {
                        editora.setId(daoEditora.conBD.conjuntos.getInt("ed_id") + 1);
                    }
                }
            }
            conBD.pComandos.setInt(7, editora.getId());
            if (editora.isSituacao()) {
                conBD.pComandos.setString(8, "ATIVA");
            } else {
                conBD.pComandos.setString(8, "DESATIVADA");
            }
            conBD.conjuntos.close();
            return conBD.concretizaOperacao();
        } catch (SQLException ex) {
            conBD.errosSQL("Erro ao inserir editora no banco de dados", ex);
            return false;
        }
    }
    
    public final boolean editar (int id) {
        try {
            if (buscar(id)) {
                daoEditora.conBD.pComandos = daoEditora.conBD.getConexao().prepareStatement(alterarEditora, tipo, concorrencia);
                conBD.pComandos.setString(1, editora.getFundador());
                conBD.pComandos.setString(2, editora.getSede());
                conBD.pComandos.setString(3, editora.getPresidente());
                conBD.pComandos.setString(4, editora.getWebsite());
                conBD.pComandos.setString(5, editora.getNome());
                conBD.pComandos.setInt(6, editora.getAnoFund());
                if (editora.isSituacao()) {
                    conBD.pComandos.setString(7, "ATIVA");
                } else {
                    conBD.pComandos.setString(7, "DESATIVADA");
                }
                conBD.pComandos.setInt(8, id);
            } 
            return conBD.concretizaOperacao();
        } catch (SQLException sql) {
            conBD.errosSQL("Erro ao alterar editora no banco de dados", sql);
            return false;
        }
    }
    
    public final boolean buscar (int id) {
        return conBD.buscar(id, selecionaID, "ed_id");
    } 
    
    public final boolean excluir (int id) {
        try {
            daoEditora.conBD.pComandos = daoEditora.conBD.getConexao().prepareStatement(excluirEditora, tipo, concorrencia);        
            
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
        daoEditora.conBD.conjuntos = daoEditora.conBD.getConexao().createStatement(1005,1008).executeQuery("SELECT ed_id FROM editora WHERE ed_id > "+id); 
        if (conBD.conjuntos.first()) {
            while (!daoEditora.conBD.conjuntos.isAfterLast()) {
                daoEditora.conBD.pComandos = daoEditora.conBD.getConexao().prepareStatement("UPDATE editora SET ed_id = "+id+" WHERE ed_id = "+
                conBD.conjuntos.getInt("ed_id")+";", tipo, concorrencia);

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
}
