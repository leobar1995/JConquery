package br.com.marino.jconquery.bd.query.mysql;

import br.com.marino.jconquery.bd.excecao.BDExcecao;

/**
 * Classe responsável por criar a instrução sql para consulta
 *
 * @author Leonardo
 */
public class QueryMysqlSelect {

    private String table;
    private String field;
    private Where where;

    public QueryMysqlSelect(String field) {
        this("", field, new Where());
    }

    private QueryMysqlSelect(String table, String fields, Where where) {
        this.table = table;
        this.field = fields;
        this.where = where;
    }

    public QueryMysqlSelect() {
        this("", "", new Where());
    }

    public QueryMysqlSelect field(String campo) {

        if (field.isEmpty()) {
            field += campo;
            return this;
        }

        field += ", " + campo;
        return this;

    }

    public QueryMysqlSelect from(String tabela) {
        this.table = tabela;
        return this;
    }

    public QueryMysqlSelect where(String campo, Object valor, String operador) {
        where.where(campo, valor, operador);
        return this;
    }

    public QueryMysqlSelect andWhere(String campo, Object valor, String operador) {
        where.andWhere(campo, valor, operador);
        return this;
    }

    public QueryMysqlSelect orWhere(String campo, Object valor, String operador) {
        where.orWhery(campo, valor, operador);
        return this;
    }

    public String getSql() throws BDExcecao {

        if (field.isEmpty()) {
            throw new BDExcecao("Não foi informado os campos na query!");
        }

        if (table.isEmpty()) {
            throw new BDExcecao("Não foi informado a tabela na query!");
        }

        if (!where.getSqlWhere().isEmpty()) {
            if (where.getSqlWhere().substring(0, 4).trim().equals("AND")
                    || where.getSqlWhere().substring(0, 4).trim().equals("OR")) {
                throw new BDExcecao("Clausula informada sem whery");
            }
        }

        String sql = "SELECT " + field + " FROM " + table;

        if (!where.getSqlWhere().isEmpty()) {
            sql += " WHERE " + where.getSqlWhere();
        }

        return sql + ";";

    }
}
