package study.nhatha.swd.menu;

import java.util.HashMap;
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
    this.optionToAction = new HashMap<>();
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
    show();
    System.out.print("Your choice: ");

    int optionIndex = Integer.parseInt(keyboard.nextLine());
    optionToAction.get(
        String.valueOf(optionIndex)
    ).doMenu();
  }

  private void printName() {
    System.out.println(this.name);
  }

  private void printOptions() {
    optionToAction.keySet().forEach(System.out::println);
  }

  @FunctionalInterface
  public interface Action {
    void doMenu();
  }
}
