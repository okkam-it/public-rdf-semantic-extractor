package it.okkam.connectors;

import it.okkam.exceptions.EntityNotFoundException;
import it.okkam.exceptions.UnsupportedTypeException;
import it.okkam.extractor.RequestInfo;
import it.okkam.extractor.RequestResponse;

import java.io.IOException;
import java.util.List;

public interface Connector {

  public RequestResponse getEntity(RequestInfo entity)
      throws IOException, UnsupportedTypeException, EntityNotFoundException;

  public boolean availableType(String type);

  public List<String> acceptedTypes();

  public boolean isAlive() throws IOException;

}
