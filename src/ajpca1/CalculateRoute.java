/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajpca1;

import java.util.*;
import java.util.Map.Entry;
import java.util.Collections;

/**
 *
 * @author Darren
 */
public class CalculateRoute {

    private String Board = "";
    private String Alight = "";
    /**
     * the arraylist is created so that it can store multiple strings strings
     * inside of 6 of which includes 2 train station names , 2 Trains station
     * Codes , and 2 train line names
     */
    //this are the arrays for storing the information of each boarding and alighting stations
    private ArrayList<String> code1 = new ArrayList<String>();
    private ArrayList<String> code2 = new ArrayList<String>();
    private ArrayList<String> line1 = new ArrayList<String>();
    private ArrayList<String> line2 = new ArrayList<String>();

    /**
     * this code is to store the all the value of the station necessary for
     * traveling from one place to another eg: yishun ns13 north south line ,
     * bishan ns17 north south line
     */
    private String Msg = "";
    private String route = "";
    private ArrayList<String> routeList = new ArrayList<>();

    //this code are to store all intersechange train stations 
    private ArrayList<String> IntersectName = new ArrayList<String>();
    private ArrayList<String> IntersectCode = new ArrayList<String>();
    private ArrayList<String> IntersectLine = new ArrayList<String>();

    /**
     * this is the constructor that will take in the boarding and alighting
     * stations
     *
     * @param boarding
     * @param Alighting
     */
    public CalculateRoute(String boarding, String Alighting) {
        Board = boarding;
        Alight = Alighting;
    }

    /**
     * this is to return the destination array list
     *
     * @return destination
     */
    public String GetDestination() {
        return Msg;
    }

