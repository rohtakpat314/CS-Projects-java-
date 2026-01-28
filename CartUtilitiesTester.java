/*
 * Author: Rohtak Patwardhan
 * Citations: No help given or received. 
 */

/**
 * Contains testing methods for each method in CartUtilities.
 * Each testing method returns true when all its test cases pass, otherwise false.
 */
public class CartUtilitiesTester {

  static String[][] cart = {{"pineapple", "1"}, {"lettuce", "3"}, 
      {"orange", "5"}, {"null", "null"}, {"null", "null"}};

  static int cartSize = 3;

  static String description = "lettuce";

  static boolean allPass = true; 

  static String[] inventory = {"banana", "lettuce", "orange"};

  static int[] costs = {1, 2, 4};

  // no need for testing method header comments as these are self-explanatory.
  public static boolean testIndexOfItem() {
    if (CartUtilities.indexOfItem(cart, cartSize, description) != 1) {
      System.out.println("OOPS: expected index to be 1 " + "but it was " + 
          CartUtilities.indexOfItem(cart, cartSize, description));

      allPass = false; // wrong index
    }

    if (CartUtilities.indexOfItem(cart, cartSize, "orangutan") != -1) {
      System.out.println("OOPS: expected index to be -1 since it's not present " + "but it was " 
          + CartUtilities.indexOfItem(cart, cartSize, "orangutan"));

      allPass = false; // wrong index
    }
    return allPass;
  }

  public static boolean testAddItemToCart() {
    if (CartUtilities.addItemToCart(cart, cartSize, "jello") != 4) {
      System.out.println("OOPS: expected cart size to be 4 " + "but it was " 
          + CartUtilities.indexOfItem(cart, cartSize, description));
      allPass = false; // wrong index
    }

    if (CartUtilities.addItemToCart(cart, cartSize, "orange") != 3) {
      System.out.println("OOPS: expected cart size to be 3 " + "but it was " 
          + CartUtilities.indexOfItem(cart, cartSize, description));
      allPass = false; // wrong index
    }
    return allPass;
  }

  public static boolean testRemoveItemFromCart() {
    if (CartUtilities.removeItemFromCart(cart, cartSize, 1) != 2) {
      System.out.println("OOPS: expected cart size to be 2 " + "but it was " 
          + CartUtilities.indexOfItem(cart, cartSize, description));
      allPass = false; // wrong index
    }

    if (CartUtilities.removeItemFromCart(cart, cartSize, 5) != 3) {
      System.out.println("OOPS: expected cart size to be 3 " 
          + "but it was " + CartUtilities.indexOfItem(cart, cartSize, description));
      allPass = false; // wrong index
    }
    return allPass;
  }

  public static boolean testGetCostOfItem() {
    if (CartUtilities.getCostOfItem(inventory, costs, "orange") != 4) { 
      System.out.println("OOPS: expected orange cost to be 4 " + "but it was " 
          + CartUtilities.getCostOfItem(inventory, costs, "orange"));
      allPass = false; 
    }

    if (CartUtilities.getCostOfItem(inventory, costs, "peach") != -1) { 
      System.out.println("OOPS: expected peach cost to be -1 " + "but it was " 
          + CartUtilities.getCostOfItem(inventory, costs, "orange"));
      allPass = false; 
    }
    return allPass;
  }

  public static boolean testGetTotalCost() {
    // TODO: test a case where all items are present in inventory and have quantity 1
    String[][] cart1 = {{"apple", "1"}, {"pear", "1"}};
    String[] inventory1 = {"apple", "pear"};
    int[] costs1 = {2, 2};
    int cart1Size = 2;
    if (CartUtilities.getTotalCost(cart1, cart1Size, inventory1, costs1) != 4) { 
      System.out.println("OOPS: expected total cost was 4 " + "but it was " 
          + CartUtilities.getCostOfItem(inventory, costs, "orange"));
      allPass = false; 
    }

    // TODO: test a case where at least one item is not present
    String[][] cart2 = {{"apple", "1"}, {"pear", "1"}};
    String[] inventory2 = {"apple"};
    int[] costs2 = {2, 2};
    int cart2Size = 2;
    if (CartUtilities.getTotalCost(cart2, cart2Size, inventory2, costs2) != 2) { 
      System.out.println("OOPS: expected total cost was 2 " + "but it was " + 
          CartUtilities.getCostOfItem(inventory, costs, "orange"));
      allPass = false; 
    }

    // TODO: test a case where at least one item has quantity > 1
    String[][] cart3 = {{"apple", "4"}, {"pear", "9"}};
    String[] inventory3 = {"apple", "pear"};
    int[] costs3 = {2, 2};
    int cart3Size = 2;
    if (CartUtilities.getTotalCost(cart3, cart3Size, inventory3, costs3) != 26) { 
      System.out.println("OOPS: expected total cost was 26 " + "but it was " 
          + CartUtilities.getCostOfItem(inventory, costs, "orange"));
      allPass = false; 
    }
    return allPass;
  }

  public static void main(String[] args) {
    System.out.println("=== CART UTILITIES TESTER ===");

    boolean allPass = true, testPass = true;

    System.out.println("testIndexOfItem():");
    testPass = testIndexOfItem();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    System.out.println("testAddItemToCart():");
    testPass = testAddItemToCart();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    System.out.println("testRemoveItemFromCart():");
    testPass = testRemoveItemFromCart();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    System.out.println("testGetCostOfItem():");
    testPass = testGetCostOfItem();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    System.out.println("testGetTotalCost():");
    testPass = testGetTotalCost();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    if (allPass) {
      System.out.println("\nCONGRATULATIONS! All of your tests passed.");
    }
  }

}
