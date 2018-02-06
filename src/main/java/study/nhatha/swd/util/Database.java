package study.nhatha.swd.util;

import study.nhatha.swd.console.Notification;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Database {
  private static final String DB_CONFIG_PATH = "./res/db.properties";
  private static final Properties PROPERTIES = new Properties();
  private static String url;
  private static String username;
  private static String password;

  static {
    try {
      PROPERTIES.load(new FileInputStream(DB_CONFIG_PATH));
      url       = PROPERTIES.getProperty("db.jdbc.url");
      username  = PROPERTIES.getProperty("db.jdbc.username");
      password  = PROPERTIES.getProperty("db.jdbc.password");
    } catch (IOException e) {
      Notification.error(e.getMessage());
    }
  }

  public static Connection getConnection() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      Notification.error(e);
    }
    return connection;
  }

  private Database() {
  }
}
