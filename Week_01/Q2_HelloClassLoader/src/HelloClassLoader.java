import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.nio.file.Files;

public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try{
            Class<?> HelloClazz = new HelloClassLoader().findClass("Hello");
            Object hello = HelloClazz.newInstance();
            hello.getClass().getMethod("hello").invoke(hello);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("Hello.xlass");
        byte[] bytes_original = new byte[0];
        try {
            bytes_original = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = convert_bytes(bytes_original);
        return defineClass(name, bytes, 0, bytes.length);
    }

    public byte[] convert_bytes(byte[] bytes){
        if (bytes != null) {
            for (int i=0; i<bytes.length; i++){
                bytes[i] = (byte)(255 - bytes[i]);
            }
        }
        return bytes;
    }

}
