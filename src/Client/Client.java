package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Client {

    private BufferedReader socketIn;
    private BufferedReader userIn;
    private PrintWriter socketOut;
    private static Client client;
    private String serverAddress;

    private Client(){

        try {
            userIn = new BufferedReader(new InputStreamReader(System.in));

           do{
               System.out.println("Input Server IP Address \nTrue Format is: 000.000.000.000");
               serverAddress = userIn.readLine();
           }while(!isTrueAddress(serverAddress));

            Socket socket = new Socket(serverAddress, 55955);

            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {

        }
    }
    public static Client getInstance(){
        if (client ==null)
            client =new Client();
        return client;
    }

    private boolean isTrueAddress(String str){
        //000.000.000.000
        boolean result=true;
        if(str.length()<16&&str.charAt(3)=='.'&&str.charAt(7)=='.'&&str.charAt(11)=='.')
            for(int i=0;i<15;){
              result&= "000".compareTo(str.substring(i,i+3))<=0&&"255".compareTo(str.substring(i,i+3))>=0;
                i+=4;
            }
        else result&=false;

        return result;
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

    public static void main(String[] args) {
        Client client = Client.getInstance();
        new Thread(()-> {
            try {
                    while (true) {

                        System.out.println(client.getSocketIn().readLine());
                    }
                } catch (IOException e) {

                }
        },"name").start();

        new Thread(() -> {
            try {
                while (true) {

                    client.getSocketOut().println(client.getUserIn().readLine());
                }
            } catch (IOException e) {

            }
        }).start();
    }
}
