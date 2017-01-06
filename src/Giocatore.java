import java.util.Scanner;

/**
 * Created by Gio on 04/01/2017.
 */
public class Giocatore {
   private  MosseGiocatore mosseGiocatore;
   private  MappaDiGioco mappaGiocoGiocatore;
   private boolean vincitore;
   private String nome;
    public Giocatore(){
        nomeGiocatore();
        this.vincitore = false;
    }

    private void nomeGiocatore() {
        System.out.println("Nome Giocatore: ");
        this.nome = new Scanner(System.in).nextLine();


    }

    public void setVincitore(boolean vincitore) {
        this.vincitore = vincitore;
    }

    public void setMappa(MappaDiGioco m){
        this.mappaGiocoGiocatore = m;


    }
    public MappaDiGioco getMappa(){
        return mappaGiocoGiocatore;


    }
    public void setMosseGiocatore(){
        this.mosseGiocatore = new MosseGiocatore(mappaGiocoGiocatore);
    }
    public MosseGiocatore getMosseGiocatore(){
         return mosseGiocatore;
    }


    public String getNome() {
        return nome;
    }

    public boolean vinto() {
        return vincitore;


    }
}
