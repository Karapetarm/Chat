package Client;

public class ClientOut implements Runnable {
    Client client=Client.getInstance();
    @Override
    public void run() {
        try {
            while (true) {
                client.getSocketOut().println(client.getUserIn().readLine());
            }
        } catch (Exception e) {

        }
    }
}
