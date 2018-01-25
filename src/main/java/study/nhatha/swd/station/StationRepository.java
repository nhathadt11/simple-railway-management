package study.nhatha.swd.station;

import study.nhatha.swd.builder.SqlSelectQuery;
import study.nhatha.swd.generic.Repository;
import study.nhatha.swd.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationRepository implements Repository<Station> {

  @Override
  public void add(Station item) {
    String sql = "INSERT INTO station(code, name) values(?, ?)";
    try ( Connection connection = Database.getConnection();
          PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, item.getCode());
      statement.setString(2, item.getName());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void add(Iterable<Station> items) {
    String sql = "INSERT INTO state(code, name) values(?, ?)";
    try ( Connection connection = Database.getConnection();
          PreparedStatement statement = connection.prepareStatement(sql)) {
      for (Station station : items) {
        statement.setString(1, station.getCode());
        statement.setString(2, station.getName());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Station item) {
    String sql = "UPDATE station SET code = ?, name = ? WHERE id = ?";
    try ( Connection connection = Database.getConnection();
          PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, item.getCode());
      statement.setString(2, item.getName());
      statement.setInt(3, item.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Station item) {
    String sql = "DELETE FROM station WHERE id = ?";
    try ( Connection connection = Database.getConnection();
          PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, item.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Station> query() {

    return null;
  }

  @Override
  public List<Station> queryAll() {
    SqlSelectQuery query = new SqlSelectQuery.SqlSelectQueryBuilder()
        .from("station")
        .build();
    List<Station> stations = new ArrayList<>();

    try ( Connection connection = Database.getConnection();
          PreparedStatement statement = connection.prepareStatement(query.toQueryString());
          ResultSet cursor = statement.executeQuery()) {
       while (cursor.next()) {
         stations.add(new Station(
             cursor.getInt(1),
             cursor.getString(2),
             cursor.getString(3)
         ));
       }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return stations;
  }
}
