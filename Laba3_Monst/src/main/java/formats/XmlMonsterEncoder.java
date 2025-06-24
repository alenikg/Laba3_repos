
package formats;

import com.mycompany.laba3_monst.Monster;
import com.mycompany.laba3_monst.MonsterEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author elenagoncarova
 */
public class XmlMonsterEncoder extends MonsterEncoder {
    
    private boolean checkIfCanEncode(String filename) {
        return this.getExtension(filename).equals("xml");
    }
    
    @Override
    public boolean encode(String filename, List<Monster> monsters) throws Exception {
        if (!checkIfCanEncode(filename)) {
            return this.nextEncoderEncode(filename, monsters);
        }
        exportMonsters(monsters, filename);
        for (Monster monster : monsters) {
            monster.setDataSource(filename);
        }
        return true;
    }
    
    private void exportMonsters(List<Monster> monsters, String filename) { 
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            exportMonsters(monsters, fileOutputStream);
            System.out.println("Чудовища экспортированы в " + filename);
        } catch (IOException | XMLStreamException e) {
            System.err.println("Ошибка экспорта чудовищ в XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    private void exportMonsters(List<Monster> monsters, OutputStream outputStream) throws IOException, XMLStreamException {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance(); 
        XMLStreamWriter writer = null;

        try {
            writer = outputFactory.createXMLStreamWriter(outputStream, "UTF-8");

            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("monsters");

            for (Monster monster : monsters) {
                writer.writeStartElement("monster");
            
                writeElement(writer, "name", monster.getName());
                writeElement(writer, "description", monster.getDescription());
                writeElement(writer, "danger", String.valueOf(monster.getDanger()));
                writeElement(writer, "habitat", monster.getHabitat());
                if (monster.getFirstMention() != null) {
                    writeElement(writer, "firstMention", DATE_FORMAT.format(monster.getFirstMention()));
                }
                writeElement(writer, "vulnerability", monster.getVulnerability());
                writeElement(writer, "invulnerability", monster.getInvulnerability());
                writeElement(writer, "height", monster.getHeight());
                writeElement(writer, "weight", monster.getWeight());

                if (monster.getImmunities() != null && !monster.getImmunities().isEmpty()) {
                    writer.writeStartElement("immunities");
                    for (String immunity : monster.getImmunities()) {
                        writeElement(writer, "immunity", immunity);
                    }
                    writer.writeEndElement();
                }

                writeElement(writer, "activeTime", monster.getActiveTime());

                if (monster.getRecipe() != null) {
                    writer.writeStartElement("recipe");
                    writeElement(writer, "type", monster.getRecipe().getType());
                    writeElement(writer, "ingredients", monster.getRecipe().getIngredients());
                    writeElement(writer, "time", String.valueOf(monster.getRecipe().getTime()));
                    writeElement(writer, "effectiveness", monster.getRecipe().getEffectiveness());
                    writer.writeEndElement();
                }

                writer.writeEndElement();
            }

            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();

        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (XMLStreamException e) {
                    System.err.println("Ошибка закрытия XMLStreamWriter: " + e.getMessage());
                }
            }
        }
    }

    private void writeElement(XMLStreamWriter writer, String elementName, String elementValue) throws XMLStreamException {
        if (elementName != null && elementValue != null && !elementValue.trim().isEmpty()) {
            writer.writeStartElement(elementName);
            writer.writeCharacters(elementValue);
            writer.writeEndElement();
        }
    }
      
}
