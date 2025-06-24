
package com.mycompany.laba3_monst;

import formats.JsonMonsterParser;
import formats.XmlMonsterParser;
import formats.YamlMonsterParser;
import java.text.SimpleDateFormat;
import java.util.List;

public abstract class MonsterParser {
    private MonsterParser nextParser = null;
    public SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); 
    
    static MonsterParser createChain() {
        MonsterParser xmlParser = new XmlMonsterParser();
        MonsterParser yamlParser = new YamlMonsterParser();
        MonsterParser jsonParser = new JsonMonsterParser();
        xmlParser.setNextParser(yamlParser);
        yamlParser.setNextParser(jsonParser);
        return xmlParser;
    }

    public void setNextParser(MonsterParser nextParser) {
        this.nextParser = nextParser;
    }

    public String getExtension(String filename) {
        String extension = "";
        int i = filename.lastIndexOf('.');
        if (i >= 0) {
            extension = filename.substring(i+1);
        }
        return extension;
    }

    public List<Monster> nextParserParse(String filename) throws Exception {
        MonsterParser nextParser = this.next();
        if (nextParser == null) {
            return null;
        }
        return nextParser.parse(filename);
    }

    public MonsterParser next() {
        return  this.nextParser;
    }

    public abstract List<Monster> parse(String filename) throws Exception;
}  

