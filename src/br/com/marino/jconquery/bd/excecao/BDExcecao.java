package br.com.marino.jconquery.bd.excecao;

/**
 * Classe responsável disparar exceções quando a erro na manipulação do banco de dados
 *
 * @author Leonardo
 */
public class BDExcecao extends Exception {

    public BDExcecao() {
    }

    public BDExcecao(String message) {
        super(message);
    }

    public BDExcecao(String message, Throwable cause) {
        super(message, cause);
    }

    public BDExcecao(Throwable cause) {
        super(cause);
    }

    public BDExcecao(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
