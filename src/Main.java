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

        List<String> input = new ArrayList<>();
        List<String> output = new ArrayList<>();




        input.add("s1;s7");
        input.add("s1;s7");
        input.add("s8;s2");

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

        for (int i = 0; i < input.size(); i++) {
            //invoke Richard method
            String[] s = input.get(i).split(";");
            String source = s[0];
            String dest = s[1];
            String rs =  track.findRoute(source, dest);
            System.out.println("source="+source+" ; dest="+dest+" ; rs="+rs);
            output.add(rs);
        }

        LinkedList<String> signalsforEachRoute = new LinkedList<String>();
//        signalsforEachRoute = track.findRoute("s1", "s7");

        Interlock interlock = new InterlockImpl();
        String filepath = "./resource/map1.json";
        //create a railway object
        Railway railway = new Railway(filepath);



        //test here
//        signalsforEachRoute.clear();
//        signalsforEachRoute.add("s1;s4;s7");
//        signalsforEachRoute.add("s1;s4;s7");
//        signalsforEachRoute.add("s1;s4;s8;s11");
//        signalsforEachRoute.add("s1;s6;s8;s11");
//        signalsforEachRoute.add("s12;s9;s3;s2");
//        signalsforEachRoute.add("s12;s7;s5;s2");




//        System.out.println("Richard result="+signalsforEachRoute.size());

        //loop output list
        for(int z = 0; z < output.size(); z++){
            System.out.println(" 111  "+output.get(z));



            //check interface demo
            Map<String,Object> map = new HashMap<>();

            String journey = output.get(z).substring( output.get(z).length()-2 , output.get(z).length() - 1).equals(";")?output.get(z).substring(0, output.get(z).length() - 1):output.get(z);

            map.put("journey",journey);
            map.put("journeyId","j"+(z+1));


            boolean flag = interlock.check(railway, map);

            if (flag){
                break;
            }
        }


        List<Railway> railways = interlock.running(railway);

    }
}
