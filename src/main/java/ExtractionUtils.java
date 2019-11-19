import it.okkam.connectors.Connector;
import it.okkam.connectors.DBpediaConnector;
import it.okkam.connectors.GeonamesConnector;
import it.okkam.connectors.WikidataConnector;

import java.io.IOException;

public class ExtractionUtils {

  public static final String DBPEDIA = "dbpedia.org";
  public static final String WIKIDATA = "www.wikidata.org";
  public static final String GEONAMES = "sws.geonames.org";

  public String get(String toGet, String type) throws IOException {
    String source = extractSource(toGet);
    String entity = extractEntity(toGet);
    Connector connector;
    if (source.equals(DBPEDIA)) {
      connector = new DBpediaConnector();
      return connector.getRdf(entity, type);
    } else if (source.equals(WIKIDATA)) {
      connector = new WikidataConnector();
      return connector.getRdf(entity, type);
    } else if (source.equals(GEONAMES)) {
      connector = new GeonamesConnector();
      return connector.getRdf(entity, type);
    }
    return "source not yet implemented";
  }

  public String extractSource(String toExtract) {
    return toExtract.split("\\/\\/")[1].split("\\/")[0];
  }

  public static String extractEntity(String url) {
    return url.split("\\/")[url.split("\\/").length - 1];
  }

}
