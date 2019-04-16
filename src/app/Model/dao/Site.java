package app.Model.dao;

import java.io.Serializable;

public class Site implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String Name;
    private String Type;
    private String data;

    public Site(String name, String type, String data ) {
        this.Type = name;
        this.data = type;
        this.Name=name;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Integer getId() {
        return id;
    }
}
