import javax.naming.spi.DirectoryManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Gio on 04/01/2017.
 */
public class DinamicaGioco {
    private Giocatore giocatore1,giocatore2;
    private boolean vincitore1, vincitore2;
    private PreparazioneGioco pr;
    private final int COLPITO = 1;
    private final int ACQUA = 0;
    private final int RIPROVARE= 2;
    private boolean controPc;
    private boolean continua = true;
    private boolean continuaPc = true;




    public DinamicaGioco(){

        //creo giocatori

        this.pr = new PreparazioneGioco();
        this.giocatore1 = pr.getGiocatore1();
        this.giocatore2 = pr.getGiocatore2();
        this.vincitore1 = giocatore1.vinto();
        this.vincitore2 = giocatore2.vinto();
        this.controPc = false;

        pr.creazioneMappa();
        pr.creazioneFlotta();
        giocatore1.setMosseGiocatore();
        giocatore2.setMosseGiocatore();
        inizioGioco();





    }

    private void inizioGioco() {
        controPc();
        while (!vincitore1 || !vincitore2) {
            continua = true;
            mossa(giocatore1);

            if(controPc){
                continua = true;
                mossaPc(giocatore2);
            }else{
                continua = true;
                mossa(giocatore2);
            }


        }
        winnerIs();

    }

    private void mossaPc(Giocatore giocatore2) {
        while(continua) {
            Random rd = new Random();
            String casella = String.valueOf((char) ('A' + rd.nextInt(giocatore2.getMappa().getLunghezza()))) + (rd.nextInt(giocatore2.getMappa().getAltezza()) + 1);
            controlloMossa(giocatore2, casella);
        }
    }

    private void controPc() {
        System.out.print("GIOCATORE CONTRO PC??  y/n");
        String s = new Scanner(System.in).nextLine();
        if(s.equalsIgnoreCase("y")){
            controPc = true;
        }
    }

    private void mossa(Giocatore g) {

        while(continua) {

            System.out.print("#####   TURNO DI " + g.getNome() + "##### \n\n Inserire coordinata :\n\n");
            String coordinata;
            coordinata = new Scanner(System.in).nextLine();
            if(coordinata.equalsIgnoreCase("mappa")){
                g.getMappa().stampaMappa();
            }
            coordinata = coordinata.toUpperCase();
            controlloMossa(g,coordinata);

        }

    }

    private void controlloMossa(Giocatore g, String coordinata) {
        switch (g.getMosseGiocatore().azioneMossa(coordinata)) {
            case COLPITO:
                System.out.print("#### COLPITO #### \n");
                g.getMappa().stampaMappa();

                break;
            case ACQUA:
                System.out.print("#### ACQUA #### \n");
                g.getMappa().stampaMappa();
                continua = false;
                break;
            case RIPROVARE:
                System.out.println("#### RIPROVARE #####");
                g.getMappa().stampaMappa();
                break;
            default:
                continua = true;

        }
        if(controlloVittoria(g)){
            winnerIs();
        }
    }

    private boolean controlloVittoria(Giocatore g) {
        MappaDiGioco m = g.getMappa();
        Casella[][] c = m.getCaselle();
        for(int i = 0; i<m.getLunghezza();i++){
            for(int j = 0;j<m.getAltezza();j++){
                if(c[i][j].getPresenzaNave() && !c[i][j].getColpito()){
                    return false;
                }
            }
        }
        g.setVincitore(true);
        return true;
    }


    private void winnerIs() {
        if(vincitore1){
            System.out.print("************ HA VINTO IL GIOCATORE " + giocatore1.getNome() + " **********");
        }else{

            System.out.print("************ HA VINTO IL GIOCATORE " + giocatore2.getNome() + "**********");
        }
 System.exit(1);
    }


}














