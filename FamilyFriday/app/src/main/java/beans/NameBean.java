package beans;

/**
 * Created by vikra on 3/11/2018.
 */

public class NameBean {
/*

Name bean will have
database id and the
name of the
employee
 */
    private Long nameId;
    private String name;

    public NameBean(){
        super();
    }

    public NameBean(long nameId, String name){
        this.nameId=nameId;
        this.name=name;
    }

    public Long getNameId() {
        return nameId;
    }

    public void setNameId(Long nameId) {
        this.nameId = nameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
