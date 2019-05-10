import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;  

public class MiddlewareServer extends Thread  {
   public static void main(String args[]) {

		clientes = new Vector<PrintStream>();
		basicServers = new Vector<String>();
		enhancedServers = new Vector<String>();
		try {
			Integer basicServerNumber;
			Integer enhancedServerNumber;
			String ip = "";
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			ServerSocket s = new ServerSocket(8000);
			
			System.out.println("--Configuracao do servidor--");
			System.out.print("Informe o numero de servidores que serao utilizados para as contas aritmeticas basicas: ");
			basicServerNumber = Integer.parseInt(teclado.readLine());
			System.out.print("Informe o numero de servidores que serao utilizados para as contas avancadas: ");
			enhancedServerNumber = Integer.parseInt(teclado.readLine());

			for (int i = 0; i < basicServerNumber; i++) {
				System.out.print("Informe o ip do servidor de contas aritmeticas basicas nº " + (i + 1) + ": ");
				ip = teclado.readLine();
				basicServers.add(ip);
			}
			
			for (int i = 0; i < enhancedServerNumber; i++) {
				System.out.print("Informe o ip do servidor de contas avancadas nº: " + (i + 1) + ": ");
				ip = teclado.readLine();
				enhancedServers.add(ip);
			}

			while (true) {
				Socket conexaoCliente = s.accept();
				Thread t1 = new MiddlewareServer(conexaoCliente);
				t1.start();			
			}
		}
		catch (IOException e) {
			System.out.println("IOException: " + e);
		}
	}

	private static Vector<PrintStream> clientes;

	private static Vector<String> basicServers;

	private static Vector<String> enhancedServers;

	private Socket conexaoCliente;

	private Socket conexaoBasicOperationsServer;

	private Socket conexaoEnhancedOperationsServer;

	private String meuNome;

	public MiddlewareServer(Socket s) {
		conexaoCliente = s;
	}

	public void BasicOperationsServer(Socket s) {
		conexaoBasicOperationsServer = s;
	}

	public void EnhancedOperationsServer(Socket s) {
		conexaoEnhancedOperationsServer = s;
	}

	public void run() {
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(conexaoCliente.getInputStream()));
			PrintStream saida = new PrintStream(conexaoCliente.getOutputStream());

			String operationNumber = "";
			String op1 = "";
			String op2 = "";
			String operator = "";
			int countFirstBasic = 0;
			int countFirstEnhanced = 0;
			String result = "";
			String resultEn = "";

			int countBasicConections = 0;
			int countEnhancedConections = 0;

			if (clientes.size() == 0) {
				clientes.add(saida);
			} else {
				Enumeration e = clientes.elements();
				while (e.hasMoreElements()) {
					PrintStream chat = (PrintStream) e.nextElement();
					if (chat != saida) {
						clientes.add(saida);
						break;	
					}
				}
			}

			while (op1 != null) {
				if (saida != null) {
					operationNumber = entrada.readLine();
					op1 = entrada.readLine();
					op2 = entrada.readLine();

					switch(operationNumber) {
						case "1":
							operator = "+";
							break;
						case "2":
							operator = "-";
							break;
						case "3":
							operator = "*";
							break;
						case "4":
							operator = "/";
							break;
						case "5":
							operator = "%";
							break;
						case "6":
							operator = "^";
							break;
						case "7":
							operator = "sqrt";
							break;
					}

					if (
						operationNumber.equals("1")
						|| operationNumber.equals("2")
						|| operationNumber.equals("3")
						|| operationNumber.equals("4")
					) {	
						System.out.println("--------------------------");
						System.out.println("Operacao recebida: ");
						System.out.println("operando 1: " + op1);
						System.out.println("operationNumber: " + operationNumber);
						System.out.println("operando 2: " + op2);

						Socket conexaoBasicOperationsServer = new Socket(basicServers.get(countBasicConections), 8080);
						countBasicConections++;
						PrintStream saida2 = new PrintStream(conexaoBasicOperationsServer.getOutputStream());
						BufferedReader entradaBasicOperationsServer = new BufferedReader(new InputStreamReader(conexaoBasicOperationsServer.getInputStream()));

						saida2.println(operationNumber);
						saida2.println(op1);
						saida2.println(op2);

						Thread t2 = new BasicOperationsServer(conexaoBasicOperationsServer);

						t2.start();

						result = entradaBasicOperationsServer.readLine();
						sendToAll(saida, op1, operator, op2, " e igual a: ", result);						

						System.out.println("\nresultado: " + result);
						System.out.println("--------------------------\n");
						
						if (countBasicConections == basicServers.size()) {
							countBasicConections = 0;
						}
					} else if (
						operationNumber.equals("5")
						|| operationNumber.equals("6")
						|| operationNumber.equals("7")
					) {
						Socket conexaoEnhancedOperationsServer = new Socket(enhancedServers.get(countEnhancedConections), 9090);
						countEnhancedConections++;
						PrintStream saida3 = new PrintStream(conexaoEnhancedOperationsServer.getOutputStream());
						BufferedReader entradaEnhancedOperationsServer = new BufferedReader(new InputStreamReader(conexaoEnhancedOperationsServer.getInputStream()));

						if (operationNumber.equals("7")) {
							System.out.println("--------------------------");
							System.out.println("Operacao recebida: ");
							System.out.println("operationNumber: " + operationNumber);
							System.out.println("op1: " + op1);

							saida3.println(operationNumber);
							saida3.println(op1);
							saida3.println(op2);
						} else {
							System.out.println("--------------------------");
							System.out.println("Operacao recebida: ");
							System.out.println("op1: " + op1);
							System.out.println("operationNumber: " + operationNumber);
							System.out.println("op2: " + op2);
							
							saida3.println(operationNumber);
							saida3.println(op1);
							saida3.println(op2);
						}

						Thread t3 = new EnhancedOperationsServer(conexaoEnhancedOperationsServer);
						t3.start();

						resultEn = entradaEnhancedOperationsServer.readLine();

						System.out.println("\nresultado: " + resultEn);
						System.out.println("--------------------------\n");

						sendToAll(saida, op1, operator, op2, " e igual a: ", resultEn);

						if (countEnhancedConections == enhancedServers.size()) {
							countEnhancedConections = 0;
						}
					}
				}
			}

		}
		catch (IOException e) {
			System.out.println("\nA conexão com um dos servidores escravos foi perdida!");
		}

		catch (NullPointerException e) {
			System.out.println("\nA conexão com um dos servidores escravos foi perdida!");
		}

		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Um dos dois tipos de servidores nao foi configurado para utilizacao. Operacao invalida! Reinicie o servidor.");
		}
		
	}

	public void sendToAll(PrintStream saida, String op1, String operator, String op2, String text, String result) {
		Enumeration e = clientes.elements();
		while (e.hasMoreElements()) {
			PrintStream chat = (PrintStream) e.nextElement();
			if (operator.equals("sqrt")) {
				chat.println(operator + "(" + op1 + ")" + text + result);
			} else {
				chat.println(op1 + " " + operator + " " + op2 + text + result);
			}
		}
		
	}
}