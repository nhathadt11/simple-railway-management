package study.nhatha.swd.menu;

public interface Menu {
  void doMenu();

  @FunctionalInterface
  interface Action {
    void doAction();
  }
}
