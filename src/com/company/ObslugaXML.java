package com.company;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class ObslugaXML {

    public static void grafikToXml(Grafik g, String nazwaPliku){
        if(nazwaPliku != null){
            try{
                FileWriter f = new FileWriter(nazwaPliku);
                BufferedWriter out = new BufferedWriter(f);
                XStream mapping = new XStream(new DomDriver());
                mapping.addPermission(AnyTypePermission.ANY);
                String xml = mapping.toXML(g);
                out.write(xml);
                out.close();
                System.out.println("Grafik: " + g
                        + " jest zapisany w pliku -> " + nazwaPliku);
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static Grafik xmlToGrafik(String nazwaPliku){
        String xml = "";
        String strLine = "";
        if (nazwaPliku != null) {
            try {
                FileReader f = new FileReader(nazwaPliku);
                BufferedReader r = new BufferedReader(f);

                while ((strLine = r.readLine()) != null)
                    xml += strLine;
                r.close();
                XStream mapping = new XStream(new DomDriver());
                mapping.addPermission(AnyTypePermission.ANY);
                return (Grafik) mapping.fromXML(xml);
            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
        }
        return null;
    }

    public static void managerToXml(Manager m, String nazwaPliku){
        if(nazwaPliku != null){
            try{
                FileWriter f = new FileWriter(nazwaPliku);
                BufferedWriter out = new BufferedWriter(f);
                XStream mapping = new XStream(new DomDriver());
                mapping.addPermission(AnyTypePermission.ANY);
                String xml = mapping.toXML(m);
                out.write(xml);
                out.close();
                System.out.println("Manager: " + m
                        + " jest zapisany w pliku -> " + nazwaPliku);
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static Manager xmlToManager(String nazwaPliku){
        String xml = "";
        String strLine = "";
        if (nazwaPliku != null) {
            try {
                FileReader f = new FileReader(nazwaPliku);
                BufferedReader r = new BufferedReader(f);

                while ((strLine = r.readLine()) != null)
                    xml += strLine;
                r.close();
                XStream mapping = new XStream(new DomDriver());
                mapping.addPermission(AnyTypePermission.ANY);
                return (Manager) mapping.fromXML(xml);
            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
        }
        return null;
    }
}