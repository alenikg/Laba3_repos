
package formats;

import com.mycompany.laba3_monst.Monster;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elenagoncarova
 */
public class Storage {
    public static List<Monster> xmlMonsters = new ArrayList<>();
    public static List<Monster> yamlMonsters = new ArrayList<>();
    public static List<Monster> jsonMonsters = new ArrayList<>();

    public static void setMonstersForFormat(List<Monster> monsters, String fileExtension) {
        switch (fileExtension) {
            case "xml":
                xmlMonsters = monsters;
                break;
            case "yaml":
            case "yml":
                yamlMonsters = monsters;
                break;
            case "json":
                jsonMonsters = monsters;
                break;
        }
    }    
}
