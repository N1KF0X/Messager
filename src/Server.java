
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("InfiniteLoopStatement")
public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(1020)) {
            while (true) {
                Socket socket = serverSocket.accept();
                serverClient(socket);
            }
        }
    }

    private static void serverClient(Socket socket) throws IOException{
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        System.out.println("Обслуживаемый клиент: " + socket.getInetAddress());

        while (true) {
            byte[] respond = new byte[1024];
            for (int len; (len = inputStream.read(respond)) != -1; ) {
                String request = new String(respond, 0, len);
                System.out.println("Пришло: " + request);

                outputStream.write(respond, 0, len);
                outputStream.flush();

                System.out.println("Отправлено: " + request);
                break;
            }
        }
    }
}
