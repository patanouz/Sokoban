package sokoban;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
    
    private Component component;
    private Figure figure;

    public KeyboardListener(Component component, Figure figure) {
        this.figure = figure;
        this.component = component;
    }

    @Override
    public void keyPressed(KeyEvent e) {
   
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                figure.move(-100, 0);
                break;
            case KeyEvent.VK_RIGHT:
                figure.move(100, 0);
                break;
            case KeyEvent.VK_UP:
                figure.move(0, -75);
                break;
            case KeyEvent.VK_DOWN:
                figure.move(0, 75);
                break;
            default:
                break;
        }
        
        component.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
}