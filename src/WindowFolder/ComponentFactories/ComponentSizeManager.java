package WindowFolder.ComponentFactories;


import java.awt.*;

public class ComponentSizeManager {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Component leftComponent;
    Component mainComponent;
    Component centerComponent;
    Component rightComponent;
    public ComponentSizeManager(Component leftComponent, Component mainComponent, Component centerComponent,
                                Component rightComponent) {
        this.leftComponent = leftComponent;
        this.mainComponent = mainComponent;
        this.centerComponent = centerComponent;
        this.rightComponent = rightComponent;
    }
    public void sizeControl() {
        leftComponent.setMinimumSize(new Dimension(screenSize.width/12, screenSize.height*3/5));
        leftComponent.setPreferredSize(new Dimension(screenSize.width/10, screenSize.height*3/5));
        mainComponent.setMinimumSize(new Dimension(screenSize.width/2, screenSize.height/2));
        centerComponent.setMinimumSize(new Dimension(screenSize.width/5, screenSize.height/5));
        rightComponent.setPreferredSize(new Dimension(screenSize.width/5, screenSize.height*3/5));
        rightComponent.setMinimumSize(new Dimension(screenSize.width/5, screenSize.height*3/5));
    }
}
