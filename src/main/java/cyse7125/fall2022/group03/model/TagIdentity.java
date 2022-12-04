package cyse7125.fall2022.group03.model;

import java.io.Serializable;
import java.util.Objects;

public class TagIdentity implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String useri;
    private String tagname;
    
    public TagIdentity() {
        
    }

    public TagIdentity(String useri, String tagname) {
        super();
        this.useri = useri;
        this.tagname = tagname;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(tagname, useri);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TagIdentity other = (TagIdentity) obj;
        return Objects.equals(tagname, other.tagname) && Objects.equals(useri, other.useri);
    }
    
}
