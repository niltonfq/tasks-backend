package br.ce.wcaquino.taskbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;


public class TaskControllerTest {

	@Mock
	private TaskRepo taskRepo;
	
	@InjectMocks
	private TaskController controller;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	

	@Test
	void naoDeveSalvarTaskSemDescricao() {
		Task task = new Task();
		task.setDueDate(LocalDate.now());
		
		try {
			controller.save(task);
		} catch (ValidationException e) {
			assertEquals("Fill the task description", e.getMessage());
		}
	}
	
	@Test
	void naoDeveSalvarTaskSemData() {
		Task task = new Task();
		task.setTask("Descrição");
		
		try {
			controller.save(task);
		} catch (ValidationException e) {
			assertEquals("Fill the due date", e.getMessage());
		}
	}
	
	@Test
	void naoDeveSalvarTaskComDataPassada() {
		Task task = new Task();
		task.setDueDate(LocalDate.of(2010,1,1));
		task.setTask("Descrição");
		
		try {
			controller.save(task);
		} catch (ValidationException e) {
			assertEquals("Due date must not be in past", e.getMessage());
		}
	}
	
	@Test
	void deveSalvarTaskComSucesso() throws ValidationException {
		Task task = new Task();
		task.setDueDate(LocalDate.now());
		task.setTask("Descrição");
		
		controller.save(task);
		Mockito.verify(taskRepo).save(task);
	}
}
