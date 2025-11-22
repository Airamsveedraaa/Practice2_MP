package libClases;
import java.util.Scanner;

public final class Fecha implements Cloneable,Proceso {

	private int dia;
	private int mes;
	private int anio;
	
	public void setFecha(int d,int m,int a)
	{
		int dmax, diaMes[] = {31,28,31,30,31,30,31,31,30,31,30,31};
		 anio=a; //VIP debo asignar año para al llamar a bisiesto() tengo el año bien
		 if (m<1) //si el mes es incorrecto
		 m=1;
		 else if (m>12) //si el mes es incorrecto
		 m=12;
		 dmax=diaMes[m-1];
		 if (m==2 && bisiesto())
		 dmax++;
		 if (d>dmax)
		 d=dmax;
		 else if (d<1)
		 d=1;
		 dia=d;
		 mes=m;
	}
	
	public Fecha(int dia,int mes,int anio) 
	{
		setFecha(dia,mes,anio);
	}
	
	public Fecha(Fecha f)
	{
		dia=f.dia;
		mes=f.mes;
		anio=f.anio;
	}
	
	public int getDia() {return dia;};
	public int getMes() {return mes;};
	public int getAnio() {return anio;};
	public void setDia(int d) {dia=d;};
	public void setMes(int m) {mes=m;};
	public void setAnio(int a) {anio=a;};
	
	public String toString()
	{
		String s="";
		if(dia<10) s=s+0;
			s=s+dia+"/";
		if(mes<10) s=s+0;
		s=s+mes+"/"+anio;
		return s;
	}
	
	public boolean bisiesto()
	{
		boolean b=false;
		if(anio%4==0) 
			b=true;
		
		if(anio%100==0 && anio%400 !=0)
				b=false;
		
		return b;
	}
	
	public void ver()
	{
		System.out.println(this);
	}
	
	public static Fecha pedirFecha() {
		Fecha fecha=null;
		boolean valida=false;
		Scanner sc=new Scanner(System.in);
		int dia,mes,anio;
		
		do {
			System.out.println("Introduce la fecha (dd/mm/aaaa)");
			String cadena=sc.next();
			String[] tokens=cadena.split("/");
			try {
				if(tokens.length!=3)
					throw new NumberFormatException();
				dia=Integer.parseInt(tokens[0]);
				mes=Integer.parseInt(tokens[1]);
				anio=Integer.parseInt(tokens[2]);
				
				fecha= new Fecha(dia,mes,anio);
				
				if(fecha.getDia() != dia || fecha.getMes()!= mes)
					throw new NumberFormatException();
				
				valida=true;
				
			}catch(NumberFormatException e) {
				System.out.println("Fecha no valida");
			}
			
		}while(!valida);
		sc.close();
		return fecha;
	}
	
	
	public static boolean mayor(Fecha f1,Fecha f2)
	{
		if(f1.anio*1000+f1.mes*100+f1.dia > f2.anio*1000+f2.mes*100+f2.dia)
			return true;
		else
			return false;
	}
	
	
	public Object Clone()
	{
		Object obj=null;
		try {
			obj=super.clone();
			
		}catch(CloneNotSupportedException ex) {
			System.out.println("No se puede duplicar");
		}
		return obj;
	}
	
	public boolean equals(Object obj)
	{
		if(this==obj)return true; //es el mismo objeto
		if(obj==null)return false;
		if(getClass()!=obj.getClass())
			return false; //si no son del mismo tipo de clase no son iguales
		Fecha c=(Fecha) obj; //casteo a fecha
		return (dia==c.dia && mes==c.mes && anio==c.anio);
	}
	
	public Fecha diaSig() {
		Fecha fechaSig = new Fecha(this.dia,this.mes,this.anio);
		
		int diaMes[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	    if (bisiesto())                          //si el año es bisiesto febrero tiene 29 dias
	      diaMes[2]=29;

	    fechaSig.dia=this.dia+1;

	    if (fechaSig.dia>diaMes[fechaSig.mes]) {                          //si al incrementar dia superamos el numero de dias de dicho mes
	        do{
	        	fechaSig.dia=fechaSig.dia-diaMes[fechaSig.mes];             //pasamos al dia del mes anterior
	        	fechaSig.mes++;			                //incrementamos un mes
	            if (fechaSig.mes>12) {                    //si al incrementar mes pasamos de 12 meses
	            	fechaSig.mes=1;                       //pasamos al mes 1
	            	fechaSig.anio++;                      //del año siguiente
	                if (fechaSig.bisiesto())              //si el año es bisiesto febrero tiene 29 dias
	                    diaMes[2]=29;
	                else
	                    diaMes[2]=28;
	            }
	        }while(fechaSig.dia>diaMes[fechaSig.mes]);                    //hasta que el dia < diamax del mes en el q ns encontremos
	    }
		
		return fechaSig;
	}
	
	
	public static void main(String[] args) {
		 Fecha f1 = new Fecha(29,2,2001), f2 = new Fecha(f1), f3 = new Fecha(29,2,2004);
		 final Fecha f4=new Fecha(05,12,2003); //es constante la referencia f4
		 System.out.println("Fechas: " + f1.toString() + ", " + f2 + ", " + f3 + ", " + f4);
		 f1=new Fecha(31,12,2016); //31/12/2016
		 f4.setFecha(28, 2, 2008); //pero no es constante el objeto al que apunta
		 System.out.println(f1 +" "+ f2.toString() +" " + f3 + " "+ f4 + " "+ f1);
		 f1=new Fecha(f4.getDia()-10, f4.getMes(), f4.getAnio()-7); //f1=18/02/2001
		 f3=Fecha.pedirFecha(); // pide una fecha por teclado
		 if (f3.bisiesto() && Fecha.mayor(f2,f1))
		 System.out.println("El " + f3.getAnio() + " fue bisiesto, " + f1 + ", " + f3);
		 System.out.print("Fin\n");
		}
	
	
	}

