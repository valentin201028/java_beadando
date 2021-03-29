//
package Modell;

/*
Todotask osztály, ami származik a Task osztályból
A startTime és az endTime eredetileg valami dátum/idő típus lett volna, de nem 
sikerült megoldani úgy, hogy egyezzen a bekért adat, és itt a tipus, tehát lett
string.

done = false:
példányostáskor alapértelmezetten false, tehát nincs kész a feladat/task.

urgent, important tulajdonságok csak tájékozató jellegűek.
*/

public class ToDoTask extends Task{
    
    protected String startTime;
    protected String endTime;
    protected Boolean urgent;
    protected Boolean important;
    protected Boolean done;
    
    public ToDoTask(Integer id, String startTime, String endTime, Boolean urgent, Boolean important, String title, String text) {
        super(id, title, text);
        this.startTime = startTime;
        this.endTime = endTime;
        this.urgent = urgent;
        this.important = important;
        this.done = false;
    }
/*
A getterek lekérdezik az értékeket, a setDone pedig készre állítja a done értékét.    
*/
    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Boolean getUrgent() {
        return urgent;
    }

    public Boolean getImportant() {
        return important;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone() {
        this.done = true;
    }
    
}
