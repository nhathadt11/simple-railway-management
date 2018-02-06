package study.nhatha.swd.menu;

import study.nhatha.swd.console.Inputer;
import study.nhatha.swd.console.Printer;
import study.nhatha.swd.util.Strings;

import java.util.Map;

public class SimpleMenu implements Menu {
  private static final String DECORATION = "---------------------";
  private String name;
  private Map<String, Menu.Action> optionToAction;

  private SimpleMenu() {
  }

  public SimpleMenu(String name, Map<String, Menu.Action> optionToAction) {
    this();
    this.name = name;
    this.optionToAction = optionToAction;
  }

  private void show() {
    printName();
    printOptions();
  }

  public void doMenu() {
    int selected;
    while (true) {
      show();
      selected = selectOption();

      if (selected == -1) return;

      doWithOption(selected);
    }
  }

  private void doWithOption(int optionIndex) {
    ((Menu.Action)
        optionToAction
        .values()
        .toArray()[optionIndex]
    ).doAction();
  }

  private int selectOption() {
    return Inputer.requestInt("Your choice (0 to quit): ") - 1;
  }

  private void printName() {
    Printer.newline(
        Strings.makeJoin(DECORATION, 2, this.name.toUpperCase())
    );
  }

  private void printOptions() {
    optionToAction.keySet().forEach(Printer::newline);
  }
}
