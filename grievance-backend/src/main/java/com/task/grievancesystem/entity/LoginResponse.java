package com.task.grievancesystem.entity;


    public class LoginResponse {
        private Long userId;
        private String username;
        private String message;

        public LoginResponse(Long userId, String username, String message) {
            this.userId = userId;
            this.username = username;
            this.message = message;
        }

        public Long getId() {
            return userId;
        }

        public void setId(Long id) {
            this.userId=userId;
        }

        public String getName() {
            return username;
        }

        public void setName(String name) {
            this.username=username;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message=message;
        }


    }