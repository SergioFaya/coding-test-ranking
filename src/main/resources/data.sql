
INSERT INTO AD (ID, DESCRIPTION, SIZE, SCORE, IRRELEVANT_SINCE)
VALUES (1,  'Este piso es una ganga, compra, compra, COMPRA!!!!!', 300, 100, SYSDATE)
;

INSERT INTO CHALET_AD(ID, GARDEN_SIZE)
VALUES(1, 100)
;

INSERT INTO AD (ID, DESCRIPTION, SIZE, SCORE, IRRELEVANT_SINCE)
VALUES (3, '', 300, null, null)
;

INSERT INTO CHALET_AD(ID, GARDEN_SIZE)
VALUES(3, 0)
;

INSERT INTO AD (ID,  DESCRIPTION, SIZE, SCORE, IRRELEVANT_SINCE)
VALUES (2,  'Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo', 300, null, null)
;

INSERT INTO FLAT_AD(ID)
VALUES(2)
;

INSERT INTO AD (ID,  DESCRIPTION, SIZE, SCORE, IRRELEVANT_SINCE)
VALUES (4, 'Ático céntrico muy luminoso y recién reformado, parece nuevo', 300, null, null)
;

INSERT INTO FLAT_AD(ID)
VALUES(4)
;

INSERT INTO AD (ID,  DESCRIPTION, SIZE, SCORE, IRRELEVANT_SINCE)
VALUES (5, 'Pisazo,', 300, null, null)
;

INSERT INTO FLAT_AD(ID)
VALUES(5)
;

INSERT INTO AD (ID,  DESCRIPTION, SIZE, SCORE, IRRELEVANT_SINCE)
VALUES (6, '', 300, null, null)
;

INSERT INTO GARAGE_AD(ID)
VALUES(6)
;

INSERT INTO AD (ID,  DESCRIPTION, SIZE, SCORE, IRRELEVANT_SINCE)
VALUES (7, 'Garaje en el centro de Albacete', 300, null, null)
;

INSERT INTO GARAGE_AD(ID)
VALUES(7)
;


INSERT INTO AD (ID, DESCRIPTION, SIZE, SCORE, IRRELEVANT_SINCE)
VALUES (8,  'Este piso es una ganga', 300, 100, null)
;

INSERT INTO CHALET_AD(ID, GARDEN_SIZE)
VALUES(8, 10)
;

INSERT INTO PICTURE (ID,URL, QUALITY)
VALUES (1, 'http://www.idealista.com/pictures/1', 'SD')
;
INSERT INTO PICTURE (ID,URL, QUALITY)
VALUES (2, 'http://www.idealista.com/pictures/2', 'HD')
;
INSERT INTO PICTURE (ID,URL, QUALITY)
VALUES (3, 'http://www.idealista.com/pictures/3', 'SD')
;
INSERT INTO PICTURE (ID,URL, QUALITY)
VALUES (4, 'http://www.idealista.com/pictures/4', 'HD')
;
INSERT INTO PICTURE (ID,URL, QUALITY)
VALUES (5, 'http://www.idealista.com/pictures/5', 'SD')
;
INSERT INTO PICTURE (ID,URL, QUALITY)
VALUES (6, 'http://www.idealista.com/pictures/6', 'SD')
;
INSERT INTO PICTURE (ID,URL, QUALITY)
VALUES (7, 'http://www.idealista.com/pictures/7', 'SD')
;
INSERT INTO PICTURE (ID,URL, QUALITY)
VALUES (8, 'http://www.idealista.com/pictures/8', 'HD')
;

INSERT INTO PICTURE (ID,URL, QUALITY)
VALUES (9, 'http://www.idealista.com/pictures/9', 'HD')
;

INSERT INTO AD_PICTURES (AD_ID, PICTURES_ID)
VALUES(2,4)
;
INSERT INTO AD_PICTURES (AD_ID, PICTURES_ID)
VALUES(3,2)
;
INSERT INTO AD_PICTURES (AD_ID, PICTURES_ID)
VALUES(4,5)
;
INSERT INTO AD_PICTURES (AD_ID, PICTURES_ID)
VALUES(5,3)
;
INSERT INTO AD_PICTURES (AD_ID, PICTURES_ID)
VALUES(5,8)
;
INSERT INTO AD_PICTURES (AD_ID, PICTURES_ID)
VALUES(6,6)
;

INSERT INTO AD_PICTURES (AD_ID, PICTURES_ID)
VALUES(8,9)
;