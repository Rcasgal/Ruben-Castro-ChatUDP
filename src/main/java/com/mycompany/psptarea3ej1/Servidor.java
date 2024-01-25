/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.psptarea3ej1;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor implements  Runnable {
    
	public final static int PORT = 2020;
	private final static int BUFFER = 1024;

	private DatagramSocket socket;
	private ArrayList<InetAddress> direccionesClientes;
	private ArrayList<Integer> puertosClientes;
	private HashSet<String> clientesExistentes;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Servidor() throws IOException {
		socket = new DatagramSocket(PORT);
		direccionesClientes = new ArrayList();
		puertosClientes = new ArrayList();
		clientesExistentes = new HashSet();
	}

	public void run() {
		byte[] buffer = new byte[BUFFER];
		while (true) {
			try {
				Arrays.fill(buffer, (byte) 0);
				DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
				socket.receive(paquete);

				String mensaje = new String(buffer, 0, buffer.length);

				InetAddress direccionCliente = paquete.getAddress();
				int puertoCliente = paquete.getPort();

				String id = direccionCliente.toString() + "|" + puertoCliente;
				if (!clientesExistentes.contains(id)) {
					clientesExistentes.add(id);
					puertosClientes.add(puertoCliente);
					direccionesClientes.add(direccionCliente);
				}

				System.out.println(id + " : " + mensaje);
				byte[] data = (id + " : " + mensaje).getBytes();
				for (int i = 0; i < direccionesClientes.size(); i++) {
					InetAddress direccionCL = direccionesClientes.get(i);
					int puertoCL = puertosClientes.get(i);
					paquete = new DatagramPacket(data, data.length, direccionCL, puertoCL);
					socket.send(paquete);
				}
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}

	public static void main(String args[]) throws Exception {
		Servidor threadServidor = new Servidor();
		threadServidor.run();
	}
}