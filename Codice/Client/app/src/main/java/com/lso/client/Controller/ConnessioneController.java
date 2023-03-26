package com.lso.client.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnessioneController {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    private final String serverName = "192.168.1.248";
    private final int serverPort = 1926;



    public ConnessioneController(){

    }

    public void startConnection(){
        try{
            socket = new Socket(serverName, serverPort);
            socket = new Socket(serverName, serverPort);
            input = new BufferedReader(new InputStreamReader( socket.getInputStream()));
            output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try{
            input.close();
            output.close();
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writeOnOutput(String out){
        output.println(out);
    }

    public String readFromInput(){
        String in = null;

        try{
            in = input.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }

        return in;
    }





    public BufferedReader getInput() {
        return input;
    }

    public void setInput(BufferedReader input) {
        this.input = input;
    }

    public PrintWriter getOutput() {
        return output;
    }

    public void setOutput(PrintWriter output) {
        this.output = output;
    }
}
