package cyse7125.fall2022.group03.model;

import java.io.Serializable;
import java.util.Objects;

public class TaskIdentity implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String userId;
    private String listId;
    private String taskId;
    
    public TaskIdentity() {
        
    }
    public TaskIdentity(String userId, String listId, String taskId) {
        super();
        this.userId = userId;
        this.listId = listId;
        this.taskId = taskId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getListId() {
        return listId;
    }
    public void setListId(String listId) {
        this.listId = listId;
    }
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(listId, taskId, userId);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TaskIdentity other = (TaskIdentity) obj;
        return Objects.equals(listId, other.listId) && Objects.equals(taskId, other.taskId)
                && Objects.equals(userId, other.userId);
    }
    
}
