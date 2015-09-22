package br.com.marino.jconquery.bd.query.mysql;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Where {

    private String where;

    private Where(String where) {
        this.where = where;
    }

    public Where() {
        this("");
    }

    private void setAndOrWhery(String prefixo, String campo, Object valor, String operador) {

        if (valor instanceof Integer) {
            where += " " + prefixo + " " + campo + " " + operador + " " + valor.toString();
        }

        if (valor instanceof String) {
            where += " " + prefixo + " " + campo + " " + operador + " " + "\"" + valor.toString() + "\"";
        }

        if (valor instanceof Double) {
            where += " " + prefixo + " " + campo + " " + operador + " " + valor.toString();
        }

        if (valor instanceof Float) {
            where += " " + prefixo + " " + campo + " " + operador + " " + valor.toString();
        }

        if (valor instanceof Date) {
            Date data = (Date) valor;
            SimpleDateFormat format =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            where += " " + prefixo + " " + campo + " " + operador + " " + "\"" + format.format(data) + "\"";
        }

    }

    private void setWhere(String campo, Object valor, String operador) {

        if (valor instanceof Integer) {
            where = campo + " " + operador + " " + valor.toString();
        }

        if (valor instanceof String) {
            where = campo + " " + operador + " " + "\"" + valor.toString() + "\"";
        }

        if (valor instanceof Double) {
            where = campo + " " + operador + " " + valor.toString();
        }

        if (valor instanceof Float) {
            where = campo + " " + operador + " " + valor.toString();
        }

        if (valor instanceof Date) {
            Date data = (Date) valor;
            SimpleDateFormat format =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            where = campo + " " + operador + " " + "\"" + format.format(data) + "\"";
        }

    }

    public void where(String campo, Object valor, String operador) {
        setWhere(campo, valor, operador);
    }

    public void andWhere(String campo, Object valor, String operador) {
        setAndOrWhery("AND", campo, valor, operador);
    }

    public void orWhery(String campo, Object valor, String operador) {
        setAndOrWhery("OR", campo, valor, operador);
    }

    public String getSqlWhere() {
        return where;
    }
}
