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
public class Quadrinho implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nomeHq, lingua, formato, publi;
    private String editora, escritor, desenhista;
    private int edicoes, id;

    public String getNomeHq() {
        return nomeHq;
    }

    public String getLingua() {
        return lingua;
    }

    public String getFormato() {
        return formato;
    }

    public String getPubli() {
        return publi;
    }

    public String getEditora() {
        return editora;
    }

    public String getEscritor() {
        return escritor;
    }

    public String getDesenhista() {
        return desenhista;
    }

    public int getEdicoes() {
        return edicoes;
    }
    
    public int getId() {
        return id;
    }
    

    public void setNomeHq(String nomeHq) {
        this.nomeHq = nomeHq;
    }
    
    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void setPubli(String publi) {
        this.publi = publi;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setEscritor(String escritor) {
        this.escritor = escritor;
    }

    public void setDesenhista(String desenhista) {
        this.desenhista = desenhista;
    }

    public void setEdicoes(int edicoes) {
        this.edicoes = edicoes;
    }
    
    public void setId(int id) {
        this.id = id;
    }
}
