/**
 * Created by Gio on 02/01/2017.
 */
public class Casella {
    private boolean colpito = false;
    private boolean presenzaNave = false;
    private int cordinataX;
    private int cordinataY;
    private boolean libera;

    public Casella(int x, int y) {
        this.cordinataX = x;
        this.cordinataY = y;
        this.libera = true;
    }
    //se inserisco nave nella casella
    public void inserisciNave(){
        this.presenzaNave= true;
    }
//se casella stata selezionata
    public void colpito(){
        this.colpito = true;

    }

    // vari getter
    public int getCordinataX() {
        return cordinataX;
    }

    public int getCordinataY() {
        return cordinataY;
    }

    public boolean getColpito() {
        return colpito;
    }

    public boolean getPresenzaNave() {
        return presenzaNave;
    }

    public boolean getLibera() {
        return libera;
    }

    public void setLibera(boolean usata) {
        this.libera = usata;
    }
}
