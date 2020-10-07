DROP TABLE word;

CREATE TABLE word (
    id INT IDENTITY(1,1) PRIMARY KEY,
    guid UNIQUEIDENTIFIER,
    deleted BIT NOT NULL DEFAULT 0,
    created DATETIME,
    modified DATETIME,
    value NVARCHAR(50) NOT NULL,
    chars NVARCHAR(50) NOT NULL,
    length INT NOT NULL
);
	
CREATE TRIGGER on_insert_word ON word
FOR INSERT AS 
    UPDATE word 
    SET created = CURRENT_TIMESTAMP, modified = CURRENT_TIMESTAMP, guid = NEWID()
    WHERE word.id = (SELECT id FROM INSERTED);
GO

CREATE TRIGGER on_update_word ON word
FOR UPDATE AS 
    UPDATE word
    SET modified = CURRENT_TIMESTAMP
    WHERE word.id = (SELECT id FROM INSERTED);
GO

INSERT INTO word (value, chars, length) VALUES ('aaa', 'a', 3);
INSERT INTO word (value, chars, length) VALUES ('baseball', 'abels', 8);