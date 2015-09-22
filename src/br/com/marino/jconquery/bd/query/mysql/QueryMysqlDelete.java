package br.com.marino.jconquery.bd.query.mysql;

import br.com.marino.jconquery.bd.excecao.BDExcecao;

/**
 * Classe responsável por criar a instrução sql para exclusão
 *
 * @author Leonardo
 */
public class QueryMysqlDelete {

    private String table;
    private Where where;

    private QueryMysqlDelete(String table, Where where) {
        this.table = table;
        this.where = where;
    }

    public QueryMysqlDelete() {
        this("", new Where());
    }

    public QueryMysqlDelete from(String table) {
        this.table = table;
        return this;
    }

    public QueryMysqlDelete where(String campo, Object valor, String operador) {
        where.where(campo, valor, operador);
        return this;
    }

    public QueryMysqlDelete andWhere(String campo, Object valor, String operador) {
        where.andWhere(campo, valor, operador);
        return this;
    }

    public QueryMysqlDelete orWhere(String campo, Object valor, String operador) {
        where.orWhery(campo, valor, operador);
        return this;
    }

    public String getSql() throws BDExcecao {

        if (table.isEmpty()) {
            throw new BDExcecao("Não foi informado a tabela na query!");
        }

        if (!where.getSqlWhere().isEmpty()) {
            if (where.getSqlWhere().substring(0, 4).trim().equals("AND")
                    || where.getSqlWhere().substring(0, 4).trim().equals("OR")) {
                throw new BDExcecao("Clausula informada sem whery");
            }
        }

        String sql = "DELETE FROM " + table;

        if (!where.getSqlWhere().isEmpty()) {
            sql += " WHERE " + where.getSqlWhere();
        }

        return sql + ";";

    }
}
