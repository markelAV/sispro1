package app.database.entities;

import javax.persistence.*;

@Entity(name = "File3")
@Table(name = "File3", uniqueConstraints = @UniqueConstraint(columnNames = {"LOCATION", "SIZE","EDITDATE"}))
public class File3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "LOCATION", nullable = false)
    private String location;

    @Column(name = "SIZE",  nullable = false)
    private int size;

    @Column(name = "EDITDATE", nullable = false)
    private String editDate;
    //@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class, cascade = CascadeType.ALL)
    //private User user;

    public File3() {
    }

    public File3(String location, int size, String editDate) {
        this.location = location;
        this.size = size;
        this.editDate = editDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    @Override
    public String toString() {
        return "File3{" +
                "location=" + location + " , " +
                "size=" + size + " , " +
                "editDate=" + editDate +
                '}';
    }
}

