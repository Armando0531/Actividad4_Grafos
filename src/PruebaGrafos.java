/*
 * Codigo principal sacado del libro
 * Estructura de datos en java Joyanes 
 * 
 * */

import java.util.Scanner;

class Vertice {
	private String nombre;
    private int numVertice;
    
    public Vertice() {}
    public Vertice(String x){
    	nombre = x;
	    numVertice = -1;
	}
    
    public String getNombre() {
		return nombre;
    }
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumVertice() {
		return numVertice;
	}
	public void setNumVertice(int numVertice) {
		this.numVertice = numVertice;
	}

    public String nomVertice() {
    	return nombre;
    }
    public boolean equals(Vertice n) {
    	return nombre.equals(n.nombre);
    }
    public void asigVert(int n){
        numVertice = n;
    }
    public String toString(){
    	return nombre + " (" + numVertice + ")";
    }
      
}

class GrafoMatriz{
    int numVerts;
    static int maxVerts;
    Vertice [] verts;
    int [][] matAd;
    
    public GrafoMatriz(){
    	this(maxVerts);
    }
    public GrafoMatriz(int mx){
    	maxVerts=mx;
        matAd = new int [mx][mx];
        verts = new Vertice[mx];
        for (int i = 0; i < mx; i++)
        	for (int j = 0; i < mx; i++)
        		matAd[i][j] = 0;
        numVerts = 0;
    }
    
    public int numVertice(String vs) {
         Vertice v = new Vertice(vs);
         boolean encontrado = false;
         int i = 0;
         for (; (i < numVerts) && !encontrado;){
        	 encontrado = verts[i].equals(v);
        	 if (!encontrado) 
        		 i++ ;
        	 }
         return (i < numVerts) ? i : -1 ;
    }
    public void nuevoVertice (String nom){
    	boolean esta = numVertice(nom) >= 0;
    	if (!esta){
    		Vertice v = new Vertice(nom);
    		v.asigVert(numVerts);
    		verts[numVerts++] = v;
    	}
    }
    public void nuevoArco(String a, String b)throws Exception{
    	int va, vb;
        va = numVertice(a);
        vb = numVertice(b);
        if (va < 0 || vb < 0) throw new Exception ("Vértice no existe");
        matAd[va][vb] = 1;
    }
    public boolean adyacente(String a, String b)throws Exception{
    	int va, vb;
        va = numVertice(a);
        vb = numVertice(b);
        if (va < 0 || vb < 0) throw new Exception ("Vértice no existe");
        return matAd[va][vb] == 1;
    }
    public static int[]recorrerAnchura(GrafoMatriz g, String org) throws Exception{
    	int w, v;
        int [] m;
     
        v = g.numVertice(org);
        
        int CLAVE =-1;
        if (v < 0) throw new Exception("Vértice origen no existe");
        
        ColaLista cola = new ColaLista();
        m = new int[g.numVerts];
        // inicializa los vértices como no marcados
        for (int i = 0; i < g.numVerts; i++)
        m[i] = CLAVE;
        m[v] = 0; // vértice origen queda marcado
        cola.insertar(new Integer(v));
        while (! cola.colaVacia()){
        	Integer cw;
        	cw = (Integer) cola.quitar();
        	w = cw.intValue();
        	System.out.println("Vértice " + g.verts[w] + "visitado");
        	// inserta en la cola los adyacentes de w no marcados
        	for (int u = 0; u < g.numVerts; u++)
        	if ((g.matAd[w][u] == 1) && (m[u] == CLAVE))
        	{
        	// se marca vertice u con número de arcos hasta el
        	m[u] = m[w] + 1;
        	cola.insertar(new Integer(u));
        	}
        }
        return m;
        }
    
}

class Nodo {
	Object elemento;
	Nodo siguiente;
	int dato;
	
	public Nodo(Object x){
		elemento = x;
		siguiente = null;
		}
	public Nodo(int x){
		dato = x;
	    siguiente = null;
	}
	public Nodo(int x, Nodo n){
	    dato = x;
	    siguiente = n;
	}
	
	public int getDato(){
	    return dato;
	}
	public Nodo getEnlace(){
	    return siguiente;
	}
	public void setEnlace(Nodo enlace){
	    this.siguiente = enlace;
	}
}

class ColaLista { 
	protected Nodo frente;
	protected Nodo fin;
	
