package study.nhatha.swd.tour;

public class Tour {
  private int id;
  private String code;
  private String name;

  public Tour(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public Tour(int id, String code, String name) {
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
    return String.format("%5d %10s %10s", id, code, name);
  }
}
