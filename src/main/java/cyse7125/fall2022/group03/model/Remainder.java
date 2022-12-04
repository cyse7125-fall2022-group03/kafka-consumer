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
//@IdClass(value = RemainderIdentity.class)
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Remainder {
    
    //@Id
    //private String taskId;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id",length = 32)
    @JSONField(serialize = false)
    private String remainderId;
    
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    //@JsonSerialize(using = LocalDateTimeSerializer.class)
    //@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    
    LocalDateTime dateTime = LocalDateTime.now();
    
    //@Column(name = "remainder_created", updatable = false, nullable = false)
    @Column(name = "remainder_created")
    private LocalDateTime remainderCreated = LocalDateTime.now();
    @Column(name = "remainder_updated")
    private LocalDateTime remainderUpdated = LocalDateTime.now();
    public Remainder() {
        super();
    }
    //public Remainder(String taskId, LocalDateTime dateTime, String remainderCreated, String remainderUpdated) {
    public Remainder(LocalDateTime dateTime, LocalDateTime remainderCreated, LocalDateTime remainderUpdated) {
        super();
        //this.taskId = taskId;
        this.dateTime = dateTime;
        this.remainderCreated = remainderCreated;
        this.remainderUpdated = remainderUpdated;
    }
//    public String getTaskId() {
//        return taskId;
//    }
//    public void setTaskId(String taskId) {
//        this.taskId = taskId;
//    }
    public String getRemainderId() {
        return remainderId;
    }
    public void setRemainderId(String remainderId) {
        this.remainderId = remainderId;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public LocalDateTime getRemainderCreated() {
        return remainderCreated;
    }
    public void setRemainderCreated(LocalDateTime remainderCreated) {
        this.remainderCreated = remainderCreated;
    }
    public LocalDateTime getRemainderUpdated() {
        return remainderUpdated;
    }
    public void setRemainderUpdated(LocalDateTime remainderUpdated) {
        this.remainderUpdated = remainderUpdated;
    }
    
}
