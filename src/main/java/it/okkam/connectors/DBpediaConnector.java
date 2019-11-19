package it.okkam.connectors;

import it.okkam.enums.DBpediaAcceptedTypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DBpediaConnector implements Connector {

  public static final String URL = "http://dbpedia.org/data/";

  @Override
  public String getRdf(String entity, String type) throws IOException {
    if (!availableType(type)) {
      // manage unsupported type
      return null;
    }
    URL url = new URL(URL + entity + "." + DBpediaAcceptedTypes.valueOf(type).getExtension());
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
    for (DBpediaAcceptedTypes val : DBpediaAcceptedTypes.values()) {
      if (val.name().equals(type)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List<String> acceptedTypes() {
    List<String> ret = new ArrayList<>();
    for (DBpediaAcceptedTypes val : DBpediaAcceptedTypes.values()) {
      ret.add(val.name());
    }
    return ret;
  }

}
