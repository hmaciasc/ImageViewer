package control;

import ui.ImageDisplay;

public class PrevImageCommand implements Command{
    
    private final ImageDisplay display;

    public PrevImageCommand(ImageDisplay display) {
        this.display = display;
    }

    @Override
    public boolean isEnabled() {
        return display.getImage().getNext() != null;
    }

    @Override
    public void execute() {
        display.setImage(display.getImage().getPrev());
    }
    
    
}
