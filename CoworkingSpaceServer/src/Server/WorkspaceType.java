package Server;

import Utility.Globals.*;

abstract public class WorkspaceType {
    private WorkspaceName name;
    private int capacity;
    private String description;
    private float cost;

    
    // Constructors
    public WorkspaceType(WorkspaceName name, int capacity, String description, float cost) {
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.cost = cost;
    }

    // Setters & Getters
    public WorkspaceName getName() {
        return name;
    }

    public void setName(WorkspaceName name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
      
    // Methods
}
