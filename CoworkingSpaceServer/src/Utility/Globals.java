package Utility;

public class Globals {
    public enum WorkspaceStatus {
        AVAILABLE,
        OCCUPIED,
        SHARED,
        UNDER_MAINTENANCE;
    }
    
    public enum WorkspaceName {
        OPEN_WORKSPACE,
        DEDICATED_DESK,
        STANDARD_DESK,
        OFFICE_SUITE,
        EVENT_SPACE;
    }
    
    public enum ReservationStatus {
        RESERVED,
        ONGOING,
        COMPLETED,
        CANCELED
    }

    public enum OrderStatus {
        INPROGRESS,
        RECEIVED
    }
}
