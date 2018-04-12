package servlets;

import beans.Datum;
import beans.Ocene;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DB;

/**
 *
 * @author Drazen
 */
public class OceneServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    String dan1 = request.getParameter("dan1");
    String dan2 = request.getParameter("dan2");
    String mesec1 = request.getParameter("mesec1");
    String mesec2 = request.getParameter("mesec2");
    String god1 = request.getParameter("god1");
    String god2 = request.getParameter("god2");
    Datum d = new Datum(dan1, mesec1, god1, dan2, mesec2, god2);
    request.getSession().setAttribute("datum", d);

    String datum1 = god1 + "-" + mesec1 + "-" + dan1;
    String datum2 = god2 + "-" + mesec2 + "-" + dan2;
    Date d1 = null;
    Date d2 = null;
    String address = "start.jsp";

    try {
      d1 = Date.valueOf(datum1);
      d2 = Date.valueOf(datum2);
    } catch (IllegalArgumentException ile) {
      request.setAttribute("messagedat", "Nepravilan format datuma!");
      request.getRequestDispatcher(address).forward(request, response);
    }
    if (d1.after(d2)) {
      request.setAttribute("messagedat", "Prvi datum je posle drugog!");
      request.getRequestDispatcher(address).forward(request, response);
    }

    Connection con = null;
    Statement stmt = null;
    String poruka = "";
    address = "ocene.jsp";

    try {
      con = DB.getInstance().getConnection();
      if (con == null) {
        request.setAttribute("message", "Pokusajte kasnije");
        request.getRequestDispatcher("error.jsp").forward(request, response);
        return;
      }
      stmt = con.createStatement();
      String upit = "select ime, prezime, sifra, ocena, datum from student s, ispiti i "
              + "where s.indeks=i.indeks and i.datum between '" + d1 + "' and '" + d2 + "';";
      ResultSet rs = stmt.executeQuery(upit);
      List<Ocene> ocene = new ArrayList<>();
      while (rs.next()) {
        String ime = rs.getString("ime");
        String prezime = rs.getString("prezime");
        String sifra = rs.getString("sifra");
        int ocena = rs.getInt("ocena");
        Date datum = rs.getDate("datum");
        Ocene o = new Ocene(ime, prezime, sifra, ocena, datum);
        ocene.add(o);
      }

      request.setAttribute("ocene", ocene);
      stmt.close();
      DB.getInstance().putConnection(con);

    } catch (SQLException sqle) {
      poruka = sqle.getLocalizedMessage();
      request.setAttribute("message", poruka);
      address = "error.jsp";
      DB.getInstance().putConnection(con);
    }

    request.getRequestDispatcher(address).forward(request, response);
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   */
  public String getServletInfo() {
    return "Short description";
  }
  // </editor-fold>
}
