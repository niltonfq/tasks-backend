package br.ce.wcaquino.taskbackend.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateUtilsTest {

	@Test
	public void deveRetornarTrueParaDatasFuturas() {
		LocalDate data = LocalDate.of(2030, 1 , 1);
		assertTrue(DateUtils.isEqualOrFutureDate(data));
	}
	
	@Test
	public void deveRetornarFalseParaDatasPassadas() {
		LocalDate data = LocalDate.of(2010, 1 , 1);
		assertFalse(DateUtils.isEqualOrFutureDate(data));
	}

	@Test
	public void deveRetornarFalseParaHoje() {
		LocalDate data = LocalDate.now();
		assertTrue(DateUtils.isEqualOrFutureDate(data));
	}
}
