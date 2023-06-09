    # DB 생성
DROP DATABASE IF EXISTS `JSPAM`;
CREATE DATABASE `JSPAM`;
USE `JSPAM`;

# article 테이블 생성
CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL,
    memberId INT NOT NULL
);
# 작성자 member에서 id 컬럼 저장할 column 생성
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER regDate;
ALTER TABLE article MODIFY COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER regDate;
# memberId 관련 where에 in 사용할 수 있음
UPDATE article SET memberId = 3 WHERE id IN (33,55);

# article 테스트데이터 생성
INSERT INTO article
SET regDate = NOW(),
title = '제목 1',
`body` = '내용 1';

INSERT INTO article
SET regDate = NOW(),
title = '제목 2',
`body` = '내용 2';

INSERT INTO article
SET regDate = NOW(),
title = '제목 3',
`body` = '내용 3';

INSERT INTO article
SET regDate = NOW(),
title = '제목 4',
`body` = '내용 4';

INSERT INTO article
SET regDate = NOW(),
title = '제목 5',
`body` = '내용 5';

SELECT * FROM article;

INSERT INTO article (regDate, title, `body`)
SELECT NOW(), RAND(), RAND()
FROM article;

INSERT INTO article
SET
regDate = NOW(),
title = 'title',
BODY = 'body';

# 전체 select
SELECT * FROM article;

# article - member 조인
SELECT a.*, m.loginId FROM article AS a
INNER JOIN `member` AS m ON a.memberId = m.id ORDER BY id DESC LIMIT ?, ?;
SELECT a.*, m.loginId FROM article AS a
INNER JOIN `member` AS m ON a.memberid = m.id WHERE a.id =?;

# member 테이블 생성
DROP TABLE IF EXISTS `member`;
SELECT * FROM `member`;

CREATE TABLE `member`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    loginId CHAR(100) NOT NULL UNIQUE,
    loginPw CHAR(200) NOT NULL,
    `name` CHAR(100) NOT NULL
);

# member 테스트데이터 생성
INSERT INTO `member`
SET regDate = NOW(),
loginId = 'test1',
loginPw = 'test1',
`name` = 'test';

INSERT INTO `member`
SET regDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = 'test';

INSERT INTO `member`
SET regDate = NOW(),
loginId = 'test3',
loginPw = 'test3',
`name` = 'test';

