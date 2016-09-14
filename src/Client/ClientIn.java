package Client;

import java.io.IOException;

public class ClientIn implements Runnable {
    Client client=Client.getInstance();
    @Override
    public void run() {
        try {
            while (true) {
                String line = client.getSocketIn().readLine();

                    System.out.println(line);

            }
        }catch (IOException e){

        }
    }
}
