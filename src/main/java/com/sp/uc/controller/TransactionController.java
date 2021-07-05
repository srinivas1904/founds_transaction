package com.sp.uc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sp.uc.entity.ResponseTransaction;
import com.sp.uc.repository.utils.InputValidator;
import com.sp.uc.repository.utils.TransactionInput;
import com.sp.uc.service.TransactionService;

@RestController
@RequestMapping("api/v1")
public class TransactionController {


	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

	private static final String INVALID_TRANSACTION = "Account information is invalid or transaction has been denied for your protection. Please try again.";

	private final TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping(value = "/transactions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> makeTransfer(@Valid @RequestBody TransactionInput transactionInput) {
		if (InputValidator.isSearchTransactionValid(transactionInput)) {
			ResponseTransaction makeTransfer = transactionService.makeTransfer(transactionInput);
			return new ResponseEntity<>(makeTransfer, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(INVALID_TRANSACTION, HttpStatus.BAD_REQUEST);
		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return errors;
	}
}
