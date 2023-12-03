import java.net.*;
import java.io.*;
import java.util.Scanner;

public class emailsender {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("SMTP Server: ");
        String smtpServer = scanner.nextLine();
        System.out.print("SMTP Port: ");
        int smtpPort = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Your Email Address: ");
        String fromEmail = scanner.nextLine();
        System.out.print("Recipient's Email Address: ");
        String toEmail = scanner.nextLine();
        System.out.print("Subject: ");
        String subject = scanner.nextLine();

        StringBuilder emailContent = new StringBuilder();
        System.out.println("Enter the email content (end with a line containing a single dot '.' to finish):");
        while (true) {
            String line = scanner.nextLine();
            if (line.equals(".")) {
                break;
            }
            emailContent.append(line).append("\r\n");
        }

        scanner.close();

        try {
            Socket socket = new Socket(smtpServer, smtpPort);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String response = inputStream.readLine();
            System.out.println("Server Response: " + response);

            outputStream.writeBytes("HELO " + smtpServer + "\r\n");
            response = inputStream.readLine();
            System.out.println("Server Response: " + response);

            outputStream.writeBytes("MAIL FROM:<" + fromEmail + ">\r\n");
            response = inputStream.readLine();
            System.out.println("Server Response: " + response);

            outputStream.writeBytes("RCPT TO:<" + toEmail + ">\r\n");
            response = inputStream.readLine();
            System.out.println("Server Response: " + response);

            outputStream.writeBytes("DATA\r\n");
            response = inputStream.readLine();
            System.out.println("Server Response: " + response);

            outputStream.writeBytes("Subject:" + subject + "\r\n");
            outputStream.writeBytes("FROM:<" + fromEmail + ">\r\n");
            outputStream.writeBytes("TO:<" + toEmail + ">\r\n");
            outputStream.writeBytes("\r\n");
            outputStream.writeBytes(emailContent.toString());
            outputStream.writeBytes("\r\n");
            outputStream.writeBytes(".\r\n");

            response = inputStream.readLine();
            System.out.println("Server Response: " + response);

            outputStream.writeBytes("QUIT\r\n");
            response = inputStream.readLine();
            System.out.println("Server Response: " + response);

            socket.close();
            System.out.println("Closed Connection with Server");
        } catch (UnknownHostException e) {
            System.out.println("Unknown Host: " + smtpServer);
        } catch (IOException e) {
            System.out.println("Error in Connecting to Port");
        }
    }
}
