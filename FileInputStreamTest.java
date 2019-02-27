import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class FileInputStreamTest
{
    public static void main(String[] args)
    {
        try
        {
            File file1=new File("F:\\io\\a.jpg");
            FileInputStream fis=new FileInputStream(file1);
            File file2=new File("F:\\io\\aaa\\b.jpg");
            FileOutputStream fos=new FileOutputStream(file2);
            int len=0;
            byte[] bs=new byte[1000];
            while((len=fis.read(bs))!=-1)
            {
                fos.write(bs,0,len);
            }
                fis.close();
                fos.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("操作的文件不存在");
        }
        catch(IOException e)
        {
            System.out.println("发生IO操作异常");
        }
    }
}
