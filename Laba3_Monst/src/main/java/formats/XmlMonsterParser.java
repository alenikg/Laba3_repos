
package formats;

import com.mycompany.laba3_monst.Monster;
import com.mycompany.laba3_monst.MonsterParser;
import com.mycompany.laba3_monst.Recipe;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author elenagoncarova
 */
public class XmlMonsterParser extends MonsterParser {
    private boolean checkIfCanParse(String filename) {
        return this.getExtension(filename).equals("xml");
    }
    
    @Override
    public List<Monster> parse(String filename) throws Exception {
        if (!checkIfCanParse(filename)) {
            return this.nextParserParse(filename);
        }
        List<Monster> monsters = new ArrayList<>();
        Monster monster = null;
        String currentElement = null;
        List<String> immunities = null;
        Recipe recipe = null;

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

            XMLStreamReader reader = inputFactory.createXMLStreamReader(bufferedInputStream);

            while (reader.hasNext()) {
                int eventType = reader.next();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        String elementName = reader.getLocalName();
                        switch (elementName) {
                            case "monster":
                                monster = new Monster();
                                monster.setDataSource(filename);
                                break;
                            case "immunities":
                                immunities = new ArrayList<>();
                                break;
                            case "recipe":
                                recipe = new Recipe();
                                break;
                            default:
                                currentElement = elementName;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText().trim();
                        if (text.isEmpty()) break;

                        if (monster != null) {
                            switch (currentElement) {
                                case "name":
                                    monster.setName(text);
                                    break;
                                case "description":
                                    monster.setDescription(text);
                                    break;
                                case "danger":
                                    monster.setDanger(Integer.parseInt(text));
                                    break;
                                case "habitat":
                                    monster.setHabitat(text);
                                    break;
                                case "firstMention":
                                    monster.setFirstMention(DATE_FORMAT.parse(text));
                                    break;
                                case "height":
                                    monster.setHeight(text);
                                    break;
                                case "vulnerability":
                                    monster.setVulnerability(text);
                                    break;
                                case "invulnerability":
                                    monster.setInvulnerability(text);
                                    break;
                                case "weight":
                                    monster.setWeight(text);
                                    break;
                                case "immunity":
                                    if (immunities != null) {
                                        immunities.add(text);
                                    }
                                    break;
                                case "activeTime":
                                    monster.setActiveTime(text);
                                    break;
                                case "type":
                                    if (recipe != null) {
                                        recipe.setType(text);
                                    }
                                    break;
                                case "ingredients":
                                    if (recipe != null) {
                                        recipe.setIngredients(text);
                                    }
                                    break;
                                case "time":
                                    if (recipe != null) {
                                        recipe.setTime(Integer.parseInt(text));
                                    }
                                    break;
                                case "effectiveness":
                                    if (recipe != null) {
                                        recipe.setEffectiveness(text);
                                    }
                                    break;
                            }
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        elementName = reader.getLocalName();
                        switch (elementName) {
                            case "monster":
                                monsters.add(monster);
                                monster = null;
                                break;
                            case "immunities":
                                if (monster != null) {
                                    monster.setImmunities(immunities);
                                }
                                immunities = null;
                                break;
                            case "recipe":
                                if (monster != null) {
                                    monster.setRecipe(recipe);
                                }
                                recipe = null;
                                break;
                            default:
                                currentElement = null;
                        }
                        break;
                }
            }
        }
        return monsters;
    }

    
}
