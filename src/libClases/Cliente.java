package libClases;

public class Cliente implements Cloneable,Proceso {
	private static int contador=1;
	private final String nif;
	private final int codCliente;
	private String nombre;
	private final Fecha fechaNac;
	private final Fecha fechaAlta;
	
	private static final Fecha FechaDefecto= new Fecha(1,1,2018);
	
	//Constructores
	public Cliente(String NIF, String nom, Fecha fNac, Fecha fAlta)
	{
		this.nif=NIF;
		this.nombre=nom;
		this.fechaNac=(Fecha)fNac.Clone();
		this.fechaAlta=(Fecha)fAlta.Clone();
		this.codCliente=contador++;
	}
	
	public Cliente (String NIF, String nom, Fecha fNac) {
		
		Fecha fAlta= new Fecha(01,01,2018);
		this.nif=NIF;
		this.nombre=nom;
		this.fechaNac=(Fecha)fNac.Clone();
		this.fechaAlta=(Fecha)fAlta.Clone();
		this.codCliente=contador++;
	}
	
	public Cliente(Cliente c)
	{
		this.nif=c.nif;
		this.nombre=c.nombre;
		this.fechaNac=(Fecha)c.fechaNac.Clone();
		this.fechaAlta=(Fecha)c.fechaAlta.Clone();
		this.codCliente=contador++;
	}
	
	//Getters publicos de la clase
	public String getNIF() {return nif;};
	public int getCodCliente() {return codCliente;};
	public String getNombre() {return nombre;};
	public Fecha getFechaNac() {return (Fecha)fechaNac.Clone();};
	public Fecha getFechaAlta() {return (Fecha)fechaAlta.Clone();};
	public static Fecha getFechaPorDefecto() {return (Fecha)FechaDefecto.Clone();};
	
	
	//Setters publicos de la clase, afectan al nombre del cliente, al dni y a la fecha de alta
	public void SetFechaAlta(Fecha f) {
		this.fechaAlta.setFecha(f.getDia(),f.getMes(),f.getAnio());
	}
	
	public static void SetFechaDefecto(Fecha f) {
		FechaDefecto.setFecha(f.getDia(),f.getMes(),f.getAnio());
	}
	
	public void SetNombre(String nom)
	{
		this.nombre=nom;
	}
	
	public float factura() {return 0;}
	
	
	public Object Clone()
	{
		return new Cliente(this);
	}
	
	
	public void ver()
{
		System.out.println(this);
}
	
	public String toString() 
	{
		return nif + " " + fechaNac+": " + nombre + " (" + codCliente + " - " + fechaAlta + ") ";
	}
	
	public boolean equals(Object o) {
		return o instanceof Cliente && nif.equals(((Cliente)o).nif);
	}
}
