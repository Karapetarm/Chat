package Client;

public class ClientIn implements Runnable {
    Client client=Client.getInstance();
    @Override
    public void run() {
        try {
            while (true) {
                String line = client.getSocketIn().readLine();
               // if (line != null) {
                    System.out.println(line);
                //}
            }
        }catch (Exception e){

        }
    }
}
