package it.okkam.exceptions;

public class UnsupportedTypeException extends Exception {

  private static final long serialVersionUID = 1L;

  public UnsupportedTypeException(String connector, String type) {
    super(type + " is not supported for connector " + connector);
  }

}
