package bean;

public class Fermata {
	private int idFermata ;
	private String nome ;
	private double coodX;
	private double coordY;
	
	public double getCoodX() {
		return coodX;
	}
	public void setCoodX(double coodX) {
		this.coodX = coodX;
	}
	public double getCoordY() {
		return coordY;
	}
	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}
	public Fermata(int idFermata, String nome, double coodX, double coordY) {
		super();
		this.idFermata = idFermata;
		this.nome = nome;
		this.coodX = coodX;
		this.coordY = coordY;
	}
	public int getIdFermata() {
		return idFermata;
	}
	public void setIdFermata(int idFermata) {
		this.idFermata = idFermata;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	

	
	@Override
	public String toString() {
		return nome ;
	}

	@Override
	public int hashCode() {
		return ((Integer)idFermata).hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fermata other = (Fermata) obj;
		if (idFermata != other.idFermata)
			return false;
		return true;
	}
}
