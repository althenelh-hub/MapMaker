package WindowFolder;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Terrain.*;
import Factories.*;
public class GameObjectPanel extends JTabbedPane {

    public void test() {
        JPanel panel = new JPanel();
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            Factory<Unit> factory;
            GameName name;
            @Override
            public void actionPerformed(ActionEvent e) {
                factory.change(name);
            }
        });
        panel.add(button);
        addTab("Units", panel);
        JButton creator = new JButton();
        creator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add(new CreationMenu());
            }
        });
    }
}
