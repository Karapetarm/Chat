package Client;

import java.io.IOException;

public class ClientOut implements Runnable {
    Client client=Client.getInstance();
    @Override
    public void run() {
        try {
            while (true) {
                client.getSocketOut().println(client.getUserIn().readLine());
            }
        } catch (IOException e) {

        }
    }
}
