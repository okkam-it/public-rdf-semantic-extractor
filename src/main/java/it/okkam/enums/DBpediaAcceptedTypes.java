package it.okkam.enums;

public enum DBpediaAcceptedTypes {
  NTRIPLES("ntriples"), N3("n3"), TURTLE("ttl"), JSON("json"), XML_RDF("rdf"), ATOM("atom"), JSOD(
      "jsod");

  private String extension;

  private DBpediaAcceptedTypes(String extension) {
    this.extension = extension;
  }

  public String getExtension() {
    return extension;
  }

}
