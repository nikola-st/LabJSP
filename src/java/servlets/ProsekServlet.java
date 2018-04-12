package servlets;

import beans.Student;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DB;

public class ProsekServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    Connection con = null;
    Statement stmt = null;
    String poruka = null;
    String address = "prosek.jsp";
    String ind = request.getParameter("indeks");
    if (ind.isEmpty()) {
      poruka = "Niste uneli indeks!";
    }
    int indeks = 0;
    Student s = new Student();
    s.setIndeks(ind);
    request.setAttribute("student", s);
    try {
      indeks = Integer.parseInt(ind);
    } catch (NumberFormatException nfe) {
      if (poruka == null) {
        poruka = "Indeks mora biti u formatu ggggbbbb!";
      }
      request.setAttribute("messageprosek", poruka);
      request.getRequestDispatcher("start.jsp").forward(request, response);
      return;
    }

    if (indeks < 19500000 || indeks > 20160000) {
      request.setAttribute("messageprosek", "Indeks mora biti u formatu ggggbbbb!");
      request.getRequestDispatcher("start.jsp").forward(request, response);
      return;
    }

    try {
      con = DB.getInstance().getConnection();
      if (con == null) {
        request.setAttribute("message", "Pokusajte kasnije");
        request.getRequestDispatcher("error.jsp").forward(request, response);
        return;
      }

      stmt = con.createStatement();
      String upit = "select ime, prezime, avg (ocena) as prosek from ispiti i, student s "
              + "where i.indeks=" + indeks + " and i.indeks=s.indeks "
              + "group by ime, prezime;";
      ResultSet rs = stmt.executeQuery(upit);
      if (rs.next()) {
        String ime = rs.getString("ime");
        String prezime = rs.getString("prezime");
        s.setIme(ime + " " + prezime);
        String prosek = rs.getString("prosek");
        s.setProsek(prosek);
        request.setAttribute("imastudent", true);
      } else {
        request.setAttribute("message", "Ne postoji student sa indeksom " + s.getIndeks());
      }
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
