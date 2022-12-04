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
//@IdClass(value = TagIdentity.class)
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Tag {
    
    //@Id
	@JSONField(serialize = false)
    @Column(name = "useri")
    private String useri;
    
    @Id
    @Column(name = "tagId")
    @GeneratedValue(generator = "jpa-uuid")
    @JSONField(serialize = false)
    private String tagId;
    
    @Column(name = "tagname")
    private String tagname;
    
    @Column(name = "tag_created", updatable = false, nullable = false)
    private LocalDateTime tagCreated;
    @Column(name = "tag_updated", nullable = false)
    private LocalDateTime tagUpdated;
    
    //cant have task id as would not know while creating
    //so better is using foreign key concept, which auto populates
    /*@JSONField(serialize = false)
    @ManyToOne(targetEntity = Task.class, cascade = {CascadeType.PERSIST})
    @JoinColumns({
        @JoinColumn(name = "listId", referencedColumnName = "listId"),
        @JoinColumn(name = "taskId", referencedColumnName = "taskId"),
        @JoinColumn(name = "userId", referencedColumnName = "userId")
    })
    private Task taskObject;
    */
    
    public Tag() {
        super();
    }


    public Tag(String useri, String tagname, LocalDateTime tagCreated, LocalDateTime tagUpdated) {
        super();
        this.useri = useri;
        this.tagname = tagname;
        this.tagCreated = tagCreated;
        this.tagUpdated = tagUpdated;
        //this.taskObject = taskObject;
    }

//    public Task getTaskObject() {
//		return taskObject;
//	}
//
//
//	public void setTaskObject(Task taskObject) {
//		this.taskObject = taskObject;
//	}


	public String getUseri() {
        return useri;
    }

    public void setUseri(String useri) {
        this.useri = useri;
    }

    public String getTagname() {
        return tagname;
    }


    public void setTagname(String tagname) {
        this.tagname = tagname;
    }


    public LocalDateTime getTagCreated() {
        return tagCreated;
    }


    public void setTagCreated(LocalDateTime tagCreated) {
        this.tagCreated = tagCreated;
    }


    public LocalDateTime getTagUpdated() {
        return tagUpdated;
    }


    public void setTagUpdated(LocalDateTime tagUpdated) {
        this.tagUpdated = tagUpdated;
    }


	public String getTagId() {
		return tagId;
	}


	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

   
}
