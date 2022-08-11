import java.io.*;
import java.util.List;
import java.util.ArrayList;

/*
   Deals with Saving/Loading data
   Methods are mostly static
 */
public class Storage {
    private static final String FILENAME = "SavedData/SaveData.txt";

    public static void Save(List<Task> saveItems) {
        try {
            FileOutputStream fos = new FileOutputStream(FILENAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(saveItems);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot save, File not found");
        } catch (IOException e) {
            System.out.println("Cannot Initialize Stream");
        }

    }

    public static List<Task> ReadItems() {
        try {

            FileInputStream fis = new FileInputStream(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Task> readItems = (ArrayList<Task>) ois.readObject();
            ois.close();
            return readItems;



        } catch (FileNotFoundException e) {
            return new ArrayList<>(); //empty Task list for initial initialization
        } catch (IOException e) {
            System.out.println("Cannot Initialize Stream");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            e.printStackTrace();
        }
        return null;
    }

    public static TaskList load() {
        List<Task> currItems = ReadItems();
        return new TaskList(currItems);

    }

}
