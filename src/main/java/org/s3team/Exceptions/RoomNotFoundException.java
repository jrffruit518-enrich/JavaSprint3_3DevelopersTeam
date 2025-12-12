package org.s3team.Exceptions;

/**
 * ClassName: RoomNotFoundException
 * Package: org.s3team.Exceptions
 * Description:
 * Author: Rong Jiang
 * Create:11/12/2025 - 22:17
 * Version:v1.0
 *
 */
public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String message) {
        super(message);
    }
}
