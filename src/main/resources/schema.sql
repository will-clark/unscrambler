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

ALTER TABLE word ADD CONSTRAINT u_value UNIQUE(value);

CREATE TRIGGER tgr_insert_word ON word
FOR INSERT AS 
    UPDATE word 
    SET created = CURRENT_TIMESTAMP, modified = CURRENT_TIMESTAMP, guid = NEWID()
    WHERE word.id = (SELECT id FROM INSERTED);
GO

CREATE TRIGGER tgr_update_word ON word
FOR UPDATE AS 
    UPDATE word
    SET modified = CURRENT_TIMESTAMP
    WHERE word.id = (SELECT id FROM INSERTED);
GO