import java.io.*;
import java.net.*;
public class Cliente extends Thread {
//	Flag que indica quando se deve terminar a execução.
	private static boolean done = false;

	private Socket conexao;
	public Cliente(Socket s) {
		conexao = s;
	}

	public static void main(String args[]) {
		try {
			Socket conexao = new Socket("127.0.0.1", 8000);
			String linha;
			String operationNumber;
			String op1;
			String op2;

			PrintStream saida = new PrintStream(conexao.getOutputStream());
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Calculadora distribuída");
				System.out.println(
					"Operacoes disponiveis: \n" +
					"1 - soma --------------(+)\n" +
					"2 - subtracao ---------(-)\n" +
					"3 - multiplicacao -----(*)\n" +
					"4 - divisao -----------(/)\n" +
					"5 - porcentagem -------(%)\n" +
					"6 - potenciacao -------(^)\n" +
					"7 - radiciacao --------(sqrt)\n"
				);

			Thread t = new Cliente(conexao);
			t.start();

			System.out.print("Informe o numero da operacao que deseja realizar: ");
			
			while (true) {
				operationNumber = teclado.readLine();
				System.out.println();
				if (done) {break;}

				switch(operationNumber) {
					case "1":
						System.out.print("Informe o primeiro operando: ");
						op1 = teclado.readLine();
						System.out.print("Operacao selecionada: " + "       +\n");
						System.out.print("Informe o segundo operando:  ");
						op2 = teclado.readLine();

						if (op1.equals("") || op1 == null) {
							System.out.print("Informe numeros validos!");
							break;
						}

						try {
							Double.parseDouble(op1);
						} catch (NumberFormatException e) {
							System.out.println("Informe numeros validos!");
							break;
						}

						if (op2.equals("") || op2 == null) {
							System.out.print("Informe numeros validos!");
							break;
						}

						try {
							Double.parseDouble(op2);
						} catch (NumberFormatException e) {
							System.out.println("Informe numeros validos!");
							break;
						}

						

						saida.println(operationNumber);
						saida.println(op1);
						saida.println(op2);

						break;
					case "2":
						System.out.print("Informe o primeiro operando: ");
						op1 = teclado.readLine();
						System.out.print("Operacao selecionada: " + "       -\n");
						System.out.print("Informe o segundo operando:  ");
						op2 = teclado.readLine();

						if (op1.equals("") || op1 == null) {
							System.out.print("Informe numeros validos!");
							break;
						}

						try {
							Double.parseDouble(op1);
						} catch (NumberFormatException e) {
							System.out.println("Informe numeros validos!");
							break;
						}

						if (op2.equals("") || op2 == null) {
							System.out.print("Informe numeros validos!");
							break;
						}

						try {
							Double.parseDouble(op2);
						} catch (NumberFormatException e) {
							System.out.println("Informe numeros validos!");
							break;
						}

						saida.println(operationNumber);
						saida.println(op1);
						saida.println(op2);

						break;
					case "3":
						System.out.print("Informe o primeiro operando: ");
						op1 = teclado.readLine();
						System.out.print("Operacao selecionada: " + "       *\n");
						System.out.print("Informe o segundo operando:  ");
						op2 = teclado.readLine();

						if (op1.equals("") || op1 == null) {
							System.out.print("Informe numeros validos!");
							break;
						}

						try {
							Double.parseDouble(op1);
						} catch (NumberFormatException e) {
							System.out.println("Informe numeros validos!");
							break;
						}

						if (op2.equals("") || op2 == null) {
							System.out.print("Informe numeros validos!");
							break;
						}

						try {
							Double.parseDouble(op2);
						} catch (NumberFormatException e) {
							System.out.println("Informe numeros validos!");
							break;
						}

						saida.println(operationNumber);
						saida.println(op1);
						saida.println(op2);

						break;
					case "4":
						System.out.print("Informe o primeiro operando: ");
						op1 = teclado.readLine();
						System.out.print("Operacao selecionada: " + "       /\n");
						System.out.print("Informe o segundo operando:  ");
						op2 = teclado.readLine();

						if (op1.equals("") || op1 == null) {
							System.out.print("Informe numeros validos!");
							break;
						}

						try {
							Double.parseDouble(op1);
						} catch (NumberFormatException e) {
							System.out.println("Informe numeros validos!");
							break;
						}

						if (op2.equals("") || op2 == null) {
							System.out.print("Informe numeros validos!");
							break;
						}

						try {
							Double.parseDouble(op2);
						} catch (NumberFormatException e) {
							System.out.println("Informe numeros validos!");
							break;
						}

						saida.println(operationNumber);
						saida.println(op1);
						saida.println(op2);

						break;
					case "5":
						System.out.print("Informe o primeiro operando: ");
						op1 = teclado.readLine();
						System.out.print("Operacao selecionada: " + "       %\n");
						System.out.print("Informe o segundo operando:  ");
						op2 = teclado.readLine();

						if (op1.equals("") || op1 == null) {
							System.out.print("Informe numeros validos!");
							break;
						}

						try {
							Double.parseDouble(op1);
						} catch (NumberFormatException e) {
							System.out.println("Informe numeros validos!");
							break;
						}

						if (op2.equals("") || op2 == null) {
							System.out.print("Informe numeros validos!");
							break;
						}

						try {
							Double.parseDouble(op2);
						} catch (NumberFormatException e) {
							System.out.println("Informe numeros validos!");
							break;
						}

						saida.println(operationNumber);
						saida.println(op1);
						saida.println(op2);

						break;
					case "6":
						System.out.print("Informe o primeiro operando: ");
						op1 = teclado.readLine();
						System.out.print("Operacao selecionada: " + "       ^\n");
						System.out.print("Informe o segundo operando:  ");
						op2 = teclado.readLine();

						if (op1.equals("") || op1 == null) {
							System.out.print("Informe numeros validos!");
							break;
						}

						try {
							Double.parseDouble(op1);
						} catch (NumberFormatException e) {
							System.out.println("Informe numeros validos!");
							break;
						}

						if (op2.equals("") || op2 == null) {
							System.out.print("Informe numeros validos!");
							break;
						}

						try {
							Double.parseDouble(op2);
						} catch (NumberFormatException e) {
							System.out.println("Informe numeros validos!");
							break;
						}

						saida.println(operationNumber);
						saida.println(op1);
						saida.println(op2);

						break;
					case "7":
						System.out.print("Operacao selecionada: " + "       sqrt\n");
						System.out.print("Informe o operando: ");
						op1 = teclado.readLine();

						if (op1.equals("") || op1 == null) {
							System.out.print("Informe numeros validos!");
							break;
						}

						try {
							Double.parseDouble(op1);
						} catch (NumberFormatException e) {
							System.out.println("Informe numeros validos!");
							break;
						}

						saida.println(operationNumber);
						saida.println(op1);
						saida.println(op1);

					default:
						System.out.print("Selecione uma operacao valida: ");
				}
			}
		}
		catch (IOException e) {
			System.out.println("Não há servidores disponíveis para conexão!");
		} 
		
	}
	
//	execução da thread
	public void run() {
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
			String linha;
			while (true) {
				linha = entrada.readLine();

				if (linha == null) {
					System.out.println("Conexão encerrada!");
					break;
				}

				System.out.println();
				System.out.println(linha);
				System.out.print("----------------------------\n");
				System.out.print("Informe o numero da operacao que deseja realizar: ");
				// System.out.print("...> ");
			}
		}
		catch (IOException e) {
			System.out.println("\nConexão perdida. Reinicie a aplicação!");
		}

		done = true;
	}
}