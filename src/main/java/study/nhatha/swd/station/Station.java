package study.nhatha.swd.station;

public class Station {
  public static final String HEADERS_FORMAT = "%-10s %-10s %-10s";
  public static final String[] HEADERS = new String[]{ "ID", "Code", "Name" };
  private int id;
  private String code;
  private String name;

  public Station(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public Station(int id, String code, String name) {
    this.id = id;
    this.code = code;
    this.name = name;
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

  @Override
  public String toString() {
    return String.format("%-10d %-10s %s", id, code, name);
  }
}
