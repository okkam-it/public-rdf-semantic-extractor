package it.okkam.connectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WikidataConnector implements Connector {

  public static final String URL = "https://www.wikidata.org/wiki/Special:EntityData/";

  // .json, .rdf, .ttl or .nt

  public String getRdf(String entity, String type) throws IOException {
    URL url = new URL(URL + entity + "." + type);
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

}
