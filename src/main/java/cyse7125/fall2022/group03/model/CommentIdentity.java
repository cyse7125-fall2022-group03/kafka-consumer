package cyse7125.fall2022.group03.model;

import java.io.Serializable;
import java.util.Objects;

public class CommentIdentity implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String taskId;
    private String commentId;
    
    public CommentIdentity(String taskId, String commentId) {
        super();
        this.taskId = taskId;
        this.commentId = commentId;
    }
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getCommentId() {
        return commentId;
    }
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(commentId, taskId);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CommentIdentity other = (CommentIdentity) obj;
        return Objects.equals(commentId, other.commentId) && Objects.equals(taskId, other.taskId);
    }
    
    
}
