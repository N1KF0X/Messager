
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

    private static void serverClient(Socket socket) throws IOException {
        System.out.println("Обслуживаемый клиент: " + socket.getInetAddress());
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        byte[] mass = new  byte[1024];
        for( int len; (len = inputStream.read(mass)) != -1;){
            String str = new String(mass, 0, len);
            outputStream.write(mass, 0, len);
            outputStream.flush();
            System.out.println("Отправленно: " + str);
            break;
        }
    }
}
