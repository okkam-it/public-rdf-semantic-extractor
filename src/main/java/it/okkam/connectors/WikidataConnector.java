package it.okkam.connectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WikidataConnector implements Connector {

  public static final String URL = "https://www.wikidata.org/wiki/Special:EntityData/";

  @Override
  public String getRdf(String entity, String type) throws IOException {
    if (!availableType(type)) {
      // manage unsupported type
      return null;
    }
    URL url = new URL(URL + entity + "." + WikidataAcceptedTypes.valueOf(type).getExtension());
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuilder content = new StringBuilder();
    while ((inputLine = in.readLine()) != null) {
      content.append(inputLine);
    }
    in.close();
    if (content.toString().contains("Not Found")) {
      return "Not Found";
    }
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

}
