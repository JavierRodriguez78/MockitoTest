package com.javier.rodriguez.TestTarea;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Connection;
import org.mockito.Mockito.*;
import org.mockito.internal.matchers.Equals;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;



public class TestReposiotyr {

	
	@Test
	public void unaTareaSeGuardaConLaFechaActual() throws SQLException{
		Tarea tarea = new Tarea("Reto");
		Connection connection = mock(Connection.class);
		PreparedStatement preparedStatement = mock(PreparedStatement.class);
		when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
		
		TareaRepository repository = new TareaRepository(connection);
		repository.save(tarea);
		
	}
	
	@Test
	public void unaTareaSeGuardaConLaFechaActualDificil() throws SQLException{
		Tarea tarea = new Tarea("Reto");
		Connection connection = mock(Connection.class);
		PreparedStatement preparedStatement = mock(PreparedStatement.class);
		when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
		
		TareaRepository repository = new TareaRepository(connection);
		long antes = new Date().getTime();
		repository.save(tarea);
		long despues = new Date().getTime();
		long lapso = despues-antes;
		
		//Mirar si la fecha en el objeto Tarea es la fecha actual.
		assertThat((double)tarea.getFechaCreacion().getTime(), closeTo((antes+despues)/2, lapso));
		
		//Comprobar si el Repositoro llamó la PreparedStamente 
		//pasando la fecha Actual.
		ArgumentCaptor<java.sql.Timestamp> sqlDateCapture = ArgumentCaptor.forClass(java.sql.Timestamp.class);
		verify(preparedStatement).setTimestamp(Mockito.eq(2),sqlDateCapture.capture());
		assertThat((double)sqlDateCapture.getValue().getTime(), closeTo((antes+despues)/2, lapso));
		
	}
}
