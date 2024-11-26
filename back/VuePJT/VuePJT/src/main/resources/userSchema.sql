drop schema if exists `vuePJT`;
CREATE SCHEMA `vuePJT`;
USE vuePJT;

CREATE TABLE IF NOT EXISTS `videos` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `title` varchar(40) NOT NULL,
    `channel` varchar(40) NOT NULL,
    `url` VARCHAR(500) NOT NULL,
    `content` varchar(500),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS `users` (
  `id` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
`phone` varchar(40) NOT NULL,
  `age` int NOT NULL,
`img` varchar(500),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `crew` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `crew_name` VARCHAR(45) NOT NULL,
    `crew_leader` VARCHAR(45) NOT NULL,
    `crew_create_date` DATE DEFAULT (CURRENT_DATE) NOT NULL,
--    `crew_due_date` DATE NOT NULL,
    `crew_curr_member` INT,
    `crew_max_member` INT NOT NULL,
    `crew_content` VARCHAR(500) NOT NULL,
    `crew_exercise_code` INT NULL,
    `crew_location` VARCHAR(45) NULL,
    `crew_exercise_date` VARCHAR(45) NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(`crew_leader`) REFERENCES users(`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `exercise`(
	`id` INT NOT NULL auto_increment,
    `exercise` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `crew_board`(
	`crew_id` INT NOT NULL,
    `board_id` INT NOT NULL AUTO_INCREMENT,
    `board_writer` VARCHAR(45) NOT NULL,
    `board_content` VARCHAR(500) NOT NULL,
    `create_date` DATE DEFAULT (CURRENT_DATE) NOT NULL,
    PRIMARY KEY(`board_id`),
    FOREIGN KEY(`crew_id`) REFERENCES crew(`id`) ON UPDATE CASCADE on delete CASCADE, 
    FOREIGN KEY(`board_writer`) REFERENCES users(`id`) ON UPDATE CASCADE on delete CASCADE
);

CREATE TABLE IF NOT EXISTS `crew_members`(
	`crew_id` INT NOT NULL,
    `crew_name` VARCHAR(40) NOT NULL,
    `crew_leader` VARCHAR(45) NOT NULL,
    `crew_member` VARCHAR(45),
    FOREIGN KEY(`crew_id`) REFERENCES crew(`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(`crew_leader`) REFERENCES users(`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(`crew_member`) REFERENCES users(`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `crew_detail_board`(
	`crew_id` INT NOT NULL,
    `board_id` INT NOT NULL AUTO_INCREMENT,
	`board_title` VARCHAR(45) NOT NULL,
    `board_writer` VARCHAR(45) NOT NULL,
    `board_content` VARCHAR(500) NOT NULL,
    PRIMARY KEY (`board_id`),
    FOREIGN KEY(`crew_id`) REFERENCES crew(`id`) ON UPDATE CASCADE on delete CASCADE,
    FOREIGN KEY(`board_writer`) REFERENCES users(`id`) ON UPDATE CASCADE on delete CASCADE
);

CREATE TABLE IF NOT EXISTS `comments` (
	`crew_id` INT NOT NULL,
	`id` INT NOT NULL AUTO_INCREMENT,
	`board_id` INT NOT NULL,
    `writer` varchar(40) NOT NULL,
    `content` varchar(40) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY(`crew_id`) REFERENCES crew(`id`)  ON UPDATE CASCADE on delete CASCADE ,
    FOREIGN KEY (`board_id`) REFERENCES crew_detail_board(`board_id`)  ON UPDATE CASCADE on delete CASCADE ,
    FOREIGN KEY (`writer`) REFERENCES users(`id`) ON UPDATE CASCADE on delete CASCADE 
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS `exercise_place`(
	`id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `location` VARCHAR(500) NOT NULL,
    `exercise` VARCHAR(40),
    `homepage` VARCHAR(500),
    `info` VARCHAR(500),
    `call` VARCHAR(20),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `exercise_place_review`(
	`id` INT NOT NULL AUTO_INCREMENT,
    `place_id` INT NOT NULL,
    `writer` VARCHAR(45) NOT NULL,
    `review` VARCHAR(500) NOT NULL,
    `rate` INT,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`place_id`) REFERENCES exercise_place(`id`) ON UPDATE CASCADE on delete CASCADE,
    FOREIGN KEY(`writer`) REFERENCES users(`id`) ON UPDATE CASCADE on delete CASCADE
);


-- drop table `exercise_place`;

-- SELECT * FROM exercise_place;

 
 -- 클라이밍 관련 시설
INSERT INTO `exercise_place` (`name`, `location`, `exercise`, `homepage`, `info`, `call`) VALUES
('어웨이크클라이밍 타임월드점', '대전 서구 둔산로 18 향촌월드프라자 10층', '클라이밍', 'https://www.instagram.com/awake_timeworld/', '평일(월~금) 11:00~23:00\n주말,공휴일11:00~20:00\n명절 정상영업 연중 무휴', '0507-1488-1039'),
('베이스캠프 볼더스팟 클라이밍 유성점', '대전 유성구 온천로 52 4층', '클라이밍', 'https://www.instagram.com/basecamp_yuseong/', '평일(월~금) 11:00~23:00\n주말,공휴일11:00~20:00', '0507-1361-8850'),
('베이스캠프 볼더스팟 클라이밍 둔산점', '대전 서구 대덕대로 243 3층', '클라이밍', 'https://www.instagram.com/basecamp_dunsan/#', '평일(월~금) 11:00~23:00\n주말,공휴일11:00~20:00\n11/26-11/29 휴무', '0507-1354-5139'),
('하나클라이밍짐', '대전 유성구 월드컵대로 32', '클라이밍', 'http://hanasports.or.kr/', '평일(화~금) 11:00~23:00\n주말,공휴일11:00~19:00\n월요일 정기휴무', '042-826-5014'),
('클라이밍리드 유성점', '대전 유성구 계룡로 129 이화빌딩 2층', '클라이밍', 'https://cafe.daum.net/leadclimbing', '평일(월~금) 11:00~22:30\n주말,공휴일12:00~19:00', '042-825-5013'),
('클라이밍리드 충대점', '대전 유성구 대학로76번안길 62', '클라이밍', 'https://cafe.daum.net/leadclimbing', '평일(월~금) 11:00~22:30\n토요일12:00~19:00\n일요일 정기휴무', '042-825-5014');

-- 풋살 관련 시설
INSERT INTO `exercise_place` (`name`, `location`, `exercise`, `homepage`, `info`, `call`) VALUES
('유성풋살구장', '대전 유성구 유성대로713번길 83', '풋살', 'https://www.instagram.com/p/B30635plI1_/?igshid=1s8ll4hl8lr7x&img_index=1', '24시간 영업\n연중무휴', '042-825-2112'),
('한밭풋살구장', '대전 유성구 현충원로 364', '풋살', '', '24시간 영업\n연중무휴', '0507-1366-8484'),
('남선공원 풋살장', '', '풋살', 'https://www.instagram.com/namsun_fitnessclub/', '평일(월~금) 10:00~23:00\n주말,공휴일07:00~19:00', '0507-1346-5614'),
('삼정풋살파크', '대전 유성구 한밭대로371번길 25-4 삼정풋살파크', '풋살', 'http://samjungpark.co.kr/', '매일\n07:00 ~ 02:00', '042-710-6603'),
('위너스풋살파크 목원대점', '대전 유성구 원신흥동 576-2', '풋살', '', '24시간 영업\n연중무휴', '0507-1321-0046'),
('경성실내풋살장', '대전 서구 갈마로 209 6층', '풋살', '', '매일\n09:00 ~ 24:00', '0507-1385-0201'),
('대전가장풋살존', '대전 서구 괴정로198번길 43 21-3, 21-9', '풋살', 'https://open.kakao.com/o/gSLyZtMf', '24시간 영업\n연중무휴', '010-7913-3325'),
('S루프탑풋살파크 둔산', '대전 서구 대덕대로185번길 46 11층 루프탑 풋살파크', '풋살', 'https://blog.naver.com/cyll112', '24시간 영업\n연중무휴', '0507-1333-0193');

-- 축구 관련 시설
INSERT INTO `exercise_place` (`name`, `location`, `exercise`, `homepage`, `info`, `call`) VALUES
('사정동 사계절 잔디축구장', '대전 중구 사정공원로 160', '축구', '', '평일 : 50000원\n주말 : 75000원', '042-581-4801'),
('중구체육복지센터축구장', '대전 중구 산서로 346', '축구', 'https://www.djjunggu.go.kr/kr/sub06_05_04_03.do?partCode=06', '매일\n06:00~22:00', '042-606-6273'),
('관저체육공원운동장', '대전 서구 관저2동 산78-1', '축구', 'http://cafe.daum.net/seaguground', '매일\n06:00 ~ 22:00', '042-542-6078'),
('덕암축구센터', '대전 대덕구 대덕대로1448번길 199', '축구', 'https://www.dhcfc.kr/', '매일\n06:00 ~ 22:00', '042-936-9091');


-- 농구 관련 시설
INSERT INTO `exercise_place` (`name`, `location`, `exercise`, `homepage`, `info`, `call`) VALUES
('와이농구교실 산내점', '대전 동구 산내로1299번길 53 1층', '농구', 'https://www.instagram.com/y_basketball_academy_2', '매일09:00~24:00', '0507-1315-0337'),
('DB프로미 농구교실', '대전 유성구 학하북로 75-21', '농구', 'http://cafe.naver.com/juniorpromy1', '평일(월~금)15:00~22:00\n토요일/일요일09:00~22:00', '042-369-4449'),
('월드짐농구교실&키즈클럽', '대전 유성구 은구비남로7번길 50 민현빌딩5층', '농구', 'https://blog.naver.com/gka234', '매일10:00~23:50', '042-487-2323'),
('점프농구교실', '대전 동구 한밭대로 1322 티애비뉴 5층 503호 점프농구교실', '농구', 'https://www.instagram.com/jumpbasketball_academy', '평일(월~금)09:00~22:00\n토요일09:00~17:00\n공휴일/일요일휴무', '0507-1337-1829'),
('삼성리틀썬더스 대전점', '대전 서구 둔산로31번길 66 다송빌딩 4, 5층', '농구', 'https://blog.naver.com/hitting7824', '', '010-5439-5055'),
('MVP 농구교실', '대전 중구 계백로 1715 경복궁메디컬파크 1동 702호', '농구', 'https://mvpbasketball.modoo.at/', '평일(월~금)15:00~22:00\n토요일/일요일09:00~21:00', '042-525-5455');

-- 배드민턴 관련 시설
INSERT INTO `exercise_place` (`name`, `location`, `exercise`, `homepage`, `info`, `call`) VALUES
('대덕스포츠클럽', '대전 대덕구 대덕대로1486번길 85 (목상동)', '배드민턴', 'https://sportsclub.sports.or.kr/club/daejeon-daedeok', '평일(월~금)09:00~18:00\n토요일09:00~18:00\n공휴일/일요일', '042-932-7333'),
('도솔다목적체육관', '대전 서구 배재로197번길 41', '배드민턴', 'https://www.sgsports.or.kr/page/sub3_4.php', '평일(월~금)06:00~22:00\n토요일06:00~20:00\n공휴일/일요일', '042-522-3820'),
('도마실국민체육센터', '대전 서구 유등로 235', '배드민턴', 'https://sportsclub.sports.or.kr/club/daejeon-seogu', '평일(월~금)06:00~22:00\n토요일06:00~18:00\n공휴일/일요일', '042-582-5556'),
('나래 배드민턴 트레이닝 센터', '대전 유성구 반석동로 51-18', '배드민턴', 'https://www.instagram.com/narae_sports_/?igsh=ZnhmeXU1cWt6eHh3#', '매일06:00~23:00', '042-825-7994'),
('자양배드민턴클럽', '대전 동구 계족로 184-55', '배드민턴', 'https://blog.naver.com/jayangclub', '평일(월~금) : 오후 7시 ~ 오후 10:00분\n토요일 : 오후 5:30 ~ 오후 8:30분\n일요일 : 오후 2시 ~ 오후 5시', '010-4409-8582');

-- 테니스 관련 시설
INSERT INTO `exercise_place` (`name`, `location`, `exercise`, `homepage`, `info`, `call`) VALUES
('론테니스존', '대전 서구 둔산로 8 밀레니엄프라자 10층 1001호, 1003호', '테니스', 'https://tenniszone.modoo.at/', '매일06:00 - 22:30', '01088298253'),
('에이스 실내 테니스장', '대전 서구 둔산남로180번길 15', '테니스', 'https://blog.naver.com/ace_tennis', '매일06:00 - 21:30', '0507-1358-8634'),
('톡테니스 본점 송촌', '대전 대덕구 계족산로81번길 54 7층 701호', '테니스', 'https://cafe.naver.com/talktennis', '매일05:00 - 24:00', '0507-1410-0789'),
('대덕실내테니스아카데미', '대전 대덕구 우암로492번길 66 대덕 실내테니스 아카데미', '테니스', 'https://www.instagram.com/dd_tennis_academy/', '매일05:00 - 24:00', '0507-1394-8440'),
('왓어샷테니스장 대전', '대전 서구 둔산로 18 9층', '테니스', 'https://www.instagram.com/what_a_shot_daejeon', '평일(월~금)06:00~24:00\n토요일/일요일08:00~22:00', '0507-1347-1197'),
('가오동 실내 테니스장', '대전 동구 대전로 446 3층 301호,302호', '테니스', 'https://blog.naver.com/hanna8571', '매일06:00 - 24:00', '0507-1374-8440'),
('팡테니스', '대전 중구 계백로 1580 5층 \' 팡테니스 ‘', '테니스', 'https://m.blog.naver.com/kjw9882', '월~토06:00~22:00\n일요일 휴무', '010-4403-1714');

INSERT INTO exercise(exercise) VALUES ('농구'),('배드민턴'),('테니스'),('클라이밍'),('풋살'),('축구');

-- 여기까지는 날리면 안됨!!!!

INSERT INTO `users` (`id`, `password`, `name`, `email`, `phone`, `age`, `img`) VALUES
('kimjinho', 'password123', '김진호', 'kimjinho@gmail.com', '010-1234-5678', 28, 'profile1.jpg'),
('leejiwon', 'securePassword!2024', '이지원', 'leejiwon@naver.com', '010-2345-6789', 25, 'profile2.jpg'),
('choihyunsuk', 'qwerty987', '최현석', 'choihyunsuk@kakao.com', '010-3456-7890', 32, 'profile3.jpg'),
('parkminseok', 'password0987', '박민석', 'parkminseok@hanmail.net', '010-4567-8901', 30, 'profile4.jpg'),
('jungsooah', '1234abcdef', '정수아', 'jungsooah@outlook.com', '010-5678-9012', 26, 'profile5.jpg'),
('yoonseojin', 'seojin!1234', '윤서진', 'yoonseojin@naver.com', '010-6789-0123', 27, 'profile6.jpg'),
('kimtaeyang', '1234password!', '김태양', 'kimtaeyang@gmail.com', '010-7890-1234', 29, 'profile7.jpg'),
('suhyejin', '5678secure', '서혜진', 'suhyejin@kakao.com', '010-8901-2345', 31, 'profile8.jpg'),
('limsoojung', 'abcd9876', '임수정', 'limsoojung@naver.com', '010-9012-3456', 24, 'profile9.jpg'),
('hongjihoon', 'password4321', '홍지훈', 'hongjihoon@hanmail.net', '010-1234-6789', 33, 'profile10.jpg');

INSERT INTO `crew` (`crew_name`, `crew_leader`, `crew_create_date`, `crew_curr_member`, `crew_max_member`, `crew_content`, `crew_exercise_code`, `crew_location`, `crew_exercise_date`) VALUES
('클라이밍 동호회', 'kimjinho', '2024-10-01', 0, 10, '클라이밍을 즐기는 동호회입니다.', 4, '대전 유성구', '월요일'),
('풋살팀 A', 'leejiwon', '2024-08-15', 0, 15, '풋살을 즐기고 다양한 경기를 합니다.', 5, '대전 서구', '화요일'),
('축구 사랑', 'choihyunsuk', '2024-07-12', 0, 12, '축구를 좋아하는 사람들이 모인 팀입니다.', 6, '대전 중구', '수요일'),
('배드민턴 마스터즈', 'parkminseok', '2024-05-22', 0, 8, '배드민턴을 즐기는 마스터들입니다.', 2, '대전 동구', '목요일'),
('테니스 챌린저스', 'jungsooah', '2024-06-10', 0, 6, '테니스에 도전하는 사람들의 모임입니다.', 3, '대전 대덕구', '금요일'),
('클라이밍 애호가들', 'yoonseojin', '2024-03-18', 0, 10, '클라이밍을 사랑하는 이들이 모였습니다.', 4, '대전 서구', '토요일'),
('풋살 천국', 'kimtaeyang', '2024-09-01', 0, 18, '풋살을 즐기며 운동하는 팀입니다.', 5, '대전 유성구', '일요일'),
('축구의 신', 'suhyejin', '2024-01-05', 0, 16, '축구를 사랑하는 사람들이 모인 곳입니다.', 6, '대전 중구', '월요일'),
('배드민턴 도전자들', 'limsoojung', '2024-02-17',0, 8, '배드민턴 도전하는 사람들이 모인 팀입니다.', 2, '대전 동구', '화요일'),
('테니스 오픈 챌린지', 'hongjihoon', '2024-04-09', 0, 10, '테니스 실력을 겨루는 사람들입니다.', 3, '대전 서구', '수요일');

INSERT INTO `crew_board` (`crew_id`, `board_writer`, `board_content`, `create_date`) VALUES
(1, 'kimjinho', '이번 주 클라이밍 활동 공지입니다.', '2024-11-20'),
(2, 'leejiwon', '풋살팀 경기 일정 업데이트합니다.', '2024-11-18'),
(3, 'choihyunsuk', '이번 주 축구 연습 공지', '2024-11-16'),
(4, 'parkminseok', '배드민턴 대회 참가 신청 받습니다.', '2024-11-19'),
(5, 'jungsooah', '테니스 경기 일정 조정 공지', '2024-11-15'),
(6, 'yoonseojin', '다음 주 클라이밍 연습 공지', '2024-11-14'),
(7, 'kimtaeyang', '풋살팀 경기 연기 안내', '2024-11-13'),
(8, 'suhyejin', '축구 연습장 변경 안내', '2024-11-12'),
(9, 'limsoojung', '배드민턴 대회 장소 확정 공지', '2024-11-11'),
(10, 'hongjihoon', '테니스 연습 일정 공유합니다.', '2024-11-10');

INSERT INTO `crew_detail_board` (`crew_id`, `board_title`, `board_writer`, `board_content`) VALUES
(1, '클라이밍 활동 후기', 'kimjinho', '이번 활동은 정말 즐거웠습니다. 많은 분들이 참석해주셔서 감사합니다. 다음에도 많이 참여해주세요.'),
(2, '풋살팀 경기 후기', 'leejiwon', '풋살 경기 정말 재미있었습니다. 다음에도 멋진 경기 펼쳐봅시다!'),
(3, '축구 팀 훈련 후기', 'choihyunsuk', '축구 훈련 후기를 남깁니다. 연습을 꾸준히 해서 좋은 결과를 만들자고요!'),
(4, '배드민턴 대회 후기', 'parkminseok', '배드민턴 대회 참가자들 정말 고생 많았습니다. 좋은 결과를 기대합니다.'),
(5, '테니스 경기 후기', 'jungsooah', '테니스 경기 다들 수고 많았어요. 실력을 다져서 더 좋은 경기를 만들어봅시다!'),
(6, '클라이밍 활동 후기', 'yoonseojin', '이번 활동에서 많은 것을 배웠습니다. 감사합니다!'),
(7, '풋살팀 경기 후기', 'kimtaeyang', '이번 풋살 경기도 재미있었습니다. 다음에 더 멋진 경기를 기대해요!'),
(8, '축구 연습 후기', 'suhyejin', '축구 연습이 정말 유익했습니다. 잘 준비해서 좋은 결과를 만들어봅시다!'),
(9, '배드민턴 대회 후기', 'limsoojung', '배드민턴 대회 참가자들 모두 수고하셨습니다. 더욱 멋진 대회가 되기를 바랍니다.'),
(10, '테니스 연습 후기', 'hongjihoon', '테니스 연습이 매우 유익했습니다. 실력을 계속 키워봅시다!');

INSERT INTO `comments` (`crew_id`, `board_id`, `writer`, `content`) VALUES
(1, 1, 'leejiwon', '클라이밍 정말 재밌었어요!'),
(2, 2, 'choihyunsuk', '풋살 재밌게 했습니다! 다음에도 기대돼요!'),
(3, 3, 'parkminseok', '축구 연습 열심히 하겠습니다!'),
(4, 4, 'jungsooah', '배드민턴 대회 기대돼요!'),
(5, 5, 'yoonseojin', '테니스 경기 잘 준비하겠습니다.'),
(6, 6, 'kimtaeyang', '클라이밍 정말 즐거웠어요!'),
(7, 7, 'suhyejin', '풋살 재밌었습니다. 계속 연습할게요!'),
(8, 8, 'limsoojung', '배드민턴 대회 잘 준비해야겠어요!'),
(9, 9, 'hongjihoon', '테니스 연습 유익했습니다!'),
(10, 10, 'kimjinho', '경기 정말 즐거웠습니다! 다음에 더 잘 하겠습니다!');

select * from users;
