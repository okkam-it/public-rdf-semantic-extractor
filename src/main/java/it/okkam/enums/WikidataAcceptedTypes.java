package it.okkam.enums;

public enum WikidataAcceptedTypes {
  NTRIPLES("nt"), N3("n3"), TURTLE("ttl"), JSON("json"), XML_RDF("rdf");

  private String extension;

  private WikidataAcceptedTypes(String extension) {
    this.extension = extension;
  }

  public String getExtension() {
    return extension;
  }

}
