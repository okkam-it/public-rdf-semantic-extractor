public class ExtractionUtils {

  public static String DBPEDIA = "dbpedia.org";
  public static String WIKIDATA = "www.wikidata.org";
  public static String GEONAMES = "sws.geonames.org";

  public String extract(String toExtract) {
    String source = extractSource(toExtract);
    if (source.equals(DBPEDIA)) {
      return extractEntity(toExtract);
    } else if (source.equals(WIKIDATA)) {
      return extractEntity(toExtract);
    } else if (source.equals(GEONAMES)) {
      return extractEntity(toExtract);
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
