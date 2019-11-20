package it.okkam.connectors;

import it.okkam.enums.WikidataAcceptedTypes;
import it.okkam.exceptions.EntityNotFoundException;
import it.okkam.exceptions.UnsupportedTypeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WikidataConnector implements Connector {

  public static final String URL = "https://www.wikidata.org/wiki/Special:EntityData/";
  public static final String NAME = "Wikidata";

  @Override
  public String getEntity(String entity, String type)
      throws IOException, UnsupportedTypeException, EntityNotFoundException {
    if (!availableType(type)) {
      throw new UnsupportedTypeException(NAME, type);
    }
    URL url = new URL(URL + entity + "." + WikidataAcceptedTypes.valueOf(type).getExtension());
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");

    if (con.getResponseCode() == 400) {
      throw new EntityNotFoundException(entity, NAME);
    }
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
    for (WikidataAcceptedTypes val : WikidataAcceptedTypes.values()) {
      if (val.name().equals(type)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List<String> acceptedTypes() {
    List<String> ret = new ArrayList<>();
    for (WikidataAcceptedTypes val : WikidataAcceptedTypes.values()) {
      ret.add(val.name());
    }
    return ret;
  }

  @Override
  public boolean isAlive() throws IOException {
    URL url = new URL(URL);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    return con.getResponseCode() != 400;
  }

}
