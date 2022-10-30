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
            byte[] request;

            while (true) {
                request = new byte[1024];

                line = scanner.nextLine();
                if (line.equals("")){
                    break;
                }

                outputStream.write(line.getBytes("UTF-8"));
                outputStream.flush();
                System.out.println("Отправлено: " + line);

                for( int ln; (ln = inputStream.read(request)) != -1;){
                    String respond = new String(request, 0, ln);
                    System.out.println("Пришло: " + respond);
                    break;
                }
            }
        }
    }
}
