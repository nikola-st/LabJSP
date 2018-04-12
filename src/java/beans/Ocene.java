package beans;

import java.sql.Date;

/**
 *
 * @author Drazen
 */
public class Ocene {

  private String ime;
  private String prezime;
  private String sifra;
  private int ocena;
  private Date datum;

  public Ocene(String ime, String prezime, String sifra, int ocena, Date datum) {
    this.ime = ime;
    this.prezime = prezime;
    this.sifra = sifra;
    this.ocena = ocena;
    this.datum = datum;
  }

  public String getIme() {
    return ime;
  }

  public void setIme(String ime) {
    this.ime = ime;
  }

  public String getPrezime() {
    return prezime;
  }

  public void setPrezime(String prezime) {
    this.prezime = prezime;
  }

  public String getSifra() {
    return sifra;
  }

  public void setSifra(String sifra) {
    this.sifra = sifra;
  }

  public int getOcena() {
    return ocena;
  }

  public void setOcena(int ocena) {
    this.ocena = ocena;
  }

  public Date getDatum() {
    return datum;
  }

  public void setDatum(Date datum) {
    this.datum = datum;
  }
}
