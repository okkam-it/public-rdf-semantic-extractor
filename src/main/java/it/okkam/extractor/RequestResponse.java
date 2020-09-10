package it.okkam.extractor;

public class RequestResponse {

  private RequestInfo info;
  private String body;
  private String ontology;

  public RequestInfo getInfo() {
    return info;
  }

  public void setInfo(RequestInfo info) {
    this.info = info;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getOntology() {
    return ontology;
  }

  public void setOntology(String ontology) {
    this.ontology = ontology;
  }

}
