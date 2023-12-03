import java.net.*;
import java.io.*;

public class emailku
{
  public static void main(String[] argc)
  {
        Socket s1;
        DataOutputStream p1=null;
        BufferedReader d1=null;
        String recvreply;

        try
        {
            s1=new Socket("mail.sertifikasi.web.id",25);
            p1=new DataOutputStream(s1.getOutputStream());
            d1=new BufferedReader(new InputStreamReader(s1.getInputStream()));

            recvreply=d1.readLine();
            System.out.println("Server Response : " + recvreply);
            
            p1.writeBytes("HELO mail.sertifikasi.web.id\r\n");
            recvreply=d1.readLine();
            System.out.println("Server Response : " + recvreply);

            p1.writeBytes("MAIL FROM:<felstly@gmail.com>\r\n");
            recvreply=d1.readLine();
            System.out.println("Server Response : " + recvreply);

            p1.writeBytes("RCPT TO:<felix@sertifikasi.web.id>\r\n");
            recvreply=d1.readLine();
            System.out.println("Server Response : " + recvreply);

            p1.writeBytes("DATA\r\n");
            recvreply=d1.readLine();
            System.out.println("Server Response : " + recvreply);

            p1.writeBytes("Subject:Perkenalan\r\n");
            p1.writeBytes("FROM:<felstly@gmail.com>\r\n");
            p1.writeBytes("TO:<felix@sertifikasi.web.id>\r\n");
            p1.writeBytes("\r\n");
            p1.writeBytes("Hello,\r\n");
            p1.writeBytes("Saya hanya ingin memperkenalkan diri.\r\n");
            p1.writeBytes("Silahkan kunjungi website kami di:\r\n");
            p1.writeBytes("www.sertifikasi.web.id\r\n");
            p1.writeBytes("\r\n");
            p1.writeBytes("Felix\r\n");
            p1.writeBytes("============\r\n");

            p1.writeBytes(".\r\n");
            recvreply=d1.readLine();
            System.out.println("Server Response : " + recvreply);
            p1.writeBytes("QUIT\r\n");

            recvreply=d1.readLine();
            System.out.println("Server Response : " + recvreply);

            s1.close();
            System.out.println("Closed Connection with Server");
        }
        catch(IOException e)	
        {
            System.out.println("Error in Connecting to Port");
        }
    }
}
