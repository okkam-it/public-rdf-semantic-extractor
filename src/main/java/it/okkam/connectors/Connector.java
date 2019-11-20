package it.okkam.connectors;

import it.okkam.exceptions.EntityNotFoundException;
import it.okkam.exceptions.UnsupportedTypeException;

import java.io.IOException;
import java.util.List;

public interface Connector {

  public String getEntity(String entity, String type)
      throws IOException, UnsupportedTypeException, EntityNotFoundException;

  public boolean availableType(String type);

  public List<String> acceptedTypes();

  public boolean isAlive() throws IOException;

}
