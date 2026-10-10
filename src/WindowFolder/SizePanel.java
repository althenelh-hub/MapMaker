package WindowFolder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SizePanel extends JPanel {
    SizeController columnController;
    SizeController rowController;
    SizeController cellSizeController;
    MapControl control;
    public SizePanel(int xStart, int yStart, int startCellSize, MapControl mapControl) {
        this.control = mapControl;
        columnController = new SizeController(xStart);
        columnController.addToComponent(this);
        columnController.addListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormatChecker checker = new FormatChecker();
                String text = columnController.field.getText();
                if(checker.checkOnDigits(text)) {
                    control.addColumns(Integer.parseInt(text));
                }
            }
        }, new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                control.addColumns(columnController.slider.getValue());
            }
        });
        rowController = new SizeController(yStart);
        rowController.addToComponent(this);
        rowController.addListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormatChecker checker = new FormatChecker();
                String text = rowController.field.getText();
                if(checker.checkOnDigits(text)) {
                    control.addRows(Integer.parseInt(text));
                }
            }
        }, new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                control.addRows(rowController.slider.getValue());
            }
        });
        cellSizeController = new SizeController(startCellSize);
        cellSizeController.addToComponent(this);
        cellSizeController.addListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormatChecker checker = new FormatChecker();
                String text = cellSizeController.field.getText();
                if(checker.checkOnDigits(text)) {
                    mapControl.changeCellSize(Integer.parseInt(text));
                }
            }
        }, new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mapControl.changeCellSize(cellSizeController.slider.getValue());
            }
        });
    }

}
