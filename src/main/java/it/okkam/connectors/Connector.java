package it.okkam.connectors;

import java.io.IOException;

public interface Connector {

  public static final String URL = "";

  public String getRdf(String entity, String type) throws IOException;

}
