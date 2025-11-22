package libClases;

public class ClienteMovil extends Cliente{

	private Fecha permanencia;
	private float Precio;
	private float minutosHablados;
	
	//Constructores
	
	public ClienteMovil(String NIF,String nom,Fecha fNac,Fecha fAlta,Fecha permanencia,float minutos,float precio)
	{
		super(NIF,nom,fNac,fAlta);
		this.minutosHablados=minutos;
		this.Precio=precio;
		this.permanencia=(Fecha)permanencia.Clone();
	}
	public ClienteMovil(String NIF, String nom, Fecha fNac, Fecha fAlta,float minutos,float precio)
	{
	    this(NIF, nom, fNac, fAlta, new Fecha(fAlta.getDia(), fAlta.getMes(), fAlta.getAnio()+1), minutos, precio);

	}
	
	public ClienteMovil(String NIF, String nom, Fecha fNac,float minutos,float precio)
	{
	    this(NIF, nom, fNac, getFechaPorDefecto(), minutos, precio);
	}
	
	public ClienteMovil(ClienteMovil c)
	{
		super(c);
		this.minutosHablados=c.minutosHablados;
		this.Precio=c.Precio;
		this.permanencia=(Fecha)c.permanencia.Clone();
	}
	
	//Getters
	public Fecha getPermanencia() {return (Fecha)permanencia.Clone();}
	public float getMinutos() {return minutosHablados;};
	public float getPrecio() {return Precio;};
	
	//Setters
	
	public void setPermanencia(Fecha f)
	{
		this.permanencia=(Fecha)f.Clone();
	}
	
	
	public void setPrecio(float p)
	{
		this.Precio=p;
	}
	
	@Override
	public float factura()
	{
		return Precio*minutosHablados;
	}
	
	@Override
	public Object Clone()
	{
		return new ClienteMovil(this);
	}

	@Override
	public String toString()
	{
		return super.toString() + " " + permanencia + " " + minutosHablados + " x " + Precio +  " -->" + factura();
	}
	
	@Override
	public boolean equals(Object o)
	{
		return o instanceof ClienteMovil && getNIF().equals(((Cliente)o).getNIF());
	}
}
