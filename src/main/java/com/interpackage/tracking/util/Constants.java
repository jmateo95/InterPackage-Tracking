package com.interpackage.tracking.util;

public final class Constants {

    public static final String API_TRACKING_V1 = "/api/tracking/v1";
    public static final String HOST = "http://localhost:";
    public static final String REQUIRED_FIELDS = "Todos los campos son requeridos.";
    public static final String INTERNAL_SERVER_ERROR_DB = "Error al ejecutar transacción intenta de nuevo";
    public static final String REQUIRED_ID = "Debe ingresar un ID";
    public static final String ADMIN_ROL = "Admin";
    public static final String USER_ROL = "User";
    public static final String OPERATOR_ROL = "Operator";
    public static final String MESSAGE_NOTIFICATION = "Notification";
    public static final String PENDING_STATE = "Pending";
    public static final String CHECK_IN_STATE = "Ingreso";
    public static final String NOT_FOUND = "No se encontro la entidad";

    private Constants() {
    }
}
