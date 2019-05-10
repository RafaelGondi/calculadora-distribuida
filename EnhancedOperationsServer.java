import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnhancedOperationsServer extends Thread  {
	 public static void main(String args[]) {

		try {

			ServerSocket s = new ServerSocket(9090);
			while (true) {
				Socket conexao = s.accept();
				Thread t = new EnhancedOperationsServer(conexao);
				t.start();

			}
		}
		catch (IOException e) {

			System.out.println("IOException: " + e);
		}
	}

	private Socket conexao;

	private String meuNome;

	public EnhancedOperationsServer(Socket s) {
		conexao = s;
	}

	public void run() {
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
			PrintStream saidaE = new PrintStream(conexao.getOutputStream());
			
			if (true) {
				String operationNumber = entrada.readLine();
				String op1 = entrada.readLine();
				String op2 = entrada.readLine();

				operationNumber = operationNumber.trim();
				op1 = op1.trim();
				op2 = op2.trim();

				System.out.println("--------------------------");

				switch(operationNumber) {
					case "5":
						System.out.println("Operacao recebida: ");
						System.out.println("operando 1: " + op1);
						System.out.println("operationNumber: " + operationNumber);
						System.out.println("operando 2: " + op2);
						System.out.println("resultado: " + String.valueOf((Double.parseDouble(op1) / 100) * Double.parseDouble(op2)));
						saidaE.println("resultado: " + String.valueOf((Double.parseDouble(op1) / 100) * Double.parseDouble(op2)));
						break;
					case "6":
						System.out.println("Operacao recebida: ");
						System.out.println("operando 1: " + op1);
						System.out.println("operationNumber: " + operationNumber);
						System.out.println("operando 2: " + op2);
						System.out.println("resultado: " + String.valueOf(Math.pow(Double.parseDouble(op1), Double.parseDouble(op2))));
						saidaE.println("resultado: " + String.valueOf(Math.pow(Double.parseDouble(op1), Double.parseDouble(op2))));
						break;
					case "7":
						System.out.println("opa --- 7");
						System.out.println("Operacao recebida: ");
						System.out.println("operationNumber: " + operationNumber);
						System.out.println("operando 1: " + op1);
						System.out.println("resultado: " + String.valueOf(Math.sqrt(Double.parseDouble(op1))));
						saidaE.println("resultado: " + String.valueOf(Math.sqrt(Double.parseDouble(op1))));
						break;
					default:
						break;
				}

				System.out.println("--------------------------\n");
			}
		}
		catch (IOException e) {
			System.out.println("IOException: " + e);
		}

		catch (NullPointerException e) {
			System.out.println("\nA conexão com um dos servidores nao pode ser estabelecida!");
		}
	}
}