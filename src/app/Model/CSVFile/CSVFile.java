package app.Model.CSVFile;

import java.io.*;
import java.util.ArrayList;

public class CSVFile {
    private String nameFile;

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public CSVFile(String nameFile) {
        this.nameFile = nameFile;
    }
    public ArrayList<EntityTable> read(String name) throws IOException{
        setNameFile(name);
        ArrayList<EntityTable> list =new ArrayList<>();

        FileInputStream reader = new FileInputStream(name);
        InputStreamReader isr = new InputStreamReader(reader,"Windows-1251");
        FileModule file = new FileModule();
        BufferedReader read = new BufferedReader(isr);
        String str;
        String[] lines;
        str = read.readLine();
        while(str!=null){
            lines=str.split(",");
            if(lines.length==3){
                list.add(new EntityTable(lines[0],lines[1],lines[2]));
            }
            str=read.readLine();
        }
        return list;
    }
    public void write(ArrayList<EntityTable> lines) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(nameFile));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <lines.size() ; i++) {
            builder.append(lines.get(i).getName())
                    .append(',')
                    .append(lines.get(i).getType())
                    .append(',')
                    .append(lines.get(i).getDate())
                    .append('\n'); //todo возможно это не нужно

        }
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }
}
