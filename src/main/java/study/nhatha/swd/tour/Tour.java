package study.nhatha.swd.tour;

public class Tour {
  private int id;
  private String code;
  private String name;
  private String stationCode;
  private String stationName;
  private String trackCode;
  private String trackName;
  private String sourceCode;
  private String sourceName;
  private String destinationCode;
  private String destinationName;

  public Tour(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public Tour(int id, String code, String name) {
    this.id = id;
    this.code = code;
    this.name = name;
  }

  public Tour(int id, String code, String name, String stationCode, String stationName, String trackCode, String trackName, String sourceCode, String sourceName, String destinationCode, String destinationName) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.stationCode = stationCode;
    this.stationName = stationName;
    this.trackCode = trackCode;
    this.trackName = trackName;
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

  public String getStationCode() {
    return stationCode;
  }

  public void setStationCode(String stationCode) {
    this.stationCode = stationCode;
  }

  public String getStationName() {
    return stationName;
  }

  public void setStationName(String stationName) {
    this.stationName = stationName;
  }

  public String getTrackCode() {
    return trackCode;
  }

  public void setTrackCode(String trackCode) {
    this.trackCode = trackCode;
  }

  public String getTrackName() {
    return trackName;
  }

  public void setTrackName(String trackName) {
    this.trackName = trackName;
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
        "%5d %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s",
        id, code, name, stationCode, stationName, trackCode, trackName, sourceCode, sourceName, destinationCode, destinationName
    );
  }
}
