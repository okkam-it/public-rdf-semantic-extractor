package it.okkam.enums;

public enum GeonamesAcceptedTypes {
  XML_RDF("rdf");

  private String extension;

  private GeonamesAcceptedTypes(String extension) {
    this.extension = extension;
  }

  public String getExtension() {
    return extension;
  }

}
