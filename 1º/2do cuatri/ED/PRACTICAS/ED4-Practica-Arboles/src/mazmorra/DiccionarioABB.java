package mazmorra;

public class DiccionarioABB {

	private NodoABB raiz;
	private int numElementos;

	public DiccionarioABB() {
		raiz = null;
		numElementos = 0;
	}

	public boolean vacio() {
		return raiz == null;
	}

	public int getNumElementos() {
		return numElementos;
	}

	public Sala getElemento(int clave) {
		return this.getElementoRec(raiz, clave);
	}

	private Sala getElementoRec(NodoABB nodo, int clave) {
		Sala resultado = null;
		if (nodo == null) {
			resultado = null;
		} else if (nodo.getClave() == clave) {
			resultado = nodo.getDato();
		} else if (nodo.getClave() > clave) {
			resultado = this.getElementoRec(nodo.getIzquierdo(), clave);
		} else {
			resultado = this.getElementoRec(nodo.getDerecho(), clave);
		}
		return resultado;
	}

	public boolean contiene(int clave) {
		return (getElemento(clave) != null);
	}


	public void insertar(int clave, Sala dato) {
		raiz = this.insertarRec(raiz, clave, dato);
	}

	private NodoABB insertarRec(NodoABB nodo, int clave, Sala dato){
		if (nodo == null) {     // Crear nuevo nodo
			nodo = new NodoABB(clave, dato);
			numElementos++;
		} else if (clave < nodo.getClave()) {    // Subárbol izquierdo
			NodoABB nuevoIzq = this.insertarRec(nodo.getIzquierdo(), clave, dato);
			nodo.setIzquierdo(nuevoIzq);
		} else if (clave > nodo.getClave()) {    // Subárbol derecho
			NodoABB nuevoDer = this.insertarRec(nodo.getDerecho(), clave, dato);
			nodo.setDerecho(nuevoDer);
		} else {      // Clave repetida
			System.out.println("Error. La clave " + clave + " ya existe");
		}
		return nodo;    // Devolver la nueva raíz del subárbol
	}


	public void borrar(int clave) {
		raiz = this.borrarRec(raiz, clave);
	}

