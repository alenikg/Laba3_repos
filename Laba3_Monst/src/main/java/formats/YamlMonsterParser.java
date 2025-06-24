
package formats;

import com.mycompany.laba3_monst.Monster;
import com.mycompany.laba3_monst.MonsterParser;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;

/**
 *
 * @author elenagoncarova
 */
public class YamlMonsterParser extends MonsterParser {
    public static class ListConstructor<T> extends Constructor {
        private final Class<T> clazz;
        public ListConstructor(final Class<T> clazz, LoaderOptions loadingConfig) {
            super(loadingConfig);
            this.clazz = clazz;
        }
        @Override
        protected Object constructObject(final Node node) {
            if (node instanceof SequenceNode && isRootNode(node)) {
                ((SequenceNode) node).setListType(clazz);
            }
            return super.constructObject(node);
        }
        private boolean isRootNode(final Node node) {
            return node.getStartMark().getIndex() == 0;
        }
    }

    private LoaderOptions getLoaderOptions() {
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setTagInspector(new TagInspector() {
            @Override
            public boolean isGlobalTagAllowed(Tag tag) {
                return true;
            }
        });
        return loaderOptions;
    }

    private boolean checkIfCanParse(String filename) {
        String extension = this.getExtension(filename);
        return extension.equals("yaml") || extension.equals("yml");
    }
    @Override
    public List<Monster> parse(String filename) throws Exception {
         if (!checkIfCanParse(filename)) {
            return this.nextParserParse(filename);
        }
        List<Monster> monsters = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream(filename)) {
            Yaml yaml = new Yaml(new ListConstructor<>(Monster.class, getLoaderOptions()));
            Iterable<Object> objList = yaml.loadAll(inputStream);
            for (Object obj : objList) {
                if (obj instanceof Monster) {
                    Monster monster = (Monster) obj;
                    monsters.add(monster);
                } else if (obj instanceof Iterable) {
                    Iterable items = (Iterable) obj;
                    for (Object item : items) {
                        if (item instanceof Monster) {
                            Monster monster = (Monster) item;
                            monsters.add(monster);
                        }
                    }
                }
            }
            for (Monster monster : monsters) {
                monster.setDataSource(filename);
            }
            return monsters;
        }
    }
    
}
