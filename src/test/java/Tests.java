import it.okkam.enums.DBpediaAcceptedTypes;
import it.okkam.enums.GeonamesAcceptedTypes;
import it.okkam.enums.WikidataAcceptedTypes;

import java.io.IOException;

import org.junit.Test;

public class Tests {

  @Test
  public void processDbpedia() throws IOException {
    ExtractionUtils ext = new ExtractionUtils();
    System.out.println(ext.get("http://dbpedia.org/resource/Pier_Paolo_Pasolini",
        DBpediaAcceptedTypes.JSON.toString()));
  }

  @Test
  public void processWikidata() throws IOException {
    ExtractionUtils ext = new ExtractionUtils();
    System.out.println(
        ext.get("https://www.wikidata.org/wiki/Q25120", WikidataAcceptedTypes.JSON.toString()));
  }

  @Test
  public void processGeonames() throws IOException {
    ExtractionUtils ext = new ExtractionUtils();
    System.out.println(
        ext.get("http://sws.geonames.org/1261700", GeonamesAcceptedTypes.XML_RDF.toString()));
  }

}
