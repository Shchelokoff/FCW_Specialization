package model;

public class Camel extends PackedAnimals {
    public Camel(String name, String skills) {
        super(name, skills);
    }

    @Override
    public void displayCommands() {
        System.out.println("Команды для верблюда " + getName() + ": " + getSkills());
    }

    @Override
    public void teachNewCommand(String command) {
        String updatedSkills = getSkills() + "," + command;
        setSkills(updatedSkills);
        System.out.println("Верблюд " + getName() + " научился новой команде: " + command);
    }
}