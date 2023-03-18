
--users 데이터
INSERT INTO users VALUES (1, '2023-03-14 12:35:29.857156','2023-03-14 12:35:29.857156','2023-09-01','UNDERGRADUATE_STUDENT','컴퓨터공학부','1234@naver.com','1234','FEMALE','1234','1234','$2a$10$jvXXjxBH9uxs6wCjsOapu.oCqPdQNO22/VvfNYbyWvlAz1GDrjO76',NULL);

--schedules 데이터
INSERT INTO schedules VALUES (1,'2023-03-14 13:52:38.000000','2023-03-14 13:52:46.764849','2023-03-24','WAIT','2023-03-15','RECEIVED'),(2,'2023-03-14 13:52:38.000000','2023-03-14 13:52:50.544745','2023-03-14','PROCEEDING','2023-03-13','PROPOSAL'),(3,'2023-03-14 13:52:38.000000','2023-03-14 13:52:38.000000','2023-03-15','PROCEEDING','2023-03-13','INTERIM_REPORT'),(4,'2023-03-14 13:52:38.000000','2023-03-14 13:52:38.000000','2023-03-15','PROCEEDING','2023-03-13','FINAL_REPORT'),(5,'2023-03-14 13:52:38.000000','2023-03-14 13:52:38.000000','2023-03-15','PROCEEDING','2023-03-13','FINAL_PASS'),(6,'2023-03-14 13:52:38.000000','2023-03-14 13:52:38.000000','2023-03-15','PROCEEDING','2023-03-13','OTHER_QUALIFICATIONS');

--schedule_board 데이터
insert into cspop.schedule_board(id, created_date, last_modified_date, final_pass_text, final_report_text, interim_report_text, other_qualifications_text, proposal_text, received_text)
VALUES (1,NOW(),NOW(),'내용','내용','내용','내용','내용','내용');

--notice_board 데이터
INSERT INTO `CSPOP`.`notice_board` (`id`, `created_date`, `last_modified_date`, `fixed`, `text`, `title`, `views`, `admins_id`) VALUES ('1', '1999-10-13', '1999-10-13', 1, 'text1', 'text1', 1, '1');
INSERT INTO `CSPOP`.`notice_board` (`id`, `created_date`, `last_modified_date`, `fixed`, `text`, `title`, `views`, `admins_id`) VALUES ('2', '1999-10-13', '1999-10-13', 0, 'text1', 'text1', 1, '1');
INSERT INTO `CSPOP`.`notice_board` (`id`, `created_date`, `last_modified_date`, `fixed`, `text`, `title`, `views`, `admins_id`) VALUES ('3', '1999-10-13', '1999-10-13', 0, 'text1', 'text1', 1, '1');
INSERT INTO `CSPOP`.`notice_board` (`id`, `created_date`, `last_modified_date`, `fixed`, `text`, `title`, `views`, `admins_id`) VALUES ('4', '1999-10-13', '1999-10-13', 1, 'text1', 'text1', 1, '1');
INSERT INTO `CSPOP`.`notice_board` (`id`, `created_date`, `last_modified_date`, `fixed`, `text`, `title`, `views`, `admins_id`) VALUES ('5', '1999-10-13', '1999-10-13', 0, 'text1', 'text1', 1, '1');
INSERT INTO `CSPOP`.`notice_board` (`id`, `created_date`, `last_modified_date`, `fixed`, `text`, `title`, `views`, `admins_id`) VALUES ('6', '1999-10-13', '1999-10-13', 0, 'text1', 'text1', 1, '1');
INSERT INTO `CSPOP`.`notice_board` (`id`, `created_date`, `last_modified_date`, `fixed`, `text`, `title`, `views`, `admins_id`) VALUES ('7', '1999-10-13', '1999-10-13', 0, 'text1', 'text1', 1, '1');
INSERT INTO `CSPOP`.`notice_board` (`id`, `created_date`, `last_modified_date`, `fixed`, `text`, `title`, `views`, `admins_id`) VALUES ('8', '1999-10-13', '1999-10-13', 1, 'text1', 'text1', 1, '1');
INSERT INTO `CSPOP`.`notice_board` (`id`, `created_date`, `last_modified_date`, `fixed`, `text`, `title`, `views`, `admins_id`) VALUES ('9', '1999-10-13', '1999-10-13', 1, 'text1', 'text1', 1, '1');
