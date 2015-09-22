package br.com.marino.jconquery.bd;

import br.com.marino.jconquery.bd.excecao.BDExcecao;
import java.sql.ResultSet;

/**
 * Interface responsável por definir métodos para manipulação do banco de dados
 *
 * @author Leonardo
 */
public interface BD {

    /**
     * Interface para conectar com o banco de dados
     *
     * @throws BDException
     */
    void connect() throws BDExcecao;

    /**
     * Interface por encerrar as comunicações
     * @throws BDExcecao 
     */
    void close() throws BDExcecao;

    /**
     * Interface por consultar no banco de dados
     * @param sql
     * @return
     * @throws BDExcecao 
     */
    ResultSet select(String sql) throws BDExcecao;

    /**
     * Interface por fazer a alteração do registro no banco de dados
     * @param sql
     * @throws BDExcecao 
     */
    void update(String sql) throws BDExcecao;

    /**
     * Interface para deletar o registro no banco de dados
     * @param sql
     * @throws BDExcecao 
     */
    void delete(String sql) throws BDExcecao;
}
