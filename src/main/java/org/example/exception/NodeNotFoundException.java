package org.example.exception;

public class NodeNotFoundException extends RuntimeException {
    public NodeNotFoundException(String message) {
        super(message);
    }
}
