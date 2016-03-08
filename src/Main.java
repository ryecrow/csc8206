import Richard.Track;
import com.google.gson.Gson;
import yiwei.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        String name = args[0];
        String name = "./resource/map1";
//        String journeys  = args[1];
//        String journeys = "s1;s11,s1;s11,s1;s11,s12;s2,s12;s2,s12;s2,";
        String journeys  = "s1;s7,s8;s2,";

        String filepath = ""+name;
        List<String> input = new ArrayList<>();
        List<String> output = new ArrayList<>();

        journeys  = journeys.substring(journeys.length() - 2, journeys.length() - 1).equals(",") ? journeys.substring(0, journeys.length() - 1) : journeys;
        String[] js = journeys.split(",");
        for (int i = 0; i < js.length; i++) {
            input.add(js[i]);
        }

//        String name = "map2";



//        input.add("s1;s11");
//        input.add("s1;s11");
//        input.add("s1;s11");
//        input.add("s1;s11");
//        input.add("s12;s2");
//        input.add("s12;s2");
//        input.add("s12;s2");
//        input.add("s12;s2");

        Track track = new Track();
        track.read(filepath + ".txt");

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
        f.setVisible(false);


        for (int i = 0; i < input.size(); i++) {
            //invoke Richard method
            String[] s = input.get(i).split(";");
            String source = s[0];
            String dest = s[1];
            String rs = track.findRoute(source, dest);
            System.out.println("source=" + source + " ; dest=" + dest + " ; rs=" + rs);
            output.add(rs);
        }

//        track.getGloabl();

        LinkedList<String> signalsforEachRoute = new LinkedList<String>();

        Interlock interlock = new InterlockImpl();
        //create a railway object
        Railway railway = new Railway(filepath + ".json");


        //loop output list
        for (int z = 0; z < output.size(); z++) {

            //check interface demo
            Map<String, Object> map = new HashMap<>();

            String journey = output.get(z).substring(output.get(z).length() - 2, output.get(z).length() - 1).equals(";") ? output.get(z).substring(0, output.get(z).length() - 1) : output.get(z);

            map.put("journey", journey);
            map.put("journeyId", "j" + (z + 1));


            boolean flag = interlock.check(railway, map);

        }

        List<String> railways = interlock.running(railway);
        Gson gson = new Gson();
        List<Railway> railwayList = new ArrayList<>();
        for (int i = 0; i < railways.size(); i++) {
            Railway r = gson.fromJson(railways.get(i),Railway.class);
            railwayList.add(r);
//            List<Journey> journeyList = r.getJourneys();
//            System.out.println("railway i="+i);
//            for (int j = 0; j < journeyList.size(); j++) {
//                System.out.println(journeyList.get(j).getId()+" state= "+journeyList.get(j).getState());
//            }
//
//            System.out.println("\n");
        }
        try {
            JsonFile.writeFile("result.json",gson.toJson(railwayList) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
