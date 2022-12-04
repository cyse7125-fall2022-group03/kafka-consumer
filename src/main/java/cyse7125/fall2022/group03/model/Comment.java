package cyse7125.fall2022.group03.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
//@IdClass(value = CommentIdentity.class)
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Comment {
    
    //@Id
    //private String taskId;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id",length = 32)
    @JSONField(serialize = false)
    private String commentId;
    
    private String comment;
    
    //@Column(name = "comment_created", updatable = false, nullable = false)
    @Column(name = "comment_created")
    private LocalDateTime commentCreated = LocalDateTime.now();
    //@Column(name = "comment_updated", nullable = false)
    @Column(name = "comment_updated")
    private LocalDateTime commentUpdated = LocalDateTime.now();
    
    public Comment() {
        super();
    }
    //public Comment(String taskId, String comment, String commentCreated, String commentUpdated) {
    public Comment(String comment, LocalDateTime commentCreated, LocalDateTime commentUpdated) {
        super();
        //this.taskId = taskId;
        this.comment = comment;
        this.commentCreated = commentCreated;
        this.commentUpdated = commentUpdated;
    }
//    public String getTaskId() {
//        return taskId;
//    }
//    public void setTaskId(String taskId) {
//        this.taskId = taskId;
//    }
    public String getCommentId() {
        return commentId;
    }
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public LocalDateTime getCommentCreated() {
        return commentCreated;
    }
    public void setCommentCreated(LocalDateTime commentCreated) {
        this.commentCreated = commentCreated;
    }
    public LocalDateTime getCommentUpdated() {
        return commentUpdated;
    }
    public void setCommentUpdated(LocalDateTime commentUpdated) {
        this.commentUpdated = commentUpdated;
    }
    
    

}
