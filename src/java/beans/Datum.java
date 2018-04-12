package beans;

import java.util.Calendar;

/**
 *
 * @author Drazen
 */
/*
 Pomocna klasa za odredjivanje podrazumevanih vrednosti pri izboru
 datuma na stranici start.jsp. Nema set metode.
 */
public class Datum {

  private String dan1 = "1";
  private String mesec1;
  private String godina1;
  private String dan2;
  private String mesec2;
  private String godina2;

  public Datum() {
    Calendar c = Calendar.getInstance();
    dan2 = Integer.toString(c.get(Calendar.DATE));
    mesec1 = mesec2 = Integer.toString(c.get(Calendar.MONTH) + 1);
    godina1 = godina2 = Integer.toString(c.get(Calendar.YEAR));
  }

  public Datum(String a, String b, String c, String d, String e, String f) {
    dan1 = a;
    mesec1 = b;
    godina1 = c;
    dan2 = d;
    mesec2 = e;
    godina2 = f;
  }

  public String getDan1() {
    return dan1;
  }

  public String getMesec1() {
    return mesec1;
  }

  public String getGodina1() {
    return godina1;
  }

  public String getDan2() {
    return dan2;
  }

  public String getMesec2() {
    return mesec2;
  }

  public String getGodina2() {
    return godina2;
  }

}
