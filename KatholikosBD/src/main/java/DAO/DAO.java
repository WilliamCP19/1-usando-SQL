/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Janelas.CadastrarBD;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author willi
 */
public class DAO {
    private Connection conexao = null;
    protected ResultSet conjuntos = null;
    protected PreparedStatement pComandos = null;
    
    private String url;
    private static DAO dao;
    private String username;
    private String password;
    private int validar;
    private final Properties info;
    private FileInputStream leitor;
    private ControllerArquivo arq;

    protected DAO() {
        url = "";
        username = "";
        password = "";
        validar = 0;
        info = new Properties();
        arq = new ControllerArquivo();
    }

    public static DAO getDao() {
        if (dao == null) {
            dao = new DAO();
        }
        return dao;
    }

    public int getValidar() {
        return validar;
    }

    public void setValidar(int validar) {
        this.validar = validar;
    }

    public Connection getConexao() {
        return conexao;
    }

    public PreparedStatement getpComandos() {
        return pComandos;
    }

    public ResultSet getConjuntos() {
        return conjuntos;
    }

    public void setConjuntos(ResultSet conjuntos) {
        this.conjuntos = conjuntos;
    }
    
    public Connection realizaConexao(boolean stateButton) throws SQLException {
        if (conexao == null) {
            if (stateButton) {
                 conexao = DriverManager.getConnection(url, username, password);
            }
        } else {
            if (!stateButton) {
                conexao = null;
            }   
        }
       return conexao;
    }
    
    public final boolean verificaConexao () {
        if (conexao != null) {
             return true;
        }
        informativo("A Conex??o n??o foi realizada");
        return false;
    }
    
    public final boolean ArquivoVazio () {
        try {
            arq.setArquivo(new java.io.File (System.getProperty("user.dir"), "infoBD.properties"));
            arq.getArquivo().createNewFile();
            leitor = new FileInputStream(arq.getArquivo());
            if (leitor.read() == -1) {
                return true;
            } else {
                info.load(leitor);
                url = info.getProperty("jdbc.url");
                username = info.getProperty("jdbc.username");
                password = info.getProperty("jdbc.password");
                if (username == null) {
                    username = "";
                } 
                if (password == null) {
                    password = "";
                } return false;
            }
        } catch (FileNotFoundException ex) {
            errosSQL("Arquivo n??o encontrado!!!", ex);
        } catch (IOException ex) {
            errosSQL("Erro de entrada!!!", ex);
        }
        return false;
    }
    
    public final void initAtributos (String url, String username, String password) throws SQLException {
        this.url = url;
        this.username = username;
        this.password = password;
        preencheArqPro();
        CadastrarBD.getJanelaCadBD().dispose();
    }

    public final void preencheArqPro () {
        FileOutputStream out;
        try {
            out = new FileOutputStream(arq.getArquivo());
            JOptionPane.showMessageDialog(null, "?? utilizado o maven, portanto necessita-se que voc?? adicione a dependencia do driver do banco manualmente.");
            JOptionPane.showMessageDialog(null, "Por isso s?? ?? poss??vel cadastrar dois SGBD - s??o dependencias que j?? est??o presentes no programa.");
        byte[] dados = ("\njdbc.url="+url+"\n"+"jdbc.username="+username+"\n"+"jdbc.password="+password+"\n").getBytes();
        out.write(dados);
        JOptionPane.showMessageDialog(null, "Adi????o realizada!");
        } catch (FileNotFoundException ex) {
            errosSQL("Arquivo n??o encontrado!!!", ex);
        } catch (IOException ex) {
            errosSQL("Erro de entrada!!!", ex);
        }
    }
    
    public final boolean concretizaOperacao () throws SQLException {
        int res = pComandos.executeUpdate();
        pComandos.close();
        if (res == 1) {
            getConexao().commit();
            return true;
        } else {
            getConexao().rollback();
            return false;
        }
    }
    
     public final void informativo (String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Informativo", JOptionPane.INFORMATION_MESSAGE);
    }
    
     public final void errosSQL (String mensagem, Exception erro) {
         JOptionPane.showMessageDialog(null, mensagem+"\n"+erro.getMessage(), "Informativo", JOptionPane.ERROR_MESSAGE);
     }
     
    public final int buscador (String departamento) {
        informativo("O ID ?? auto incremental e come??a em 1, significa que a cada novo cadastro este ID recebe incremento de 1\nDito isto.");
        int marcador = Integer.parseInt(JOptionPane.showInputDialog(
                null, "Digite o n??mero de identifica????o do "+departamento,
                "Identificador de "+departamento,
                JOptionPane.QUESTION_MESSAGE));
        return marcador;
    } 
    
    public final void alteraImagens (String diretorio, JLabel quadro) {
        ImageIcon image = new ImageIcon ("src/main/java/Dados/Imagens/"+diretorio);
        image.setImage(image.getImage().getScaledInstance(quadro.getWidth(), quadro.getHeight(), 1));
        quadro.setIcon(image);
    }
    
    public final void inicializaTudo (String diretorio, String nomePes,JLabel imagem, JLabel nomeLB) {
        alteraImagens (diretorio, imagem);
        nomeLB.setText(nomePes);
    }
    
    public final void mensagens (int esc) {
        switch (esc) {
            case 1:
                JOptionPane.showMessageDialog (null, "Adi????o realizada com sucesso", "Adicionar", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Edi????o feita com sucesso", "Editar", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Exlus??o Realizada com Sucesso", "Excluir", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                break;
        }
    }
    
    public final boolean buscar (int id, String sql, String coluna) {
        try {
            conjuntos = conexao.createStatement(1005, 1008).executeQuery(sql);
            
            while (conjuntos.next()) {
                if (conjuntos.getInt(coluna) == id) {
                    return true;
                }
            }
            conjuntos.close();
            informativo("Este id n??o existe no banco de dados!"); return false;
        } catch (SQLException bsql) {
            errosSQL("N??o foi posso??vel percorrer a tabela editora!", bsql);
            return false;
        }
    }
}
