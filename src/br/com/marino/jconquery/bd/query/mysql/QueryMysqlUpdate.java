package br.com.marino.jconquery.bd.query.mysql;

import br.com.marino.jconquery.bd.excecao.BDExcecao;

/**
 * Classe responsável por criar a instrução sql para alteração
 * @author Leonardo
 */
public class QueryMysqlUpdate {

    private String table;
    private Values values;
    private Where where;

    public QueryMysqlUpdate(String table) {
        this(table, new Values(), new Where());
    }

    public QueryMysqlUpdate(String table, Values values, Where where) {
        this.table = table;
        this.values = values;
        this.where = where;
    }

    public QueryMysqlUpdate value(String field, Object valor) {
        values.set(field, valor);
        return this;
    }

    public QueryMysqlUpdate where(String campo, Object valor, String operador) {
        where.where(campo, valor, operador);
        return this;
    }

    public QueryMysqlUpdate andWhere(String campo, Object valor, String operador) {
        where.andWhere(campo, valor, operador);
        return this;
    }

    public QueryMysqlUpdate orWhere(String campo, Object valor, String operador) {
        where.orWhery(campo, valor, operador);
        return this;
    }

    public String getSql() throws BDExcecao {

        if (table.isEmpty()) {
            throw new BDExcecao("Não foi informado a tabela na query!");
        }

        if (values.getSqlUpdate().isEmpty()) {
            throw new BDExcecao("Não foi informado os valores a serem alterados");
        }

        if (!where.getSqlWhere().isEmpty()) {
            if (where.getSqlWhere().substring(0, 4).trim().equals("AND")
                    || where.getSqlWhere().substring(0, 4).trim().equals("OR")) {
                throw new BDExcecao("Clausula informada sem whery");
            }
        }

        String sql = "UPDATE " + table + " SET " + values.getSqlUpdate();

        if (!where.getSqlWhere().isEmpty()) {
            sql += " WHERE " + where.getSqlWhere();
        }

        return sql + ";";

    }
}
