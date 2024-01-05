package model;

public abstract class PackedAnimals extends Animals{
    public PackedAnimals(String name, String skills) {
        super(name, skills);
    }
    public abstract void displayCommands();

    public abstract void teachNewCommand(String command);
}
