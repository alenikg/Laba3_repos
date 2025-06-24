
package view;

import com.mycompany.laba3_monst.Monster;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author elenagoncarova
 */
public class MonstersTreeModel implements TreeModel {
    private String root = "Монстры";
    private List<Monster> list = new ArrayList<>();
    private final List<TreeModelListener> listeners = new ArrayList<>();

    public List<Monster> getList() {
        return list;
    }

    private void printTree() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Монстер: " + list.get(i));
        }
    }

    public void setMonsters(List<Monster> monsters) {
        this.list = monsters;
        for (TreeModelListener listener : listeners) {
            listener.treeStructureChanged(
                    new TreeModelEvent(this, new Object[] { getRoot() })
            );
        }
        printTree();
    }

    public List<Monster> getMonsters() {
        return list;
    }

    public void addMonster(Monster monster) {
        list.add(monster);
        for (TreeModelListener listener : listeners) {
            listener.treeStructureChanged(
                    new TreeModelEvent(this, new Object[] { getRoot() })
            );
        }
        printTree();
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public int getChildCount(Object node)
    {
        if (node == root)
            return list.size();
        return 0;
    }

    @Override
    public Object getChild(Object node, int index)
    {
        if (node == root)
            return list.get(index);
        return null;
    }

    @Override
    public int getIndexOfChild(Object node, Object child)
    {
        if (node == root)
            return list.indexOf(child);
        return 0;
    }

    @Override
    public boolean isLeaf(Object node)
    {
        return node != root;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object value) {
        System.out.println(path);
        System.out.println(value);
    }

    @Override
    public void addTreeModelListener(TreeModelListener tml) {
        listeners.add(tml);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener tml) {
        listeners.remove(tml);
    }
}