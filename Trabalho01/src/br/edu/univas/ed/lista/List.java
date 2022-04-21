package br.edu.univas.ed.lista;

import br.edu.univas.ed.lista.dados.Entity;

public class List {
	
	public No noInicio;
	public No noUltimo;
	public  int tamanho=0;
	
	
	
	public void add(Entity piece1) {
		
		No newNo =  new No();
		newNo.piece = piece1;
		
		if(empty()) {
			
			noInicio =  newNo;
			noUltimo = newNo;
			tamanho++;
			
		}  else {
			
			noUltimo.proximo = newNo;
			newNo.anterior = noUltimo;
			noUltimo =  newNo;
			tamanho++;
		}
	}
	
	public void addStart(Entity piece1) {
		No newNo =  new No();
		newNo.piece = piece1;
		newNo.anterior  = null;
		newNo.proximo = noInicio;
		if(noInicio != null) {
			noInicio.anterior = newNo;
			
		}
		
		noInicio = newNo;
		if(tamanho  == 0) {
			noUltimo = noInicio;
			
		}
		tamanho++;
		
	}
	
	public void addEnd(Entity piece2) {
		No newNo =  new No();
		newNo.piece = piece2;
		newNo.proximo = null;
		newNo.anterior = noUltimo;
		if(noUltimo != null) {
			noUltimo.proximo = newNo;
		}
		
		noUltimo = newNo;
		if(empty()) {
			noInicio = newNo;
			
		}
		tamanho++;
	}
	
	public Entity removeStart() {
		
		if(empty()) {
			return null;
			
		}
		Entity removed = noInicio.piece;
		noInicio = noInicio.proximo;
		if(noInicio != null) {
			noInicio.anterior = null;
		} else {
			noUltimo = null;
		}
		tamanho--;
		
		return removed;
	}

	public Entity removeEnd() {
		
		if(empty()) {
			return null;
			
		}
		Entity removed = noUltimo.piece;
		noUltimo = noUltimo.anterior;
		if(noUltimo != null)  {
			noUltimo.proximo = null; 
		} else {
			noInicio = null;
			
		}
		tamanho--;
		
		return removed;
	}
	
	public void addMid(int indice, Entity piece3) {
		 if(indice <= 0) {
			 addStart(piece3);
			 
		 } else if (indice >= tamanho) {
			 addEnd(piece3);
			 
		 } else {
			 No position = noInicio;
			 
			 
			 for (int i = 0;i < indice - 1;i++) {
				  position = position.proximo;
				 
			 }
			 
			 
			 No newNo = new No();
			 newNo.piece = piece3;
			 newNo.anterior = position;
			 newNo.proximo = position.proximo; 
			 
			
			 position.proximo = newNo;
			 newNo.proximo.anterior = newNo;
			 tamanho++;
			 
		 }
		
		
	}
	
	public Entity removeMid(int indice) {
		if(indice < 0 || indice >= tamanho || noInicio == null) {
			return null;
			//CHECK DE MUDANÇAS
		} else if (indice == 0) {
			return removeStart();
			
		} else if (indice == tamanho - 1) {
			return removeEnd();
			
		} else  {
			No position = noInicio;
			for (int i = 0;i < indice;i++) {
				position = position.proximo;
				
			}
			if(position.anterior != null) {
				position.anterior.proximo = position.proximo;
				
			}
			if(position.proximo != null) {
				position.proximo.anterior = position.anterior; 
				
			}
			tamanho--;
			return  position.piece;
			
		}
		
	}
	
	public boolean empty() {
		return noInicio == null;	
	}
	
	 public String print1() {
		int index = 0;
		String str = "Indice --> Peças \n";
		No position = noInicio;
		
		while (position != null) {
			str += " ("+index+")   --> "+position.piece+" \n";  
			index++;
			position = position.proximo;
		}
		
	    return str;
	 }

	 public void print2() {
		 No aux = noInicio;
		 int index = 0;
		 
		 System.out.print("Indice: ");
		 while(index < tamanho) {
			 if(index == tamanho-1) {
				 System.out.println("("+index+") \n");  
			 } else {
				 System.out.print("("+index+") "); 
			 }
			 
			 index++;
		 }
		
		 aux = noInicio;
		 System.out.print("        ");
		 while(aux != null) {
			 if(aux.proximo == null) {
				 System.out.println(" "+aux.piece.value1+"  ");  
			 } else {
				 System.out.print(" "+aux.piece.value1+"  "); 
			 }
			 aux = aux.proximo;
		 }
		 
		 aux = noInicio;
		 System.out.print("Peças:  ");
		 while(aux != null) {
			 if(aux.proximo == null) {
				 System.out.println(" -  ");  
			 } else {
				 System.out.print(" -  "); 
			 }
			 aux = aux.proximo;
		 }
		 
		 aux = noInicio;
		 System.out.print("        ");
		 while(aux != null) {
			 if(aux.proximo == null) {
				 System.out.println(" "+aux.piece.value2 +"  ");  
			 } else {
				 System.out.print(" "+aux.piece.value2+"  "); 
			 }
			 aux = aux.proximo;
		 } 
		}

	 public void print3() {
		 No aux = noInicio;
		 
		 while(aux != null) {
			 if (aux.proximo == null) {
				 System.out.print("-"+aux.piece+"-\n");
				 
			 } else {
				 System.out.print("-"+aux.piece);
				 
			 }
			 aux = aux.proximo;
		 }
		  
	 }
	 
	 public int botSearchIndex (int value1, int value2) {
		 int botIndex = 0;
		 No aux = noInicio;
		 
		 while (aux != null) {
			 if(aux.piece.value1 == value1 || aux.piece.value2 == value1) {
				 return botIndex;
				 
			 }
			 aux = aux.proximo;
			 botIndex++;
		 }
		 
		 botIndex = 0;
		 aux = noInicio;
		 
		 while (aux != null) {
			 if(aux.piece.value1 == value2 || aux.piece.value2 == value2) {
				 return botIndex;
				 
			 }
			 
			 aux = aux.proximo;
			 botIndex++;
		 }
		 
		 return -1;
	 }
	 
}
