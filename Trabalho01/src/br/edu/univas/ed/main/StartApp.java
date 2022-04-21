package br.edu.univas.ed.main;

import br.edu.univas.ed.lista.List;
import br.edu.univas.ed.lista.dados.Entity;

import java.util.*;

public class StartApp {
	
	static List  player = new List();
	static List  gameBoard = new List();
	static List  bot = new List();
	static Scanner ler = new Scanner(System.in);
	static Random random = new Random();
	static Boolean whoPlay = true;
	static int passed = 0, toBuy = 14, rounds = 1;

	
	private static int readInteger () {
		int value  = ler.nextInt();
		ler.nextLine();
		return  value;
		
	}
	
	public static void main(String[] args) {
		
		menu();
		System.out.println("Criado por: ");
		System.out.println("Water Ap");
		System.out.println("F. Rafael \n\n");
		
		System.out.println("Começando o jogo...\n");
		
		for(int i =0;i <  7;i++) {
			Entity toAdd = new Entity();
			toAdd.value1 = random.nextInt(7);
			toAdd.value2 = random.nextInt(7);
			player.add(toAdd);
		    	
		}
		for(int i =0;i <  7;i++) {
			Entity toAdd = new Entity();
			toAdd.value1 = random.nextInt(7);
			toAdd.value2 = random.nextInt(7);
			bot.add(toAdd);
		    	
		}
	
		game();
		
	}
	
	private static void menu() {
		
		System.out.println("================================ ");
		System.out.println("===========  Dominó  =========== ");
		System.out.println("================================ ");
	}
	
	private static void menu2() {
		
		System.out.println();
		System.out.println("O que você vai fazer?");
		System.out.println("1- Realizar jogada   ");
		System.out.println("2- Comprar           ");
		System.out.println("3- Passar a vez      ");
		System.out.println("Escolha uma opção.   ");
	}
	
	private static void menu3() {
		System.out.println("\n");
		menu();
		System.out.println("Fim de Jogo!!!");
		System.out.println("Peças jogadas: ");
		gameBoard.print3();
		
	}
	
	private static boolean verifWin() {
		if(player.tamanho == 0) {
			menu3();
			System.out.println("\n\n");
			System.out.println("O player ganhou!!!");
			return true;
			
		} else if (bot.tamanho == 0) {
			menu3();
			System.out.println("\n\n");
			System.out.println("O bot ganhou!!!");
			return true;
			
		} else if (passed > 2 && toBuy == 0) {
			if (player.tamanho < bot.tamanho) {
				menu3();
				System.out.println("\n\n");
				System.out.println("O player ganhou!!!");
				System.out.println("Por ter menos peças...");
				return true;
				
			} else {
				menu3();
				System.out.println("\n\n");
				System.out.println("O bot ganhou!!!");
				System.out.println("Por ter menos peças...");
				return true;
				
			}
			
		}
		return false;
	}
	
	private static void game() {
		
		boolean control = true;
		while (control) {
			
			if(verifWin()) {
				return;
				
			}
			
			whoPlay = true;
			printBoard();
			printPlayerPiece();
			makePlay();
			
			if(verifWin()) {
				return;
				
			}
			
			
			whoPlay = false;
			printBoard();
			makePlay();
			
			rounds++;
			
		}
	}

	private static void printPlayerPiece()  {
		
		System.out.println("Suas peças: ");
		player.print2();
		System.out.println("Quantidade de peças: "+ player.tamanho + ". \n");
		
		
	}
	
	
	private static void printBoard() {
		
		System.out.println("\n");
		menu();
		System.out.println("Rodada: "+rounds+" \n");
		
		System.out.println("== Tabuleiro == \n\n");
		if(gameBoard.empty()) {
			
			System.out.print("  *Tabuleiro vazio* ");	
			
		} else {
			
			gameBoard.print3();
		
		}
		System.out.println("\n\n\n== Tabuleiro == \n\n");
		System.out.println("O Bot está com "+ bot.tamanho+" peça(s).");
		System.out.println("Existem "+toBuy+" peça(s) para comprar\n");
		
	}
	
