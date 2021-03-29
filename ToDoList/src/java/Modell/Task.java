
package Modell;

/*
Task osztály
Csak getterek, setterekre nincs szükség, sőt nem is szabad utólag settelni.
*/
public class Task {
    protected Integer id;
    protected String title;
    protected String text;
    
    public Task(Integer id, String title, String text){
        this.id = id;
        this.title = title;
        this.text = text;
    }
    
    
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

  
}
