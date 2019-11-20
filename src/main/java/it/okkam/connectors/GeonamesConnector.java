package it.okkam.connectors;

import it.okkam.enums.GeonamesAcceptedTypes;
import it.okkam.exceptions.UnsupportedTypeException;
import it.okkam.extractor.RequestInfo;
import it.okkam.extractor.RequestResponse;

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
  public RequestResponse getEntity(RequestInfo info) throws IOException, UnsupportedTypeException {
    if (!availableType(info.getType())) {
      throw new UnsupportedTypeException(NAME, info.getType());
    }
    URL url = new URL(URL + info.getEntity() + "/about."
        + GeonamesAcceptedTypes.valueOf(info.getType()).getExtension());
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuilder content = new StringBuilder();
    while ((inputLine = in.readLine()) != null) {
      content.append(inputLine);
    }
    in.close();
    RequestResponse resp = new RequestResponse();
    resp.setInfo(info);
    resp.setBody(content.toString());
    return resp;
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

  @Override
  public boolean isAlive() throws IOException {
    URL url = new URL(URL);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    return con.getResponseCode() != 400;
  }

}
