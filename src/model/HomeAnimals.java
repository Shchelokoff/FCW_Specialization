package model;

public abstract class HomeAnimals extends Animals{
    public HomeAnimals(String name, String skills) {
        super(name, skills);
    }
    public abstract void displayCommands();

    public abstract void teachNewCommand(String command);
}
