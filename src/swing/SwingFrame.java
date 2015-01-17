package swing;

import control.Command;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingFrame extends JFrame{
    
    public static final int NEXT = 0;
    public static final int PREV = 1;
    private Command[] commandList;
    private SwingImageDisplay display;
    private IdleManager idleManager = new IdleManager();
    
    public SwingFrame(){
        setTitle("Image Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(createDisplay());
        add(createToolbar(), BorderLayout.SOUTH);
        pack();
    }
    
    public JPanel createDisplay(){
        display = new SwingImageDisplay();
        display.setPreferredSize(new Dimension(1280,720));
        return display;
    }

    public SwingImageDisplay getDisplay() {
        return display;
    }
    
    public void register(Command[] commandList) {
        this.commandList = commandList;
    }
    
    public void start() {
        setVisible(true);
    }
    
    private JPanel createToolbar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(createButton(PREV, "<"));
        panel.add(createButton(NEXT, ">"));
        return panel;
    }

    private JButton createButton(int operation, String label) {
        JButton button = new JButton(label);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                commandList[operation].execute();
            }
        });
        idleManager.add(new IdleTask() {
            @Override
            public void execute(){
                if (commandList == null) {
                    return;
                }
                button.setEnabled(commandList[operation].isEnabled());
            }
        });
        return button;
    }
    
    private class IdleManager extends EventQueue {
        private ArrayList<IdleTask> tasks = new ArrayList<>();
        
        public IdleManager(){
            Toolkit.getDefaultToolkit().getSystemEventQueue().push(this);
        }
        
        @Override
        protected void dispatchEvent(AWTEvent event){
            super.dispatchEvent(event);
            for (IdleTask task : tasks) {
                task.execute();
            }
        }
        
        private void add(IdleTask task){
            tasks.add(task);
        }
    }
    
    private interface IdleTask {
        public void execute();
    }
    
    
        
}
