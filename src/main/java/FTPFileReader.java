import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import java.io.*;


public class FTPFileReader {
    public static void main(String[] args) {
        String server = "185.5.53.193";
        int port = 21; // Default FTP port
        String username = "root";
        String password = "jaN6AL97lnJ7";
        String remoteFilePath = "/home/supun/MyLog.log"; // Replace with the path of the file on the remote server
        String localFilePath = "local_file.txt"; // Replace with the desired local path and file name

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            OutputStream outputStream = new FileOutputStream(localFilePath);
            boolean success = ftpClient.retrieveFile(remoteFilePath, outputStream);

            if (success) {
                System.out.println("File has been downloaded successfully!");

                // Read file content
                BufferedReader reader = new BufferedReader(new FileReader(localFilePath));
                String line;
                System.out.println("File content:");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();
            } else {
                System.out.println("File download failed!");
            }
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
