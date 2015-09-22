package br.com.marino.jconquery.bd.query.mysql;

import br.com.marino.jconquery.bd.excecao.BDExcecao;

/**
 * Classe responsável por criar a instrução sql inserção
 * @author Leonardo
 */
public class QueryMysqlInserir {

    private String table;
    private Values values;

    private QueryMysqlInserir(String tabela, Values camposValores) {
        this.table = tabela;
        this.values = camposValores;
    }

    public QueryMysqlInserir() {
        this("", new Values());
    }

    public QueryMysqlInserir into(String table) {
        this.table = table;
        return this;
    }

    public QueryMysqlInserir value(String field, Object value) {
        values.set(field, value);
        return this;
    }

    public String getSql() throws BDExcecao {

        if (table.isEmpty()) {
            throw new BDExcecao("Não foi informado a tabela!");
        }

        if (values.getSqlInsert().isEmpty()) {
            throw new BDExcecao("Não foi informado os valores!");
        }

        return "INSERT INTO " + table + " " + values.getSqlInsert() + ";";
    }
}
