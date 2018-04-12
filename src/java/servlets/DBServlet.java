package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DB;

/**
 *
 * @author Drazen
 */
public class DBServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    Statement stmt = null;
    Connection con = null;
    String poruka = "";
    try {

      con = DB.getInstance().getConnection();
      if (con == null) {
        request.setAttribute("message", "Pokusajte kasnije");
        request.getRequestDispatcher("error.jsp").forward(request, response);
        return;
      }
      stmt = con.createStatement();
      try {
        stmt.executeUpdate("drop table Ispiti;");
      } catch (SQLException e) {
      } finally {
        stmt.close();
      }

      stmt = con.createStatement();
      try {
        stmt.executeUpdate("drop table Student;");
      } catch (SQLException e) {
      } finally {
        stmt.close();
      }

      stmt = con.createStatement();
      String query = "create table Student ("
              + "indeks INTEGER PRIMARY KEY,"
              + "ime text(15),"
              + "prezime text(15),"
              + "godina INTEGER);";

      stmt.executeUpdate(query);
      query = "create table Ispiti ("
              + "IDisp INTEGER auto_increment PRIMARY KEY,"
              + "indeks INTEGER NOT NULL,"
              + "sifra text(8),"
              + "ocena INTEGER NOT NULL,"
              + "datum DATE,"
              + "FOREIGN KEY (indeks) references Student(indeks));";
      stmt.executeUpdate(query);

      query = "insert into Student (indeks, ime, prezime, godina) values ";
      stmt.executeUpdate(query + "(20110123,'Drazen','Draskovic',4);");
      stmt.executeUpdate(query + "(20120321,'Sanja','Delcev',3);");
      stmt.close();

      PreparedStatement ps = con.prepareStatement("insert into Ispiti (indeks, sifra, ocena, datum) values (?,?,?,?);");
      ps.setInt(1, 20110123);
      ps.setString(2, "SI3AR1");
      ps.setInt(3, 7);
      ps.setDate(4, Date.valueOf("2015-05-15"));
      ps.executeUpdate();
      ps.setString(2, "SI4PIA");
      ps.setInt(3, 9);
      ps.setDate(4, Date.valueOf("2016-06-23"));
      ps.executeUpdate();
      ps.setString(2, "SI3OS2");
      ps.setInt(3, 10);
      ps.setDate(4, Date.valueOf("2015-09-15"));
      ps.executeUpdate();
      ps.setString(2, "SI4MIPS");
      ps.setInt(3, 8);
      ps.setDate(4, Date.valueOf("2016-01-20"));
      ps.executeUpdate();

      ps.setInt(1, 20120321);
      ps.setString(2, "SI2RM1");
      ps.setInt(3, 10);
      ps.setDate(4, Date.valueOf("2014-06-16"));
      ps.executeUpdate();
      ps.setString(2, "SI3ROI");
      ps.setInt(3, 9);
      ps.setDate(4, Date.valueOf("2015-06-20"));
      ps.executeUpdate();
      ps.setString(2, "SI3KDP");
      ps.setInt(3, 10);
      ps.setDate(4, Date.valueOf("2015-06-22"));
      ps.executeUpdate();

      ps.close();

      DB.getInstance().putConnection(con);
      request.getRequestDispatcher("StartServlet").forward(request, response);
    } catch (SQLException sqle) {
      poruka = sqle.getLocalizedMessage();
      DB.getInstance().putConnection(con);
      request.setAttribute("message", poruka);
      request.getRequestDispatcher("error.jsp").forward(request, response);
      sqle.printStackTrace();
    }
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
