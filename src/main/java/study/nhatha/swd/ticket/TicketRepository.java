package study.nhatha.swd.ticket;

import study.nhatha.swd.generic.Repository;
import study.nhatha.swd.util.Database;
import study.nhatha.swd.util.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static study.nhatha.swd.builder.SqlKeyword.*;

public class TicketRepository implements Repository<Ticket> {
  private static String columns = Stream.of(
      "code",
      "name",
      "tourCode",
      "tourName",
      "trainCode",
      "seatCode",
      "price",
      "firstName",
      "lastName",
      "phoneNumber",
      "permanentAddress").collect(Collectors.joining(COMMA));
  private static String valuesToUpdate = Stream.of(
      "code = ?",
      "name = ?",
      "tourCode = ?",
      "tourName = ?",
      "trainCode = ?",
      "seatCode = ?",
      "price = ?",
      "firstName = ?",
      "lastName = ?",
      "phoneNumber = ?",
      "permanentAddress = ?"
  ).collect(Collectors.joining(COMMA));
  private Connection connection;

  public TicketRepository() {
    this.connection = Database.getConnection();
  }

  @Override
  public void add(Ticket ticket) {
    String values = Sql.makeJoin(QUESTION_MARK, 11, COMMA);

    StringBuilder sql = new StringBuilder();
    sql.append("INSERT INTO ticket");
    sql.append(ROUND_BRACKET_OPEN);
    sql.append(columns);
    sql.append(ROUND_BRACKET_CLOSE);
    sql.append("VALUES");
    sql.append(ROUND_BRACKET_OPEN);
    sql.append(values);
    sql.append(ROUND_BRACKET_CLOSE);
    try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
      statement.setString(1, ticket.getCode());
      statement.setString(2, ticket.getName());
      statement.setString(3, ticket.getTourCode());
      statement.setString(4, ticket.getTourName());
      statement.setString(5, ticket.getTrainCode());
      statement.setString(6, ticket.getSeatCode());
      statement.setFloat(7, ticket.getPrice());
      statement.setString(8, ticket.getCustomer().getFirstName());
      statement.setString(9, ticket.getCustomer().getLastName());
      statement.setString(10, ticket.getCustomer().getPhoneNumber());
      statement.setString(11, ticket.getCustomer().getPermanentAddress());

      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void add(Iterable<Ticket> tickets) {
    tickets.forEach(this::add);
  }

  @Override
  public void update(Ticket ticket) {
    StringBuilder sql = new StringBuilder();

    sql.append("UPDATE ticket SET");
    sql.append(SPACE);
    sql.append(valuesToUpdate);
    sql.append("WHERE");
    sql.append(SPACE);
    sql.append("id = ?");

    try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
      statement.setString(1, ticket.getCode());
      statement.setString(2, ticket.getName());
      statement.setString(3, ticket.getTourCode());
      statement.setString(4, ticket.getTourName());
      statement.setString(5, ticket.getTrainCode());
      statement.setString(6, ticket.getSeatCode());
      statement.setFloat(7, ticket.getPrice());
      statement.setString(8, ticket.getCustomer().getFirstName());
      statement.setString(9, ticket.getCustomer().getLastName());
      statement.setString(10, ticket.getCustomer().getPhoneNumber());
      statement.setString(11, ticket.getCustomer().getPermanentAddress());
      statement.setInt(12, ticket.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Ticket ticket) {
    String sql = "DELETE FROM ticket WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, ticket.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Ticket> query() {

    return null;
  }

  @Override
  public List<Ticket> queryAll() {
    String sql = "SELECT * FROM ticket";
    List<Ticket> tickets = new ArrayList<>();

    try ( PreparedStatement statement = connection.prepareStatement(sql);
          ResultSet cursor = statement.executeQuery()) {

      Ticket ticket;
      while (cursor.next()) {
        ticket = new Ticket(
            cursor.getInt("id"),
            cursor.getString("code"),
            cursor.getString("name"),
            cursor.getString("tourCode"),
            cursor.getString("tourName"),
            cursor.getString("trainCode"),
            cursor.getString("seatCode"),
            cursor.getDate("createdAt"),
            cursor.getFloat("price")
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
      e.printStackTrace();
    }

    return tickets;
  }
}
