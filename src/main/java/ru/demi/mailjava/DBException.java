package ru.demi.mailjava;

/**
 * @author demi
 * @date 30.01.16
 */
public class DBException extends Throwable {
    public DBException(Throwable e) {
        super(e);
    }
}
