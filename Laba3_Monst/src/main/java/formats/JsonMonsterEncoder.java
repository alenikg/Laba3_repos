
package formats;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.laba3_monst.Monster;
import com.mycompany.laba3_monst.MonsterEncoder;
import java.io.File;
import java.util.List;

public class JsonMonsterEncoder extends MonsterEncoder {
    private boolean checkIfCanEncode(String filename) {
        return this.getExtension(filename).equals("json");
    }
    
    @Override
    public boolean encode(String filename, List<Monster> monsters) throws Exception {
if (!checkIfCanEncode(filename)) {
            return this.nextEncoderEncode(filename, monsters);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filename), monsters);
        for (Monster monster : monsters) {
            monster.setDataSource(filename);
        }
        return true;
    }   
}
