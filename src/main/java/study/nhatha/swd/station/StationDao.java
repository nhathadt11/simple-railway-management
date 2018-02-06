package study.nhatha.swd.station;

import study.nhatha.swd.builder.SqlSelectQuery;
import study.nhatha.swd.console.Notification;
import study.nhatha.swd.generic.DataAccess;
import study.nhatha.swd.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDao implements DataAccess<Station> {
  private Connection connection;

  public StationDao() {
    connection = Database.getConnection();
  }

  @Override
  public void add(Station station) {
    String sql = "INSERT INTO station(code, name) values(?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, station.getCode());
      statement.setString(2, station.getName());
      statement.executeUpdate();
    } catch (SQLException e) {
      Notification.error(e);
    }
  }

  @Override
  public void add(Iterable<Station> stations) {
    String sql = "INSERT INTO station(code, name) values(?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      for (Station station : stations) {
        statement.setString(1, station.getCode());
        statement.setString(2, station.getName());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      Notification.error(e);
    }
  }

  @Override
  public void update(Station station) {
    String sql = "UPDATE station SET code = ?, name = ? WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, station.getCode());
      statement.setString(2, station.getName());
      statement.setInt(3, station.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      Notification.error(e);
    }
  }

  @Override
  public void delete(Station station) {
    String sql = "DELETE FROM station WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, station.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      Notification.error(e);
    }
  }

  @Override
  public Station query(Station item) {
    return null;
  }

  @Override
  public Station query(int itemId) {
    return null;
  }

  @Override
  public Station queryByCode(String code) {
    String sql = "SELECT * FROM station WHERE code = ?";
    Station found = null;

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, code);
      ResultSet cursor = statement.executeQuery();
      if (cursor.next()) {
        found = new Station(
            cursor.getInt("id"),
            cursor.getString("code"),
            cursor.getString("name")
        );
      }
    } catch (SQLException e) {
      Notification.error(e);
    }

    return found;
  }

  @Override
  public List<Station> queryAll() {
    SqlSelectQuery query = new SqlSelectQuery.SqlSelectQueryBuilder()
        .from("station")
        .build();
    List<Station> stations = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(query.toQueryString());
          ResultSet cursor = statement.executeQuery()) {
       while (cursor.next()) {
         stations.add(new Station(
             cursor.getInt(1),
             cursor.getString(2),
             cursor.getString(3)
         ));
       }
    } catch (SQLException e) {
      Notification.error(e);
    }

    return stations;
  }
}
