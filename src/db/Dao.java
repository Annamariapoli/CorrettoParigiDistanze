package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.Connessione;
import bean.Fermata;

public class Dao {
	public List<Fermata> getAllf(){
		String query="select *  from fermata f   order by f.nome";
		Connection conn = DBConnect.getConnection();
		List<Fermata> all= new LinkedList<>();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				Fermata f = new Fermata (res.getInt("id_fermata"), res.getString("nome"), res.getDouble("coordX"), res.getDouble("coordY"));
				all.add(f);
			}
			conn.close();
			return all;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}

	public List<Connessione> getAllC(){
		String query="select *  from connessione";
		Connection conn = DBConnect.getConnection();
		List<Connessione> allc= new LinkedList<>();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				Connessione c = new Connessione(res.getInt("id_connessione"), res.getInt("id_linea"), res.getInt("id_stazP"), res.getInt("id_stazA"));
				allc.add(c);
			}
			conn.close();
			return allc;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}

}
