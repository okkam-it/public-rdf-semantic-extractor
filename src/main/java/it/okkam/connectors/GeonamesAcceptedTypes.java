package it.okkam.connectors;

public enum GeonamesAcceptedTypes {
  XML("rdf");

  private String extension;

  private GeonamesAcceptedTypes(String extension) {
    this.extension = extension;
  }

  public String getExtension() {
    return extension;
  }

}
