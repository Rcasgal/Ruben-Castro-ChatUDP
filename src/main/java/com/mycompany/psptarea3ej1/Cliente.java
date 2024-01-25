/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.psptarea3ej1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class Emisor implements Runnable {
    
	public final static int PORT = 2020;
	private DatagramSocket socket;
	private String hostName;
	private VistaClientes ventana;

	Emisor(DatagramSocket socket, String hostName, VistaClientes ventana) {
		this.socket = socket;
		this.hostName = hostName;
		this.ventana = ventana;
	}

	private void enviarMensaje(String s) throws Exception {
		byte buffer[] = s.getBytes();
		InetAddress direccion = InetAddress.getByName(hostName);
		DatagramPacket paqueteEnviado = new DatagramPacket(buffer, buffer.length, direccion, PORT);
		socket.send(paqueteEnviado);
	}

	public void run() {
		boolean conexion = false;
		do {
			try {
				enviarMensaje(" -- Nuevo cliente en el chat -- ");
				conexion = true;
			} catch (Exception e) {
				ventana.displayMessage(e.getMessage());
			}
		} while (!conexion);
		while (true) {
			try {
				while (!ventana.mensajeListo) {
					Thread.sleep(100);
				}
				enviarMensaje(ventana.getMensaje());
				ventana.setMensajeListo(false);
			} catch (Exception e) {
				ventana.displayMessage(e.getMessage());
			}
		}
	}
}

class Receptor implements Runnable {
	DatagramSocket socket;
	byte buffer[];
	VistaClientes window;

	Receptor(DatagramSocket socket, VistaClientes window) {
		this.socket = socket;
		buffer = new byte[1024];
		this.window = window;
	}

	public void run() {
		while (true) {
			try {
				DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
				socket.receive(paquete);
				String pqRecibido = new String(paquete.getData(), 1, paquete.getLength() - 1).trim();
				System.out.println(pqRecibido);
				window.displayMessage(pqRecibido);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}

public class Cliente {

	public static void main(String args[]) throws Exception {
		
		VistaClientes ventana = new VistaClientes();
		String host = ventana.getHost();
		ventana.setTitle("UDP CHAT  Server: " + host);
		DatagramSocket socket = new DatagramSocket();
		Receptor receptor = new Receptor(socket, ventana);
		Emisor emisor = new Emisor(socket, host, ventana);
		Thread threadReceptor = new Thread(receptor);
		Thread threadEmisor = new Thread(emisor);
		threadReceptor.start();
		threadEmisor.start();
	}
}