package Bank.asm04.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileService {
    public BinaryFileService(){}
    // Read file method
    public static <T> List<T> readFile(String fileName) {
        List<T> objects = new ArrayList<>();
        try (ObjectInputStream file = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    T object = (T) file.readObject();
                    objects.add(object);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (EOFException e){
            return new ArrayList<>();
        } catch (IOException io){
            System.out.println("IO Exception " + io.getMessage());
        } catch (ClassNotFoundException e){
            System.out.println("Class not fount exception " + e.getMessage());
        }
        return objects;
    }

    // Write file method
    public static <T> void writeFile(String fileName, List<T> objects){
        try(ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))){
            for(T object : objects){
                file.writeObject(object);
            }
        } catch (IOException e){
            System.out.println("IO exception " + e.getMessage());
        }
    }
}
