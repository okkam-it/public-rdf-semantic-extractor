import it.okkam.enums.DBpediaAcceptedTypes;
import it.okkam.enums.GeonamesAcceptedTypes;
import it.okkam.enums.WikidataAcceptedTypes;
import it.okkam.exceptions.EntityNotFoundException;
import it.okkam.exceptions.UnsupportedTypeException;

import java.io.IOException;

import org.junit.Test;

public class Tests {

  @Test
  public void processDbpedia()
      throws IOException, UnsupportedTypeException, EntityNotFoundException {
    ExtractionUtils ext = new ExtractionUtils();
    System.out.println(ext.get("http://dbpedia.org/resource/Pier_Paolo_Pasolini",
        DBpediaAcceptedTypes.JSON.toString()));
  }

  @Test
  public void processWikidata()
      throws IOException, UnsupportedTypeException, EntityNotFoundException {
    ExtractionUtils ext = new ExtractionUtils();
    System.out.println(
        ext.get("https://www.wikidata.org/wiki/Q25120", WikidataAcceptedTypes.JSON.toString()));
  }

  @Test
  public void processGeonames()
      throws IOException, UnsupportedTypeException, EntityNotFoundException {
    ExtractionUtils ext = new ExtractionUtils();
    System.out.println(
        ext.get("http://sws.geonames.org/1261700", GeonamesAcceptedTypes.XML_RDF.toString()));
  }

  @Test(expected = UnsupportedTypeException.class)
  public void processGeonamesWrongType()
      throws IOException, UnsupportedTypeException, EntityNotFoundException {
    ExtractionUtils ext = new ExtractionUtils();
    System.out.println(ext.get("http://sws.geonames.org/1261700", "abc"));
  }

  @Test(expected = EntityNotFoundException.class)
  public void processWikidataEntityNotFound()
      throws IOException, UnsupportedTypeException, EntityNotFoundException {
    ExtractionUtils ext = new ExtractionUtils();
    System.out.println(ext.get("https://www.wikidata.org/wiki/Q251203462773",
        WikidataAcceptedTypes.JSON.toString()));
  }

}
