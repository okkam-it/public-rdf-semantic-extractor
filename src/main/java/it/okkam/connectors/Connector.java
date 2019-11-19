package it.okkam.connectors;

import java.io.IOException;
import java.util.List;

public interface Connector {

  public String getRdf(String entity, String type) throws IOException;

  public boolean availableType(String type);

  public List<String> acceptedTypes();

}
