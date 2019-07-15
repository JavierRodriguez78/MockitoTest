package com.javier.rodriguez.TestTarea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class TareaRepository {

	private Connection connection;
	
	public TareaRepository(Connection connection) {
		this.connection = connection;
	}

	public void save(Tarea tarea) {
		PreparedStatement prepareStatement = null;
		try {
			Date ahora = new Date();
			tarea.setFechaCreacion(ahora);
			prepareStatement = connection.prepareStatement("INSERT INTO tareas (nombre, fecha) VALUES (?, ?)");
			prepareStatement.setString(1, tarea.getNombre());
			prepareStatement.setTimestamp(2, new java.sql.Timestamp(ahora.getTime()));
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}