package org.s3team.Exceptions;

/**
 * ClassName: ThemeNotFoundException
 * Package: org.s3team.Exceptions
 * Description:
 * Author: Rong Jiang
 * Create:11/12/2025 - 22:29
 * Version:v1.0
 *
 */
public class ThemeNotFoundException extends RuntimeException {
    public ThemeNotFoundException(String message) {
        super(message);
    }
}
