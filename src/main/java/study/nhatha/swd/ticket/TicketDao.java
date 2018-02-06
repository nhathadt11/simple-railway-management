package study.nhatha.swd.ticket;

import study.nhatha.swd.generic.DataAccess;
import study.nhatha.swd.util.Database;
import study.nhatha.swd.console.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDao implements DataAccess<Ticket> {
  private Connection connection;

  public TicketDao() {
    this.connection = Database.getConnection();
  }

  @Override
  public void add(Ticket ticket) {
    String sql = "INSERT INTO ticket (code, name, tourId, price) VALUES(?, ?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      int tourId = idByCode("tour", ticket.getTourCode());

      statement.setString (1, ticket.getCode());
      statement.setString (2, ticket.getName());
      statement.setInt    (3, tourId);
      statement.setFloat  (4, priceByTourId(tourId));

      statement.executeUpdate();
    } catch (SQLException e) {
      Notification.error(e);
    }
  }

  @Override
  public void add(Iterable<Ticket> tickets) {
    tickets.forEach(this::add);
  }

  @Override
  public void update(Ticket ticket) {
    String sql = "UPDATE ticket SET " +
        "firstName = ?, lastName = ?, phoneNumber = ?, permanentAddress = ? WHERE id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
      statement.setString (1, ticket.getCustomer().getFirstName());
      statement.setString (2, ticket.getCustomer().getLastName());
      statement.setString (3, ticket.getCustomer().getPhoneNumber());
      statement.setString (4, ticket.getCustomer().getPermanentAddress());
      statement.setInt    (5, ticket.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      Notification.error(e);
    }
  }

  @Override
  public void delete(Ticket ticket) {
    String sql = "DELETE FROM ticket WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, ticket.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      Notification.error(e);
    }
  }

  @Override
  public Ticket query(Ticket item) {
    return null;
  }

  @Override
  public Ticket query(int itemId) {
    return null;
  }

  @Override
  public Ticket queryByCode(String code) {
    String sql = "SELECT *" +
        "FROM ticket " +
        "LEFT JOIN (" +
        "SELECT tour.id AS tourId, tour.code AS tourCode, tour.name AS tourName, train.code AS trainCode " +
        "FROM tour " +
        "LEFT JOIN train ON tour.trainId = train.id " +
        ") AS tour_train ON ticket.tourId = tour_train.tourId " +
        "WHERE code = ?";
    Ticket found = null;

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, code);
      ResultSet cursor = statement.executeQuery();
      if (cursor.next()) {
        found = new Ticket(
            cursor.getInt   ("id"),
            cursor.getString("code"),
            cursor.getString("name"),
            cursor.getString("tourCode"),
            cursor.getString("tourName"),
            cursor.getString("trainCode"),
            cursor.getDate  ("createdAt"),
            cursor.getFloat ("price")
        );
      }
    } catch (SQLException e) {
      Notification.error(e);
    }

    return found;
  }

  @Override
  public List<Ticket> queryAll() {
    String sql = "SELECT *" +
        "FROM ticket " +
        "LEFT JOIN (" +
        "SELECT tour.id AS tourId, tour.code AS tourCode, tour.name AS tourName, train.code AS trainCode " +
        "FROM tour " +
        "LEFT JOIN train ON tour.trainId = train.id " +
        ") AS tour_train ON ticket.tourId = tour_train.tourId";
    List<Ticket> tickets = new ArrayList<>();

    try ( PreparedStatement statement = connection.prepareStatement(sql);
          ResultSet cursor = statement.executeQuery()) {

      Ticket ticket;
      while (cursor.next()) {
        ticket = new Ticket(
            cursor.getInt   ("id"),
            cursor.getString("code"),
            cursor.getString("name"),
            cursor.getString("tourCode"),
            cursor.getString("tourName"),
            cursor.getString("trainCode"),
            cursor.getDate  ("createdAt"),
            cursor.getFloat ("price")
        );

        ticket.setCustomer(ticket.new Customer(
            cursor.getString("firstName"),
            cursor.getString("lastName"),
            cursor.getString("phoneNumber"),
            cursor.getString("permanentAddress")
        ));

        tickets.add(ticket);
      }
    } catch (SQLException e) {
      Notification.error(e);
    }

    return tickets;
  }

  private int idByCode(String tableName, String code) {
    String sql = String.format("SELECT * FROM tour WHERE code = ?", tableName);
    int id = -1;

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, code);
      ResultSet cursor = statement.executeQuery();

      if (cursor.next()) {
        id = cursor.getInt("id");
      }
    } catch (SQLException e) {
      Notification.error(e);
    }

    return id;
  }

  private float priceByTourId(int tourId) {
    String sql =
        "SELECT tourId_price.price " +
        "FROM ticket " +
        "LEFT JOIN (" +
        "SELECT tour.id AS tourId, train.seatPrice AS price " +
        "FROM tour " +
        "LEFT JOIN train ON tour.trainId = train.id" +
        ") AS tourId_price ON tourId_price.tourId = ?";
    float price = 0;

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, tourId);
      ResultSet cursor = statement.executeQuery();

      if (cursor.next()) {
        price = cursor.getFloat("price");
      }
    } catch (SQLException e) {
      Notification.error(e);
    }

    return price;
  }
}
