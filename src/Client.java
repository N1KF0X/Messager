import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket("localhost", 1020)){
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(System.in);
            String line;
            byte[] response;

            while (true) {
                line = scanner.nextLine();
                if (line.equals("")){
                    break;
                }
                outputStream.write(line.getBytes("UTF-8"));
                outputStream.flush();
                System.out.println("Отправленно: " + "\"" + line + "\"");
                response = new byte[1024];
                for( int ln; (ln = inputStream.read(response)) != -1;){
                    String str = new String(response, 0, ln);
                    System.out.println("Пришло: " + str);
                    break;
                }
            }
        }
    }
}
