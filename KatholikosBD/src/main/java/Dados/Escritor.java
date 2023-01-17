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
public class Escritor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome, nacionalidade;
    private StringBuilder princTrabalhos = new StringBuilder(), premios;
    private int diaNasc, mesNasc, anoNasc, id;
    private boolean desenhista, escritor;

    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public StringBuilder getPrincTrabalhos() {
        return princTrabalhos;
    }

    public StringBuilder getPremios() {
        return premios;
    }

    public int getDiaNasc() {
        return diaNasc;
    }

    public int getMesNasc() {
        return mesNasc;
    }

    public int getAnoNasc() {
        return anoNasc;
    }

    public int getId() {
        return id;
    }

    public boolean isDesenhista() {
        return desenhista;
    }

    public boolean isEscritor() {
        return escritor;
    }
    

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setPrincTrabalhos(StringBuilder princTrabalhos) {
        this.princTrabalhos = princTrabalhos;
    }

    public void setPremios(StringBuilder premios) {
        this.premios = premios;
    }

    public void setDiaNasc(int diaNasc) {
        this.diaNasc = diaNasc;
    }

    public void setMesNasc(int mesNasc) {
        this.mesNasc = mesNasc;
    }

    public void setAnoNasc(int anoNasc) {
        this.anoNasc = anoNasc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDesenhista(boolean desenhista) {
        this.desenhista = desenhista;
    }

    public void setEscritor(boolean escritor) {
        this.escritor = escritor;
    }
}