
package com.mycompany.laba3_monst;

import formats.JsonMonsterEncoder;
import formats.XmlMonsterEncoder;
import formats.YamlMonsterEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

public abstract class MonsterEncoder {
    private MonsterEncoder nextEncoder = null;
    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    static MonsterEncoder createChain() {
        MonsterEncoder xmlEncoder = new XmlMonsterEncoder();
        MonsterEncoder yamlEncoder = new YamlMonsterEncoder();
        MonsterEncoder jsonEncoder = new JsonMonsterEncoder();
        xmlEncoder.setNextEncoder(yamlEncoder);
        yamlEncoder.setNextEncoder(jsonEncoder);
        return xmlEncoder;
    } 
    
    public void setNextEncoder(MonsterEncoder nextEncoder) {
        this.nextEncoder = nextEncoder;
    }

    public String getExtension(String filename) {
        String extension = "";
        int i = filename.lastIndexOf('.');
        if (i >= 0) {
            extension = filename.substring(i+1);
        }
        return extension;
    }

    public boolean nextEncoderEncode(String filename, List<Monster> monsters) throws Exception {
        MonsterEncoder nextEncoder = this.next();
        if (nextEncoder == null) {
            return false;
        }
        return nextEncoder.encode(filename, monsters);
    }

    public MonsterEncoder next() {
        return  this.nextEncoder;
    }

    public abstract boolean encode(String filename, List<Monster> monsters) throws Exception;
}

