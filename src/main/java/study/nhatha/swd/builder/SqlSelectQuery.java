package study.nhatha.swd.builder;

import study.nhatha.swd.util.Args;
import study.nhatha.swd.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static study.nhatha.swd.builder.SqlKeyword.*;

public class SqlSelectQuery implements Query {
  private List<String> fields;
  private String table;
  private List<String> conditions;

  public SqlSelectQuery(SqlSelectQueryBuilder builder) {
    this.table = builder.table;
    this.fields = builder.fields;
    this.conditions = builder.conditions;
  }

  @Override
  public String toQueryString() {
    StringBuilder query = new StringBuilder();

    query.append(selectFragment());
    query.append(NEW_LINE);
    query.append(fromFragment());
    query.append(NEW_LINE);
    query.append(whereFragment());

    return query.toString();
  }

  private String selectFragment() {
    StringBuilder builder = new StringBuilder();

    builder.append(SELECT);
    if (!Util.isNullOrEmpty(fields)) {
      builder.append(
          fields.stream().collect(
            Collectors.joining(COMMA)
          )
      );
    } else {
      builder.append(ALL);
    }
    builder.append(SPACE);

    return builder.toString();
  }

  private String fromFragment() {
    StringBuilder builder = new StringBuilder();

    builder.append(FROM);
    builder.append(SPACE);
    builder.append(table);

    return builder.toString();
  }

  private String whereFragment() {
    StringBuilder builder = new StringBuilder();

    if (!Util.isNullOrEmpty(conditions)) {
      String andWithSpaceAround = SPACE + AND + SPACE;
      builder.append(WHERE);
      builder.append(SPACE);
      builder.append(
          conditions.stream().collect(
              Collectors.joining(andWithSpaceAround)
          )
      );
    }

    return builder.toString();
  }

  public static class SqlSelectQueryBuilder {
    private List<String> fields;
    private String table;
    private List<String> conditions;

    public SqlSelectQueryBuilder select(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public SqlSelectQueryBuilder from(String table) {
      this.table = table;
      return this;
    }

    public SqlSelectQueryBuilder where(List<String> conditions) {
      this.conditions = conditions;
      return this;
    }

    public SqlSelectQueryBuilder where(String condition) {
      if (Util.isNull(conditions)) {
        conditions = new ArrayList<>();
      }

      conditions.add(condition);

      return this;
    }

    public SqlSelectQuery build() {
      Args.requireNonNull(this.table);

      return new SqlSelectQuery(this);
    }
  }
}
