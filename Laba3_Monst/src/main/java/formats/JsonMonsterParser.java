
package formats;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.laba3_monst.Monster;
import com.mycompany.laba3_monst.MonsterParser;
import java.io.File;
import java.util.List;

public class JsonMonsterParser extends MonsterParser {
    
    private boolean checkIfCanParse(String filename) {
        return this.getExtension(filename).equals("json");
    }
    @Override
    public List<Monster> parse(String filename) throws Exception {
        if (!checkIfCanParse(filename)) {
            return this.nextParserParse(filename);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<Monster> monsters = objectMapper.readValue(new File(filename), new TypeReference<>() {});
        for (Monster monster : monsters) {
            monster.setDataSource(filename);
        }
        return monsters;    
    }   
}
