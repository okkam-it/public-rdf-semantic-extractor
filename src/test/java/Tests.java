import it.okkam.connectors.DBpediaConnector;
import it.okkam.connectors.GeonamesConnector;
import it.okkam.connectors.WikidataConnector;

import java.io.IOException;

import org.junit.Test;

public class Tests {

  @Test
  public void processDbpedia() throws IOException {
    ExtractionUtils ext = new ExtractionUtils();
    String entity = ext.extract("http://dbpedia.org/resource/Pier_Paolo_Pasolini");
    DBpediaConnector connector = new DBpediaConnector();
    System.out.println(entity + " --> " + connector.getRdf(entity, "json"));
  }

  @Test
  public void processWikidata() throws IOException {
    ExtractionUtils ext = new ExtractionUtils();
    String entity = ext.extract("https://www.wikidata.org/wiki/Q25120");
    WikidataConnector connector = new WikidataConnector();
    System.out.println(entity + " --> " + connector.getRdf(entity, "json"));
  }

  @Test
  public void processGeonames() throws IOException {
    ExtractionUtils ext = new ExtractionUtils();
    String entity = ext.extract("http://sws.geonames.org/1261700");
    GeonamesConnector connector = new GeonamesConnector();
    System.out.println(entity + " --> " + connector.getRdf(entity, "rdf"));
  }

}
