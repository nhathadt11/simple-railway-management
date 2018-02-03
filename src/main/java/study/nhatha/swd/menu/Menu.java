package study.nhatha.swd.menu;

import study.nhatha.swd.console.Printer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
  private String name;
  private Map<String, Action> optionToAction;
  private Scanner keyboard;

  private Menu() {
    this.keyboard = new Scanner(System.in);
  }

  public Menu(String name) {
    this();
    this.name = name;
    this.optionToAction = new LinkedHashMap<>();
  }

  public Menu(String name, Map<String, Action> optionToAction) {
    this();
    this.name = name;
    this.optionToAction = optionToAction;
  }

  private void show() {
    printName();
    printOptions();
  }

  public void doMenu() {
    int userSelectedIndex;
    Action action;
    do {
      show();
      Printer.inline("Your choice (0 to quit): ");

      userSelectedIndex = Integer.parseInt(keyboard.nextLine());
      action = (Action) optionToAction.values().toArray()[userSelectedIndex - 1];

      action.doAction();
    } while (userSelectedIndex != 0);
  }

  private void printName() {
    System.out.println(this.name);
  }

  private void printOptions() {
    optionToAction.keySet().forEach(Printer::newline);
  }

  @FunctionalInterface
  public interface Action {
    void doAction();
  }

  public interface Doable {
    void doMenu();
  }
}
