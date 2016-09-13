package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private BufferedReader socketIn;
    private BufferedReader userIn;
    private PrintWriter socketOut;
    private static Client client;

    private Client(){

        try {
            userIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input Server IP : ");
            String serverAddress = userIn.readLine();
            Socket socket = new Socket(serverAddress, 55955);

            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {

        }
    }
    public static Client getInstance(){
        if (client ==null)
            client =new Client();
        return client;
    }

    public BufferedReader getSocketIn() {
        return socketIn;
    }

    public BufferedReader getUserIn() {
        return userIn;
    }

    public PrintWriter getSocketOut() {
        return socketOut;
    }

    public static void main(String [] args){

        ClientIn clientIn=new ClientIn();
        Thread threadClentIn = new Thread(clientIn);
        threadClentIn.start();

        ClientOut clientOut =new ClientOut();
        Thread threadClientOut=new Thread(clientOut);
        threadClientOut.start();
    }
}
