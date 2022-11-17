/* 기업 사용자 추가 */
INSERT INTO users(user_id, account_id, dtype) VALUES('enterprise-user-id', 'enterprise-account-id', 'enterprise');
INSERT INTO enterprise(user_id, phone_number, organization, email, name) VALUES('enterprise-user-id','01000000001', 'devnus_organization', 'devnus1@devnus.com', 'devnus1_name');

/* 일반 사용자 추가 */
INSERT INTO users(user_id, account_id, dtype) VALUES('labeler-user-id', 'labeler-account-id', 'labeler');
INSERT INTO labeler(user_id, birth_year, email, name) VALUES('labeler-user-id', '2000', 'devnus2@devnus.com', 'devnus2_name');