DROP table stock IF EXISTS;

CREATE TABLE IF NOT EXISTS stock (
    quarter INTEGER,
    stock CHAR(20),
    date Date,
    open VARCHAR(100),
    high VARCHAR(100),
    low VARCHAR(100),
    close VARCHAR(100),
    volume VARCHAR(100),
    percentChangePrice VARCHAR(100),
    percentChangeVolumeOverLastWk VARCHAR(100),
    previousWeeksVolume VARCHAR(100),
    nextWeeksOpen VARCHAR(100),
    nextWeeksClose VARCHAR(100),
    percentChangeNextWeeksPrice VARCHAR(100),
    daysToNextDividend VARCHAR(100),
    percentReturnNextDividend VARCHAR(100)
);

