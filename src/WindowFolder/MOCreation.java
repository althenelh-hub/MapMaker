package WindowFolder;
import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.colorchooser.DefaultColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import Data.*;
import Factories.MObjectAdder;
import PaintInstructions.*;
import WindowFolder.ComponentFactories.*;
import WindowFolder.PaintPanel.MultiPaintPanel;

public class MOCreation extends JDialog {

    public MOCreation(MObjectAdder mObjectAdder, PaintAdder paintAdder, GameObjectPanel pane,
                      FormFactoryManager formFactoryManager, String currentFactoryName) {
        //Dialog settings
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(1000,800));
        setLayout(new BorderLayout());
        //Dialog settings
        //Name field and finishAction
        JLabel name = new JLabel("Name:");
        String mObjectName = "";
        ConfirmationField field = new ConfirmationField(Color.WHITE, Color.ORANGE, mObjectName);
        Form form = new Form(mObjectName);
        field.addActionStrategy(new UpdateValueAction(form, field));
        JPanel namePanel = new JPanel();
        namePanel.add(name);
        namePanel.add(field);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        //Name field and finish action
        //characteristics field and adder
        JPanel panel = new JPanel();
        panel.add(namePanel);
        panel.setAlignmentY(TOP_ALIGNMENT);
        MOManagePanel managePanel = new MOManagePanel();
        panel.add(managePanel);
        JButton button = new JButton("Add");
        MOCreationLink link = new MOCreationLink(managePanel, form);
        MObjectData<InfoModifier> data = new MObjectData<>(form, managePanel);
        button.addActionListener(new AddField(data));
        panel.add(button);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel, BorderLayout.WEST);
        //characteristics field and adder
        //Preview and color chooser
        JColorChooser chooser = new JColorChooser();
        JPanel previewAndMode = new JPanel();
        FormManager formManager = formFactoryManager.createFormManager();
        MultiPaintPanel paintPanel = new MultiPaintPanel(formManager, new Point(100,100));
        paintPanel.setInstruction(formManager.getForm("Forest"));
        ChooseColor chooseColor = new ChooseColor(paintPanel);
        MObjectBox box = new MObjectBox(mObjectAdder, paintPanel , link,  currentFactoryName);
        field.addCheckers(new InputChecker[]{new NameFormatChecker(), new InputChecker() {
            @Override
            public CheckMessage check(String value) {
                CheckMessage message = new CheckMessage(true, "");
                if(mObjectAdder.contains(box.getItemAt(box.getSelectedIndex()), field.getText())) {
                    message.setValidation(false);
                    message.setError("Name: " + field.getText() + " already exists.");
                }
                return message;

            }
        }});

        FinishAction finishAction = new FinishAction(mObjectAdder, form, paintAdder, formManager, pane, box.getHolder());
        JButton finish = new JButton();
        finish.addActionListener(finishAction);
        add(finish, BorderLayout.EAST);
        previewAndMode.setLayout(new BoxLayout(previewAndMode, BoxLayout.Y_AXIS));
        previewAndMode.add(box);

        previewAndMode.add(paintPanel);
        chooser.getSelectionModel().addChangeListener(chooseColor);
        add(previewAndMode, BorderLayout.CENTER);
        add(chooser, BorderLayout.SOUTH);
        //Preview and color chooser
        setVisible(true);
    }
}
interface Holder<T> {
    T getObject();
}
class MObjectBox extends JComboBox<String> implements Holder<String>{
    public MObjectBox(MObjectAdder mObjectAdder, MultiPaintPanel preview,
                      MOCreationLink link, String currentName) {
        addItemListener(new ChangePreview(preview));
        addItemListener(new EmptyData(link));
        setItems(mObjectAdder, currentName);
    }
    public void setItems(MObjectAdder adder, String currentName) { //currentName cause add button panel can be different from
        String[] names = adder.getFactoryNames();                  //current factory
        for(String s : names) {
            addItem(s);
        }
        setSelectedItem(currentName);
    }
    public String getObject() {
        return getItemAt(getSelectedIndex());
    }
    public Holder<String> getHolder() {
        return this;
    }
}
class ChangePreview implements ItemListener {
    MultiPaintPanel paintPanel;
    public ChangePreview(MultiPaintPanel paintPanel) {
        this.paintPanel = paintPanel;
    }
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
            paintPanel.changeForm((String)e.getItem());
            paintPanel.repaint();
        }
    }
}
class EmptyData implements ItemListener {
    MOCreationLink link;
    public EmptyData(MOCreationLink link) {
        this.link = link;
    }
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
            link.empty();
        }
    }
}
class FinishAction implements ActionListener {
    MObjectAdder mObjectAdder;
    Holder<String> factoryName;
    Form form;
    PaintAdder paintAdder;
    FormManager formManager;
    GameObjectPanel panel;
    public FinishAction(MObjectAdder mObjectAdder, Form form,
                        PaintAdder paintAdder, FormManager formManager, GameObjectPanel panel, Holder<String> holder) {
        this.mObjectAdder = mObjectAdder;
        this.form = form;
        this.paintAdder = paintAdder;
        this.formManager = formManager;
        this.panel = panel;
        factoryName = holder;
    }
    public void actionPerformed(ActionEvent e) {
        mObjectAdder.store(factoryName.getObject(), form);
        paintAdder.addForm(form.getName(), formManager.getForm(factoryName.getObject()));
        panel.updateButtons(factoryName.getObject());
        panel.dialogManager.release(DialogNames.MOCreation);


    }
}
class ChooseColor implements ChangeListener {
    Receiver<Color> paintPanel;
    public ChooseColor(Receiver<Color> paintPanel) {
        this.paintPanel = paintPanel;
    }
    public void stateChanged(ChangeEvent e) {
        ColorSelectionModel model = (DefaultColorSelectionModel)e.getSource();
        Color color = model.getSelectedColor();
        paintPanel.receive(color);
    }
}








