package study.nhatha.swd.train;

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

public class TrainDao implements DataAccess<Train> {
  private Connection connection;

  public TrainDao() {
    this.connection = Database.getConnection();
  }

  @Override
  public void add(Train train) {
    String sql = "INSERT INTO train(code, name, seatNum, seatPrice) values(?, ?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString (1, train.getCode());
      statement.setString (2, train.getName());
      statement.setInt    (3, train.getSeatNum());
      statement.setFloat  (4, train.getSeatPrice());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void add(Iterable<Train> trains) {
    String sql = "INSERT INTO state(code, name, seatNum, seatPrice) VALUES(?, ?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      for (Train train : trains) {
        statement.setString (1, train.getCode());
        statement.setString (2, train.getName());
        statement.setInt    (3, train.getSeatNum());
        statement.setFloat  (4, train.getSeatPrice());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      Notification.error(e);
    }
  }

  @Override
  public void update(Train train) {
    String sql = "UPDATE train SET code = ?, name = ?, seatNum = ?, seatPrice = ? WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString (1, train.getCode());
      statement.setString (2, train.getName());
      statement.setInt    (3, train.getSeatNum());
      statement.setFloat  (4, train.getSeatPrice());
      statement.setInt    (5, train.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Train train) {
    String sql = "DELETE FROM train WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, train.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Train query(Train train) {
    return null;
  }

  @Override
  public Train query(int trainId) {
    return null;
  }

  @Override
  public Train queryByCode(String code) {
    String sql = "SELECT * FROM train WHERE code = ?";
    Train found = null;

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, code);
      ResultSet cursor = statement.executeQuery();
      if (cursor.next()) {
        found = new Train(
            cursor.getInt   ("id"),
            cursor.getString("code"),
            cursor.getString("name"),
            cursor.getInt   ("seatNum"),
            cursor.getFloat ("seatPrice")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return found;
  }

  @Override
  public List<Train> queryAll() {
    SqlSelectQuery query = new SqlSelectQuery.SqlSelectQueryBuilder()
        .from("train")
        .build();
    List<Train> trains = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(query.toQueryString());
          ResultSet cursor = statement.executeQuery()) {
      while (cursor.next()) {
        trains.add(new Train(
            cursor.getInt   ("id"),
            cursor.getString("code"),
            cursor.getString("name"),
            cursor.getInt   ("seatNum"),
            cursor.getFloat ("seatPrice")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return trains;
  }
}
