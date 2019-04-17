package app.Model.While;

import java.util.ArrayList;
import java.util.HashMap;
//todo Writing Documentation for this class and her methods and fields
//todo Complete exceptions

/**
 * Documentation for this Class WhileModule
 */
public class WhileModule { //todo перейти на регулрки
    private HashMap<String,Double> ident; // todo can be preserved conditions in the form of a tree
    private ArrayList<Double> listConst;
    private ArrayList<String> listName;
    private String operation;
    private String text;
    private boolean flagDo=true;
    private boolean flagError =true;

    public boolean isFlagDo(){return flagDo;}
    public boolean isFlagError(){return flagError;}

    public WhileModule() {
        this.ident = new HashMap<String, Double>();
        this.listConst = new ArrayList<Double>();
        this.listName = new ArrayList<String>();
        this.text="";
    }

    public boolean control(String text) throws InvalidOperationException,ParseIdentifierException,ParseConstException{
        //todo implement
        this.text=text.toLowerCase();
        String condition = highlightСondition();
        if(condition!=null){
            parseCondition(condition);
            if(controlValueOfIdent()){
                flagError=false;
                return controlCondition();
            }

        }
        return false;
    }
    private boolean controlCondition() throws InvalidOperationException{
        boolean result =false;
        double op1 =0;
        double op2=0;
        if(listName.size()==1 && listConst.size()==0){
            return Boolean.parseBoolean(listName.get(0));
        }
        if(listName.size()==1 && listConst.size()==1){ //todo по идее не првавильно так как порядок следования не известен
            op1=ident.get(listName.get(0));
            op2=listConst.get(0);
        }
        if(listName.size()==2){
            op1=ident.get(listName.get(0));
            op2=ident.get(listName.get(1));
        }
        if(operation.equals(">")){
            return op1>op2;
        }
        else if(operation.equals("<")){
            return  op1<op2;
        }
        else if(operation.equals("==")){
            return  op1==op2;
        }
        else if(operation.equals(">=") || operation.equals("=>")){
            return op1>=op2;
        }
        else if(operation.equals("<=") || operation.equals("=<")) {
            return op1<=op2;
        }
        else{
            throw new InvalidOperationException(); //todo на всякий случай
        }
    }
    private String highlightСondition(){
        StringBuilder builder = new StringBuilder(text);
        String result = null;
        int begin = text.indexOf(" while");
        if (begin == -1) {
            begin = text.indexOf("\twhile");
            if(begin==-1){
                begin = text.indexOf("\nwhile");
                if(begin==-1){
                    return null;
                }
            }
        }
        int s1 =0;
        int s2=0;
        int i=begin+6;
        while(i<text.length() && text.charAt(i)!='('){i++;}
        if(i<text.length()-6){ //todo What is 6 this is last element(min) maybe netochno
            s1++;
            i++;
            begin=i;
            while (i < text.length()&& s1 > s2 ) {
                if(text.charAt(i)=='(') s1++;
                if(text.charAt(i)==')') s2++;
                i++;
            }
            if(i<text.length()){
                result=text.substring(begin,i-1);
                builder.delete(begin-6,i);
                this.text=builder.toString();
                deleteBlockWhile(begin-1);
            }
        }
        return result;
    }
    private void deleteBlockWhile(int begin){
        StringBuilder builder = new StringBuilder(text);
        int end=begin; //todo xz
        int i = text.indexOf(" do ");
        if (i == -1) {
            i = text.indexOf("\tdo ");
            if(i==-1) {
                i = text.indexOf("\ndo ");
            }
        }
        if(i==-1){
            flagDo=false;
            i=begin;
            while (i<text.length() && text.charAt(i) !='{') { i++;}
            if(i<text.length()){
                end=i;
                while(end<text.length() && text.charAt(end) !='}'){ end++;}
                if(end<text.length()){
                    builder.delete(i,end);
                }
            }
        }
        else{
            if(i<begin){
                builder.delete(i,begin);
            }
        }
        this.text=builder.toString();
    }

    private void parseCondition(String condit) throws ParseConstException,ParseIdentifierException, InvalidOperationException{ //todo complete!
        int i =0;
        while(i<condit.length() && condit.charAt(i)==' ' && condit.charAt(i)=='\t' ){i++;}
        if(i<condit.length() && i>=0) {
            while (i < condit.length()) {
                if (Character.isDigit(condit.charAt(i)) || (condit.charAt(i) == '-' && Character.isDigit(condit.charAt(i + 1)))) {
                    i=parseConst(condit,i);
                    if(i<0) throw new ParseConstException(); //todo подуматғ и выкидывать исключение в методе "выше"
                }
                else if(condit.charAt(i)==' ' || condit.charAt(i)=='\t'){
                    while(i<condit.length() && (condit.charAt(i)==' '||condit.charAt(i)=='\t')){i++;}
                }
                else if (condit.charAt(i)=='>' || condit.charAt(i)=='<' || condit.charAt(i)=='='){
                    i=parseOperationComp(condit,i);
                    if(i<0) throw new InvalidOperationException();
                }
                else{
                    i=parseIdent(condit,i);
                    if(i<0) throw new ParseIdentifierException("Ошибка в имени переменной");
                }


            }
        }
    }

    private int parseConst(String str, int index) {
        int result = -1;
        StringBuilder builder = new StringBuilder();
        while (index < str.length() && Character.isDigit(str.charAt(index))) {
            builder.append(str.charAt(index));
            index++;
        }
        if (str.length()>0) {
            listConst.add(Double.parseDouble(builder.toString()));
            result = index;
        }
        return result;
    }
    public int parseIdent(String str, int index) {   //todo maybe check keywords for a separate method
        int result = -1;
        StringBuilder builder = new StringBuilder();
        while (index < str.length() && (Character.isLetter(str.charAt(index))||(Character.isDigit(str.charAt(index))))) {
            builder.append(str.charAt(index));
            index++;
        }
        if (str.length()>0) {
            String p = builder.toString();
            if(p.equals("true"))
            {
                listName.add(p);

            }
            else if(p.equals("false")){
                listName.add(p);
            }
            else{
                listName.add(p);
                ident.put(p,null);
                result = index;
            }
        }
        return result;
    }
    private int parseOperationComp(String str, int index) {
        int result = -1;
        StringBuilder builder = new StringBuilder();
        while (index < str.length()-1 && (str.charAt(index)=='=' || str.charAt(index)=='<' || str.charAt(index)=='>')){
            builder.append(str.charAt(index));
            index++;
        }
        if(index<str.length()-1){
            operation=builder.toString();
            result=index;
        }
        return result;
    }
    private boolean controlValueOfIdent() throws ParseIdentifierException{
        boolean result = true;
        int i=0;
        while(i<listName.size() && variableInitializationСheck(listName.get(i))){
            //поиск и инициализация переменных
            i++;
        }
        if(i<listName.size()) throw new ParseIdentifierException("Ошибка переменная не проинициализирована.");

        return i==listName.size(); //todo return i=listName.size();
    }
    private boolean variableInitializationСheck(String name){
       //todo переделать под регулярки String[] lines=text.split("\\s*(name)\\s*(=)\\s(-)")
        int i = text.indexOf(name+'=');
        StringBuilder builser = new StringBuilder();
        if(i!=-1){
            i+=name.length()+1;
            while(i<text.length()&&Character.isDigit(text.charAt(i))){ //todo без учета отрицательных значений
                builser.append(text.charAt(i));
                i++;
            }
            ident.put(name,Double.parseDouble(builser.toString()));
            return true;
        }
        return false;
    }




}
