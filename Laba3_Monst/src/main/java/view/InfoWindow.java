
package view;

import com.mycompany.laba3_monst.Monster;
import com.mycompany.laba3_monst.Recipe;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author elenagoncarova
 */
public class InfoWindow extends JDialog {
    public InfoWindow(JFrame owner, Monster monster) {
        super(owner, "Детальная информация", true);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int WIDTH = 800;
        int HEIGHT = 450;
        setBounds((dimension.width - WIDTH) / 2, (dimension.height - HEIGHT) / 2, WIDTH, HEIGHT);

        int leadingPadding = 15;
        JLabel nameLabel = new JLabel("");
        nameLabel.setBounds(leadingPadding, 10, 670, 20);
        nameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        JLabel descLabel = new JLabel("Описание:");
        descLabel.setBounds(leadingPadding, 35, 100, 20);
        descLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        descLabel.setVisible(false);
        JTextArea descTextArea = new JTextArea("");
        descTextArea.setBounds(leadingPadding, 55, 670, 40);
        descTextArea.setFont(new Font("Serif", Font.PLAIN, 16));
        descTextArea.setVisible(false);
        JLabel firstMentionLabel = new JLabel("");
        firstMentionLabel.setBounds(leadingPadding, 100, 670, 20);
        firstMentionLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        JLabel dangerLabel = new JLabel("");
        dangerLabel.setBounds(leadingPadding, 130, 100, 20);
        dangerLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        JProgressBar dangerBar = new JProgressBar(0, 10);
        dangerBar.setBounds(leadingPadding + 120, 130, 150, 20);
        dangerBar.setVisible(false);
        JLabel vulnerabilityLabel = new JLabel("");
        vulnerabilityLabel.setBounds(leadingPadding, 160, 670, 20);
        JLabel heightLabel = new JLabel("");
        heightLabel.setBounds(leadingPadding, 190, 670, 20);
        heightLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        JLabel weightLabel = new JLabel("");
        weightLabel.setBounds(leadingPadding, 220, 670, 20);
        weightLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        JLabel habitatLabel = new JLabel("");
        habitatLabel.setBounds(leadingPadding, 250, 670, 20);
        habitatLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        JLabel activeTimeLabel = new JLabel("");
        activeTimeLabel.setBounds(leadingPadding, 280, 670, 20);
        activeTimeLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        JLabel recipeLabel = new JLabel("");
        recipeLabel.setBounds(leadingPadding, 310, 670, 20);
        recipeLabel.setFont(new Font("Serif", Font.PLAIN, 16));

        JLabel recipeTypeLabel = new JLabel("");
        recipeTypeLabel.setBounds(leadingPadding, 330, 670, 20);
        recipeTypeLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        JLabel recipeIngredientsLabel = new JLabel("");
        recipeIngredientsLabel.setBounds(leadingPadding, 350, 670, 20);
        recipeIngredientsLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        JLabel recipeTimeLabel = new JLabel("");
        recipeTimeLabel.setBounds(leadingPadding, 370, 670, 20);
        recipeTimeLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        JLabel recipeEffectivenessLabel = new JLabel("");
        recipeEffectivenessLabel.setBounds(leadingPadding, 390, 670, 20);
        recipeEffectivenessLabel.setFont(new Font("Serif", Font.PLAIN, 16));

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

        descTextArea.getDocument().addDocumentListener(new DocumentListener() {
            void changeDesc(String newDesc) {
                monster.setDescription(newDesc);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                changeDesc(descTextArea.getText());
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeDesc(descTextArea.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                changeDesc(descTextArea.getText());
            }
        });
    }
}