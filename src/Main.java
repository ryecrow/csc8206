import Richard.Track;
import Yiwei.Interlock;
import Yiwei.InterlockImpl;
import Yiwei.Railway;
import Yiwei.Route;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Track track = new Track();
        track.read();

        JFrame f = new JFrame("Train Track Demo");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        JApplet applet = new Track();
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();
        f.setSize(new Dimension(1500, 1000));
        f.setVisible(true);


        LinkedList<String> signalsforEachRoute = new LinkedList<String>();
        signalsforEachRoute = track.upPath("s1", "s7");

        Interlock interlock = new InterlockImpl();
        String filepath = "./resource/map1.json";
        //create a railway object
        Railway railway = new Railway(filepath);


        for(int z = 0; z < signalsforEachRoute.size(); z++){
            System.out.println(signalsforEachRoute.get(z));



            //check interface demo
            Map<String,Object> map = new HashMap<>();

            String journey = signalsforEachRoute.get(z).substring(0, signalsforEachRoute.get(z).length() - 1);

            map.put("journey",journey);
            map.put("journeyId","j"+(z+1));
//            java.util.List<String> paths = new ArrayList<>();
//            String[] signal = journey.split(";");
//            for (int i = 0; i < signal.length ; i++) {
//                if (i < signal.length - 1) {
//                    String path = Route.dao.getBySourceAndDest(railway.getRoutes(), signal[i], signal[i + 1]).getPath();
//                    paths.add(path);
//                }
//            }
//            map.put("path",paths);

            boolean flag = interlock.check(railway, map);

            if (flag){
                break;
            }
        }


        List<Railway> railways = interlock.running(railway);

    }
}