    /**
     * this method will be the one that will get the routes from one place to
     * another
     *
     * @return ret
     */
    public boolean Getroutes() {
        boolean depart = SearchTrain(Board, 1);
        boolean arrive = SearchTrain(Alight, 2);
        int hops = 1000000;
        boolean ret = false;
        //ret = Collections.disjoint(line1, line2);

        if (depart == true && arrive == true) {
            ret = true;
            //ret = line1.retainAll(line2)||line1.containsAll(line2);

            if (Collections.disjoint(line1, line2) != true) {

                ret = true;
                /**
                 * the following lines are written get the similar string values
                 * in the 2 line iterations
                 */
                line1.retainAll(line2);
                line2.retainAll(line1);

                /**
                 * the following line are to sort the lines in alphabetical and
                 * numerical order
                 */
                Collections.sort(line1);
                Collections.sort(line2);
                Collections.sort(code1);
                Collections.sort(code2);

                /**
                 * this int it is to store the current index number of the
                 * Arraylist code1 when a match is found
                 */
                int it = -1;
                int ie = -1;
                int ir = -1;
                /**
                 * this following for loop is to check if the code 2 has the
                 * code that correspond to the line eg: CC , NW ,etc and retains
                 * it
                 */
                boolean SpecialChecker = false;
                for (int i = 0; i < code1.size(); i++) {
                    //this this is the code to check the special lines like expo and bayfront
                    if ((line1.contains("East West Line")!=true && code1.get(i).contains("CG") != true) || (code1.get(i).contains("CE") != true && line1.contains("Circle Line")!= true)) {
                        String part = code1.get(i).substring(0, 2);
                        for (int b = 0; b < code2.size(); b++) {
                            if ((code2.get(b).contains("CG") != true) && (code2.get(b).contains("CE") != true)) {
                                if (code2.get(b).substring(0, 2).equalsIgnoreCase(part) == true) {
                                    it = i;
                                    ie = b;
                                    

                                }
                            } else {
                                SpecialChecker = true;
                                if (code2.get(b).substring(0, 2).equalsIgnoreCase("CG") || code2.get(b).substring(0, 2).equalsIgnoreCase("CE")) {
                                    ie = b;
                                    ir = 2;
                                    it = i;
                                    i = code1.size() + 1;
                                }
                            }
                        }
                    } else {
                        SpecialChecker = true;
                        for (int b = 0; b < code2.size(); b++) {
                            if (code2.get(b).contains("CC") == true || code2.get(b).contains("EW") == true) {
                                it = i;
                                ie = b;
                                ir = 1;
                                i = code1.size() + 1;
                                
                            }
                        }
                    }
                }

                if (SpecialChecker == false) {
                    Msg = "Take the train at " + Board.toUpperCase() + " (" + code1.get(it) + ") in " + line1.get(0).toUpperCase()
                            + "\nand Alight at station " + Alight.toUpperCase() + " (" + code2.get(ie) + ") in line " + line2.get(0).toUpperCase();
                    Trains t = new Trains();
                    t.SetHashTable();
                    //the following code is to have the route transition plan out in a string 
                    int trainroute = Math.abs(Integer.parseInt(code2.get(ie).substring(2)) - Integer.parseInt(code1.get(it).substring(2)));
                    hops = trainroute;
                    if (Integer.parseInt(code2.get(ie).substring(2)) > Integer.parseInt(code1.get(it).substring(2))) {
                        for (int a = 0; a <= trainroute; a++) {

                            String sequenceCode = code1.get(it).substring(0, 2) + (Integer.parseInt(code1.get(it).substring(2)) + a);

                            if (t.GetTrainHashMap(line1.get(0)).get(sequenceCode) != null) {
                                if (((Integer.parseInt(code1.get(it).substring(2))) + a) == (Integer.parseInt(code2.get(ie).substring(2)))) {
                                    route += code2.get(ie);
                                } else {
                                    route += sequenceCode + " > ";
                                }
                            }
                        }
                    } else if (Integer.parseInt(code1.get(it).substring(2)) > Integer.parseInt(code2.get(ie).substring(2))) {
                        for (int a = 0; a <= trainroute; a++) {
                            String sequenceCode = code1.get(it).substring(0, 2) + (Integer.parseInt(code1.get(it).substring(2)) - a);
                            if (t.GetTrainHashMap(line1.get(0)).get(sequenceCode) != null) {
                                if (((Integer.parseInt(code1.get(it).substring(2))) - a) == (Integer.parseInt(code2.get(ie).substring(2)))) {
                                    route += code2.get(ie);
                                } else {
                                    route += sequenceCode + " > ";
                                }
                            }
                        }
                    }
                    routeList.add(route);
                } else if (SpecialChecker == true && line1.get(0).equalsIgnoreCase("Circle Line")) {

                    Trains t = new Trains();
                    t.SetHashTable();
                    //the following code is to have the route transition plan out in a string 
                    if (ir == 1) {
                        Msg = "Take the train at " + Board.toUpperCase() + " (" + code1.get(it) + ") in " + line1.get(0).toUpperCase()
                                + " change trains at CE1 BAYFRONT to the CC4 PROMENADE"
                                + "\nand Alight at station " + Alight.toUpperCase() + " (" + code2.get(ie) + ") in line " + line2.get(0).toUpperCase();
                        int trainroute = Math.abs(4 - Integer.parseInt(code2.get(ie).substring(2))) + Integer.parseInt(code1.get(it).substring(2));
                        hops = trainroute;

                        if (4 > Integer.parseInt(code2.get(ie).substring(2))) {
                            for (int a = 0; a <= trainroute; a++) {
                                String sequenceCode;
                                if (a < 2) {
                                    sequenceCode = code1.get(it).substring(0, 2) + (Integer.parseInt(code1.get(it).substring(2)) - a);
                                } else {
                                    sequenceCode = code2.get(ie).substring(0, 2) + (4 - (a - 2));
                                }
                                if (t.GetTrainHashMap(line1.get(0)).get(sequenceCode) != null) {
                                    if (a == (trainroute)) {
                                        route += code2.get(ie);
                                    } else if (a < Integer.parseInt(code1.get(it).substring(2))) {
                                        route += code1.get(it).substring(0, 2) + (Integer.parseInt(code1.get(it).substring(2)) - a) + " > ";
                                    } else {
                                        route += sequenceCode + " > ";
                                    }
                                }
                            }
                            //the aboveis fixed
                        } else if (4 < Integer.parseInt(code2.get(ie).substring(2))) {
                            for (int a = 0; a <= trainroute; a++) {
                                String sequenceCode;
                                if (a < 2) {
                                    sequenceCode = code1.get(it).substring(0, 2) + (Integer.parseInt(code1.get(it).substring(2)) - a);
                                } else {
                                    sequenceCode = code2.get(it).substring(0, 2) + (4 + (a - 2));
                                }
                                if (t.GetTrainHashMap(line1.get(0)).get(sequenceCode) != null) {
                                    if (a == (trainroute)) {
                                        route += code2.get(ie);
                                    } else if (a < Integer.parseInt(code1.get(it).substring(2))) {
                                        route += sequenceCode + " > ";
                                    } else {
                                        route += sequenceCode + " > ";
                                    }
                                }
                            }
                        }
                    } else if (ir == 2) {
                        Msg = "Take the train at " + Board.toUpperCase() + " (" + code1.get(it) + ") in " + line1.get(0).toUpperCase()
                                + " change trains at CC4 PROMENADE to the CE1 BAYFRONT"
                                + "\nand Alight at station " + Alight.toUpperCase() + " (" + code2.get(ie) + ") in line " + line2.get(0).toUpperCase();
                        int trainroute = Math.abs(4 - Integer.parseInt(code1.get(it).substring(2))) + Integer.parseInt(code2.get(ie).substring(2));
                        hops = trainroute;
                        if (4 > Integer.parseInt(code1.get(it).substring(2))) {
                            for (int a = 0; a <= trainroute; a++) {
                                String sequenceCode;
                                if (a >= (trainroute - 2)) {
                                    sequenceCode = code2.get(ie).substring(0, 2) + (Integer.parseInt(code2.get(ie).substring(2)) - (trainroute - a));
                                } else {
                                    sequenceCode = code1.get(it).substring(0, 2) + (4 - (a - 2));
                                }
                                if (t.GetTrainHashMap(line1.get(0)).get(sequenceCode) != null) {
                                    if (a == (trainroute)) {
                                        route += code2.get(ie);
                                    } else if (a < Integer.parseInt(code1.get(it).substring(2))) {
                                        route += code1.get(it).substring(0, 2) + (Integer.parseInt(code1.get(it).substring(2)) - a) + " > ";
                                    } else {
                                        route += sequenceCode + " > ";
                                    }
                                }
                            }
                            //the aboveis fixed
                        } else if (4 < Integer.parseInt(code2.get(ie).substring(2))) {
                            for (int a = 0; a <= trainroute; a++) {
                                String sequenceCode;
                                if (a < 2) {
                                    sequenceCode = code1.get(it).substring(0, 2) + (Integer.parseInt(code1.get(it).substring(2)) - a);
                                } else {
                                    sequenceCode = code2.get(it).substring(0, 2) + (4 - (a - 2));
                                }
                                if (t.GetTrainHashMap(line1.get(0)).get(sequenceCode) != null) {
                                    if (a == (trainroute)) {
                                        route += code2.get(ie);
                                    } else if (a < Integer.parseInt(code1.get(it).substring(2))) {
                                        route += code1.get(it).substring(0, 2) + (Integer.parseInt(code1.get(it).substring(2)) - a) + " > ";
                                    } else {
                                        route += sequenceCode + " > ";
                                    }
                                }
                            }
                        }
                    }
                    routeList.add(route);
                } else if (SpecialChecker == true && line1.get(0).equalsIgnoreCase("East West Line")) {
                    Trains t = new Trains();
                    t.SetHashTable();
                    //this is basically the same thing as the oabove section
                    if (ir == 2) {
                        Msg = "Take the train at " + Board.toUpperCase() + " (" + code1.get(it) + ") in " + line1.get(0).toUpperCase()
                                + " change trains at EW4 TANAH MERAH to the CG1 EXPO"
                                + "\nand Alight at station " + Alight.toUpperCase() + " (" + code2.get(ie) + ") in line " + line2.get(0).toUpperCase();
                        int trainroute = Math.abs(4 - Integer.parseInt(code1.get(it).substring(2))) + Integer.parseInt(code2.get(ie).substring(2));
                        hops = trainroute;
                        if (4 > Integer.parseInt(code1.get(it).substring(2))) {
                            for (int a = 0; a <= trainroute; a++) {
                                String sequenceCode;
                                if (a >= (trainroute - 2)) {
                                    sequenceCode = code2.get(ie).substring(0, 2) + (Integer.parseInt(code2.get(ie).substring(2)) - (trainroute - a));
                                } else {
                                    sequenceCode = code1.get(it).substring(0, 2) + (4 - (a - 2));
                                }
                                if (t.GetTrainHashMap(line1.get(0)).get(sequenceCode) != null) {
                                    if (a == (trainroute)) {
                                        route += code2.get(ie);
                                    } else if (a < Integer.parseInt(code1.get(it).substring(2))) {
                                        route += code1.get(it).substring(0, 2) + (Integer.parseInt(code1.get(it).substring(2)) - a) + " > ";
                                    } else {
                                        route += sequenceCode + " > ";
                                    }
                                }
                            }
                            //the aboveis fixed
                        } else if (4 < Integer.parseInt(code2.get(ie).substring(2))) {
                            for (int a = 0; a <= trainroute; a++) {
                                String sequenceCode;
                                if (a < 2) {
                                    sequenceCode = code1.get(it).substring(0, 2) + (Integer.parseInt(code1.get(it).substring(2)) - a);
                                } else {
                                    sequenceCode = code2.get(it).substring(0, 2) + (4 - (a - 2));
                                }
                                if (t.GetTrainHashMap(line1.get(0)).get(sequenceCode) != null) {
                                    if (a == (trainroute)) {
                                        route += code2.get(ie);
                                    } else if (a < Integer.parseInt(code1.get(it).substring(2))) {
                                        route += code1.get(it).substring(0, 2) + (Integer.parseInt(code1.get(it).substring(2)) - a) + " > ";
                                    } else {
                                        route += sequenceCode + " > ";
                                    }
                                }
                            }
                        }
                    } else if (ir == 1) {

                        Msg = "Take the train at " + Board.toUpperCase() + " (" + code1.get(it) + ") in " + line1.get(0).toUpperCase()
                                + " change trains at the CG1 EXPO to EW4 TANAH MERAH"
                                + "\nand Alight at station " + Alight.toUpperCase() + " (" + code2.get(ie) + ") in line " + line2.get(0).toUpperCase();
                        int trainroute = Math.abs(4 - Integer.parseInt(code2.get(ie).substring(2))) + Integer.parseInt(code1.get(it).substring(2));
                        hops = trainroute;

                        if (4 > Integer.parseInt(code2.get(ie).substring(2))) {
                            for (int a = 0; a <= trainroute; a++) {
                                String sequenceCode;
                                if (a < 2) {
                                    sequenceCode = code1.get(it).substring(0, 2) + (Integer.parseInt(code1.get(it).substring(2)) - a);
                                } else {
                                    sequenceCode = code2.get(ie).substring(0, 2) + (4 - (a - 2));
                                }
                                if (t.GetTrainHashMap(line1.get(0)).get(sequenceCode) != null) {
                                    if (a == (trainroute)) {
                                        route += code2.get(ie);
                                    } else if (a < Integer.parseInt(code1.get(it).substring(2))) {
                                        route += code1.get(it).substring(0, 2) + (Integer.parseInt(code1.get(it).substring(2)) - a) + " > ";
                                    } else {
                                        route += sequenceCode + " > ";
                                    }
                                }
                            }
                            //the aboveis fixed
                        } else if (4 < Integer.parseInt(code2.get(ie).substring(2))) {
                            for (int a = 0; a <= trainroute; a++) {
                                String sequenceCode;
                                if (a < 2) {
                                    sequenceCode = code1.get(it).substring(0, 2) + (Integer.parseInt(code1.get(it).substring(2)) - a);
                                } else {
                                    sequenceCode = code2.get(it).substring(0, 2) + (4 - (a - 2));
                                }
                                if (t.GetTrainHashMap(line1.get(0)).get(sequenceCode) != null) {
                                    if (a == (trainroute)) {
                                        route += code2.get(ie);
                                    } else if (a < Integer.parseInt(code1.get(it).substring(2))) {
                                        route += code1.get(it).substring(0, 2) + (Integer.parseInt(code1.get(it).substring(2)) - a) + " > ";
                                    } else {
                                        route += sequenceCode + " > ";
                                    }
                                }
                            }
                        }
                    }
                    routeList.add(route);
                }

            } else if (Collections.disjoint(line1, line2)) {

                Trains t = new Trains();
                t.SetHashTable();
                //this is to get every interchange station in the boarding anf alighting line
                for (String line : line1) {
                    Collection<String> TrainStation = t.GetTrainHashMap(line).values();
                    for (String Stations : TrainStation) {
                        for (String NameOfLines : (Set<String>) t.GetHashTable().keySet()) {
                            if (NameOfLines != line) {
                                if (t.GetTrainHashMap(NameOfLines).containsValue(Stations) == true) {
                                    IntersectLine.add(NameOfLines);
                                    IntersectName.add(Stations);
                                    IntersectCode.add((String) getKeyByValue(t.GetTrainHashMap(line), Stations));
                                }
                            }
                        }
                    }
                }
                for (String line : line2) {
                    Collection<String> TrainStation = t.GetTrainHashMap(line).values();
                    for (String Stations : TrainStation) {
                        for (String NameOfLines : (Set<String>) t.GetHashTable().keySet()) {
                            if (NameOfLines != line) {
                                if (t.GetTrainHashMap(NameOfLines).containsValue(Stations) == true) {
                                    IntersectLine.add(NameOfLines);
                                    IntersectName.add(Stations);
                                    IntersectCode.add((String) getKeyByValue(t.GetTrainHashMap(line), Stations));
                                }
                            }
                        }
                    }
                }
                int countNum1;
                int countNum2;
                for (int k = 0; k < line1.size(); k++) {

                    for (int i = 0; i < IntersectCode.size(); i++) {
                        //the following code is to check the intersect code

                        for (int j = 0; j < line2.size(); j++) {

                            //this code is to get the indirect path to the to another line like from yishun to bishan to serangoon to kovan instead of 
                            //going from yishun to dhoby ghaut to kovan
                            for (int h = 0; h < IntersectCode.size(); h++) {

                                if (code2.get(j).contains(IntersectCode.get(i).substring(0, 2))) {

                                    if (IntersectLine.get(i).equalsIgnoreCase(IntersectLine.get(h)) && IntersectCode.get(i).subSequence(0, 2).equals(IntersectCode.get(h).subSequence(0, 2)) == false) {

                                        if (code1.get(k).substring(0, 2).equalsIgnoreCase(IntersectCode.get(h).substring(0, 2))) {

                                            int Counting = Math.abs(Integer.parseInt(code1.get(k).substring(2)) - Integer.parseInt(IntersectCode.get(h).substring(2)));

                                            Counting += Math.abs(Integer.parseInt(IntersectCode.get(i).substring(2)) - Integer.parseInt(code2.get(j).substring(2)));

                                            Counting += Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2))
                                                    - Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(h))).substring(2))
                                            );
                                            if (Counting < hops) {
                                                hops = Counting;
                                                Msg = "Take the train in " + Board.toUpperCase() + " (" + code1.get(k) + ") in line " + line1.get(k).toUpperCase() + "\nAnd interchange at "
                                                        + IntersectName.get(h).toUpperCase() + " (" + IntersectCode.get(h) + " / " + getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h)) + " )to line " + IntersectLine.get(h).toUpperCase() + "\nAnd interchange again at "
                                                        + IntersectName.get(i).toUpperCase() + "(" + getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i)) + " / " + IntersectCode.get(i) + " )to line " + line2.get(j).toUpperCase() + "\nand alight at "
                                                        + Alight.toUpperCase() + " (" + code2.get(j) + " ) in line " + line2.get(j).toUpperCase();
                                                route = "";
                                                int b = -1;
                                                int c = -1;
                                                for (int a = 0; a < hops; a++) {

                                                    if (Integer.parseInt(code1.get(k).substring(2)) < Integer.parseInt(IntersectCode.get(h).substring(2))) {
                                                        if (a < Math.abs(Integer.parseInt(IntersectCode.get(h).substring(2)) - Integer.parseInt(code1.get(k).substring(2))) && t.GetTrainHashMap(line1.get(k)).get(code1.get(k).substring(0, 2) + (Integer.parseInt(code1.get(k).substring(2)) + a)) != null) {
                                                            route += code1.get(k).substring(0, 2) + (Integer.parseInt(code1.get(k).substring(2)) + a) + " > ";
                                                        } else if (a == (Integer.parseInt(IntersectCode.get(h).substring(2)) - Integer.parseInt(code1.get(k).substring(2)))) {
                                                            route += IntersectCode.get(h) + " / " + getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h)) + " > ";
                                                            b = 1;
                                                        }
                                                    } else if (Integer.parseInt(code1.get(k).substring(2)) > Integer.parseInt(IntersectCode.get(h).substring(2))) {
                                                        if (a < Math.abs(Integer.parseInt(IntersectCode.get(h).substring(2)) - Integer.parseInt(code1.get(k).substring(2))) && t.GetTrainHashMap(line1.get(k)).get(code1.get(k).substring(0, 2) + (Integer.parseInt(code1.get(k).substring(2)) - a)) != null) {
                                                            route += code1.get(k).substring(0, 2) + (Integer.parseInt(code1.get(k).substring(2)) - a) + " > ";
                                                        } else if (a == Math.abs(Integer.parseInt(IntersectCode.get(h).substring(2)) - Integer.parseInt(code1.get(k).substring(2)))) {
                                                            route += IntersectCode.get(h) + " / " + getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h)) + " > ";
                                                            b = 1;
                                                        }
                                                    }
                                                    if (b != -1 && Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) > Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2)))) {
                                                        String trainName = ((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(0, 2) + (Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) - b);
                                                        boolean Isit = t.GetTrainHashMap(IntersectLine.get(h)).get(trainName) != null;
                                                        int testing = Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) - Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2)));
                                                        if (b < Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) - Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2))) && Isit == true) {
                                                            b++;
                                                            route += trainName + " > ";
                                                        } else if (b == Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) - Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2)))) {
                                                            route += getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i)) + " / " + IntersectCode.get(i) + " > ";
                                                            c = 0;
                                                            b = -1;
                                                        } else {
                                                            b++;
                                                        }
                                                    } else if (b != -1 && Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) < Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2)))) {
                                                        String trainName = ((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(0, 2) + (Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) + b);
                                                        boolean Isit = t.GetTrainHashMap(IntersectLine.get(h)).get(trainName) != null;
                                                        int testing = Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) - Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2)));
                                                        if (b < Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) - Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2))) && Isit == true) {
                                                            b++;
                                                            route += trainName + " > ";
                                                        } else if (b == Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) - Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2)))) {
                                                            route += getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i)) + " / " + IntersectCode.get(i) + " > ";
                                                            c = 0;
                                                            b = -1;
                                                        } else {
                                                            b++;
                                                        }
                                                    } else if (c != -1 && Integer.parseInt(IntersectCode.get(i).substring(2)) < Integer.parseInt(code2.get(j).substring(2))) {
                                                        String trainName = code2.get(j).substring(0, 2) + (Integer.parseInt(IntersectCode.get(i).substring(2)) + c + 1);
                                                        boolean Isit = t.GetTrainHashMap(line2.get(j)).get(trainName) != null;
                                                        if (Isit == true && c < Math.abs(Integer.parseInt(IntersectCode.get(i).substring(2)) - Integer.parseInt(code2.get(j).substring(2)))) {
                                                            if (trainName.equals(code2.get(j))) {
                                                                route += code2.get(j);
                                                                c = -1;
                                                            } else {
                                                                route += trainName + " > ";
                                                                c++;
                                                            }
                                                        } else if (c == Math.abs(Integer.parseInt(IntersectCode.get(i).substring(2)) - Integer.parseInt(code2.get(j).substring(2)))) {
                                                            route += code2.get(j);
                                                        }
                                                    }
                                                    if (c != -1 && Integer.parseInt(IntersectCode.get(i).substring(2)) > Integer.parseInt(code2.get(j).substring(2))) {
                                                        String trainName = code2.get(j).substring(0, 2) + (Integer.parseInt(IntersectCode.get(i).substring(2)) - c);
                                                        boolean Isit = t.GetTrainHashMap(line2.get(j)).get(trainName) != null;
                                                        if (Isit == true && c < Math.abs(Integer.parseInt(IntersectCode.get(i).substring(2)) - Integer.parseInt(code2.get(j).substring(2)))) {
                                                            if (trainName.equals(code2.get(j))) {
                                                                route += code2.get(j);
                                                                c = -1;
                                                            } else {
                                                                route += trainName + " > ";
                                                                c++;
                                                            }
                                                        } else if (c == Math.abs(Integer.parseInt(IntersectCode.get(i).substring(2)) - Integer.parseInt(code2.get(j).substring(2)))) {
                                                            route += code2.get(j);

                                                        }
                                                    }

                                                }

                                            }
                                        } else if (code2.get(j).substring(0, 2).equalsIgnoreCase(IntersectCode.get(h).substring(0, 2))) {

                                            int Counting = Math.abs(Integer.parseInt(code2.get(j).substring(2)) - Integer.parseInt(IntersectCode.get(h).substring(2)));

                                            Counting += Math.abs(Integer.parseInt(IntersectCode.get(i).substring(2)) - Integer.parseInt(code1.get(k).substring(2)));

                                            Counting += Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2))
                                                    - Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(h))).substring(2)));
                                            if (Counting <= hops) {
                                                hops = Counting;
                                                Msg = "Take the train in " + Board.toUpperCase() + " (" + code1.get(k) + ") in line " + line1.get(k).toUpperCase() + "\nAnd interchange at "
                                                        + IntersectName.get(h).toUpperCase() + " (" + IntersectCode.get(h) + " / " + getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h)) + " )to line " + IntersectLine.get(h).toUpperCase() + "\nAnd interchange again at "
                                                        + IntersectName.get(i).toUpperCase() + "(" + IntersectCode.get(i) + " / " + getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i)) + " )to line " + line2.get(j).toUpperCase() + "\nAnd alight at "
                                                        + Alight.toUpperCase() + " (" + code2.get(j) + " ) in line " + line2.get(j).toUpperCase();

                                                route = "";
                                                int b = -1;
                                                int c = -1;
                                                for (int a = 0; a < hops; a++) {

                                                    if (Integer.parseInt(code1.get(k).substring(2)) < Integer.parseInt(IntersectCode.get(h).substring(2))) {
                                                        if (a < Math.abs(Integer.parseInt(IntersectCode.get(h).substring(2)) - Integer.parseInt(code1.get(k).substring(2))) && t.GetTrainHashMap(line1.get(k)).get(code1.get(k).substring(0, 2) + (Integer.parseInt(code1.get(k).substring(2)) + a)) != null) {
                                                            route += code1.get(k).substring(0, 2) + (Integer.parseInt(code1.get(k).substring(2)) + a) + " > ";
                                                        } else if (a == (Integer.parseInt(IntersectCode.get(h).substring(2)) - Integer.parseInt(code1.get(k).substring(2)))) {
                                                            route += IntersectCode.get(h) + " / " + getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h)) + " > ";
                                                            b = 1;
                                                        }
                                                    } else if (Integer.parseInt(code1.get(k).substring(2)) > Integer.parseInt(IntersectCode.get(h).substring(2))) {
                                                        if (a < Math.abs(Integer.parseInt(IntersectCode.get(h).substring(2)) - Integer.parseInt(code1.get(k).substring(2))) && t.GetTrainHashMap(line1.get(k)).get(code1.get(k).substring(0, 2) + (Integer.parseInt(code1.get(k).substring(2)) - a)) != null) {
                                                            route += code1.get(k).substring(0, 2) + (Integer.parseInt(code1.get(k).substring(2)) - a) + " > ";
                                                        } else if (a == Math.abs(Integer.parseInt(IntersectCode.get(h).substring(2)) - Integer.parseInt(code1.get(k).substring(2)))) {
                                                            route += IntersectCode.get(h) + " / " + getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h)) + " > ";
                                                            b = 1;
                                                        }
                                                    }
                                                    if (b != -1 && Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) > Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2)))) {
                                                        String trainName = ((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(0, 2) + (Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) - b);
                                                        boolean Isit = t.GetTrainHashMap(IntersectLine.get(h)).get(trainName) != null;
                                                        int testing = Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) - Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2)));
                                                        if (b < Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) - Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2))) && Isit == true) {
                                                            b++;
                                                            route += trainName + " > ";
                                                        } else if (b == Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) - Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2)))) {
                                                            route += getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i)) + " / " + IntersectCode.get(i) + " > ";
                                                            c = 0;
                                                            b = -1;
                                                        } else {
                                                            b++;
                                                        }
                                                    } else if (b != -1 && Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) < Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2)))) {
                                                        String trainName = ((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(0, 2) + (Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) + b);
                                                        boolean Isit = t.GetTrainHashMap(IntersectLine.get(h)).get(trainName) != null;
                                                        int testing = Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) - Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2)));
                                                        if (b < Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) - Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2))) && Isit == true) {
                                                            b++;
                                                            route += trainName + " > ";
                                                        } else if (b == Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) - Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i))).substring(2)))) {
                                                            route += getKeyByValue(t.GetTrainHashMap(IntersectLine.get(i)), IntersectName.get(i)) + " / " + IntersectCode.get(i) + " > ";
                                                            c = 0;
                                                            b = -1;
                                                        } else {
                                                            b++;
                                                        }
                                                    } else if (c != -1 && Integer.parseInt(IntersectCode.get(i).substring(2)) < Integer.parseInt(code2.get(j).substring(2))) {
                                                        String trainName = code2.get(j).substring(0, 2) + (Integer.parseInt(IntersectCode.get(i).substring(2)) + c + 1);
                                                        boolean Isit = t.GetTrainHashMap(line2.get(j)).get(trainName) != null;
                                                        if (Isit == true && c < Math.abs(Integer.parseInt(IntersectCode.get(i).substring(2)) - Integer.parseInt(code2.get(j).substring(2)))) {
                                                            if (trainName.equals(code2.get(j))) {
                                                                route += code2.get(j);
                                                                c = -1;
                                                            } else {
                                                                route += trainName + " > ";
                                                                c++;
                                                            }
                                                        } else if (c == Math.abs(Integer.parseInt(IntersectCode.get(i).substring(2)) - Integer.parseInt(code2.get(j).substring(2)))) {
                                                            route += code2.get(j);
                                                        }
                                                    }
                                                    if (c != -1 && Integer.parseInt(IntersectCode.get(i).substring(2)) > Integer.parseInt(code2.get(j).substring(2))) {
                                                        String trainName = code2.get(j).substring(0, 2) + (Integer.parseInt(IntersectCode.get(i).substring(2)) - c);
                                                        boolean Isit = t.GetTrainHashMap(line2.get(j)).get(trainName) != null;
                                                        if (Isit == true && c < Math.abs(Integer.parseInt(IntersectCode.get(i).substring(2)) - Integer.parseInt(code2.get(j).substring(2)))) {
                                                            if (trainName.equals(code2.get(j))) {
                                                                route += code2.get(j);
                                                                c = -1;
                                                            } else {
                                                                route += trainName + " > ";
                                                                c++;
                                                            }
                                                        } else if (c == Math.abs(Integer.parseInt(IntersectCode.get(i).substring(2)) - Integer.parseInt(code2.get(j).substring(2)))) {
                                                            route += code2.get(j);

                                                        }
                                                    }

                                                }

                                            }
                                        }
                                    }
                                }
                            }

                            if (IntersectLine.get(i).equalsIgnoreCase(line1.get(k))) {
                                //this if  code will get me the direct intersection from one line to another
                                //like from yishun to dover there will be 3 stations that will be caught in this code which is 
                                //city hall , raffles place , and jurong east 
                                if (code2.get(j).contains(IntersectCode.get(i).substring(0, 2))) {

                                    countNum1 = Math.abs(Integer.parseInt(IntersectCode.get(i).substring(2)) - Integer.parseInt(code2.get(j).substring(2)));

                                    for (int h = 0; h < IntersectCode.size(); h++) {

                                        if (IntersectLine.get(h) == line1.get(k) && IntersectName.get(h) == IntersectName.get(i)) {
                                            countNum2 = Math.abs(Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h))).substring(2)) - Integer.parseInt(code1.get(k).substring(2)));

                                            if ((countNum1 + countNum2) <= hops) {

                                                hops = (countNum1 + countNum2);
                                                Msg = "Take the train in " + Board.toUpperCase() + " (" + code1.get(k) + ") in line " + line1.get(k).toUpperCase()
                                                        + "\nand inter change at " + IntersectName.get(h).toUpperCase() + " (" + getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h)) + " / " + IntersectCode.get(i) + ")"
                                                        + "to the " + line2.get(j).toUpperCase().toUpperCase() + "\nthen alight at " + Alight.toUpperCase() + " (" + code2.get(j) + ") at " + line2.get(j).toUpperCase();

                                                //this is  the code to get the routes
                                                String secondCode = "";
                                                String lol = "";
                                                int b = 1;
                                                route = "";
                                                if (code1.get(k).contains(((String) getKeyByValue(t.GetTrainHashMap(line1.get(k)), IntersectName.get(h))).substring(0, 2))) {
                                                    for (int a = 0; a <= hops; a++) {
                                                        //this is the train code when the from one place to another increase

                                                        if (Integer.parseInt(code1.get(k).substring(2)) <= Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(line1.get(k)), IntersectName.get(h))).substring(2))) {
                                                            //this code is to check if the line exist
                                                            lol = code1.get(k).substring(0, 2) + (Integer.parseInt(code1.get(k).substring(2)) + a);
                                                            if (t.GetTrainHashMap(line1.get(k)).get(code1.get(k).substring(0, 2) + (Integer.parseInt(code1.get(k).substring(2)) + a)) != null) {
                                                                boolean tryit = t.GetTrainHashMap(line1.get(k)).get(lol).equals(IntersectName.get(h));

                                                                if ((t.GetTrainHashMap(line1.get(k)).get(lol).equals(IntersectName.get(h))) == false && secondCode == "") {
                                                                    lol = (String) t.GetTrainHashMap(line1.get(k)).get(lol);
                                                                    route += code1.get(k).substring(0, 2) + (Integer.parseInt(code1.get(k).substring(2)) + a) + " > ";
                                                                } else if ((t.GetTrainHashMap(line1.get(k)).get(lol).equals(IntersectName.get(h)))) {
                                                                    secondCode = (String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h));
                                                                    route += lol + " / " + IntersectCode.get(h) + " > ";
                                                                    secondCode = IntersectCode.get(h);
                                                                }
                                                            }

                                                        } //this is when it decreases 
                                                        else if (Integer.parseInt(code1.get(k).substring(2)) >= Integer.parseInt(((String) getKeyByValue(t.GetTrainHashMap(line1.get(k)), IntersectName.get(h))).substring(2))) {
                                                            lol = code1.get(k).substring(0, 2) + (Integer.parseInt(code1.get(k).substring(2)) - a);
                                                            if (t.GetTrainHashMap(line1.get(k)).get(code1.get(k).substring(0, 2) + (Integer.parseInt(code1.get(k).substring(2)) - a)) != null && secondCode == "") {
                                                                if ((t.GetTrainHashMap(line1.get(k)).get(lol).equals(IntersectName.get(h))) == false) {
                                                                    lol = (String) t.GetTrainHashMap(line1.get(k)).get(lol);
                                                                    route += code1.get(k).substring(0, 2) + (Integer.parseInt(code1.get(k).substring(2)) - a) + " > ";
                                                                } else if ((t.GetTrainHashMap(line1.get(k)).get(lol).equals(IntersectName.get(h)))) {
                                                                    secondCode = (String) getKeyByValue(t.GetTrainHashMap(IntersectLine.get(h)), IntersectName.get(h));
                                                                    route += lol + " / " + IntersectCode.get(h) + " > ";
                                                                    secondCode = IntersectCode.get(h);
                                                                }
                                                            }

                                                        }

                                                        if (secondCode != "") {
                                                            if (Integer.parseInt(code2.get(j).substring(2)) >= Integer.parseInt(IntersectCode.get(h).substring(2))) {
                                                                if (secondCode.equals(code2.get(j))) {
                                                                    route += code2.get(j);
                                                                    a = hops + 1;
                                                                } else if ((code2.get(j).substring(0, 2) + (Integer.parseInt(IntersectCode.get(i).substring(2)) + b)).equals(code2.get(j))) {
                                                                    route += code2.get(j);
                                                                    a = hops + 1;
                                                                } else if (t.GetTrainHashMap(line2.get(j)).get(IntersectCode.get(i).substring(0, 2) + (Integer.parseInt(IntersectCode.get(i).substring(2)) + b)) != null) {
                                                                    route += code2.get(j).substring(0, 2) + (Integer.parseInt(IntersectCode.get(i).substring(2)) + b) + " > ";
                                                                    secondCode = code2.get(j).substring(0, 2) + (Integer.parseInt(IntersectCode.get(i).substring(2)) + b);
                                                                    b++;
                                                                }

                                                            } else if (Integer.parseInt(code2.get(j).substring(2)) <= Integer.parseInt(IntersectCode.get(h).substring(2))) {
                                                                if (secondCode.equals(code2.get(j))) {
                                                                    route += code2.get(j);
                                                                    a = hops + 1;
                                                                } else if ((code2.get(j).substring(0, 2) + (Integer.parseInt(IntersectCode.get(i).substring(2)) - b)).equals(code2.get(j))) {
                                                                    route += code2.get(j);
                                                                    a = hops + 1;
                                                                } else if (t.GetTrainHashMap(line2.get(j)).get(IntersectCode.get(i).substring(0, 2) + (Integer.parseInt(IntersectCode.get(i).substring(2)) - b)) != null) {
                                                                    route += code2.get(j).substring(0, 2) + (Integer.parseInt(IntersectCode.get(i).substring(2)) - b) + " > ";
                                                                    secondCode = code2.get(j).substring(0, 2) + (Integer.parseInt(IntersectCode.get(i).substring(2)) - b);
                                                                    b++;
                                                                }

                                                            }
                                                        }

                                                    }
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                routeList.add(route);
                ret = true;
            } else {
                ret = false;
            }
        }
        if (ret == true) {
            System.out.println(route);
            System.out.println("this route takes " + hops + " stations to reach");
        }
        return ret;
    }

    /**
     * this method will get all the information in a single train station
     *
     * @param search
     * @param trains
     * @return r
     */
    public boolean SearchTrain(String search, int trains) {

        boolean r = false;

        Trains t = new Trains();
        t.SetHashTable();
        Set<String> links = t.GetHashTable().keySet();
        /**
         * string key represents the Line Name
         */
        for (String key : links) {
            Collection<String> value = t.GetTrainHashMap(key).values();
            /**
             * String train represents the station name
             */
            for (String train : value) {

                if (train.equalsIgnoreCase(search)) {
                    r = true;
                    if (trains == 1) {
                        line1.add(key);
                        code1.add((String) getKeyByValue(t.GetTrainHashMap(key), train));
                    } else if (trains == 2) {
                        line2.add(key);
                        code2.add((String) getKeyByValue(t.GetTrainHashMap(key), train));
                    }
                }

            }
            if (r == false) {
                Set<String> yay = t.GetTrainHashMap(key).keySet();
                for (String train : yay) {
                    if (train.equalsIgnoreCase(Board) && trains == 1) {
                        r = true;

                        line1.add(key);
                        code1.add(Board.toUpperCase());
                        Board = (String) t.GetTrainHashMap(key).get(train);
                    } else if (train.equalsIgnoreCase(Alight) && trains == 2) {
                        line2.add(key);
                        code2.add(Alight.toUpperCase());
                        Alight = (String) t.GetTrainHashMap(key).get(train);
                        r = true;
                    }

                }
            }
        }
        return r;
    }

    /**
     * this method will return key by using the value to find it
     *
     * @param <T>
     * @param <E>
     * @param map
     * @param value
     * @return entry.getKey()
     */
    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

}
