
package Service;

//Ez az osztály csak azt vizsgálja, hogy az egyes megadott adatok validak-e.
public class IsValidService {
    
    public String IsTitleValid(String title){
        try {
            String ret = "1";
            if(title.length() < 1){
                ret = "Hiba! A cím nem maradhat üresen.";
            }
            else if(title.contains("@") || title.contains("$")){
                ret = "Hiba! A cím tiltott karaktert tartalmaz.(@,$) ";
            }
            return ret;
        } 
        catch (Exception e) {
            e.toString();
        }
        return "1";
    }
    
    public String IsTextValid(String text){
        try {
            String ret = "1";
            if(text.length() < 1){
                ret = "Hiba! A leírás nem maradhat üresen.";
            }
            else if(text.contains("@") || text.contains("$")){
                ret = "Hiba! A leírás tiltott karaktert tartalmaz.(@,$) ";
            }
            return ret;
        } 
        catch (Exception e) {
            e.toString();
        }
        return "1";
    }
    
    public String IsTimeValid(String startTime, String endTime){
        try {
            String ret = "1";
            startTime = startTime.trim();
            endTime = endTime.trim();
            if(startTime.length() < 1){
                ret = "Hiba! A kezdés ideje nem maradhat teljesen üresen.";
            }
            else if(endTime.length() < 1){
                ret = "Hiba! A befejezés ideje nem maradhat teljesen üresen.";
            }
            return ret;
        } catch (Exception e) {
            e.toString();
        }
        return "1";
    }
    
    
}
