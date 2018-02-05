package study.nhatha.swd.track;

import study.nhatha.swd.generic.DataAccess;
import study.nhatha.swd.util.Database;
import study.nhatha.swd.console.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDao implements DataAccess<Track> {
  private Connection connection;

  public TrackDao() {
    this.connection = Database.getConnection();
  }

  @Override
  public void add(Track track) {
    String sql = "INSERT INTO track(code, name, sourceId, destinationId) values(?, ?, ?, ?)";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString (1, track.getCode());
      statement.setString (2, track.getName());
      statement.setInt    (3, idByCode("station", track.getSourceCode()));
      statement.setInt    (4, idByCode("station", track.getDestinationCode()));
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void add(Iterable<Track> tracks) {
    String sql = "INSERT INTO track(code, name, sourceId, destinationId) VALUES(?, ?, ?, ?)";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      for (Track track : tracks) {
        statement.setString (1, track.getCode());
        statement.setString (2, track.getName());
        statement.setInt    (3, idByCode("station", track.getSourceCode()));
        statement.setInt    (4, idByCode("station", track.getDestinationCode()));
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Track track) {
    String sql = "UPDATE track SET code = ?, name = ?, sourceId = ?, destinationId = ? WHERE id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString (1, track.getCode());
      statement.setString (2, track.getName());
      statement.setInt    (3, idByCode("station", track.getSourceCode()));
      statement.setInt    (4, idByCode("station", track.getDestinationCode()));
      statement.setInt    (5, track.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      Notification.error(String.format("Code: %d Message: %s", e.getErrorCode(), e.getMessage()));
    }
  }

  @Override
  public void delete(Track track) {
    String sql = "DELETE FROM track WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, track.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Track query(Track item) {
    return null;
  }

  @Override
  public Track query(int itemId) {
    return null;
  }

  @Override
  public Track queryByCode(String code) {
    StringBuilder sql = new StringBuilder(
        "SELECT track.id, track.code, track.name," +
        "s1.code AS sourceCode, s1.name AS sourceName," +
        "s2.code AS destinationCode, s2.name AS destinationName "
    )
        .append("FROM track ")
        .append("LEFT JOIN station AS s1 ON (track.sourceId = s1.id) ")
        .append("LEFT JOIN station AS s2 ON (track.destinationId = s2.id) ")
        .append("WHERE track.code = ?");
    List<Track> tracks = null;

    try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
      statement.setString(1, code);
      tracks = listFromCursor(statement.executeQuery());
    } catch (SQLException e) {
      Notification.error(e);
    }

    return !tracks.isEmpty() ? tracks.get(0) : null;
  }

  @Override
  public List<Track> queryAll() {
    StringBuilder sql = new StringBuilder(
        "SELECT track.id, track.code, track.name," +
            "s1.code AS sourceCode, s1.name AS sourceName," +
            "s2.code AS destinationCode, s2.name AS destinationName "
    )
        .append("FROM track ")
        .append("LEFT JOIN station AS s1 ON (track.sourceId = s1.id) ")
        .append("LEFT JOIN station AS s2 ON (track.destinationId = s2.id)");
    List<Track> tracks = null;

    try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
      tracks = listFromCursor(statement.executeQuery());
    } catch (SQLException e) {
      Notification.error(e);
    }

    return tracks;
  }

  private List<Track> listFromCursor(ResultSet cursor) {
    List<Track> tracks = new ArrayList<>();

    try {
      while (cursor.next()) {
        tracks.add(new Track(
            cursor.getInt   ("id"),
            cursor.getString("code"),
            cursor.getString("name"),
            cursor.getString("sourceCode"),
            cursor.getString("sourceName"),
            cursor.getString("destinationCode"),
            cursor.getString("destinationName")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return tracks;
  }

  private int idByCode(String tableName, String code) {
    String sql = String.format("SELECT id FROM %s WHERE code = ?", tableName);
    int id = -1;

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
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
