drop database if exists movie_platform;
create database movie_platform;
use movie_platform;

-- 영화 테이블
create table movie(
	mno int unsigned primary key auto_increment, -- PK번호
    mName varchar(100) not null, -- 영화명
    msupervision varchar(30) not null, -- 영화감독
    mgenre varchar(30) not null, -- 장르
    mintro LONGTEXT, -- 영화소개
	nickname varchar(30) not null, -- 닉네임
    mdate date default(current_date), -- 작성일
    mpwd VARCHAR(4) not null -- 비번4자리
);

-- 영화 댓글 테이블
create table comment_movie(
	cno int unsigned primary key auto_increment, -- PK번호
    mno int unsigned not null, -- 영화fK번호
    ccomment TEXT not null, -- 댓글내용
	nickname varchar(30) not null, -- 닉네임
    mpwd VARCHAR(4) not null, -- 비번4자리
    cdate date default(current_date), -- 작성일
	FOREIGN KEY (mno) REFERENCES movie(mno) ON DELETE CASCADE
);

-- 영화 데이터 삽입
INSERT INTO movie (mno, mName, msupervision, mgenre, mintro, nickname, mpwd) VALUES
(1, '인셉션', '크리스토퍼 놀란', 'SF/액션', '타인의 꿈에 들어가 생각을 훔치는 이야기', '놀란짱팬', '1234'),
(2, '너의 이름은.', '신카이 마코토', '애니메이션', '몸이 서로 뒤바뀐 두 남녀의 이야기', '애니덕후', '5678'),
(3, '기생충', '봉준호', '드라마/스릴러', '가난한 가족과 부유한 가족의 만남이 불러온 비극', '영화광', '9876'),
(4, '어벤져스: 엔드게임', '루소 형제', '액션/판타지', '타노스로부터 세상을 되찾기 위한 최후의 전쟁', '마블사랑', '4321'),
(5, '라라랜드', '데이미언 셔젤', '뮤지컬/로맨스', '꿈을 쫓는 청춘들의 아름다운 로맨스', '재즈감성', '0000');

-- 영화 댓글 테이블 데이터 삽입 (총 10개)
INSERT INTO comment_movie (mno, ccomment, nickname, mpwd, cdate) VALUES
(1, '정말 꿈과 현실을 넘나드는 스토리가 대박... 놀란 감독은 진짜 천재!', '영화마니아', '1234', '2025-06-10 13:00:00'),
(1, '스토리가 너무 복잡해서 한 번 더 봐야 할 것 같아요. 근데 영상미는 최고!', '초보관객', '5678', '2025-06-10 13:05:00'),
(2, '영상미랑 OST가 진짜 미쳤다... 특히 마지막 장면에서 눈물 줄줄 흘렸어요ㅠㅠ', '감성폭발', '9876', '2025-06-10 13:10:00'),
(2, '애니메이션인데 이렇게 현실적인 감정을 담을 수 있다니... 신카이 마코토 감독님 존경합니다!', '애니좋아', '4321', '2025-06-10 13:15:00'),
(3, '블랙 코미디와 스릴러를 넘나드는 봉준호 감독의 연출력에 소름 돋았어요.', '봉준호팬', '1111', '2025-06-10 13:20:00'),
(3, '계급 사회의 현실을 비판하는 메시지가 너무 강렬해서 불편했어요. 그래도 잘 만든 영화는 인정.', '현실주의자', '2222', '2025-06-10 13:25:00'),
(4, '아이언맨의 희생에 극장에서 오열했습니다ㅠㅠ 영원한 히어로..', '마블덕후', '3333', '2025-06-10 13:30:00'),
(4, '타노스가 왜 또 나온거죠? 솔직히 좀 지루했어요.. 전작이 더 좋았던 것 같아요.', '지겨운타노스', '4444', '2025-06-10 13:35:00'),
(5, '꿈을 향해 달려가는 두 사람의 이야기에 너무 공감했어요. 엠마 스톤 연기 최고!', '꿈많은소녀', '5555', '2025-06-10 13:40:00'),
(5, '음악이 너무 좋아요. 길거리 걸을 때 이 영화 OST만 듣고 다녀요.', '뮤지컬사랑', '6666', '2025-06-10 13:45:00');


select * from movie;         # 영화 테이블
select * from comment_movie; # 영화 댓글 테이블
