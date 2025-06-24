
package view;

import com.mycompany.laba3_monst.Monster;
import com.mycompany.laba3_monst.MonsterEncoder;
import com.mycompany.laba3_monst.MonsterParser;
import com.mycompany.laba3_monst.Recipe;
import formats.Storage;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.TreePath;

/**
 *
 * @author elenagoncarova
 */
public class MainFrame extends JFrame implements ActionListener {
    private boolean HAS_INFO_WINDOW = true;
    private String selectedFilename;
    private JLabel selectedFileLabel;
    private JTextField exportFileTextField;
    private JTree tree;
    private MonstersTreeModel treeModel = new MonstersTreeModel();
    private MonsterParser parser = MonsterParser.createChain();
    private MonsterEncoder encoder = MonsterEncoder.createChain();
    private Monster selectedMonster;

    JLabel nameLabel = new JLabel("");
    JLabel descLabel = new JLabel("");
    JTextArea descTextArea = new JTextArea("");
    JLabel firstMentionLabel = new JLabel("");
    JLabel dangerLabel = new JLabel("");
    JProgressBar dangerBar = new JProgressBar(0, 10);
    JLabel vulnerabilityLabel = new JLabel("");
    JLabel heightLabel = new JLabel("");
    JLabel weightLabel = new JLabel("");
    JLabel habitatLabel = new JLabel("");
    JLabel activeTimeLabel = new JLabel("");
    JLabel recipeLabel = new JLabel("");

    JLabel recipeTypeLabel = new JLabel("");
    JLabel recipeIngredientsLabel = new JLabel("");
    JLabel recipeTimeLabel = new JLabel("");
    JLabel recipeEffectivenessLabel = new JLabel("");

