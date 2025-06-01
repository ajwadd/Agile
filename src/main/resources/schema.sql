CREATE TABLE app_user (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255),
                          email VARCHAR(255),
                          mobile_number VARCHAR(255),

                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          created_by VARCHAR(255),
                          updated_at TIMESTAMP,
                          updated_by VARCHAR(255)
);
CREATE TABLE standup_entry (
                               id UUID PRIMARY KEY,
                               user_id BIGINT NOT NULL,
                               date DATE NOT NULL,
                               yesterday VARCHAR(1000) NOT NULL,
                               today VARCHAR(1000) NOT NULL,
                               blockers VARCHAR(1000) NOT NULL,

                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               created_by VARCHAR(255),
                               updated_at TIMESTAMP,
                               updated_by VARCHAR(255),

                               CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES AppUser(id)
);

CREATE TABLE role (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) UNIQUE NOT NULL
);

