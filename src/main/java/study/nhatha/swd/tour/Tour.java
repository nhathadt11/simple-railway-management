package study.nhatha.swd.tour;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Tour {
  public static final String HEADERS_FORMAT = "%5s %-10s %-10s %-10s %-10s %-20s %-20s";
  public static final String[] HEADERS = new String[]{"ID", "Code", "Name", "Track", "Train", "Time Start", "Time End"};
  public static final DateFormat dateFormat = new SimpleDateFormat("YY-MM-DD hh:mm");
  private int id;
  private String code;
  private String name;
  private String trackCode;
  private String trainCode;
  private Date timeStart;
  private Date timeEnd;

  public Tour(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public Tour(int id, String code, String name) {
    this(code, name);
    this.id = id;
  }

  public Tour(String code, String name, String trackCode, String trainCode, Date timeStart, Date timeEnd) {
    this(code, name);
    this.trackCode = trackCode;
    this.trainCode = trainCode;
    this.timeStart = timeStart;
    this.timeEnd = timeEnd;
  }

  public Tour(int id, String code, String name, String trackCode, String trainCode, Date timeStart, Date timeEnd) {
    this(code,name, trackCode, trainCode, timeStart, timeEnd);
    this.id = id;
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

  public String getTrackCode() {
    return trackCode;
  }

  public void setTrackCode(String trackCode) {
    this.trackCode = trackCode;
  }

  public String getTrainCode() {
    return trainCode;
  }

  public void setTrainCode(String trainCode) {
    this.trainCode = trainCode;
  }

  public Date getTimeStart() {
    return timeStart;
  }

  public void setTimeStart(Date timeStart) {
    this.timeStart = timeStart;
  }

  public Date getTimeEnd() {
    return timeEnd;
  }

  public void setTimeEnd(Date timeEnd) {
    this.timeEnd = timeEnd;
  }

  @Override
  public String toString() {
    return String.format(
        "%5d %-10s %-10s %-10s %-10s %-20s %-20s",
        id, code, name, trackCode, trainCode, dateFormat.format(timeStart), dateFormat.format(timeEnd)
    );
  }
}
