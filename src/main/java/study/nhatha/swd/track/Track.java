package study.nhatha.swd.track;

public class Track {
  public static final String HEADERS_FORMAT = "%5s %-20s %-30s %-20s %-20s %-20s %-20s";
  public static final String[] HEADERS = new String[]{
      "ID", "Code", "Name", "Source Code", "Source Name", "Destination Code", "Destination Name"
  };
  private int id;
  private String code;
  private String name;
  private String sourceCode;
  private String sourceName;
  private String destinationCode;
  private String destinationName;

  public Track(String code, String name, String sourceCode, String destinationCode) {
    this.code = code;
    this.name = name;
    this.sourceCode = sourceCode;
    this.destinationCode = destinationCode;
  }

  public Track(int id, String code, String name, String sourceCode, String sourceName,
               String destinationCode, String destinationName) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.sourceCode = sourceCode;
    this.sourceName = sourceName;
    this.destinationCode = destinationCode;
    this.destinationName = destinationName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSourceCode() {
    return sourceCode;
  }

  public void setSourceCode(String sourceCode) {
    this.sourceCode = sourceCode;
  }

  public String getSourceName() {
    return sourceName;
  }

  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
  }

  public String getDestinationCode() {
    return destinationCode;
  }

  public void setDestinationCode(String destinationCode) {
    this.destinationCode = destinationCode;
  }

  public String getDestinationName() {
    return destinationName;
  }

  public void setDestinationName(String destinationName) {
    this.destinationName = destinationName;
  }

  @Override
  public String toString() {
    return String.format(
        "%5d %-20s %-30s %-20s %-20s %-20s %-20s",
        id, code, name, sourceCode, sourceName, destinationCode, destinationName
    );
  }
}
