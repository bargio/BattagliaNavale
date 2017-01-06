/**
 * Created by Gio on 03/01/2017.
 */
public class Nave {
    private int dimensione;
    private String[] casellaNave;
    private int counter;
    public Nave(int dim){

        this.dimensione = dim;
        this.casellaNave = new String[dim];
        this.counter = 0;

    }

    public void insNomeCaselleNave(int x, int y) {
        String casella = String.valueOf((char)('A'+x) ) + (y+1);
        casellaNave[counter] = casella;
        counter++;


    }

    public int getDimensione()
    {
        return dimensione;
    }


    public void stampaNomeCaselle(){
        for(int i=0; i<counter;i++ ){
            System.out.println(casellaNave[i]);
        }

    }



}
