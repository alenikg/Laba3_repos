
package com.mycompany.laba3_monst;

import formats.Storage;
import view.MainFrame;

/**
 *
 * @author elenagoncarova
 */
public class Laba3_Monst {

    public static void test() {
        String XML_FILE = "bestiarum.xml";
        String YAML_FILE = "bestiarum.yaml";
        String JSON_FILE = "bestiarum.json";
        MonsterParser parser = MonsterParser.createChain();
        try {
            Storage.xmlMonsters = parser.parse(XML_FILE);
            for (Monster monster : Storage.xmlMonsters) {
                System.out.println(monster);
            }
            System.out.println("Parsing XML complete.");

            Storage.yamlMonsters = parser.parse(YAML_FILE);
            for (Monster monster : Storage.yamlMonsters) {
                System.out.println(monster);
            }
            System.out.println("Parsing YAML (SnakeYAML) complete.");

            Storage.jsonMonsters = parser.parse(JSON_FILE);
            for (Monster monster : Storage.jsonMonsters) {
                System.out.println(monster);
            }
            System.out.println("Parsing JSON (Jackson) complete.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

}
