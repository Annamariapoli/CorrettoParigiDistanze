package bean;

public class Connessione {
	private int idConnessione;
	private int idLinea;
	private int idStazP;
	private int idStazA;
	
	private Linea linea ;
	
	
	
	
	public Linea getLinea() {
		return linea;
	}
	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	public Connessione(int idConnessione, int idLinea, int idStazP, int idStazA) {
		super();
		this.idConnessione = idConnessione;
		this.idLinea = idLinea;
		this.idStazP = idStazP;
		this.idStazA = idStazA;
	}
	public int getIdConnessione() {
		return idConnessione;
	}
	public void setIdConnessione(int idConnessione) {
		this.idConnessione = idConnessione;
	}
	public int getIdLinea() {
		return idLinea;
	}
	public void setIdLinea(int idLinea) {
		this.idLinea = idLinea;
	}
	public int getIdStazP() {
		return idStazP;
	}
	public void setIdStazP(int idStazP) {
		this.idStazP = idStazP;
	}
	public int getIdStazA() {
		return idStazA;
	}
	public void setIdStazA(int idStazA) {
		this.idStazA = idStazA;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idConnessione;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connessione other = (Connessione) obj;
		if (idConnessione != other.idConnessione)
			return false;
		return true;
	}
	
	
}