	private NodoABB borrarRec(NodoABB nodo, int clave) {
		if (nodo == null) {
			System.out.println("la clave buscada no existe");
		} else if (nodo.getClave() > clave) {  // Subarbol izquierdo
			NodoABB nuevoIzq = this.borrarRec(nodo.getIzquierdo(), clave);
			nodo.setIzquierdo(nuevoIzq);
		} else if (nodo.getClave() < clave) {  // Subarbol derecho
			NodoABB nuevoDer = this.borrarRec(nodo.getDerecho(), clave);
			nodo.setDerecho(nuevoDer);
		} else {  // Borrar elemento en nodo
			if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
				nodo = null;  // Caso 1
			} else if (nodo.getDerecho() == null) {  // Caso 2
				nodo = nodo.getIzquierdo();
			} else if (nodo.getIzquierdo() == null) {  // Caso 2
				nodo = nodo.getDerecho();
			} else {    // Caso 3
				NodoABB nuevoIzq = this.cambiarPorMenor(nodo,
						nodo.getIzquierdo());
				nodo.setIzquierdo(nuevoIzq);
			}
			numElementos--;
		}
		return nodo;
	}

	private NodoABB cambiarPorMenor(NodoABB nodoBorrar, NodoABB nodoMenor) {
		if (nodoMenor.getDerecho() != null) {   // Subárbol derecho
			NodoABB nuevoDer = this.cambiarPorMenor(nodoBorrar, nodoMenor.getDerecho());
			nodoMenor.setDerecho(nuevoDer);
			return nodoMenor;
		} else {  // Encontrado nodo menor inmediato
			nodoBorrar.setClave(nodoMenor.getClave()); // Cambiar datos
			nodoBorrar.setDato(nodoMenor.getDato());
			return nodoMenor.getIzquierdo();
			// Devolver subarbol izquierdo de menor inmediato
		}
	}

	public String toString() {
		return this.toString(raiz, 1);
	}

	private String toString(NodoABB nodo, int nivel) {
		String resultado = "";
		if (nodo != null) {
			resultado = this.toString(nodo.getIzquierdo(), nivel + 1)
					+ nodo.getDato()
					+ " [Nivel " + nivel + "]\n"
					+ this.toString(nodo.getDerecho(), nivel+1);
		}
		return resultado;
	}


	/**
	 * imprimir el árbol en un formato de matriz de Strings, recorriendolo en
	 * profundidad.
	 */
	private int calcularAltura(NodoABB nodo) {
		int altura = 0;
		if (nodo != null) {
			int alturaIzq = calcularAltura(nodo.getIzquierdo());
			int alturaDer = calcularAltura(nodo.getDerecho());
			if (alturaIzq > alturaDer) {
				altura = 1 + alturaIzq;
			} else {
				altura = 1 + alturaDer;
			}
		}
		return altura;
	}


	public void mostrar2D() {
		int altura = calcularAltura(raiz);

		if (altura != 0) {
			// columnas = 2^altura - 1
			int columnas = 1;
			for (int i = 0; i < altura; i++) {
				columnas *= 2;
			}
			columnas -= 1;

			String[][] matriz = new String[altura][columnas];

			// matriz con cadenas vacias
			for (int i = 0; i < altura; i++) {
				for (int j = 0; j < columnas; j++) {
					matriz[i][j] = "  ";
				}
			}


			mostrar2DR(raiz, 0, columnas / 2, matriz, altura);

			// Imprimir matriz
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz[i].length; j++) {
					System.out.print(matriz[i][j]);
				}
				System.out.println();
			}
		}else{
			System.out.println("Árbol vacío");
		}

	}

	private void mostrar2DR(NodoABB nodo, int fila, int col, String[][] matriz, int alturaTotal) {
		if (nodo != null) {
			// desplazamiento = 2^(alturaTotal-fila-2)
			int desplazamiento = 1;
			for (int i = 0; i < alturaTotal - fila - 2; i++) {
				desplazamiento = desplazamiento * 2;

			}

			String claveStr;
			if (nodo.getClave() < 10) {
				claveStr = " " + nodo.getClave();
			} else {
				claveStr = "" + nodo.getClave();
			}

			matriz[fila][col] = claveStr;


			mostrar2DR(nodo.getIzquierdo(), fila + 1, col - desplazamiento, matriz, alturaTotal);
			mostrar2DR(nodo.getDerecho(), fila + 1, col + desplazamiento, matriz, alturaTotal);
		}
	}

	/**
	 * Este método dividirá el árbol binario de búsqueda del diccionario en dos según un valor de
	 * la clave, obteniendo dos subárboles, correspondientes a dos diccionarios. El primer subárbol
	 * contendrá todos los nodos con clave menor o igual a la indicada, y el otro subárbol contendrá
	 * los nodos con clave mayor. La estructura padre-hijo original debe mantenerse siempre que
	 * sea posible.
	 */
	public DiccionarioABB[] partir(int clave) {
		DiccionarioABB[] resultado = new DiccionarioABB[]{new DiccionarioABB(), new DiccionarioABB()};

		NodoABB[] raices = partirRecursivo(this.raiz, clave);

		resultado[0].raiz = raices[0];
		resultado[1].raiz = raices[1];

		// actualizamos numero de elementos
		resultado[0].numElementos = contarNodos(resultado[0].raiz);
		resultado[1].numElementos = contarNodos(resultado[1].raiz);

		return resultado;
	}


	// partimos en subarbol<= y subarbol>
	private NodoABB[] partirRecursivo(NodoABB nodo, int clave) {
		NodoABB[] resultado = new NodoABB[]{null, null};

		if (nodo != null) {
			// copia del nodo actual
			NodoABB copia = new NodoABB(nodo.getClave(), nodo.getDato());

			if (nodo.getClave() <= clave) {
				// subárbol derecho
				NodoABB[] resultadoDerecho = partirRecursivo(nodo.getDerecho(), clave);

				copia.setIzquierdo(copiarSubarbol(nodo.getIzquierdo()));
				copia.setDerecho(resultadoDerecho[0]);

				// menores/iguales, mayores
				resultado = new NodoABB[]{ copia, resultadoDerecho[1] };
			} else {
				// subárbol izquierdo
				NodoABB[] resultadoIzquierdo = partirRecursivo(nodo.getIzquierdo(), clave);

				copia.setDerecho(copiarSubarbol(nodo.getDerecho()));
				copia.setIzquierdo(resultadoIzquierdo[1]);

				// menores, mayores
				resultado = new NodoABB[]{ resultadoIzquierdo[0], copia };
			}
		}

		return resultado;
	}


	// copia del subarbol
	private NodoABB copiarSubarbol(NodoABB nodo) {
		NodoABB copia = null;
		if (nodo != null) {
			copia = new NodoABB(nodo.getClave(), nodo.getDato());
			copia.setIzquierdo(copiarSubarbol(nodo.getIzquierdo()));
			copia.setDerecho(copiarSubarbol(nodo.getDerecho()));
		}
		return copia;
	}

	// cuenta los nodos del subarbol
	private int contarNodos(NodoABB nodo) {
		int res = 0;

		if (nodo != null) {
			res = 1 + contarNodos(nodo.getIzquierdo()) + contarNodos(nodo.getDerecho());
		}
		return res;
	}

}
