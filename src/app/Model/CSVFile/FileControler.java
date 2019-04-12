package app.Model.CSVFile;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FileControler {
    private String nameFile=null;

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public EntityTable[] open(String name){
        setNameFile(name);
        EntityTable[] lines =null;
        try{
            FileInputStream reader = new FileInputStream(name);
            InputStreamReader isr = new InputStreamReader(reader,"Windows-1251");
            FileModule file = new FileModule();
            BufferedReader read = new BufferedReader(isr);
            System.out.println(read.readLine());
            lines = file.read(new BufferedReader(isr));

        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return lines;

    }
    public ArrayList<EntityTable> open2(String name){
        setNameFile(name);
        ArrayList<EntityTable> lines =null;
        try{
            FileInputStream reader = new FileInputStream(name);
            InputStreamReader isr = new InputStreamReader(reader,"Windows-1251");
            FileModule file = new FileModule();
            BufferedReader read = new BufferedReader(isr);
            lines = file.read2(new BufferedReader(isr));

        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return lines;

    }
    public EntityTable[] update(){
        EntityTable[] result=null;
        if(nameFile!=null) result=open(nameFile);
        return result;
    }
    public void addline(EntityTable... str){
        try{
            EntityTable[] table =update();
            FileOutputStream writer = new FileOutputStream(nameFile);
            OutputStreamWriter wr = new OutputStreamWriter(writer,"Windows-1251");
            FileModule file = new FileModule();;
            file.add(wr,concatenate(table,str));
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }

    }
    public void edit(EntityTable... tables){
        try{
            FileOutputStream writer = new FileOutputStream(nameFile);
            OutputStreamWriter wr = new OutputStreamWriter(writer,"Windows-1251");
            FileModule file = new FileModule();
            file.edit(wr,tables);
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    private EntityTable[] concatenate (EntityTable[] A, EntityTable[] B) {
        int aLen = A.length;
        int bLen = B.length;

        @SuppressWarnings("unchecked")
        EntityTable[] C = (EntityTable[]) Array.newInstance(A.getClass().getComponentType(), aLen+bLen);
        System.arraycopy(A, 0, C, 0, aLen);
        System.arraycopy(B, 0, C, aLen, bLen);

        return C;
    }
}
