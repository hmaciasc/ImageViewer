package application;

import control.Command;
import control.NextImageCommand;
import control.PrevImageCommand;
import filesystem.FileImageLoader;
import javafx.embed.swing.SwingFXUtils;
import model.Image;
import swing.SwingFrame;
import swing.SwingImageDisplay;
import ui.ImageDisplay;

public class App {
    public static void main(String[] args) {
        Image image = new FileImageLoader(args[0]).load();
        SwingFrame frame = new SwingFrame();
        frame.getDisplay().setImage(image);
        frame.register(createOperations(frame.getDisplay()));
        frame.start();
        
    }

    private static Command[] createOperations(ImageDisplay display) {
        Command[] commandList = new Command[2];
        commandList[SwingFrame.NEXT] = new NextImageCommand(display);
        commandList[SwingFrame.PREV] = new PrevImageCommand(display);
        return commandList;
    }
}
