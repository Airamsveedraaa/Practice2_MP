package libClases;

public class ClienteTarifaPlana extends Cliente{

	private static float ExcesoMinutos=0.15f;
	private static int Minutos=300;
	private static float PrecioMinutos=20f;
	
	private float minutosHablados;
	private String nacionalidad;
	
	//Constructores
	public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, Fecha fAlta, float minutosHablados, String Nacionalidad)
	{
		super(NIF,nom,fNac,fAlta);
		this.minutosHablados=minutosHablados;
		this.nacionalidad=Nacionalidad;
	}
	
	public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, float minutosHablados, String Nacionalidad)
	{
		super(NIF,nom,fNac);
		this.minutosHablados=minutosHablados;
		this.nacionalidad=Nacionalidad;
	}
	
	public ClienteTarifaPlana(Cliente c)
	{
		super(c);
	}
	
	
	//Setters
	public void setMinutos(int m) 
	{
		this.minutosHablados=m;
	}
	
	public void setNacionalidad(String Nacionalidad)
	{
		this.nacionalidad=Nacionalidad;
	}
	
	public static void setTarifa(int m,float p)
	{
		Minutos=m;
		PrecioMinutos=p;
	}
	
	//Getters
	public static int getLimite() {return Minutos;};
	public String getNacionalidad() {return nacionalidad;};
	public static float getTarifa() {return PrecioMinutos;};
	public float getMinutosHablados() {return minutosHablados;};
	
	
	public float factura()
	{
		float total=PrecioMinutos;
		if(minutosHablados > Minutos) {
			total=total+(minutosHablados-Minutos)*ExcesoMinutos;
		}
		return total;
	}
	
	public String toString()
	{
		return super.toString() + " " + nacionalidad + " [" + Minutos + " por" + PrecioMinutos + " ]" + "---> " + factura();
	}
	
	public Object Clone()
	{
		return new ClienteTarifaPlana(getNIF(),getNombre(),getFechaNac(),getFechaAlta(),getMinutosHablados(),getNacionalidad());
	}
	
	public boolean equals(Object o)
	{
		return o instanceof ClienteTarifaPlana && getNIF().equals(((ClienteTarifaPlana)o).getNIF());
	}
	
}
