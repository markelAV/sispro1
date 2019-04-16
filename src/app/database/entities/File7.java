package app.database.entities;

import javax.persistence.*;

@Entity(name = "File7")
@Table(name = "File7", uniqueConstraints = @UniqueConstraint(columnNames = {"ADDRESS", "ACCESS","DATA"}))
public class File7 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "ACCESS",  nullable = false)
    private String access;

    @Column(name = "DATA", nullable = false)
    private String data;
    //@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class, cascade = CascadeType.ALL)
    //private User user;

    public File7() {
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "File7{" +
                "address=" + address + " , " +
                "access=" + access + " , " +
                "data=" + data +
                '}';
    }
}

