import it.okkam.connectors.Connector;
import it.okkam.connectors.DBpediaConnector;
import it.okkam.connectors.GeonamesConnector;
import it.okkam.connectors.WikidataConnector;
import it.okkam.exceptions.EntityNotFoundException;
import it.okkam.exceptions.UnsupportedTypeException;

import java.io.IOException;

public class ExtractionUtils {

  private static final String DBPEDIA = "dbpedia.org";
  private static final String WIKIDATA = "www.wikidata.org";
  private static final String GEONAMES = "sws.geonames.org";

  public String getEntity(String url, String type)
      throws IOException, UnsupportedTypeException, EntityNotFoundException {
    RequestInfo info = extractInfo(url);
    Connector connector = null;
    if (info.getSource().equals(DBPEDIA)) {
      connector = new DBpediaConnector();
    } else if (info.getSource().equals(WIKIDATA)) {
      connector = new WikidataConnector();
    } else if (info.getSource().equals(GEONAMES)) {
      connector = new GeonamesConnector();
    }
    if (connector != null && connector.isAlive()) {
      return connector.getEntity(info.getEntity(), type);
    }
    return "source not yet implemented";
  }

  private RequestInfo extractInfo(String url) {
    RequestInfo ret = new RequestInfo();
    ret.setEntity(url.split("\\/")[url.split("\\/").length - 1]);
    ret.setSource(url.split("\\/\\/")[1].split("\\/")[0]);
    return ret;
  }

}
