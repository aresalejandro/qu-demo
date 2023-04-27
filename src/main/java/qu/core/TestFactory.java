package qu.core;

import org.testng.annotations.Factory;
import qu.scenarios.*;

import java.util.ArrayList;
import java.util.List;

public class TestFactory {

  private static Object[] scenariosTest;

  @Factory()
  public Object[] factoryMethod() {
    List<Object> testsToRun = new ArrayList<>();

    testsToRun.add(new LoginScenario());
    testsToRun.add(new InvalidLoginScenario());
    testsToRun.add(new EmptyLoginScenario());
    testsToRun.add(new CheckoutFormScenario());
    testsToRun.add(new CheckoutCartScenario());
    testsToRun.add(new GridItemScenario());
    testsToRun.add(new GridAllItemScenario());
    testsToRun.add(new SearchScenario());
    testsToRun.add(new SearchEmptyScenario());

    scenariosTest = testsToRun.toArray();
    return scenariosTest;
  }

}
