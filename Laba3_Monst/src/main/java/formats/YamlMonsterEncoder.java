
package formats;

import com.mycompany.laba3_monst.Monster;
import com.mycompany.laba3_monst.MonsterEncoder;
import java.io.FileWriter;
import java.util.List;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 *
 * @author elenagoncarova
 */
public class YamlMonsterEncoder extends MonsterEncoder {

    private boolean checkIfCanEncode(String filename) {
        String extension = this.getExtension(filename);
        return extension.equals("yaml") || extension.equals("yml");
    }
    
    @Override
    public boolean encode(String filename, List<Monster> monsters) throws Exception {
        if (!checkIfCanEncode(filename)) {
            return this.nextEncoderEncode(filename, monsters);
        }
        Yaml yaml = new Yaml(new Constructor(Monster.class, new LoaderOptions()));
        for (Monster monster : monsters) {
            monster.setDataSource(null);
        }
        FileWriter writer = new FileWriter(filename);
        yaml.dump(monsters, writer);
        for (Monster monster : monsters) {
            monster.setDataSource(filename);
        }
        return true;
    }
       
}
