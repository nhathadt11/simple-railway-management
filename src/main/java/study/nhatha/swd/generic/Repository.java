package study.nhatha.swd.generic;

import java.util.List;

public interface Repository<T> {
  void add(T item);
  void add(Iterable<T> items);
  void update(T item);
  void delete(T item);
  List<T> query();
  List<T> queryAll();
}
