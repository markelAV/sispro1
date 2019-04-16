package app.Model.ASM;

import com.sun.jna.Native;

public class ASMModule {
    public interface Operation extends  com.sun.jna.Library{
        double division(double a, double b);
        int and(int a, int b);
    }
    public static double div(double a, double b){
        final Operation op =(Operation) Native.loadLibrary("C:\\Users\\Артём\\Desktop\\hello.dll",Operation.class);
        final double result = op.division(a,b);
        return result;
    }

    public static int add(int a, int b){
        final Operation op =(Operation) Native.loadLibrary("C:\\Users\\Артём\\Desktop\\hello.dll",Operation.class);
        final int result = op.and(a,b);
        return result;
    }
}
