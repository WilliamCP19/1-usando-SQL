/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dados;

import java.io.Serializable;

/**
 *
 * @author willi
 */
public class Editora implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fundador, sede, presidente, website, nome;
    private int anoFund, id;
    private boolean situacao;

    public String getFundador() {
        return fundador;
    }

    public String getSede() {
        return sede;
    }

    public String getPresidente() {
        return presidente;
    }

    public String getWebsite() {
        return website;
    }

    public int getAnoFund() {
        return anoFund;
    }
    
    public int getId() {
        return id;
    }

    public boolean isSituacao() {
        return situacao;
    }
    
    public String getNome() {
        return nome;
    }
    

    public void setFundador(String fundador) {
        this.fundador = fundador;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setAnoFund(int anoFund) {
        this.anoFund = anoFund;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
}
