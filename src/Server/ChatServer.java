package Server;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashSet;

public class ChatServer {

    public static HashSet<PrintWriter> writers = new HashSet<>();
    public static HashSet<String> onlineUsers = new HashSet<>();

    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(55955);
        try {
            while (true) {
                new UserOnServer(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

}
