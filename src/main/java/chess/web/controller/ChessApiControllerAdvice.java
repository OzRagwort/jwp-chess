package chess.web.controller;

import chess.web.service.dto.ErrorResponseDto;
import org.apache.catalina.connector.Response;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ChessApiController.class)
public class ChessApiControllerAdvice {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponseDto> DataAccessException(DataAccessException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(Response.SC_BAD_REQUEST, exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> exception(Exception exception) {
        exception.printStackTrace();
        String message = exception.getMessage();
        return ResponseEntity.badRequest().body(new ErrorResponseDto(Response.SC_BAD_REQUEST, message));
    }
}