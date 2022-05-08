/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.benas.klientas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Benas
 */
public class ComUtils {
    private AES aes;
    private RSAc rSAc;
    private Socket socket;
    PrintWriter out;
    BufferedReader br;
    
    public ComUtils() throws IOException{
        this.rSAc = new RSAc();
        this.aes = new AES();
        this.socket = new Socket("localhost", 8022);
            socket.setSoTimeout(350000);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
}
    
    
public void KeyExchange() throws IOException, Exception{
            out.println(rSAc.getPub());
            String AESKey = rSAc.decrypt(br.readLine());
            aes.setSecretKey(AESKey);
}
public String readLine(){
        try {
            
            String line = br.readLine();
            return aes.decryptWithPrefixIV(line);
        } catch (IOException ex) {
            Logger.getLogger(ComUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(ComUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
}
public void println(String string){
        try {
            out.println(aes.encryptWithPrefixIV(string));
        } catch (Exception ex) {
            Logger.getLogger(ComUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}

public void Sclose(){
        try {
            br.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ComUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
