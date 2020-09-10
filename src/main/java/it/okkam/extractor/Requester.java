package it.okkam.extractor;

import it.okkam.connectors.Connector;
import it.okkam.connectors.DBpediaConnector;
import it.okkam.connectors.GeonamesConnector;
import it.okkam.connectors.WikidataConnector;
import it.okkam.exceptions.EntityNotFoundException;
import it.okkam.exceptions.UnsupportedTypeException;

import java.io.IOException;

public class Requester {

  private static final String DBPEDIA = "dbpedia.org";
  private static final String WIKIDATA = "www.wikidata.org";
  private static final String GEONAMES = "sws.geonames.org";

  public RequestResponse getEntity(String url, String type)
      throws IOException, UnsupportedTypeException, EntityNotFoundException {
    RequestInfo info = extractInfo(url);
    info.setType(type);
    Connector connector = null;
    if (info.getSource().equals(DBPEDIA)) {
      connector = new DBpediaConnector();
    } else if (info.getSource().equals(WIKIDATA)) {
      connector = new WikidataConnector();
    } else if (info.getSource().equals(GEONAMES)) {
      connector = new GeonamesConnector();
    }
    if (connector != null && connector.isAlive()) {
      return connector.getEntity(info);
    }
    RequestResponse ret = new RequestResponse();
    ret.setInfo(info);
    ret.setBody("source not yet implemented");
    return ret;
  }

  private RequestInfo extractInfo(String url) {
    RequestInfo ret = new RequestInfo();
    ret.setEntity(url.split("\\/")[url.split("\\/").length - 1]);
    ret.setSource(url.split("\\/\\/")[1].split("\\/")[0]);
    return ret;
  }

}
