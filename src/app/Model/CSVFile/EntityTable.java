package app.Model.CSVFile;

public class EntityTable {
    private String name;
    private String type;
    private String date;
    public EntityTable(String adress, String mode, String date){
        this.name =adress;
        this.type =mode;
        this.date=date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append(name)
                .append(',')
                .append(type)
                .append(',')
                .append(date);
        return buf.toString();

    }
}
