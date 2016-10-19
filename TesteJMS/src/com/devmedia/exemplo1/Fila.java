package com.devmedia.exemplo1;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
  
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/test") })
public class Fila implements MessageListener {
 
    @Override
    public void onMessage(Message message) {
        ObjectMessage objMsg = (ObjectMessage) message;
        try {
            System.out.println("--- Recebendo mensagem --- ");
            
            Compra compra = (Compra) objMsg.getObject();
            
            System.out.println(compra.getNome());
            System.out.println(compra.getNumeroCartao());
            
            System.out.println("--- Itens --- ");
            
            float precoTotal = 0;
            for (Item item : compra.getItens()) {
            	System.out.println(item.getNome());
            	precoTotal += item.getPreco() * item.getQuantidade();
            }
            System.out.println("Total: " + precoTotal);
            
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
 
}