    public MainFrame() {
        setTitle("Монстры");
        setLayout(null); // Using absolute positioning
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int WIDTH = HAS_INFO_WINDOW ? 600 : 1000;
        int HEIGHT = 550;
        setBounds((dimension.width - WIDTH) / 2, (dimension.height - HEIGHT) / 2, WIDTH, HEIGHT);

        int TREE_WIDTH = HAS_INFO_WINDOW ? 550 : 255;
        tree = new JTree(treeModel);
        tree.setBounds(15, 10, 150, 360);
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setBounds(15, 10, TREE_WIDTH, 370);
        add(scrollPane);
        scrollPane.setViewportView(tree);

        nameLabel.setBounds(280, 10, 670, 20);
        nameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        descLabel.setBounds(280, 35, 100, 20);
        descLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        descLabel.setVisible(false);
        descTextArea.setBounds(280, 55, 670, 40);
        descTextArea.setFont(new Font("Serif", Font.PLAIN, 16));
        descTextArea.setVisible(false);
        firstMentionLabel.setBounds(280, 100, 670, 20);
        firstMentionLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        dangerLabel.setBounds(280, 130, 100, 20);
        dangerLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        dangerBar.setBounds(400, 130, 150, 20);
        dangerBar.setVisible(false);
        heightLabel.setBounds(280, 160, 670, 20);
        heightLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        vulnerabilityLabel.setBounds(280, 190, 670, 20);
        vulnerabilityLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        weightLabel.setBounds(280, 220, 670, 20);
        weightLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        habitatLabel.setBounds(280, 250, 670, 20);
        habitatLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        activeTimeLabel.setBounds(280, 280, 670, 20);
        activeTimeLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        recipeLabel.setBounds(280, 310, 670, 20);
        recipeLabel.setFont(new Font("Serif", Font.PLAIN, 16));

        recipeTypeLabel.setBounds(280, 330, 670, 20);
        recipeTypeLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        recipeIngredientsLabel.setBounds(280, 350, 670, 20);
        recipeIngredientsLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        recipeTimeLabel.setBounds(280, 370, 670, 20);
        recipeTimeLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        recipeEffectivenessLabel.setBounds(280, 400, 670, 20);
        recipeEffectivenessLabel.setFont(new Font("Serif", Font.PLAIN, 16));

        JButton fileChooserBtn = new JButton("Выбрать файл");
        fileChooserBtn.setBounds(10, 390, 140, 30);
        fileChooserBtn.addActionListener(this);
        fileChooserBtn.setActionCommand("chooseFile");

        selectedFileLabel = new JLabel("Выбранный файл");
        selectedFileLabel.setBounds(15, 420, 500, 20);

        JButton importBtn = new JButton("Загрузить");
        importBtn.setBounds(150, 390, 120, 30);
        importBtn.addActionListener(this);
        importBtn.setActionCommand("importFile");

        JLabel exportFileLabel = new JLabel("Файл для выгрузки");
        exportFileLabel.setBounds(15, 450, 130, 20);
        exportFileTextField = new JTextField("");
        exportFileTextField.setBounds(140, 450, 300, 20);

        JButton exportBtn = new JButton("Выгрузить");
        exportBtn.setBounds(450, 445, 120, 30);
        exportBtn.addActionListener(this);
        exportBtn.setActionCommand("exportToFile");

        add(nameLabel);
        add(descLabel);
        add(descTextArea);
        add(firstMentionLabel);
        add(dangerLabel);
        add(dangerBar);
        add(vulnerabilityLabel);
        add(heightLabel);
        add(weightLabel);
        add(habitatLabel);
        add(activeTimeLabel);
        add(recipeLabel);
        add(recipeTypeLabel);
        add(recipeIngredientsLabel);
        add(recipeTimeLabel);
        add(recipeEffectivenessLabel);

        add(fileChooserBtn);
        add(selectedFileLabel);
        add(importBtn);
        add(exportFileLabel);
        add(exportFileTextField);
        add(exportBtn);

        setVisible(true);

        descTextArea.getDocument().addDocumentListener(new DocumentListener() {
            Monster currMonster;
            void changeDesc(String newDesc) {
                if (selectedMonster != null && selectedMonster == currMonster) {
                    selectedMonster.setDescription(newDesc);
                    System.out.println(selectedMonster.getObjPropsDesc());
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                changeDesc(descTextArea.getText());
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                currMonster = selectedMonster;
                changeDesc(descTextArea.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                changeDesc(descTextArea.getText());
            }
        });

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                Object object = e.getPath().getLastPathComponent();
                if (e.getNewLeadSelectionPath() != null && object instanceof Monster) {
                    Monster monster = (Monster) object;
                    selectedMonster = monster;
                    if (HAS_INFO_WINDOW) {
                        InfoWindow infoWindow = new InfoWindow(MainFrame.this, selectedMonster);
                        infoWindow.setVisible(true);
                        infoWindow.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                descTextArea.setText(monster.getDescription());
                                tree.clearSelection();
                            }
                        });
                    } else {
                        renderInfoBlock();
                    }
                } else {
                   clearInfoBlock();
                }
            }
        });
    }

    private void renderInfoBlock() {
        Monster monster = selectedMonster;
        Recipe recipe = monster.getRecipe();
        nameLabel.setText("Имя: " + monster.getName());
        descLabel.setVisible(true);
        descTextArea.setText(monster.getDescription());
        descTextArea.setVisible(true);
        String firstMentionText = new SimpleDateFormat("dd.MM.yyyy").format(monster.getFirstMention());
        firstMentionLabel.setText("Первое упоминание: " + firstMentionText);
        dangerLabel.setText("Опасность: " + monster.getDanger());
        dangerBar.setValue(monster.getDanger());
        dangerBar.setVisible(true);
        if (monster.getVulnerability() != null) {
            vulnerabilityLabel.setText("Уязвимость: " + monster.getVulnerability());
        } else {
            vulnerabilityLabel.setText("Уязвимость: нет данных");
        }
        heightLabel.setText("Рост: " + monster.getHeight());
        weightLabel.setText("Вес: " + monster.getWeight());
        habitatLabel.setText("Обитание: " + monster.getHabitat());
        activeTimeLabel.setText("Время активности: " + monster.getActiveTime());
        if (recipe != null) {
            recipeLabel.setText("Рецепт:");
            recipeTypeLabel.setText("Тип: " + recipe.getType());
            recipeIngredientsLabel.setText("Ингредиенты: " + recipe.getIngredients());
            recipeTimeLabel.setText("Время, мин.: " + recipe.getTime());
            recipeEffectivenessLabel.setText("Эффективность: " + recipe.getEffectiveness());
        }
    }

    private void clearInfoBlock() {
        selectedMonster = null;
        nameLabel.setText("");
        descLabel.setVisible(false);
        descTextArea.setText("");
        descTextArea.setVisible(false);
        firstMentionLabel.setText("");
        dangerLabel.setText("");
        dangerBar.setVisible(false);
        vulnerabilityLabel.setText("");
        heightLabel.setText("");
        weightLabel.setText("");
        habitatLabel.setText("");
        activeTimeLabel.setText("");
        recipeLabel.setText("");
        recipeTypeLabel.setText("");
        recipeIngredientsLabel.setText("");
        recipeTimeLabel.setText("");
        recipeEffectivenessLabel.setText("");
    }

    private void chooseFile() {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите файл");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "xml, yaml, yml, json", "xml", "yaml", "yml", "json");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int chooserState = fileChooser.showDialog(null, "Выбрать файл");
        if (chooserState == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            selectedFilename = file.getName();
            selectedFileLabel.setText("Выбранный файл: " + selectedFilename);
            exportFileTextField.setText(selectedFilename);
        }
    }

    private void showUnsupportedMessage(String prefix, String filename) {
        JOptionPane.showMessageDialog(
                this,
                prefix + "Формат " + this.encoder.getExtension(filename) + " не поддерживается",
                "Неподдерживаемый формат!",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void importFile() {
        if (this.selectedFilename == null || this.selectedFilename.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Имя файла!",
                    "Не заполнены поля!",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        String filename = this.selectedFilename;
        try {
            List<Monster> monsters = parser.parse(filename);
            if (monsters == null) {
                showUnsupportedMessage("Файл не загружен. ", filename);
                return;
            }
            tree.clearSelection();
            treeModel.setMonsters(monsters);
            String root = (String) treeModel.getRoot();
            tree.expandPath(new TreePath(new Object[]{root}));
            Storage.setMonstersForFormat(monsters, parser.getExtension(filename));
            clearInfoBlock();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(
                    this,
                    "Файл не загружен",
                    "Ошибка при загрузке!",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void exportToFile() {
        String filename = this.exportFileTextField.getText();
        if (filename == null || filename.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Имя файла!",
                    "Не заполнено имя файла для экспорта!",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        try {
            boolean result = this.encoder.encode(filename, this.treeModel.getMonsters());
            if (result) {
                JOptionPane.showMessageDialog(
                        this,
                        "Файл выгружен",
                        "Успешно!",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                showUnsupportedMessage("Файл не выгружен. ", filename);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(
                    this,
                    "Файл не выгружен",
                    "Ошибка при выгрузке!",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("exit")) {
            System.exit(0);
        }
        if (command.equals("chooseFile")) {
            this.chooseFile();
        }
        if (command.equals("importFile")) {
            this.importFile();
        }
        if (command.equals("exportToFile")) {
            this.exportToFile();
        }
    }
}