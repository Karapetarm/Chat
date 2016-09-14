package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class UserOnServer extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String userName;

    public UserOnServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);

            //userInitialization();
            out.println("Tell As Your Name");
            userName = in.readLine();

            for (PrintWriter writer : ChatServer.writers) {
                if(writer!=null){
                    writer.println(userName + ": is online ");
                }
            }
            ChatServer.writers.add(out);


            for (String elem : ChatServer.onlineUsers) {
                if(elem!=null){
                    out.print(elem + ": is online, ");
                }

            }
            out.println();
            ChatServer.onlineUsers.add(userName);


            while (true) {
                String input = in.readLine();
                if (input == null) {
                    return;
                }
                for (PrintWriter writer : ChatServer.writers) {
                    writer.println(userName +": "+ input);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {

            if (userName != null) {
                ChatServer.onlineUsers.remove(userName);
                for (PrintWriter writer : ChatServer.writers) {
                    writer.println(userName +": is offline");
                }
            }

            if (out != null) {
                ChatServer.writers.remove(out);
            }

            try {
                socket.close();
            } catch (IOException e) {
            }
        }

    }

    private void userInitialization() {
        try {
            out.println("Tell As Your Name");
            userName = in.readLine();

            for (PrintWriter writer : ChatServer.writers) {
                if(writer!=null){
                writer.println(userName + ": is online ");
                }
            }
            ChatServer.writers.add(out);


            for (String elem : ChatServer.onlineUsers) {
                if(elem!=null){
                    out.print(elem + ": is online, ");
                }
            }

            out.println();
            ChatServer.onlineUsers.add(userName);

        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            if (userName != null) {
                ChatServer.onlineUsers.remove(userName);
            }
            if (out != null) {
                ChatServer.writers.remove(out);
            }
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

}
