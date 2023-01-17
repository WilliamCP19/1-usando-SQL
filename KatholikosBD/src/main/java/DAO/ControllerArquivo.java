package DAO;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author fabricio@utfpr.edu.br
 */
public class ControllerArquivo {
    protected File arquivo = null;
    protected int validar;
    
    /**
     * @return the arquivo
     */
    public File getArquivo() {
        return arquivo;
    }
        
    public void setArquivo (File arquivo) {
        this.arquivo = arquivo;
    }

    public int getValidar() {
        return validar;
    }

    public void setValidar(int validar) {
        this.validar = validar;
    }
    

    /**
     * @param TextoBotao o texto para o botão de escolha do usuário
     */
    public void setArquivo(String TextoBotao) {
        arquivo = null;
        String pastainicial = System.getProperty("user.dir");
        JFileChooser chooser = new JFileChooser(pastainicial);
        if (chooser.showDialog(null, TextoBotao) == JFileChooser.APPROVE_OPTION) {
            arquivo = chooser.getSelectedFile();
        }     
    }
}