	private static void makePlay () {
		if(whoPlay) {
			
			boolean control = true, readControl = true;
			int option,  comprados = 0;
			
			do {
				menu2();
				option = readInteger();
				System.out.println("\n");
				
				switch (option) {
					case 1:
						
						printPlayerPiece();
						System.out.println("Muito bem! digite o indice da peça que deseja jogar: ");
						int indexPiece;
						do {
							indexPiece = readInteger();
							if(indexPiece < 0  || indexPiece > (player.tamanho - 1)) {
								System.out.println("Indice não corresponde a uma peça, tente novamente: ");	
							} else {
								readControl = false;
							}
							
						} while (readControl);
						
						if (gameBoard.empty()) {
							Entity pieceForPlay = player.removeMid(indexPiece);
							
							passed = 0;
							control = false;
							System.out.println("\nVocê jogou  --> "+pieceForPlay);
						    gameBoard.addStart(pieceForPlay);	
							
						} else {
							Entity pieceForPlay = player.removeMid(indexPiece);  
							if (pieceForPlay.value1 == gameBoard.noInicio.piece.value1 || pieceForPlay.value2 == gameBoard.noInicio.piece.value1) {
								if (pieceForPlay.value2 != gameBoard.noInicio.piece.value1) {
									int aux = pieceForPlay.value2;
									pieceForPlay.value2 = pieceForPlay.value1;
									pieceForPlay.value1 = aux;
									
									passed = 0;
									control = false;
									System.out.println("\nVocê jogou  --> "+pieceForPlay);
									gameBoard.addStart(pieceForPlay);
									
								} else {
									
									passed = 0;
									control = false;
									System.out.println("\nVocê jogou  --> "+pieceForPlay);
									gameBoard.addStart(pieceForPlay);
									
								}	
							} else if (pieceForPlay.value1 == gameBoard.noUltimo.piece.value2 || pieceForPlay.value2 == gameBoard.noUltimo.piece.value2) {
								if(pieceForPlay.value1 != gameBoard.noUltimo.piece.value2) {
									int aux = pieceForPlay.value1;
									pieceForPlay.value1 = pieceForPlay.value2;
									pieceForPlay.value2 = aux;
									
									passed = 0;
									control = false;
									System.out.println("\nVocê jogou  --> "+pieceForPlay);
									gameBoard.addEnd(pieceForPlay);
									
								} else {
									
									passed = 0;
									control = false;
									System.out.println("\nVocê jogou  --> "+pieceForPlay);
									gameBoard.addEnd(pieceForPlay);
									
								}
								
							} else {
								System.out.println("\nEssa peça não pode ser jogada, porque os valores não correspondem!!!");
								player.addMid(indexPiece, pieceForPlay);
								
							}
						}
						break;
					
					case 2:
						if (toBuy > 0) {
							if(comprados < 1) {
								Random random2 = new Random();
								Entity  newPiece = new Entity();
								
								newPiece.value1 = random2.nextInt(7);
								newPiece.value2 = random2.nextInt(7);
								
								System.out.println("\nVocê Comprou a peça: "+ newPiece);
								player.addStart(newPiece);
								comprados++;
								toBuy--;
								
							} else {
								
								System.out.println("\nVocê só pode comprar uma peça por rodada!!!");
							}
							
						} else {
							System.out.println("\nNão há mais peças para comprar!!!");	
							
						}
						
						break;
						
					case 3:
						if (toBuy > 0) {
							if(comprados == 0) {
								System.out.println("Você deve tentar comprar uma peça antes de passar a vez!!!");
							} else {
								System.out.println("\nVocê passou a vez...");
								passed++;
								control = false;
							}
							
						} else {
							System.out.println("\nVocê passou a vez...");
							passed++;
							control = false;
							
						}
						
						break;
						
					default:
						System.out.println("Opção Invalida, tente novamente!!!");
					break;
					
				}	
			} while (control);
			
		} else {
		  
			System.out.println("Vez do bot:");
			
			if(gameBoard.empty()) {
				Entity pieceForPlay = player.removeMid(0);
				
				passed = 0;
				//control = false;
				System.out.println("\nBot jogou  --> "+pieceForPlay);
			    gameBoard.addStart(pieceForPlay);
				
			} else {
				
				if (bot.botSearchIndex(gameBoard.noInicio.piece.value1, gameBoard.noUltimo.piece.value2) != -1) {
					
					Entity pieceForPlay = bot.removeMid(bot.botSearchIndex(gameBoard.noInicio.piece.value1, gameBoard.noUltimo.piece.value2));
					
					if (pieceForPlay.value1 == gameBoard.noInicio.piece.value1 || pieceForPlay.value2 == gameBoard.noInicio.piece.value1) {
						if (pieceForPlay.value2 != gameBoard.noInicio.piece.value1) {
							int aux = pieceForPlay.value2;
							pieceForPlay.value2 = pieceForPlay.value1;
							pieceForPlay.value1 = aux;
							
							passed = 0;
							System.out.println("\nBot jogou  --> "+pieceForPlay);
							gameBoard.addStart(pieceForPlay);
							
						} else {
							
							passed = 0;
							System.out.println("\nBot jogou  --> "+pieceForPlay);
							gameBoard.addStart(pieceForPlay);
							
						}	
					} else if (pieceForPlay.value1 == gameBoard.noUltimo.piece.value2 || pieceForPlay.value2 == gameBoard.noUltimo.piece.value2) {
						if(pieceForPlay.value1 != gameBoard.noUltimo.piece.value2) {
							int aux = pieceForPlay.value1;
							pieceForPlay.value1 = pieceForPlay.value2;
							pieceForPlay.value2 = aux;
							
							passed = 0;
							System.out.println("\nBot jogou  --> "+pieceForPlay);
							gameBoard.addEnd(pieceForPlay);
							
						} else {
							
							passed = 0;
							System.out.println("\nBot jogou  --> "+pieceForPlay);
							gameBoard.addEnd(pieceForPlay);
							
						}
						
					} 
						
				} else {
					
					if (toBuy > 0) {
						
						Random random2 = new Random();
						Entity  newPiece = new Entity();
						
						newPiece.value1 = random2.nextInt(7);
						newPiece.value2 = random2.nextInt(7);
						
						System.out.println("\nBot Comprou a peça: "+ newPiece);
						bot.addStart(newPiece);
						toBuy--;
						
						if (bot.botSearchIndex(gameBoard.noInicio.piece.value1, gameBoard.noUltimo.piece.value2) != -1) {
							
							Entity pieceForPlay = bot.removeMid(bot.botSearchIndex(gameBoard.noInicio.piece.value1, gameBoard.noUltimo.piece.value2));
							
							if (pieceForPlay.value1 == gameBoard.noInicio.piece.value1 || pieceForPlay.value2 == gameBoard.noInicio.piece.value1) {
								if (pieceForPlay.value2 != gameBoard.noInicio.piece.value1) {
									int aux = pieceForPlay.value2;
									pieceForPlay.value2 = pieceForPlay.value1;
									pieceForPlay.value1 = aux;
									
									passed = 0;
									System.out.println("\nBot jogou  --> "+pieceForPlay);
									gameBoard.addStart(pieceForPlay);
									
								} else {
									
									passed = 0;
									System.out.println("\nBot jogou  --> "+pieceForPlay);
									gameBoard.addStart(pieceForPlay);
									
								}	
							} else if (pieceForPlay.value1 == gameBoard.noUltimo.piece.value2 || pieceForPlay.value2 == gameBoard.noUltimo.piece.value2) {
								if(pieceForPlay.value1 != gameBoard.noUltimo.piece.value2) {
									int aux = pieceForPlay.value1;
									pieceForPlay.value1 = pieceForPlay.value2;
									pieceForPlay.value2 = aux;
									
									passed = 0;
									System.out.println("\nBot jogou  --> "+pieceForPlay);
									gameBoard.addEnd(pieceForPlay);
									
								} else {
									
									passed = 0;
									System.out.println("\nBot jogou  --> "+pieceForPlay);
									gameBoard.addEnd(pieceForPlay);
									
								}
								
							}
							
							
						} else {
							System.out.println("Bot passou a vez...");
							passed++;
							
							
						}
						
					} else {
						
						System.out.println("Bot passou a vez...");
						passed++;
						
						
					}
					
				}
				
			}
		
		}

	}
	
}
