import java.util.Random;

/**
 * Created by Gio on 03/01/2017.
 */
public class MappaDiGioco {


    //attributi dimensione mappa
    private int lunghezza;
    private int altezza;
    private Casella mappa[][];
    private Random rd;
    //direzioni
    private final int NORD = 0;
    private final int SUD = 1;
    private final int EST = 2;
    private final int OVEST = 3;
    private int maxNavi;

    private final int TOT_DIREZIONI = 4;
    private final int MAX_NAVI = 5;
    public MappaDiGioco(int lunghezza, int altezza) {
        this.lunghezza = lunghezza;
        this.altezza = altezza;
        this.mappa = new Casella[lunghezza][altezza];
        this.rd = new Random();
        this.maxNavi =0;


    }

    public void inserimentoNave(Nave n) {

        ricercaCaselleDisponibili(n);
    }

    public void creazioneCaselle() {
        for(int i= 0;i<lunghezza;i++){
            for(int j= 0;j<altezza;j++) {
            mappa[i][j]= new Casella(i,j);
            }
        }
    }

    private void ricercaCaselleDisponibili(Nave n) {
        boolean casellaLibera = false;
        boolean inserita = false;
        maxNavi++;
        int dimensioneNave = n.getDimensione();
        while (!casellaLibera && maxNavi <=MAX_NAVI) {
            int partenzaX = rd.nextInt(lunghezza);
            int partenzaY = rd.nextInt(altezza);
            int maxRicercaDirezione = 0;
            if (!mappa[partenzaX][partenzaY].getPresenzaNave()) {
                while (!inserita && maxRicercaDirezione < TOT_DIREZIONI) {
                    int direzione = rd.nextInt(TOT_DIREZIONI);
                    maxRicercaDirezione++;
                    if (controlloSpazioOccupatoDaNave(dimensioneNave, direzione, partenzaX, partenzaY)) {
                        inseriscoNave(dimensioneNave, direzione, partenzaX, partenzaY,n);
                        inserita = true;
                        casellaLibera = true;
                    }
                }
            }
        }
        if(maxNavi == MAX_NAVI){
            String messaggioMaxNavi = "Massimo navi raggiunto";
            stampaMessaggio(messaggioMaxNavi);
        }

    }


    private void inseriscoNave(int dimensioneNave, int direzione, int partenzaX, int partenzaY, Nave n) {
        int segno = 1;
        int asseX = 0;
        int asseY = 0;

        //per muovermi sull'asse x nel caso sia EST o OVEST

        if (direzione == NORD || direzione == OVEST) {
            segno = -1;
        }

        if (direzione == EST || direzione == OVEST) {
            asseX = 1;

        }
        if (direzione == NORD || direzione == SUD) {
            asseY = 1;

        }
        for (int i = dimensioneNave; i > 0; i--) {
                int puntoAsseX = (i * asseX * segno);
                int puntoAsseY = (i * asseY * segno);
                puntoAsseX = partenzaX + puntoAsseX;
                puntoAsseY = partenzaY + puntoAsseY;
                mappa[puntoAsseX][puntoAsseY].inserisciNave();
                n.insNomeCaselleNave(puntoAsseX,puntoAsseY);





            }
        }




    private boolean controlloSpazioOccupatoDaNave(int dimensioneNave, int direzione, int partenzaX, int partenzaY) {
        int segno = 1;
        int asseX = 0;
        int asseY = 0;
        int spazioDiCoordinateOccupate = 0;
        //per muovermi sull'asse x nel caso sia EST o OVEST

        if (direzione == NORD || direzione == OVEST) {
            segno = -1;
        }

        if (direzione == EST || direzione == OVEST) {
            asseX = 1;
            spazioDiCoordinateOccupate = (partenzaX + dimensioneNave);
        }
        if (direzione == NORD || direzione == SUD) {
            asseY = 1;
            spazioDiCoordinateOccupate = (partenzaY + dimensioneNave);
        }
        if (spazioDiCoordinateOccupate >= 0 && (spazioDiCoordinateOccupate < lunghezza || spazioDiCoordinateOccupate < altezza)) {

            for (int i = dimensioneNave; i >= 0; i--) {
                for (int j = -1; j <= 1; j++) {
                    int puntoAsseX = (i * asseX * segno) + (j);
                    int puntoAsseY = (i * asseY * segno) + (j);
                    puntoAsseX = partenzaX + puntoAsseX;
                    puntoAsseY = partenzaY + puntoAsseY;
                    if (puntoAsseX < 0 || puntoAsseY < 0 || puntoAsseX >= lunghezza || puntoAsseY >= altezza) {
                        return false;
                    }
                    if (!mappa[puntoAsseX][puntoAsseY].getLibera()) {
                        return false;
                    }


                }

            }
            return true;
        }
    return false;
    }


//getter Altezza e Lunghezza
public int getLunghezza() {
    return lunghezza;
}

    public int getAltezza() {
        return altezza;
    }

    public Casella[][] getCaselle(){
        return mappa;
    }

    private void stampaMessaggio(String messaggio) {
                System.out.println(messaggio);

    }



    public int controlloCoordinate(int x, int y){
        mappa[x][y].colpito();
        if(mappa[x][y].getPresenzaNave()){

            return 1;
        }else{
            return 0;
        }

    }

    public void stampaMappa() {
        for(int j=1;j<=lunghezza;j++){
            System.out.print(" " + j + " ");

            }
                System.out.print("\n");
        for (int i = 0; i < lunghezza; i++) {
            for (int z = 0; z < altezza; z++) {
                if(z>=10){
                    System.out.print(" ");
                }
                if(mappa[i][z].getPresenzaNave() && mappa[i][z].getColpito()){
                     System.out.print(" 1 ");
                }
                else if(mappa[i][z].getColpito()){
                    System.out.print(" 0 ");
                    }else{
                    System.out.print(" - ");
                }
                if (z == altezza -1) {
                    System.out.println((char)('A'+i));

                }
                //stampa la presenza di navi


            }
        }

    }

    public void stampaMappaConNavi(){
        for(int j=1;j<=lunghezza;j++){
            System.out.print(" " + j + " ");

        }
        System.out.print("\n");
        for (int i = 0; i < lunghezza; i++) {
            for (int z = 0; z < altezza; z++) {
                if(z>=10){
                    System.out.print(" ");
                }
                if(mappa[i][z].getPresenzaNave()){
                    System.out.print(" 1 ");

                }else{
                    System.out.print(" - ");
                }
                if (z == altezza -1) {
                    System.out.println((char)('A'+i));

                }
                //stampa la presenza di navi


            }
        }

    }
    }



