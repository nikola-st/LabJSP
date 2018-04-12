package servlets;

import beans.Ocena;
import beans.Unos;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.DB;

/**
 *
 * @author Drazen
 */
public class IspitServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    String godina = request.getParameter("godina");
    String broj = request.getParameter("broj");
    String sifra = request.getParameter("sifra");
    int ocena = Integer.parseInt(request.getParameter("ocena"));
    String address = "start.jsp";
    int indeks = 0;
    HttpSession sesija = request.getSession();
    Unos u = new Unos();
    u.setBroj(broj);
    u.setGodina(godina);
    u.setSifra(sifra);
    u.setOcena(ocena);
    sesija.setAttribute("unos", u);

    if (godina.isEmpty() || broj.isEmpty() || sifra.isEmpty()) {
      request.setAttribute("messageisp", "Niste popunili sva polja!!!");
      request.getRequestDispatcher(address).forward(request, response);
      return;
    }
    try {
      int god = Integer.parseInt(godina);
      int br = Integer.parseInt(broj);
      if (god < 10) {
        god += 2000;
      } else if (god < 100) {
        god += 1900;
      }
      indeks = br + 10000 * god;
    } catch (NumberFormatException nfe) {
      request.setAttribute("messageisp", "Broj indeksa i godina upisa moraju biti pravilni!");
      request.getRequestDispatcher(address).forward(request, response);
      return;
    }

    if (indeks < 19500000 || indeks > 20160000) {
      request.setAttribute("messageisp", "Nepravilan broj indeksa!");
      request.getRequestDispatcher(address).forward(request, response);
      return;
    }

    Connection con = null;
    Statement stmt = null;
    String poruka = "";
    address = "ispit.jsp";

    try {
      con = DB.getInstance().getConnection();
      if (con == null) {
        request.setAttribute("message", "Pokusajte kasnije");
        request.getRequestDispatcher("error.jsp").forward(request, response);
      }
      stmt = con.createStatement();

      String upit = "select * from student where indeks=" + indeks + ";";
      ResultSet rs = stmt.executeQuery(upit);
      if (!rs.next()) {
        stmt.close();
        DB.getInstance().putConnection(con);
        request.setAttribute("messageisp", "Ne postoji student sa datim brojem indeksa!");
        request.getRequestDispatcher("start.jsp").forward(request, response);
        return;
      }

      Date datum = new Date(Calendar.getInstance().getTimeInMillis());

      upit = "select ocena from ispiti where indeks=" + indeks + " and sifra='" + sifra + "';";
      rs = stmt.executeQuery(upit);
      if (rs.next()) {
        upit = "update ispiti set ocena=" + ocena + " "
                + " where indeks=" + indeks + " and sifra='" + sifra + "';";
        stmt.executeUpdate(upit);
        upit = "update ispiti set datum='" + datum + "' "
                + " where indeks=" + indeks + " and sifra='" + sifra + "';";
        stmt.executeUpdate(upit);
        request.setAttribute("messageisp", "Ocena je uspesno promenjena.");

      } else {
        upit = "insert into ispiti (indeks, sifra, ocena, datum) values"
                + " (" + indeks + ",'" + sifra + "'," + ocena + ",'" + datum + "');";
        stmt.executeUpdate(upit);
        request.setAttribute("messageisp", "Ocena je uspesno dodata.");
      }

      upit = "select ocena, sifra, datum from ispiti where indeks=" + indeks + " order by ocena desc;";
      rs = stmt.executeQuery(upit);
      List<Ocena> ocene1 = new ArrayList<>();
      int i = 0, j = 0;

      while (rs.next()) {
        Ocena o = new Ocena();
        o.setOcena(rs.getString("ocena"));
        o.setSifra(rs.getString("sifra"));
        o.setDatum(rs.getString("datum"));
        ocene1.add(o);
        if (o.getSifra().equals(sifra)) {
          j = i;
        }
        i++;
      }

      request.setAttribute("ocene1", ocene1);
      request.setAttribute("crveno", Integer.toString(j));
      stmt.close();
      DB.getInstance().putConnection(con);
      sesija.removeAttribute("unos");

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
