
package Modell;

import java.util.List;
import java.util.ArrayList;
/*
Beégetett adatbázis:
ToDoTask példányokat tartalmazó ArrayList
Le lehet kérni az elemeit, és hozzáadni.
A legjobb benne, hogy nincs törlés, viszont ugye a service-ben egy függvény segítségével
át lehet írni a done értéket (ez alapján írja majd ki a képernyőre)
*/

public class Database {
    
    protected static List<ToDoTask> ToDoDB = new ArrayList<ToDoTask>();

    public static List<ToDoTask> getToDoDB() {
        return ToDoDB;
    }

    public static void addToDB(ToDoTask task) {
        ToDoDB.add(task);
    }
    

    
    
    
    
}
