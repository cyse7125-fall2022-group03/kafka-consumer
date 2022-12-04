package cyse7125.fall2022.group03.model;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
//import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;


@Data
@Entity
@IdClass(value = TaskIdentity.class)
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
//@Document(indexName="taskIndex")
public class Task {

    @Id
    private String userId;
    @Id
    private String listId;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "taskId",length = 32)
    private String taskId;

    private String summary;

    private String name;
    private LocalDateTime dueDate;
    @Column(name = "account_created", updatable = false, nullable = false)
    private LocalDateTime accountCreated;
    @Column(name = "account_updated", nullable = false)
    private LocalDateTime accountUpdated;

    @Enumerated(EnumType.STRING)
    private Priority priority;
    public enum Priority {
        high, medium, low
    }

    @Enumerated(EnumType.STRING)
    private Status status;
    public enum Status {
        TODO, OVERDUE, COMPLETE
    }

    //biDirectional oneToMany ManyToOne, so that Tag can access Taskid fields in TagRepository query update
    //@OneToMany(targetEntity = Tag.class,cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "taskObject")
    @OneToMany(targetEntity = Tag.class,cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "listId"),
            @JoinColumn(name = "taskId"),
            @JoinColumn(name = "userId")
    })
    private List<Tag> tagList;

    @OneToMany(targetEntity = Comment.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "listId"),
            @JoinColumn(name = "taskId"),
            @JoinColumn(name = "userId")
    })
    private List<Comment> commentList;

    @OneToMany(targetEntity = Remainder.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "listId"),
            @JoinColumn(name = "taskId"),
            @JoinColumn(name = "userId")
    })
    private List<Remainder> remainderList;


    public Task() {
        super();
    }


    public Task(String userId, String listId, String summary, String name, LocalDateTime dueDate, LocalDateTime accountCreated,
                LocalDateTime accountUpdated, Priority priority, Status status, List<Tag> tagList, List<Comment> commentList,
                List<Remainder> remainderList) {
        super();
        this.userId = userId;
        this.listId = listId;
        this.summary = summary;
        this.name = name;
        this.dueDate = dueDate;
        this.accountCreated = accountCreated;
        this.accountUpdated = accountUpdated;
        this.priority = priority;
        this.status = status;
        this.tagList = tagList;
        this.commentList = commentList;
        this.remainderList = remainderList;
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


    public String getSummary() {
        return summary;
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {

        this.dueDate = dueDate;
    }


    public LocalDateTime getAccountCreated() {
        return accountCreated;
    }


    public void setAccountCreated(LocalDateTime accountCreated) {
        this.accountCreated = accountCreated;
    }


    public LocalDateTime getAccountUpdated() {
        return accountUpdated;
    }


    public void setAccountUpdated(LocalDateTime accountUpdated) {
        this.accountUpdated = accountUpdated;
    }


    public Priority getPriority() {
        return priority;
    }


    public void setPriority(Priority priority) {
        this.priority = priority;
    }


    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }


    public List<Tag> getTagList() {
        return tagList;
    }


    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }


    public List<Comment> getCommentList() {
        return commentList;
    }


    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }


    public List<Remainder> getRemainderList() {
        return remainderList;
    }


    public void setRemainderList(List<Remainder> remainderList) {
        this.remainderList = remainderList;
    }

}
