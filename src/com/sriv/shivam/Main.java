package com.sriv.shivam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    // declaring required variables
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static InputStreamReader inputStreamReader;
    private static BufferedReader bufferedReader;
    private static String message = "";

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(47430);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server listening to port 47430");

        while(!message.equalsIgnoreCase("over")) {
            System.out.println("While loop");

            try {
                System.out.println("Try block");
                // the accept method waits for a new client connection
                // and returns an individual socket for that connection
                clientSocket = serverSocket.accept();
                System.out.println("Client Socket = " + clientSocket.getLocalPort());

                // get the inputstream from socket, which will have
                // the message from the clients
                inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);

                // reading the message
                message = bufferedReader.readLine();

                // printing the message
                System.out.println(message);

                // finally it is very important
                // that you close the sockets
                inputStreamReader.close();
                clientSocket.close();

            } catch (IOException ex) {
                System.out.println("Problem in message reading");
            }
        }
    }
}
