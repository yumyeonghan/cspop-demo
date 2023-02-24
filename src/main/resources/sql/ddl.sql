DROP TABLE IF EXISTS notice_board_upload_file;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS notice_board;
DROP TABLE IF EXISTS admins;
DROP TABLE IF EXISTS other_qualifications;
DROP TABLE IF EXISTS submit_form_upload_file;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS submit_form;
DROP TABLE IF EXISTS schedules;
DROP TABLE IF EXISTS excel_board;
DROP TABLE IF EXISTS guidance_board;
DROP TABLE IF EXISTS schedule_board;

-- -----------------------------------------------------
-- Table `test`.`admins`
-- -----------------------------------------------------
CREATE TABLE admins (
                        `id` BIGINT NOT NULL,
                        `created_date` DATETIME(6) NULL DEFAULT NULL COMMENT '등록일',
                        `last_modified_date` DATETIME(6) NULL DEFAULT NULL COMMENT '수정일',
                        `admin_id` VARCHAR(255) NOT NULL COMMENT '관리자 아이디',
                        `admin_name` VARCHAR(255) NOT NULL COMMENT '관리자 이름',
                        `admin_password` VARCHAR(255) NOT NULL COMMENT '관리자 비밀번호',
                        PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `test`.`notice_board`
-- -----------------------------------------------------
CREATE TABLE notice_board (
                              `id` BIGINT NOT NULL,
                              `created_date` DATETIME(6) NULL DEFAULT NULL COMMENT '등록일',
                              `last_modified_date` DATETIME(6) NULL DEFAULT NULL COMMENT '수정일',
                              `fixed` BIT(1) NOT NULL COMMENT '고정 여부',
                              `text` VARCHAR(255) NOT NULL COMMENT '공지사항 본문',
                              `title` VARCHAR(255) NOT NULL COMMENT '공지사항 제목',
                              `views` INT NULL DEFAULT NULL COMMENT '조회수',
                              `admins_id` BIGINT NULL DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              CONSTRAINT `fk_notice_board_to_admin`
                                  FOREIGN KEY (admins_id)
                                      REFERENCES admins(id))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `test`.`comments`
-- -----------------------------------------------------
CREATE TABLE comments (
                          `id` BIGINT NOT NULL AUTO_INCREMENT,
                          `created_date` DATETIME(6) NULL DEFAULT NULL COMMENT '등록일',
                          `last_modified_date` DATETIME(6) NULL DEFAULT NULL COMMENT '수정일',
                          `comment` VARCHAR(255) NULL DEFAULT NULL COMMENT '댓글',
                          `notice_board_id` BIGINT NULL DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          CONSTRAINT `fk_comments_to_notice_board`
                              FOREIGN KEY (notice_board_id)
                                  REFERENCES notice_board(id))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `test`.`submit_form`
-- -----------------------------------------------------
CREATE TABLE submit_form (
                             `id` BIGINT NOT NULL,
                             `created_date` DATETIME(6) NULL DEFAULT NULL COMMENT '등록일',
                             `last_modified_date` DATETIME(6) NULL DEFAULT NULL COMMENT '수정일',
                             `department` VARCHAR(255) NOT NULL COMMENT '소속학과',
                             `graduation_date` DATE NOT NULL COMMENT '졸업날짜',
                             `student_id` VARCHAR(255) NOT NULL COMMENT '학번',
                             `student_name` VARCHAR(255) NOT NULL COMMENT '학생 이름',
                             PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `test`.`submit_form_upload_file`
-- -----------------------------------------------------
CREATE TABLE submit_form_upload_file (
                                         `id` BIGINT NOT NULL,
                                         `store_file_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '서버 내부에서 관리하는 파일명',
                                         `upload_file_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '유저가 업로드한 파일명',
                                         `submit_form_id` BIGINT NULL DEFAULT NULL,
                                         PRIMARY KEY (`id`),
                                         CONSTRAINT `fk_submit_form_upload_file_to_submit_form`
                                             FOREIGN KEY (`submit_form_id`)
                                                 REFERENCES submit_form (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `test`.`users`
-- -----------------------------------------------------
CREATE TABLE users (
                       `id` BIGINT NOT NULL AUTO_INCREMENT,
                       `created_date` DATETIME(6) NULL DEFAULT NULL COMMENT '등록일',
                       `last_modified_date` DATETIME(6) NULL DEFAULT NULL COMMENT '수정일',
                       `birth` DATE NOT NULL COMMENT '생일',
                       `classification` VARCHAR(255) NULL DEFAULT NULL COMMENT '신분',
                       `department` VARCHAR(255) NOT NULL COMMENT '소속학과',
                       `email` VARCHAR(255) NOT NULL COMMENT '이메일',
                       `phone_number` VARCHAR(255) NOT NULL COMMENT '전화번호',
                       `sex` VARCHAR(255) NULL DEFAULT NULL COMMENT '성별',
                       `student_id` VARCHAR(255) NOT NULL COMMENT '학번',
                       `student_name` VARCHAR(255) NOT NULL COMMENT '학생 이름',
                       `student_password` VARCHAR(255) NOT NULL COMMENT '학생 비밀번호',
                       `submit_form_id` BIGINT NULL DEFAULT NULL,
                       PRIMARY KEY (`id`),
                       UNIQUE INDEX `submit_form_id_unique` (`submit_form_id` ASC) VISIBLE,
                       CONSTRAINT `fk_users_to_submit_form`
                           FOREIGN KEY (`submit_form_id`)
                               REFERENCES submit_form(id))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `test`.`notice_board_upload_file`
-- -----------------------------------------------------
CREATE TABLE notice_board_upload_file (
                                          `id` BIGINT NOT NULL,
                                          `store_file_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '서버 내부에서 관리하는 파일명',
                                          `upload_file_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '관리자가 업로드한 파일명',
                                          `notice_board_id` BIGINT NULL DEFAULT NULL,
                                          PRIMARY KEY (`id`),
                                          CONSTRAINT `fk_notice_board_upload_file_to_notice_board`
                                              FOREIGN KEY (`notice_board_id`)
                                                  REFERENCES notice_board (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `test`.`other_qualifications`
-- -----------------------------------------------------
CREATE TABLE other_qualifications (
                                      `id` BIGINT NOT NULL,
                                      `qualification` VARCHAR(255) NULL DEFAULT NULL COMMENT '기타자격',
                                      `submit_form_id` BIGINT NULL DEFAULT NULL,
                                      PRIMARY KEY (`id`),
                                      CONSTRAINT `fk_other_qualifications_to_submit_form`
                                          FOREIGN KEY (`submit_form_id`)
                                              REFERENCES submit_form (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `test`.`schedules`
-- -----------------------------------------------------
CREATE TABLE schedules (
                           `id` BIGINT NOT NULL,
                           `created_date` DATETIME(6) NULL DEFAULT NULL COMMENT '등록일',
                           `last_modified_date` DATETIME(6) NULL DEFAULT NULL COMMENT '수정일',
                           `end_date` DATE NULL DEFAULT NULL COMMENT '종료 일정',
                           `schedule_state` VARCHAR(255) NULL DEFAULT NULL,
                           `start_date` DATE NULL DEFAULT NULL COMMENT '시작 일정',
                           `step` VARCHAR(255) NULL DEFAULT NULL COMMENT '단계',
                           PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `test`.`excel_board`
-- -----------------------------------------------------
CREATE TABLE excel_board (
                             `id` BIGINT NOT NULL,
                             `created_date` DATETIME(6) NULL DEFAULT NULL COMMENT '등록일',
                             `last_modified_date` DATETIME(6) NULL DEFAULT NULL COMMENT '수정일',
                             `capstone_completion` VARCHAR(255) NULL DEFAULT NULL COMMENT '캡스톤 이수',
                             `graduation_date` VARCHAR(255) NULL DEFAULT NULL COMMENT '학생 졸업날짜',
                             `other_qualifications` VARCHAR(255) NULL DEFAULT NULL COMMENT '기타 자격',
                             `professor_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '교수 이름',
                             `state` VARCHAR(255) NULL DEFAULT NULL COMMENT '상태',
                             `step` VARCHAR(255) NULL DEFAULT NULL COMMENT '단계',
                             `student_id` VARCHAR(255) NULL DEFAULT NULL COMMENT '학번',
                             `student_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '학생 이름',
                             PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `test`.`guidance_board`
-- -----------------------------------------------------
CREATE TABLE guidance_board (
                                `id` BIGINT NOT NULL,
                                `created_date` DATETIME(6) NULL DEFAULT NULL COMMENT '등록일',
                                `last_modified_date` DATETIME(6) NULL DEFAULT NULL COMMENT '수정일',
                                `text` VARCHAR(255) NULL DEFAULT NULL COMMENT '안내 및 내규 본문',
                                PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `test`.`schedule_board`
-- -----------------------------------------------------
CREATE TABLE schedule_board (
                                `id` BIGINT NOT NULL,
                                `created_date` DATETIME(6) NULL DEFAULT NULL COMMENT '등록일',
                                `last_modified_date` DATETIME(6) NULL DEFAULT NULL COMMENT '수정일',
                                `final_pass_text` VARCHAR(255) NULL DEFAULT NULL COMMENT '최종통과 본문',
                                `final_report_text` VARCHAR(255) NULL DEFAULT NULL COMMENT '최종보고서 본문',
                                `interim_report_text` VARCHAR(255) NULL DEFAULT NULL COMMENT '중간보고서 본문',
                                `other_qualifications_text` VARCHAR(255) NULL DEFAULT NULL COMMENT '기타자격 본문',
                                `proposal_text` VARCHAR(255) NULL DEFAULT NULL COMMENT '제안서 본문',
                                `received_text` VARCHAR(255) NULL DEFAULT NULL COMMENT '신청접수 본문',
                                PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- alter table excel_board
-- -----------------------------------------------------
alter table excel_board
    modify id bigint auto_increment;

alter table excel_board
    auto_increment = 1;

