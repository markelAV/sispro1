package app.Model.CSVFile;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for working with files CSV
 * @Documentation this class
 * todo must describe the functionality of this class
 *  create(0), read(1), update(edit)(0), delete(0)
 *
 */
public class FileModule {

    /**
     * Retrieves a subset of the content.
     *
     * @param read the start
     * @return Array of EntityTable(table rows)
     * @throws StringIndexOutOfBoundsException
     */
    public EntityTable[] read(Reader read) throws IOException {
        CSVReader reader = new CSVReader(read);
        List<String[]> list = reader.readAll();
        EntityTable[] lines = new EntityTable[list.size()];
        String[] str;
        for (int i = 0; i <list.size() ; i++) {
           str=list.get(i);
           if(str.length==3){
               lines[i]=new EntityTable(str[0],str[1],str[2]);
           }
        }
        return lines;
    }
    public ArrayList<EntityTable> read2(BufferedReader r) throws IOException {
        ArrayList<EntityTable> list = new ArrayList<EntityTable>();
        String str;
        String[] lines;
        str = r.readLine();
        while(str!=null){
            lines=str.split(",");
            if(lines.length==3){
                list.add(new EntityTable(lines[0],lines[1],lines[2]));
            }
            str=r.readLine();

        }
        return list;
    }


    /**
     * Adds one or more lines to the file CSV.
     *
     * @param write The range of text to replace. The range object must not be null.
     * @param line The text that is to replace the range. This must not be null.
     *
     */
    //todo this methods intended for add Lines maybe must change this method on edit CSV file (or create such a method)
    public void add(Writer write, EntityTable... line) throws IOException{ //todo maybe change Writer om BufferedWriter
        if(line==null) throw new NullPointerException();
        BufferedWriter wr = new BufferedWriter(write);
        CSVWriter writer = new CSVWriter(wr);
        String[] s;
        for (int i = 0; i <line.length ; i++) {
            s = line[i].toString().split(",");
            writer.writeNext(s);
        }
        writer.flush();
    }
    /**
     * Adds one or more lines to the file CSV.
     *
     * @param write The range of text to replace. The range object must not be null.
     * @param line The text that is to replace the range. This must not be null.
     *
     */
    public void edit(Writer write, EntityTable... line) throws IOException{ //todo maybe change Writer om BufferedWriter
        if(line==null) throw new NullPointerException();
        BufferedWriter w = new BufferedWriter(write);
        CSVWriter writer = new CSVWriter(w);
        List<String[]> list = new LinkedList<String[]>();
        String[] s;
        for (int i = 0; i <line.length ; i++) {
            s = line[i].toString().split(",");
            list.add(s);
        }
        writer.writeAll(list);
        writer.flush();
    }
    public void createNewFile(String name){

    }
    public void saveAs(String name){
        createNewFile(name);
    }



}
