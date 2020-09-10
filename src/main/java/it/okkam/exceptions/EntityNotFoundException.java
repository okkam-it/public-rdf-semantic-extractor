package it.okkam.exceptions;

public class EntityNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  public EntityNotFoundException(String entity, String connector) {
    super("Unable to retrieve entity " + entity + " from " + connector);
  }

}
