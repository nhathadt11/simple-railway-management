package study.nhatha.swd.generic;

import java.util.List;

public interface DataAccess<T> {
  void add(T item);
  void add(Iterable<T> items);
  void update(T item);
  void delete(T item);
  T query(T item);
  T query(int itemId);
  T queryByCode(String code);
  List<T> queryAll();
}
