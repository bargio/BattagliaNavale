import java.util.Map;
import java.util.Scanner;

/**
 * Created by Gio on 03/01/2017.
 */
public class PreparazioneGioco {
    private Giocatore giocatore1,giocatore2;
    private  MappaDiGioco mappaGiocoGiocatore1,mappaGiocoGiocatore2;
    private Nave[] flottaG1,flottaG2;
    private final int DIM_DEFAULT = 10;
    Scanner sc;



    public PreparazioneGioco(){
        this.giocatore1 = new Giocatore();
        this.giocatore2 = new Giocatore();
        sc = new Scanner(System.in);



    }




    public void creazioneMappa() {
        stampaMessaggio("Scegli dimensioni Mappa (dimensioni default = 10) \n x:");
        int x = sc.nextInt();
        stampaMessaggio("y: ");
        int y = sc.nextInt();

        this.mappaGiocoGiocatore1 = new MappaDiGioco(x+DIM_DEFAULT,y+DIM_DEFAULT);
        this.mappaGiocoGiocatore2 = new MappaDiGioco(x+DIM_DEFAULT,y+DIM_DEFAULT);
        mappaGiocoGiocatore2.creazioneCaselle();
        mappaGiocoGiocatore1.creazioneCaselle();
        giocatore1.setMappa(mappaGiocoGiocatore1);
        giocatore2.setMappa(mappaGiocoGiocatore2);



    }


    public void creazioneFlotta() {
        stampaMessaggio("Quante navi?");
        int numNavi = sc.nextInt();
        flottaG1 = new Nave[numNavi];
        flottaG2 = new Nave[numNavi];

        for(int i = 0;i<numNavi;i++) {
            stampaMessaggio("Dimensione Nave " + i);
            int dime = sc.nextInt();
            if (controlloDimensioneOpportuna(dime)) {
                flottaG1[i] = new Nave(dime);
                flottaG2[i] = new Nave(dime);
                giocatore1.getMappa().inserimentoNave(flottaG1[i]);
                giocatore2.getMappa().inserimentoNave(flottaG2[i]);
            } else {
                stampaMessaggio("Dimensione non consona \n Scegliere nuova dimensione");
                i--;
            }
        }
    }








    private boolean controlloDimensioneOpportuna(int dime) {
        if(dime<giocatore1.getMappa().getAltezza() || dime<giocatore1.getMappa().getLunghezza()){
            return true;
        }
        return false;

    }

    private void stampaMessaggio(String s){
        System.out.println(s);

    }

    public Giocatore getGiocatore1() {
        return giocatore1;
    }

    public void setGiocatore1(Giocatore giocatore1) {
        this.giocatore1 = giocatore1;
    }

    public Giocatore getGiocatore2() {
        return giocatore2;
    }

    public void setGiocatore2(Giocatore giocatore2) {
        this.giocatore2 = giocatore2;
    }
}
