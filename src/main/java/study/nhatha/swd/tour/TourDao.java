package study.nhatha.swd.tour;

import study.nhatha.swd.console.Notification;
import study.nhatha.swd.generic.DataAccess;
import study.nhatha.swd.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourDao implements DataAccess<Tour> {
  private Connection connection;

  public TourDao() {
    this.connection = Database.getConnection();
  }

  @Override
  public void add(Tour tour) {
    String sql = "INSERT INTO tour(code, name, trackId, trainId, timeStart, timeEnd) VALUES(?, ?, ?, ?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString (1, tour.getCode());
      statement.setString (2, tour.getName());
      statement.setInt    (3, idByCode("track", tour.getTrackCode()));
      statement.setInt    (4, idByCode("train", tour.getTrainCode()));
      statement.setDate   (5, tour.getTimeStart());
      statement.setDate   (6, tour.getTimeEnd());
      statement.executeUpdate();
    } catch (SQLException e) {
      Notification.error(e);
    }
  }

  @Override
  public void add(Iterable<Tour> tours) {
    tours.forEach(this::add);
  }

  @Override
  public void update(Tour tour) {
    String sql = "UPDATE tour SET code = ?, name = ?, trackId = ?, trainId = ?, timeStart = ?, timeEnd = ? WHERE id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString (1, tour.getCode());
      statement.setString (2, tour.getName());
      statement.setInt    (3, idByCode("track", tour.getTrackCode()));
      statement.setInt    (4, idByCode("train", tour.getTrainCode()));
      statement.setDate   (5, tour.getTimeStart());
      statement.setDate   (6, tour.getTimeEnd());
      statement.setInt    (7, tour.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Tour tour) {
    String sql = "DELETE FROM tour WHERE id = ?";
    try ( Connection connection = Database.getConnection();
          PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, tour.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Tour query(Tour tour) {
    return null;
  }

  @Override
  public Tour query(int tourId) {
    return null;
  }

  @Override
  public Tour queryByCode(String code) {
    String sql = "SELECT * FROM tour WHERE code = ?";
    Tour found = null;

    try(PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, code);
      ResultSet cursor = statement.executeQuery();

      if (cursor.next()) {
        found = new Tour(
            cursor.getInt   ("id"),
            cursor.getString("code"),
            cursor.getString("name")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return found;
  }

  @Override
  public List<Tour> queryAll() {
    StringBuilder sql = new StringBuilder(
        "SELECT tour.id, tour.code, tour.name, tour.timeStart, tour.timeEnd," +
        "track.code AS trackCode, train.code AS trainCode "
    )
        .append("FROM tour ")
        .append("LEFT JOIN track ON tour.trackId = track.id ")
        .append("LEFT JOIN train ON tour.trainId = train.id ");
    List<Tour> tours = new ArrayList<>();

    try ( Connection connection = Database.getConnection();
          PreparedStatement statement = connection.prepareStatement(sql.toString());
          ResultSet cursor = statement.executeQuery()) {
      while (cursor.next()) {
        tours.add(new Tour(
            cursor.getInt   ("id"),
            cursor.getString("code"),
            cursor.getString("name"),
            cursor.getString("trackCode"),
            cursor.getString("trainCode"),
            cursor.getDate  ("timeStart"),
            cursor.getDate  ("timeEnd")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return tours;
  }

  private int idByCode(String tableName, String code) {
    String sql = String.format("SELECT * FROM %s WHERE code = ?", tableName);
    int id = -1;

    try(PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, code);
      ResultSet cursor = statement.executeQuery();

      if (cursor.next()) {
        id = cursor.getInt("id");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return id;
  }
}
