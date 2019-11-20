package it.okkam.connectors;

import it.okkam.enums.GeonamesAcceptedTypes;
import it.okkam.exceptions.UnsupportedTypeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GeonamesConnector implements Connector {

  public static final String URL = "https://sws.geonames.org/";
  public static final String NAME = "Geonames";

  @Override
  public String getEntity(String entity, String type) throws IOException, UnsupportedTypeException {
    if (!availableType(type)) {
      throw new UnsupportedTypeException(NAME, type);
    }
    URL url =
        new URL(URL + entity + "/about." + GeonamesAcceptedTypes.valueOf(type).getExtension());
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuilder content = new StringBuilder();
    while ((inputLine = in.readLine()) != null) {
      content.append(inputLine);
    }
    in.close();
    return content.toString();
  }

  @Override
  public boolean availableType(String type) {
    for (GeonamesAcceptedTypes val : GeonamesAcceptedTypes.values()) {
      if (val.name().equals(type)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List<String> acceptedTypes() {
    List<String> ret = new ArrayList<>();
    for (GeonamesAcceptedTypes val : GeonamesAcceptedTypes.values()) {
      ret.add(val.name());
    }
    return ret;
  }

}
