package szczepaniak.ppss.StackService.infrastructure.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class StorageException extends RuntimeException {

    public StorageException() {
        super("Problem with storage");
    }
    public StorageException(String message) {
        super(message);
    }
}
