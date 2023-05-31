/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
/**
 *
 * @author willi
 */
public class conectarBD {
    @Test
    public void conexaoRealizada () throws SQLException {
        Connection conexao = null;
        String url = "jdbc:mysql://localhost:3306/loja";
        String user = "root";
        String senha = "#Mysql2313707";
        conexao = DriverManager.getConnection(url, user, senha);
        assertNotNull(conexao);
    }
    @Test
    public void conexaoNaoRealizada () {
        Connection conexao = null;
        try {
            String url = "jdbc:mysql://localhost:3306/loja";
            String user = "root";
            String senha = "root";
            conexao = DriverManager.getConnection(url, user, senha);
        } catch (SQLException ex) {
            conexao = null;
        }
        assertNull(conexao);
    }
}
