package study.nhatha.swd.train;

import study.nhatha.swd.builder.SqlSelectQuery;
import study.nhatha.swd.generic.Repository;
import study.nhatha.swd.util.Database;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainRepository implements Repository<Train> {
  private Connection connection;

  public TrainRepository() {
    this.connection = Database.getConnection();
  }

  @Override
  public void add(Train item) {
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
  public void add(Iterable<Train> items) {
    String sql = "INSERT INTO state(code, name) values(?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      for (Train station : items) {
        statement.setString(1, station.getCode());
        statement.setString(2, station.getName());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Train item) {
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
  public void delete(Train item) {
    String sql = "DELETE FROM station WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, item.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Train> query() {

    return null;
  }

  @Override
  public List<Train> queryAll() {
    SqlSelectQuery query = new SqlSelectQuery.SqlSelectQueryBuilder()
        .from("station")
        .build();
    List<Train> trains = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(query.toQueryString());
          ResultSet cursor = statement.executeQuery()) {
      while (cursor.next()) {
        trains.add(new Train(
            cursor.getInt(1),
            cursor.getString(2),
            cursor.getString(3)
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return trains;
  }
}
