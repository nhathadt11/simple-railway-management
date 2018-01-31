package study.nhatha.swd.tour;

import study.nhatha.swd.builder.SqlSelectQuery;
import study.nhatha.swd.generic.Repository;
import study.nhatha.swd.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourRepository implements Repository<Tour> {
  private Connection connection;

  public TourRepository() {
    this.connection = Database.getConnection();
  }

  @Override
  public void add(Tour item) {
    String sql = "INSERT INTO station(code, name) values(?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, item.getCode());
      statement.setString(2, item.getName());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void add(Iterable<Tour> tours) {
    tours.forEach(tour -> add(tour));
  }

  @Override
  public void update(Tour item) {
    String sql = "UPDATE station SET code = ?, name = ? WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, item.getCode());
      statement.setString(2, item.getName());
      statement.setInt(3, item.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Tour item) {
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
  public List<Tour> query() {

    return null;
  }

  @Override
  public List<Tour> queryAll() {
    SqlSelectQuery query = new SqlSelectQuery.SqlSelectQueryBuilder()
        .from("station")
        .build();
    List<Tour> stations = new ArrayList<>();

    try ( Connection connection = Database.getConnection();
          PreparedStatement statement = connection.prepareStatement(query.toQueryString());
          ResultSet cursor = statement.executeQuery()) {
      while (cursor.next()) {
        stations.add(new Tour(
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
