package zxk.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WyException extends Exception {
    private String message;

}
