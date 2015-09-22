package br.com.marino.jconquery.bd.query.mysql;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Values {

    private ArrayList<String> fields;
    private ArrayList<String> values;

    private Values(ArrayList<String> fields, ArrayList<String> values) {
        this.fields = fields;
        this.values = values;
    }

    public Values() {
        this(new ArrayList<String>(), new ArrayList<String>());
    }

    public void set(String field, Object value) {

        if (value instanceof Integer) {
            setSemAspas(field, value.toString());
        }

        if (value instanceof String) {
            setComAspas(field, value.toString());
        }

        if (value instanceof Double) {
            setSemAspas(field, value.toString());
        }

        if (value instanceof Float) {
            setSemAspas(field, value.toString());
        }

        if (value instanceof Date) {
            Date data = (Date) value;
            SimpleDateFormat format =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            setComAspas(field, format.format(data));
        }

    }

    private void setComAspas(String campo, String valor) {
        fields.add(campo);
        values.add("\"" + valor + "\"");
    }

    private void setSemAspas(String campo, String valor) {
        fields.add(campo);
        values.add(valor);
    }

    public String getSqlInsert() {
        if (isEmpty()) {
            return "";
        }

        String f = "";

        for (String field : fields) {
            if (f.isEmpty()) {
                f += field;
            } else {
                f += ", " + field;
            }
        }

        String v = "";

        for (String value : values) {
            if (v.isEmpty()) {
                v += value;
            } else {
                v += ", " + value;
            }
        }

        return "(" + f + ") VALUES (" + v + ")";

    }

    public String getSqlUpdate() {

        if (isEmpty()) {
            return "";
        }

        String w = "";

        for (int i = 0; i < fields.size(); i++) {
            if (w.isEmpty()) {
                w += fields.get(i) + " = " + values.get(i);
            } else {
                w += ", " + fields.get(i) + " = " + values.get(i);
            }
        }

        return w;

    }

    private boolean isEmpty() {
        if (fields.isEmpty() && values.isEmpty()) {
            return true;
        }
        return false;
    }
}
