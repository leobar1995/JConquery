package br.com.marino.jconquery.bd;

import br.com.marino.jconquery.bd.excecao.BDExcecao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por fazer a manipulação dos dados no banco de dados MYSQL
 *
 * @author Leonardo
 */
public class BDMysql implements BD {

    private final String URL;       //obrigatório
    private final String USUARIO;   //obrigatório
    private final String SENHA;     //obrigatório
    private Connection con;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    /**
     * Método construtor
     *
     * @param URL =
     * @param USUARIO
     * @param SENHA
     */
    public BDMysql(String URL, String USUARIO, String SENHA) {
        this.URL = URL;
        this.USUARIO = USUARIO;
        this.SENHA = SENHA;
    }

    /**
     * Método responsável por conectar com o banco de dados
     *
     * @throws BDExcecao
     */
    @Override
    public void connect() throws BDExcecao {
        try {
            con = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException ex) {
            throw new BDExcecao(
                    "Não foi possível se comunicar com o banco de dados "
                    + "Mysql!", ex);
        }
    }

    /**
     * Método responsável por fechar as conexões
     *
     * @throws BDExcecao
     */
    @Override
    public void close() throws BDExcecao {
        try {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }

            if (con != null) {
                con.close();
            }

        } catch (SQLException ex) {
            throw new BDExcecao(
                    "Não foi possível encerrar a comunicação com o banco de dados "
                    + "Mysql!", ex);
        }
    }

    /**
     * Método responsável por fazer a consulta no banco de dados
     *
     * @param sql
     * @return
     * @throws BDExcecao
     */
    @Override
    public ResultSet select(String sql) throws BDExcecao {
        try {
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException ex) {
            throw new BDExcecao(
                    "Não foi possível efetuar a consulta no banco de dados "
                    + "Mysql!", ex);
        }
    }

    /**
     * Método responsável por fazer a alteração dos dados no banco de dados
     *
     * @param sql
     * @throws BDExcecao
     */
    @Override
    public void update(String sql) throws BDExcecao {

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new BDExcecao(
                    "Não foi possível efetuar a alteração dos dados!", ex);
        }

    }

    /**
     * Método responsável por fazer a exclusão do registro no banco de dados
     *
     * @param sql
     * @throws BDExcecao
     */
    @Override
    public void delete(String sql) throws BDExcecao {

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new BDExcecao(
                    "Não foi possível excluir o registro", ex);
        }

    }
}
