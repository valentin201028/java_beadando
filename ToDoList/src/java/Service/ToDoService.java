
package Service;

import Modell.ToDoTask;
import Modell.Database;
import java.util.List;
import java.util.Objects;


public class ToDoService {
    
    
    //Pédányosítás függvény
    
    public ToDoTask newOne(Integer id,String startT, String endT, Boolean urgent, Boolean important, String title, String text)
    {
        ToDoTask newTask = new ToDoTask(id, startT, endT, urgent, important, title, text);
        return newTask;
    }
    
    //Új példány hozzáadása az adatbázishoz
    public void AddToDb(Integer id,String startT, String endT, Boolean urgent, Boolean important, String title, String text)
    {
        
         Database.addToDB(newOne(id, startT, endT, urgent, important, title, text));
        
    }
    //Adatbázis elemeinek a lekérése (itt return-ölhetné egyből a Database.getToDoDB()-t)
    
    public List<ToDoTask> showTasks()
    {
        List<ToDoTask> lista = Database.getToDoDB();
        return lista;
    }
    
    //Lekéri az adatbázis méretét (hány példány van benne?)
    public Integer dbLenght(){
        List<ToDoTask> dbList = Database.getToDoDB();
        return dbList.size();
    }
    
    /*
    A megadott id-jű példányt megkeresi az adatbázisban, majd átírja a done értéket true-ra
    (Nem tudom, hogy hogyan nézhet ki ez így. Rákattintottam oldalt a segítségre, majd átírta erre :D )
    */
    public void setDone(Integer id){
        Database.getToDoDB().stream().filter((t) -> (Objects.equals(t.getId(), id))).forEachOrdered((t) -> {
            t.setDone();
        });
        
    }
    
}
