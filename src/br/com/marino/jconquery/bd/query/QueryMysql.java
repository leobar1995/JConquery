package br.com.marino.jconquery.bd.query;

import br.com.marino.jconquery.bd.query.mysql.QueryMysqlSelect;
import br.com.marino.jconquery.bd.query.mysql.QueryMysqlDelete;
import br.com.marino.jconquery.bd.query.mysql.QueryMysqlInserir;
import br.com.marino.jconquery.bd.query.mysql.QueryMysqlUpdate;

/**
 * 
 * @author Leonardo
 */
public class QueryMysql {

    public static QueryMysqlSelect select(String campos) {
        return new QueryMysqlSelect(campos);
    }

    public static QueryMysqlInserir insert() {
        return new QueryMysqlInserir();
    }

    public static QueryMysqlUpdate update(String table) {
        return new QueryMysqlUpdate(table);
    }

    public static QueryMysqlDelete delete() {
        return new QueryMysqlDelete();
    }
    
}