	public ColaLista(){
		frente = fin = null;
	}
	
    public void insertar(Object elemento){
    	Nodo a;
        a = new Nodo(elemento);
        if (colaVacia()){
        	frente = a;
        	}else{
        		fin.siguiente = a;
        	}
        fin = a;
        }
    public Object quitar() throws Exception{
    	Object aux;
    	if (!colaVacia()){
    		aux = frente.elemento;
    		frente = frente.siguiente;
    	}else
    		throw new Exception("Eliminar de una cola vacía");
    	return aux;
    }
    public void borrarCola(){
    	for (; frente != null;){
    		frente = frente.siguiente;
        }
    	System.gc();
    }
    public Object frenteCola() throws Exception{
    	if (colaVacia()){
    		throw new Exception("Error: cola vacía");
        }
    	return (frente.elemento);
    }
    public boolean colaVacia(){
    	return (frente == null);
    	}
}

class Arco{
	int destino;
    double peso;
    
    public Arco(int d){
    	destino = d;
    }
    public Arco(int d, double p){
    	this(d);
    	peso = p;
    	}
    
    public int getDestino(){
        return destino;
    }
    public boolean equals(Object n){
    	Arco a = (Arco)n;
    	return destino == a.destino;
    }
} 

class GrafoAdcia{
	int numVerts;
    static int maxVerts = 20;
    Vertice [] tablAdc;
    
    public GrafoAdcia(int mx){
    	tablAdc = new Vertice[mx];
        numVerts = 0;
        maxVerts = mx;
    }
    public int numVertice(String vs) {
        Vertice v = new Vertice(vs);
        boolean encontrado = false;
        int i = 0;
        for (; (i < numVerts) && !encontrado;){
       	 encontrado = tablAdc[i].equals(v);
       	 if (!encontrado) 
       		 i++ ;
       	 }
        return (i < numVerts) ? i : -1 ;
   }
} 

class NodoPila{
	Object elemento;
    NodoPila siguiente;
    
    NodoPila(Object x){
    	elemento = x;
        siguiente = null;
    }
} 

public class PruebaGrafos {
	public static void main(String[] args) {
		Scanner entrada = new Scanner (System.in);
		
		System.out.println("Ingresa un maximo de vertices");
		int mx=entrada.nextInt();
		GrafoMatriz grafo = new GrafoMatriz(mx);
		
	    byte op=0;
	    
	    do {
		System.out.println("Elige una opcion");
		System.out.println("1) Añadir un vertice");
		System.out.println("2) Añadir un arco");
		System.out.println("3) Saber si son adyacentes");
		System.out.println("4) Recorrer en anchura");
		System.out.println("5) Salir");
	    op=entrada.nextByte();
	    entrada.nextLine();
	    switch(op) {
	    case 1: 
	    	System.out.println("Ingresa el nombre del vertice");
			String nom=entrada.nextLine();
			grafo.nuevoVertice(nom);
			System.out.println("Se añadio un vertice nuevo");
			break;
	    case 2:
	    	System.out.println("Ingresa el nombre del vertice origen");
			String a=entrada.nextLine();
			System.out.println("Ingresa el nombre del vertice destino");
			String b=entrada.nextLine();
			try {
				grafo.nuevoArco(a, b);
				System.out.println("Se añadio arco");
			} catch (Exception e) {
				System.out.println("Error no se añadio arco! (Debe ingresar dos vertices existentes)");
			}
			break;
	    case 3: 
	    	System.out.println("Ingresa el nombre del primer vertice");
			a=entrada.nextLine();
			System.out.println("Ingresa el nombre del segundo vertice");
			b=entrada.nextLine();
			try {
				System.out.println(grafo.adyacente(a, b)?"Son adyacentes":"No son adyacentes!");
			} catch (Exception e) {
				System.out.println("Ingresa vertices existentes!!");
			}
			break;
	    case 4: 
	    	System.out.println("Ingresa el nombre del vertice origen a recorrer");
			String origen=entrada.nextLine();
			try {
				grafo.recorrerAnchura(grafo, origen);
				System.out.println();
			} catch (Exception e) {
				System.out.println("Ingresa vertices que existan porfavor");
			}
			break;
	    case 5: 
	    	break;
	    default:
	    	System.out.println("Opcion incorrecta");
	    }
	    }while(op!=5);
	}

}
