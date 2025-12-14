package libClases;
import java.util.Scanner;
public class Empresa implements Cloneable,Proceso{

	private Cliente[] clientes;
	private int nclientes;
	private int nmaxclientes;
	
	
	public int getNClientes()
	{
		return nclientes;
	}
	public Empresa()
	{
		nclientes=0;
		nmaxclientes=10;
		clientes=new Cliente[nmaxclientes];
		
	}
	
	public int GetNClienteMovil()
	{
		int contador=0;
		for(int i=0;i<this.getNClientes();i++) {
			if(clientes[i] instanceof ClienteMovil) {
				contador++;
			}
		}
		return contador;
	}
	
	private int BuscarCliente(String dni) {
		
		int pos=-1;
		int i=0;
		while( i<nclientes ) {
			if(clientes[i].getNIF().equals(dni)) {
				pos=i;
			}
			i++;
		}
		return pos;
	}
	
	public void alta(Cliente c) {
		
		if(c==null || BuscarCliente(c.getNIF())!=-1)
			return; 
		
		//Si esta lleno, duplicamos tamaño
		
		if(nclientes==clientes.length) {
			nmaxclientes*=2;
			Cliente [] clientestemp=new Cliente[nmaxclientes];
			for(int i=0;i<nclientes;i++) {
				clientestemp[i]=clientes[i];
			}
			clientes=clientestemp;
		}
		clientes[nclientes]=c;
		nclientes++;
		
	}
	
	
	public void alta()
	{
		Scanner sc=new Scanner(System.in);
		String dni;
		System.out.println("Dni del cliente: ");
		dni=sc.nextLine();
		
		int pos=this.BuscarCliente(dni);
		
		if(pos!=-1) {
			System.out.println("Ya existe un cliente con ese DNI");
			System.out.println(clientes[pos]);
		}
		
		else {
			Cliente c=null; //no existe ninguno con ese dni, lo creamos
			String nombre;
			Fecha fAlta,fNac;
			float minutosHablados;
			System.out.println("Nombre del cliente: ");
			nombre=sc.nextLine();
			System.out.println("Fecha de nacimiento del cliente: ");
			fNac=Fecha.pedirFecha();
			System.out.println("Fecha de alta del cliente: ");
			fAlta=Fecha.pedirFecha();
			System.out.println("Minutos que habla al mes: ");
			minutosHablados=sc.nextFloat();
			System.out.println("Indique el tipo de contrato del cliente (1.-Movil, 2.-Tarifa Plana)");
			int tipo;
			tipo=sc.nextInt();
			
			if(tipo==1) {
				System.out.println("Precio por minuto del cliente:");
				float precio;
				precio=sc.nextFloat();
				System.out.println("Fecha de fin de permanencia del cliente: ");
				Fecha permanencia;
				permanencia=Fecha.pedirFecha();
				c=new ClienteMovil(dni,nombre,fNac,fAlta,permanencia,minutosHablados,precio);
			}
			else if(tipo==2) {
				
				String nacionalidad;	
				System.out.println("Nacionalidad del cliente: ");
				nacionalidad=sc.nextLine();
				c=new ClienteTarifaPlana(dni,nombre,fNac,fAlta,minutosHablados,nacionalidad);
			}
			alta(c);
		}

	}
	
	
	public void baja(String dni)
	{
		if(nclientes==0)
			return; //si no hay clientes pa que busco
		int pos=BuscarCliente(dni);
		if(pos!=-1) {
		for(int i=pos;i<nclientes-1;i++) {
			clientes[i]=clientes[i+1];
		}
		nclientes--;
		if(nclientes < clientes.length / 2) {
			Cliente [] temp=new Cliente[nmaxclientes/2];
			for(int i=0;i<nclientes;i++) {
				temp[i]=clientes[i];
			}
			clientes=temp;
		}
		}
	}
	
	
	public void baja()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Introduzca el dni del cliente a eliminar: ");
		String dni=sc.nextLine();
		int pos=BuscarCliente(dni);
		if(pos!=-1) {
			System.out.println(clientes[pos]);
			System.out.println("¿Seguro que desea eliminarlo? ");
			if(sc.next().equals("s")) {
				Cliente c=clientes[pos];
				baja(dni);
				System.out.println("El cliente"+c.getNombre() + " con nif" + c.getNIF()+ " ha sido eliminado");
			}
			else {
				System.out.println("El cliente"+" con nif" + clientes[pos].getNIF()+ " no ha sido eliminado");
			}
		}
		else {
			System.out.println("El cliente no ha sido encontrado");
		}
	}
	
	
	
	public Object Clone() {
		Empresa obj=null;
		
		try {
			obj=(Empresa)super.clone();
			obj.clientes=clientes.clone();
		for(int i=0;i<this.getNClientes();i++) {
				obj.clientes[i]=(Cliente)clientes[i].Clone();
			}
		}catch(CloneNotSupportedException ex) {
			System.out.println("No se ha podido clonar");
		}
		
		
		return (Object)obj;
	}
	
	@Override
	public void ver() {
		System.out.println(this);
	}
	
	public float factura() {
		float ftotal=0.0f;
		for(int i=0;i<this.getNClientes();i++) {
			ftotal+=clientes[i].factura();
		}
		
		return ftotal;
	}
	
	
	@Override
	public String toString() 
	{
		String texto="";
		for(int i=0;i<this.getNClientes();i++) {
			texto+=clientes[i]+"\n"; //meto cada cliente y un salto de linea
		}
		
		return texto;
	}
	
	
	public void descuento(int desc) {
	    for(int i=0; i<this.getNClientes(); i++) {
	        if(clientes[i] instanceof ClienteMovil) {
	            ClienteMovil c = (ClienteMovil)clientes[i];
	            float precioActual = c.getPrecio();
	            float nuevoPrecio = precioActual * (100 - desc) / 100.0f;  // ✅
	            c.setPrecio(nuevoPrecio);
	        }
	    }
	}
	
}