/* 공통 사용자 정보를 저장할 테이블 */
CREATE TABLE users (
    user_id VARCHAR(255) NOT NULL PRIMARY KEY,
    account_id VARCHAR(255) NOT NULL,
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    dtype VARCHAR(255) NOT NULL
);

/* 기업 사용자 정보를 저장할 테이블 */
CREATE TABLE enterprise (
    user_id VARCHAR(255) NOT NULL PRIMARY KEY,
    phone_number VARCHAR(255) NOT NULL,
    organization VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

/* 관리자 정보를 저장할 테이블 */
CREATE TABLE admin (
    user_id VARCHAR(255) NOT NULL PRIMARY KEY,
    phone_number VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

/* 라벨러 사용자 정보를 저장할 테이블 */
CREATE TABLE labeler (
    user_id VARCHAR(255) NOT NULL PRIMARY KEY,
    phone_number VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    birth_year VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);
