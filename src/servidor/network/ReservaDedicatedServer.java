package servidor.network;

import servidor.model.*;
import servidor.view.MainView;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class ReservaDedicatedServer extends Thread {

    private ReservaServer reservaServer;
    private Socket reservaClientSocket;
    private MainView mainView;
    private PlatosManager platosManager;
    private ReservasManager reservasManager;
    private PedidosManager pedidosManager;
    private boolean isRunning;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private DataInputStream dis;
    private DataOutputStream dos;

    private String name = "";
    private String password = "";

    public ReservaDedicatedServer(ReservaServer reservaServer, Socket reservaClientSocket, MainView mainView,
                                  PlatosManager platosManager, ReservasManager reservasManager, PedidosManager pedidosManager) {
        this.reservaServer = reservaServer;
        this.reservaClientSocket = reservaClientSocket;
        this.mainView = mainView;
        this.platosManager = platosManager;
        this.reservasManager = reservasManager;
        this.pedidosManager = pedidosManager;
        isRunning = false;
    }

    @Override
    public void run() {

        try {
            ois = new ObjectInputStream(reservaClientSocket.getInputStream());
            oos = new ObjectOutputStream(reservaClientSocket.getOutputStream());
            dis = new DataInputStream(reservaClientSocket.getInputStream());
            dos = new DataOutputStream(reservaClientSocket.getOutputStream());

            isRunning = true;

            while (isRunning) {
                //TODO Reserva Comm Protocol
                String obj = (String) ois.readObject();
                String[] s = obj.split("%%");
                System.out.println(obj);
                Reserva reserva;

                if ((reserva = reservasManager.searchReserva(s[0], s[1])) != null) {
                    name = s[0];
                    password = s[1];
                    System.out.println("OK");
                    oos.writeObject("OK");
                    Pedido pedido = new Pedido(reserva, new Mesa());
                    pedidosManager.addPedido(pedido);
                    //reservaServer.updatePedidosView();
                    updateMessageToClient();

                    while (isRunning) {
                        System.out.println("Waiting Comand");
                        ArrayList<Plat> plats =  (ArrayList<Plat>) ois.readObject();
                        LinkedList<Plato> platos = new LinkedList<>();
                        LinkedList<Plato> platos1 = new LinkedList<>(platosManager.getPlatos());
                        platos = (LinkedList<Plato>) platos1.clone();

                        Plato[] plato = new Plato[platos1.size()];
                        plato = platos.toArray(plato);

                        Plato[] plato2 = new Plato[plato.length];
                        for(int i=0;i<plato.length;i++) plato2[i] = new Plato(plato[i]);
                        platos =new LinkedList(Arrays.asList(plato2));


                        //Collections.copy(platos, platosManager.getPlatos());

                        //Pedido Send
                        reserva.setState(2);
                        //pedidosManager.getPedidoByReservaName(name).setPlatosPendientes(platos);
                        pedidosManager.getPedidoByReservaName(name).addPlatosPendientes(platos);
                        //pedidosManager.getPedidoByReservaName(name).setPlatosProcesados(new LinkedList<>());
                        reservaServer.updatePedidosView();

                        System.out.println("Comanda Received");
                        System.out.println("Comanda Size" + plats.size());

                    }
                } else {
                    oos.writeObject("KO");
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
            reservaServer.removeDedicatedServer(this);
            mainView.setConnectedDevices(reservaServer.getDedicatedServersCount());
        }
        finally {
            try {
                ois.close();
            } catch (IOException e) {}
            try {
                oos.close();
            } catch (IOException e) {}
            try {
                dis.close();
            } catch (IOException e) {}
            try {
                dos.close();
            } catch (IOException e) {}
            try {
                reservaClientSocket.close();
            } catch (IOException e) {}
        }
    }


    public void updateMessageToClient() {
        try {
            oos.writeObject(platosManager.getPlatos());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
