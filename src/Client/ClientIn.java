package Client;

import java.io.IOException;

public class ClientIn implements Runnable {
    Client client=Client.getInstance();
    @Override
    public void run() {
        try {
            while (true) {

                    System.out.println(client.getSocketIn().readLine());
            }
        }catch (IOException e){

        }
    }
}
