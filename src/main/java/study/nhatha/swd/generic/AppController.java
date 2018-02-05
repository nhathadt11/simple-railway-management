package study.nhatha.swd.generic;

import study.nhatha.swd.menu.Menu;

public interface AppController extends Menu {
  void add();
  void addMany();
  void update();
  void delete();
  void find();
  void all();
}
