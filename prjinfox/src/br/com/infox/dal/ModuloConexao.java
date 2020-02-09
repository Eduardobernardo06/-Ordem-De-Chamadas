/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.dal;

import java.sql.*;

/**
      esse import de cima é pra trazer uma biblioteca ligada a conexão com o 
       banco de dados,faz a ponte, o * é pra trazer tudo
 * @author Bernardo
 */
public class ModuloConexao {

    //método responsável por estabelecer a conexão com o banco
//Connection é um framework(conjunto de funcionalidades) p/ conexão vindo desse pacote java.sql
    public static Connection conector() {
        java.sql.Connection conexao = null; //variável conexao com valor nulo

        //chamando o driver importado para bibliotecas
        String driver = "com.mysql.jdbc.Driver";

        //Armazendando informações referentes ao banco
        String url = "jdbc:mysql://localhost:3306/dbinfox";
        String user = "root";
        String password = "";
        
        //String url = "jdbc:mysql://192.168.0.1:3306/dbinfox";->é só fazer isso pra mudar pra um servidor dedicado
        //Estabelecendo a conexão com o banco 
        try {
            
            //se der tudo certo
            
            Class.forName(driver);//executar o arquivo do driver
            conexao = DriverManager.getConnection(url, user, password);// variável criada em cima
            return conexao;
        } catch (Exception e) {
            //a linha abaixo serve de apoio para esclarecer o erro
            //System.out.println(e);
            return null;
            
        }

    }
}
