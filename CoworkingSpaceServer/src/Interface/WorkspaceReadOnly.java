package Interface;

import Server.RoomService;
import java.util.ArrayList;
import Utility.Globals.*;

public interface WorkspaceReadOnly {
    public String getId();
    public int getLevel();
//  public WorkspaceType getType();
    public ArrayList<RoomService> getServices();
    public WorkspaceStatus getStatus();
    
    public WorkspaceName getName();
    public int getCapacity();
    public String getDescription();
    public float getCost();

}
