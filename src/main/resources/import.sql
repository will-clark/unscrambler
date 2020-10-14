DROP TABLE IF EXISTS unscramblerdb.unscrambler.import;

CREATE TABLE unscramblerdb.unscrambler.import (
    value NVARCHAR(50) NOT NULL,
    chars NVARCHAR(50) NOT NULL,
    length INT NOT NULL
);

BULK INSERT unscramblerdb.unscrambler.import
FROM '/var/opt/mssql/temp/words.csv'
WITH (fieldterminator=',', rowterminator='\n', batchsize=1000);