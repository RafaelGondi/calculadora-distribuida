import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicOperationsServer extends Thread  {
	 public static void main(String args[]) {

		try {

			ServerSocket s = new ServerSocket(8080);
			while (true) {
				Socket conexao = s.accept();
				Thread t = new BasicOperationsServer(conexao);
				t.start();

			}
		}
		catch (IOException e) {

			System.out.println("IOException: " + e);
		}
	}

	private Socket conexao;

	private String meuNome;

	public BasicOperationsServer(Socket s) {
		conexao = s;
	}

	public void run() {
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
			PrintStream saida5 = new PrintStream(conexao.getOutputStream());
			
			
			if (saida5 != null) {
				String operationNumber = entrada.readLine();
				String op1 = entrada.readLine();
				String op2 = entrada.readLine();

				operationNumber = operationNumber.trim();
				op1 = op1.trim();
				op2 = op2.trim();

				System.out.println("--------------------------");

				
				switch(operationNumber) {
					case "1":
						System.out.println("Operacao recebida: ");
						System.out.println("operando 1: " + op1);
						System.out.println("operationNumber: " + operationNumber);
						System.out.println("operando 2: " + op2);
						System.out.println("resultado: " + String.valueOf(Double.parseDouble(op1) + Double.parseDouble(op2)));
						saida5.println("resultado: " + String.valueOf(Double.parseDouble(op1) + Double.parseDouble(op2)));
						break;
					case "2":
						System.out.println("Operacao recebida: ");
						System.out.println("operando 1: " + op1);
						System.out.println("operationNumber: " + operationNumber);
						System.out.println("operando 2: " + op2);
						System.out.println("resultado: " + String.valueOf(Double.parseDouble(op1) - Double.parseDouble(op2)));
						saida5.println("resultado: " + String.valueOf(Double.parseDouble(op1) - Double.parseDouble(op2)));
						break;
					case "3":
						System.out.println("Operacao recebida: ");
						System.out.println("operando 1: " + op1);
						System.out.println("operationNumber: " + operationNumber);
						System.out.println("operando 2: " + op2);
						System.out.println("resultado: " + String.valueOf(Double.parseDouble(op1) * Double.parseDouble(op2)));
						saida5.println("resultado: " + String.valueOf(Double.parseDouble(op1) * Double.parseDouble(op2)));
						break;
					case "4":
						System.out.println("Operacao recebida: ");
						System.out.println("operando 1: " + op1);
						System.out.println("operationNumber: " + operationNumber);
						System.out.println("operando 2: " + op2);
						System.out.println("resultado: " + String.valueOf(Double.parseDouble(op1) / Double.parseDouble(op2)));
						saida5.println("resultado: " + String.valueOf(Double.parseDouble(op1) / Double.parseDouble(op2)));
						break;
				}

				System.out.println("--------------------------\n");
				
			}
		}
		catch (IOException e) {
			System.out.println("IOException: " + e);
		}

		catch (NullPointerException e) {
			System.out.println("\nA conexão com um dos servidores de operacoes basicas foi perdida!");
		}
	}
}