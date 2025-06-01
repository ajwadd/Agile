package com.example.agile.constants;
public class UsersConstants {





        private UsersConstants() {
            // Prevent instantiation
        }

        // Default values
        public static final String DEFAULT_ADDRESS = "123 Main Street, New York";

        // Status Codes
        public static final String STATUS_200 = "200";
        public static final String MESSAGE_200 = "Request processed successfully";

        public static final String STATUS_201 = "201";
        public static final String MESSAGE_201 = "User created successfully";

        public static final String STATUS_417 = "417";
        public static final String MESSAGE_417_UPDATE = "User update operation failed. Please try again or contact support.";
        public static final String MESSAGE_417_DELETE = "User delete operation failed. Please try again or contact support.";

        public static final String STATUS_404 = "404";
        public static final String MESSAGE_404 = "User not found";

        public static final String STATUS_409 = "409";
        public static final String MESSAGE_409 = "User already exists with the same mobile number";

        public static final String STATUS_500 = "500";
        public static final String MESSAGE_500 = "Internal server error. Please contact the support team.";

    }


