package com.seamfix.nimc.maybeach.exceptions;

import com.seamfix.nimc.maybeach.dto.NimcResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.ws.soap.client.SoapFaultClientException;

import javax.xml.bind.JAXBException;
import java.net.ConnectException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
//@ControllerAdvice
public class AppControllerAdvise {
    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<NimcResponseDto> handleHttpException(HttpStatusCodeException exception) {
        NimcResponseDto response = new NimcResponseDto();
        HttpStatus httpStatus = getHttpExceptionDetails(exception);
        response.setMessage(exception.getMessage());
        response.setStatus(httpStatus.value());
        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler({GeneralException.class, NullPointerException.class, JAXBException.class, SoapFaultClientException.class, IllegalStateException.class, DeviceAuthException.class, ResourceAccessException.class, ConnectException.class})
    public ResponseEntity<NimcResponseDto> handleGeneralException(GeneralException exception) {
        NimcResponseDto response = new NimcResponseDto();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        response.setMessage(exception.getMessage());
        response.setStatus(httpStatus.value());
        return new ResponseEntity<>(response, httpStatus);
    }

    @SuppressWarnings("PMD.GuardLogStatement")
    @ExceptionHandler({MethodArgumentNotValidException.class, JSONException.class})
    public ResponseEntity<NimcResponseDto> handleBadParameterException(MethodArgumentNotValidException exception) {
        NimcResponseDto response = new NimcResponseDto();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        //Get all errors
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        if (errors != null && !errors.isEmpty()) {
            log.error("exception.getMessage() {}", errors.get(0));
            response.setMessage(errors.get(0));
        } else {
            response.setMessage("Invalid input was provided");
        }

        response.setStatus(httpStatus.value());
        return new ResponseEntity<>(response, httpStatus);
    }

	/*@ExceptionHandler({SocketTimeoutException.class})
	public ResponseEntity<NimcResponseDto> handleSocketTimoutException(SocketTimeoutException exception) {
		NimcResponseDto response = new NimcResponseDto();
		HttpStatus httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
		response.setMessage(exception.getMessage());
		response.setStatus(httpStatus.value());
	      return new ResponseEntity<>(response, httpStatus);
	   }*/

    private HttpStatus getHttpExceptionDetails(HttpStatusCodeException exception) {
        int code = exception.getRawStatusCode();

        switch (code) {
            case 404:
                return HttpStatus.NOT_FOUND;
            case 400:
                return HttpStatus.BAD_REQUEST;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}