package Relatorios;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.DAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author fabricio
 */
public class ControleRelatorios {
    public static ControleRelatorios cRelatorios;
    private DAO conexao;

    private static final String pasta_relatorios = System.getProperty("user.dir") + "/src/main/java/Relatorios";

    public final File file_parametro_editora = new File (pasta_relatorios, "EditoraForFundação.jasper");
    public final File relatorio_parametro_editora_pdf = new File(pasta_relatorios, "relatorio_parametro_editoras.pdf");
    public final File file_relatorio_editora = new File(pasta_relatorios, "Editora.jasper");
    public final File relatorio_editora_pdf = new File(pasta_relatorios, "relatorio_editoras.pdf");
    public final File file_relatorio_quadrinho = new File(pasta_relatorios, "Quadrinho.jasper");
    public final File relatorio_quadrinho_pdf = new File(pasta_relatorios, "relatorio_quadrinhos.pdf");
    public final File file_relatorio_escritorDesenhista = new File(pasta_relatorios, "EscritorDesenhista.jasper");
    public final File relatorio_escritorDesenhista_pdf = new File(pasta_relatorios, "relatorio_escritoresDesenhistas.pdf");

    public Map criaParametros () {
        Map parametros = new HashMap();
        
        int max = Integer.valueOf(JOptionPane.showInputDialog(
                null,
                "Digite o ano de fundação que será usado para gerar o relatório",
                "Coletar dado: Ano de Fundação",
                JOptionPane.QUESTION_MESSAGE));
        parametros.put("ForFundação", max);
        return parametros;
    } 
    
    private ControleRelatorios() {
        conexao = DAO.getDao();
    }

    public static ControleRelatorios getcRelatorios() {
        if (cRelatorios == null) {
            cRelatorios = new ControleRelatorios();
        }
        return cRelatorios;
    }
    
    public void Relatorios (File path, File pathPDF, Map anoFund) {
        JasperPrint impressao;
        try {
            FileInputStream relatorio = new FileInputStream(path);
            
            impressao = JasperFillManager.fillReport(relatorio, anoFund, conexao.getConexao());
            if (JOptionPane.showConfirmDialog(null, "Deseja Visualizar o relatório?") == 0) {
                JasperViewer.viewReport(impressao, false);
            } else {
                JOptionPane.showMessageDialog(null, "O relatório será convertido para PDF");
                JasperExportManager.exportReportToPdfFile(impressao, pathPDF.getAbsolutePath());
                JOptionPane.showMessageDialog(null, "Gerado o arquivo com sucesso: "+pathPDF.getAbsolutePath());
            }
        } catch (JRException | FileNotFoundException erro) {
            System.err.println("Não foi possível exportar o relatório.\n\n" + erro);
        }
    }
}
