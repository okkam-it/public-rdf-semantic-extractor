import it.okkam.connectors.Connector;
import it.okkam.connectors.DBpediaConnector;
import it.okkam.connectors.GeonamesConnector;
import it.okkam.connectors.WikidataConnector;
import it.okkam.exceptions.EntityNotFoundException;
import it.okkam.exceptions.UnsupportedTypeException;

import java.io.IOException;

public class ExtractionUtils {

  public static final String DBPEDIA = "dbpedia.org";
  public static final String WIKIDATA = "www.wikidata.org";
  public static final String GEONAMES = "sws.geonames.org";

  public String get(String toGet, String type)
      throws IOException, UnsupportedTypeException, EntityNotFoundException {
    RequestInfo info = extract(toGet);
    Connector connector;
    if (info.getSource().equals(DBPEDIA)) {
      connector = new DBpediaConnector();
      return connector.getEntity(info.getEntity(), type);
    } else if (info.getSource().equals(WIKIDATA)) {
      connector = new WikidataConnector();
      return connector.getEntity(info.getEntity(), type);
    } else if (info.getSource().equals(GEONAMES)) {
      connector = new GeonamesConnector();
      return connector.getEntity(info.getEntity(), type);
    }
    return "source not yet implemented";
  }

  public RequestInfo extract(String url) {
    RequestInfo ret = new RequestInfo();
    ret.setEntity(url.split("\\/")[url.split("\\/").length - 1]);
    ret.setSource(url.split("\\/\\/")[1].split("\\/")[0]);
    return ret;
  }

}
