package cyse7125.fall2022.group03.model;

import java.io.Serializable;
import java.util.Objects;

public class RemainderIdentity implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String taskID;
    private String remainderId;
    
    public RemainderIdentity() {
        
    }
    public RemainderIdentity(String taskID, String remainderId) {
        super();
        this.taskID = taskID;
        this.remainderId = remainderId;
    }
    public String getTaskID() {
        return taskID;
    }
    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }
    public String getRemainderId() {
        return remainderId;
    }
    public void setRemainderId(String remainderId) {
        this.remainderId = remainderId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(remainderId, taskID);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RemainderIdentity other = (RemainderIdentity) obj;
        return Objects.equals(remainderId, other.remainderId) && Objects.equals(taskID, other.taskID);
    }
       

}
