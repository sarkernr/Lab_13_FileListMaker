import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Main
{
    private static boolean needsToBeSaved = false;

    public static void main(String[] args) {
        ArrayList<String> myArrList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        String itemChoice = "";

        do {
            itemChoice = displayMenu(sc, myArrList);
            switch (itemChoice) {
                case "A":
                    addItem(sc, myArrList);
                    break;
                case "D":
                    deleteItem(sc, myArrList);
                    break;
                case "I":
                    insertItem(sc, myArrList);
                    break;
                case "V":
                    viewItems(myArrList);
                    break;
                case "M":
                    moveItem(sc, myArrList);
                    break;
                case "O":
                    openList(sc, myArrList);
                    break;
                case "S":
                    saveList(sc, myArrList);
                    break;
                case "C":
                    clearList(myArrList);
                    break;
                case "Q":
                case "q":
                    if (SafeInput.getYNConfirm(sc, "Are you sure you want to quit?")) {
                        if (needsToBeSaved) {
                            if (SafeInput.getYNConfirm(sc, "You have unsaved changes. Do you want to save before quitting?")) {
                                saveList (sc, myArrList);
                            }
                        }
                        done = true;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (!done);
    }

    private static String displayMenu(Scanner sc, ArrayList<String> itemList) {
        if (itemList.isEmpty()) {
            System.out.println("You have not entered any items!");
        } else {
            System.out.println("This is the current list");
            for (int i = 0; i < itemList.size(); i++) {
                System.out.printf(" %d. %s\n", i + 1, itemList.get(i));
            }
        }
        return SafeInput.getRegExString(sc, "Type one of these letters to select:\n A: Add\n D: Delete\n I: Insert\n M: Move\n V: View\n O: Open\n S: Save\n C: Clear\n Q: Quit\n", "[AaDdIiMmVvOoSsCcQq]").toUpperCase();
    }


    public static void addItem(Scanner sc, ArrayList<String> itemList) {
        String addItem = SafeInput.getNonZeroLenString(sc,"Enter the item to add");
        itemList.add(addItem);
        System.out.println("Item added to the list");
        needsToBeSaved = true;
    }

    public static void deleteItem(Scanner sc, ArrayList<String> itemList) {
        int deleteItem = SafeInput.getRangedInt(sc,"Enter the item number to delete", 1, itemList.size());
        itemList.remove(deleteItem - 1);
        needsToBeSaved = true;
    }

    private static void insertItem(Scanner sc, ArrayList<String> itemList) {
        String insertItem = SafeInput.getNonZeroLenString(sc,"Enter the item to insert");
        int position = SafeInput.getRangedInt(sc,"Enter the position to insert at", 1, itemList.size() + 1) - 1;
        itemList.add(position, insertItem);
        System.out.println("Item inserted.");
        needsToBeSaved = true;
    }

    private static void moveItem(Scanner sc, ArrayList<String> itemList) {
        int moveFrom = SafeInput.getRangedInt(sc, "Enter the item number you want to move", 1, itemList.size()) - 1;
        int moveTo = SafeInput.getRangedInt(sc, "Enter the new position", 1, itemList.size()) - 1;
        String item = itemList.remove(moveFrom);
        itemList.add(moveTo, item);
        System.out.println("Item moved.");
        needsToBeSaved = true;
    }

    private static void openList(Scanner sc, ArrayList<String> itemList) {
        if (needsToBeSaved) {
            if (SafeInput.getYNConfirm(sc, "You have unsaved changes. Do you want to save before opening a new list?")) {
                saveList(sc, itemList);
            }
        }
        System.out.print("Enter the filename to open: ");
        String filename = sc.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            itemList.clear();
            String line;
            while ((line = br.readLine()) != null) {
                itemList.add(line);
            }
            needsToBeSaved = false;
            System.out.println("List loaded from " + filename);
        }
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void saveList(Scanner sc, ArrayList<String> itemList) {
        System.out.print("Enter the filename to save: ");
        String filename = sc.nextLine();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String item : itemList) {
                bw.write(item);
                bw.newLine();
            }
            needsToBeSaved = false;
            System.out.println("List saved to " + filename);
        }
        catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    private static void clearList(ArrayList<String> itemList) {
        itemList.clear();
        needsToBeSaved = true;
        System.out.println("List cleared.");
    }

    private static void viewItems(ArrayList<String> itemList) {
        if (itemList.isEmpty()) {
            System.out.println("You have not entered any items!");
        } else {
            System.out.println("This is the current list");
            for (int i = 0; i < itemList.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, itemList.get(i));
            }
        }
    }

}