/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modell.ToDoTask;
import Service.IsValidService;
import Service.ToDoService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Valentin
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
     ToDoService srv = new ToDoService();
     IsValidService ivs = new IsValidService();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
           PrintWriter out = response.getWriter();
           if(request.getParameter("task") != null){
                if(request.getParameter("task").equals("addTask")){
                    //adatok lekérdezése a javascript-től
                    Boolean urgent;
                    Boolean important;
                    String title = request.getParameter("title");
                    String text = request.getParameter("text");
                    String urg = request.getParameter("urgent");
                    urgent = "true".equals(urg);
                    String imp = request.getParameter("important");
                    important = "true".equals(imp);
                    String sd = request.getParameter("startDate");
                    String st = request.getParameter("startTime");
                    String ed = request.getParameter("endDate");
                    String et = request.getParameter("endTime");
                  
                    String startDate = sd + " " + st;
                    String endDate = ed + " " + et;
                    
                    //Itt vizsgálja meg, hogy validak-e a beírt értékek
                    if(!"1".equals(ivs.IsTitleValid(title)) ){
                        out.write(ivs.IsTitleValid(title));
                        throw new Exception();
                    }
                    else if(!"1".equals(ivs.IsTextValid(text)) ){
                        out.write(ivs.IsTextValid(text));
                        throw new Exception();
                    }
                    else if(!"1".equals(ivs.IsTimeValid(startDate, endDate)) ){
                        out.write(ivs.IsTimeValid(startDate, endDate));
                        throw new Exception();
                    }
                    
                    
                    /*
                    Ugye az adatbázisban nincs törlés (csak logikai: done-> true),
                    ezért működőkékes az, hogy ugye egyre több elem lesz mindig az adatbázisban
                    (de legalábbis kevesebb biztos nem), tehát az egyes task-ek id-je megegyezik
                    az adatbázis méretével, ezáltal minden id egyedi lesz.
                    */
                    Integer id = srv.dbLenght();
                    
                    //Példány hozzáadása az adatbázishoz
                    srv.AddToDb(id, startDate, endDate, urgent, important, title, text);
                }
                if(request.getParameter("task").equals("showTasks")){
                   String res=" ";
                   /*
                   A showTasksm visszaadja a ToDoTask listát, majd ezt bejárva lekéri az egyes 
                   példányok értékeit, berakja őket egy stringbe, úgy, hogy közben határoló
                   karaktereket tesz közé a könnyebb szétdarabolás érdekében, majd a kész
                   stringet küldi a javascriptnek vissza.
                   Ezt eredetileg JSON-nel terveztem, úgy lett volna szép, de nem sikerült megoldni.
                   Probléma elkerülése érdekében a szöveg és a cím nem tartalmazhatja ezeket a karaktereket.
                   */
                    List<ToDoTask> list = srv.showTasks();
                    for(ToDoTask t: list){
                        res += t.getId()+ "@" + t.getTitle() + "@" + t.getText() + "@" + t.getUrgent() + "@" + t.getImportant() + "@" + t.getStartTime() + "@" + t.getEndTime() + "@" + t.isDone()+ "$";
                    }
                    out.write(res);
                }
                
                
                if(request.getParameter("task").equals("setDone")){
                    String i = request.getParameter("ID");
                    i = i.trim(); 
                    Integer id = Integer.parseInt(i);
                    
                    //setdone metodus meghívásával true-ra állírja az átadott id-jű példánynak a tulajdonságát.
                    
                    srv.setDone(id);
                    out.write(id);
                    }
                 
           }
           
           
        }catch(Exception ex){
            ex.toString();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
