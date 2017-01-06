import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gio on 04/01/2017.
 */
public class MosseGiocatore {
    private Map<String,Integer[]> mosseGiocatore;
    private MappaDiGioco map;
    private final int COLPITO = 1;
    private final int ACQUA= 1;
    private final int RIPROVARE= 1;



    public MosseGiocatore(MappaDiGioco m){
        this.mosseGiocatore = new HashMap<>();
        this.map = m;
        creazioneListaMosseGiocatore();

    }

    private void creazioneListaMosseGiocatore() {

        for (int i = 0; i < map.getLunghezza(); i++) {
            for (int j = 0; j < map.getAltezza(); j++) {
                Integer coordinate[] = new Integer[2];
                String casella = String.valueOf((char) ('A' + i)) + (j + 1);
                coordinate[0] = i;
                coordinate[1] = j;
                mosseGiocatore.put(casella, coordinate);

            }
        }
    }

    public int azioneMossa(String coordinata){
        if(mosseGiocatore.get(coordinata)!=null){
            Integer[] coordinateMappa;
            coordinateMappa = mosseGiocatore.get(coordinata);
            mosseGiocatore.remove(coordinata);
           if(map.controlloCoordinate(coordinateMappa[0],coordinateMappa[1])==0){
               return 0;
           }else{
               return 1;
           }

        }
        return 2;
    }
}
